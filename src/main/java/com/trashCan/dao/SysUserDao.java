package com.trashCan.dao;

import java.util.List;
import java.util.Map;

public interface SysUserDao<T> extends BaseDao<T>{

	public List<Map<String, Object>> getList(Integer pageNumber, Integer pageSize, String userName, boolean isPage);
}
