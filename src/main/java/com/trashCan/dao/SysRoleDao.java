package com.trashCan.dao;

import java.util.List;
import java.util.Map;

public interface SysRoleDao<T> extends BaseDao<T>{

	public List<Map<String, Object>> getComboList();
	
	public List<Map<String, Object>> getList(Integer pageNumber, Integer pageSize, String roleName, boolean isPage);
}
