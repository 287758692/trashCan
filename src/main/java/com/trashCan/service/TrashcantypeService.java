package com.trashCan.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.trashCan.model.Trashcantype;

public interface TrashcantypeService {
    
    public Serializable insertTrashcantype(Trashcantype data);
    
    public boolean updateTrashcantype(Trashcantype data);
    
    public Trashcantype getTrashcantype(Integer Id);

    public Map<String, Object> getList(Integer pageNumber, Integer pageSize, String userName);

    public List<Map<String, Object>> getComboList();

}