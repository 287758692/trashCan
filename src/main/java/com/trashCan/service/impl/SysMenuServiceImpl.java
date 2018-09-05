package com.trashCan.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trashCan.model.SysMenu;
import com.trashCan.model.SysRoleMenu;
import com.trashCan.dao.SysMenuDao;
import com.trashCan.dao.SysRoleMenuDao;
import com.trashCan.service.SysMenuService;

@Service
public class SysMenuServiceImpl implements SysMenuService {

	@Autowired
	private SysMenuDao<SysMenu> sysMenuDao;
	
	@Autowired
	private SysRoleMenuDao<SysRoleMenu> sysRoleMenuDao;
	
	public Serializable insertSysMenu(SysMenu data){
		return sysMenuDao.save(data);
	}
	
	public boolean updateSysMenu(SysMenu data){
		return sysMenuDao.saveOrUpdate(data);
	}
	
	public SysMenu getSysMenu(Integer Id){
		SysMenu data = sysMenuDao.get(SysMenu.class, Id);
		return data;
	}
	
	public List<Map<String, Object>> getLowMenu(Integer pId){
		return sysMenuDao.getLowMenu(pId);
	}
	
	@Override
	public List<Map<String, Object>> getMenuAll(Integer roleId) {
		
		List<Map<String, Object>> roleMenu = sysRoleMenuDao.rolePrivilege(roleId);
		
		Map<String, Object> state1 = new HashMap<String, Object>();
		Map<String, Object> state2 = new HashMap<String, Object>();
		Map<String, Object> state3 = new HashMap<String, Object>();
		
		List<Map<String, Object>> ListMap1 = new ArrayList<Map<String, Object>>();
		
		for (Map<String, Object> map1 : sysMenuDao.getLowMenu(0)) {
			Map<String, Object> mapData1 = new HashMap<String, Object>();
			mapData1.put("id", map1.get("menuId"));
			mapData1.put("text", map1.get("menuName"));
			state1.put("opened", true);
			mapData1.put("state", state1);
			
			List<Map<String, Object>> ListMap2 = new ArrayList<Map<String, Object>>();
			for (Map<String, Object> map2 : sysMenuDao.getLowMenu(Integer.parseInt(map1.get("menuId").toString()))) {
				Map<String, Object> mapData2 = new HashMap<String, Object>();
				mapData2.put("id", map2.get("menuId"));
				mapData2.put("text", map2.get("menuName"));
				state2.put("opened", true);
				mapData2.put("state", state2);
				
				List<Map<String, Object>> ListMap3 = new ArrayList<Map<String, Object>>();
				for (Map<String, Object> map3 : sysMenuDao.getLowMenu(Integer.parseInt(map2.get("menuId").toString()))) {
					Map<String, Object> mapData3 = new HashMap<String, Object>();
					mapData3.put("id", map3.get("menuId"));
					mapData3.put("text", map3.get("menuName"));
					for (Map<String, Object> map33 : roleMenu) {
						if (map33.get("menuId").toString().equals(map3.get("menuId").toString())) {
							state3.put("selected", true);
							mapData3.put("state", state3);
						}
					}
					ListMap3.add(mapData3);
				}
				mapData2.put("children", ListMap3);
				ListMap2.add(mapData2);
			}
			mapData1.put("children", ListMap2);
			ListMap1.add(mapData1);
		}
		return ListMap1;
	}
}
