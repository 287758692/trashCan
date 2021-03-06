package com.trashCan.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.trashCan.model.Trashcanmaster;

public interface TrashcanmasterService {
    
    public Serializable insertTrashcanmaster(Trashcanmaster data);
    
    public boolean updateTrashcanmaster(Trashcanmaster data);
    
    public Trashcanmaster getTrashcanmaster(Integer Id);

    public Map<String, Object> getList(String city,String code);

    public Map<String, Object> getPageList(Integer pageNumber, Integer pageSize, String code);
}