package com.trashCan.dao;

import java.util.List;
import java.util.Map;

public interface TrashcantypeDao<T> extends BaseDao<T>{

    public List<Map<String, Object>> getList(Integer pageNumber, Integer pageSize, String userName, boolean isPage);

    public List<Map<String, Object>> getComboList();
}
