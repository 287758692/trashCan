package com.trashCan.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trashCan.model.SysUser;
import com.trashCan.dao.SysUserDao;
import com.trashCan.service.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserDao<SysUser> sysUserDao;
	
	public Serializable insertSysUser(SysUser data){
		return sysUserDao.save(data);
	}
	
	public boolean updateSysUser(SysUser data){
		return sysUserDao.saveOrUpdate(data);
	}
	
	public SysUser getSysUser(Integer Id){
		SysUser data = sysUserDao.get(SysUser.class, Id);
		return data;
	}
	/**
	 * 获取用户信息(登陆)
	 */
	public SysUser getUsers(String loginName, String passWord) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = "from SysUser as u where u.loginName=:loginName and u.passwd=:passWord";
		params.put("loginName", loginName);
		params.put("passWord", passWord);
		return sysUserDao.get(hql, params);
	}
	
	@Override
	public Map<String, Object> getList(Integer pageNumber,Integer pageSize,String userName) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", sysUserDao.getList(pageNumber,pageSize,userName,false).size());
		result.put("rows", sysUserDao.getList(pageNumber,pageSize,userName,true));
		return result;
	}
}
