package com.trashCan.dao;

import java.util.List;
import java.util.Map;

public interface TrashcanmasterDao<T> extends BaseDao<T>{

    public List<Map<String, Object>> getList(String city,String code);

}
