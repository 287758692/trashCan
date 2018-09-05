package com.trashCan.dao;

import java.util.List;
import java.util.Map;

public interface SysRoleMenuDao<T> extends BaseDao<T>{

	public List<Map<String, Object>> secondMenus(int roleId, int pmenuId);
	public List<Map<String, Object>> rolePrivilege(int roleId);
	public int deleteSysRoleMenu(Integer roleId);
}
