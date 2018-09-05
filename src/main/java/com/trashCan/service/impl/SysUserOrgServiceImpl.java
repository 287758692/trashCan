package com.trashCan.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trashCan.model.SysUserOrg;
import com.trashCan.dao.SysUserOrgDao;
import com.trashCan.service.SysUserOrgService;

@Service
public class SysUserOrgServiceImpl implements SysUserOrgService {

	@Autowired
	private SysUserOrgDao<SysUserOrg> sysUserOrgDao;


	public Serializable insertSysUserOrg(SysUserOrg data){
		return sysUserOrgDao.save(data);
	}
	
	public boolean updateSysUserOrg(SysUserOrg data){
		return sysUserOrgDao.saveOrUpdate(data);
	}
	
	public SysUserOrg getSysUserOrg(Integer Id){
		SysUserOrg data = sysUserOrgDao.get(SysUserOrg.class, Id);
		return data;
	}
	
	@Override
	public Integer getSysUserOrgByUserId(Integer userId){
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = "from SysUserOrg where status > 0 and userId = :userId ";
		params.put("userId", userId);
		SysUserOrg data = sysUserOrgDao.get(hql, params);
		 return data.getOrgId();
	}
}
