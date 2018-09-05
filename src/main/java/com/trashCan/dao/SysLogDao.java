package com.trashCan.dao;

import java.util.List;
import java.util.Map;

public interface SysLogDao<T> extends BaseDao<T>{

	public List<Map<String, Object>> getList(Integer pageNumber, Integer pageSize, String status, boolean isPage);
}
