package com.trashCan.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trashCan.model.Trashcanmaster;
import com.trashCan.dao.TrashcanmasterDao;
import com.trashCan.service.TrashcanmasterService;

@Service
public class TrashcanmasterServiceImpl implements TrashcanmasterService {

	@Autowired
	private TrashcanmasterDao<Trashcanmaster> trashcanmasterDao;


	public Serializable insertTrashcanmaster(Trashcanmaster data){
		return trashcanmasterDao.save(data);
	}
	
	public boolean updateTrashcanmaster(Trashcanmaster data){
		return trashcanmasterDao.saveOrUpdate(data);
	}
	
	public Trashcanmaster getTrashcanmaster(Integer Id){
		Trashcanmaster data = trashcanmasterDao.get(Trashcanmaster.class, Id);
		return data;
	}

	@Override
	public Map<String, Object> getList(String city,String code) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("rows", trashcanmasterDao.getList(city,code));
		return result;
	}

	@Override
	public Map<String, Object> getPageList(Integer pageNumber,Integer pageSize,String code) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", trashcanmasterDao.getPageList(pageNumber,pageSize,code,false).size());
		result.put("rows", trashcanmasterDao.getPageList(pageNumber,pageSize,code,true));
		return result;
	}
}
