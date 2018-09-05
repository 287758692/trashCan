package com.trashCan.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trashCan.model.SysLog;
import com.trashCan.dao.SysLogDao;
import com.trashCan.service.SysLogService;

@Service
public class SysLogServiceImpl implements SysLogService {

	@Autowired
	private SysLogDao<SysLog> sysLogDao;


	public Serializable insertSysLog(SysLog data){
		return sysLogDao.save(data);
	}
	
	public boolean updateSysLog(SysLog data){
		return sysLogDao.saveOrUpdate(data);
	}
	
	public SysLog getSysLog(Integer Id){
		SysLog data = sysLogDao.get(SysLog.class, Id);
		return data;
	}
	
	@Override
	public Map<String, Object> getList(Integer pageNumber,Integer pageSize,String status) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", sysLogDao.getList(pageNumber,pageSize,status,false).size());
		result.put("rows", sysLogDao.getList(pageNumber,pageSize,status,true));
		return result;
	}
}
