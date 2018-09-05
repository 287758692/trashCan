package com.trashCan.dao;

import java.util.List;
import java.util.Map;

public interface SysOrgDao<T> extends BaseDao<T>{

	public List<Map<String, Object>> getList(Integer pageNumber, Integer pageSize, String orgName, boolean isPage);
	
	public List<Map<String, Object>> getOrgUserList(Integer pageNumber, Integer pageSize, Integer orgId, boolean isPage);
	
	public List<Map<String, Object>> getOrgUserInList(Integer pageNumber, Integer pageSize, boolean isPage);
}
