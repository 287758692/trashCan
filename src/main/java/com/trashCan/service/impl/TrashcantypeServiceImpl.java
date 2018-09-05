package com.trashCan.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trashCan.model.Trashcantype;
import com.trashCan.dao.TrashcantypeDao;
import com.trashCan.service.TrashcantypeService;

@Service
public class TrashcantypeServiceImpl implements TrashcantypeService {

	@Autowired
	private TrashcantypeDao<Trashcantype> trashcantypeDao;


	public Serializable insertTrashcantype(Trashcantype data){
		return trashcantypeDao.save(data);
	}
	
	public boolean updateTrashcantype(Trashcantype data){
		return trashcantypeDao.saveOrUpdate(data);
	}
	
	public Trashcantype getTrashcantype(Integer Id){
		Trashcantype data = trashcantypeDao.get(Trashcantype.class, Id);
		return data;
	}

	public Map<String, Object> getList(Integer pageNumber,Integer pageSize,String userName) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", trashcantypeDao.getList(pageNumber,pageSize,userName,false).size());
		result.put("rows", trashcantypeDao.getList(pageNumber,pageSize,userName,true));
		return result;
	}

	public List<Map<String, Object>> getComboList() {
		List<Map<String, Object>> result = trashcantypeDao.getComboList();
		return result;
	}
}
