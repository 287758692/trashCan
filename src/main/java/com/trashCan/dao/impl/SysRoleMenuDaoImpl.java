package com.trashCan.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.trashCan.dao.SysRoleMenuDao;

@Repository("sysRoleMenuDao")
public class SysRoleMenuDaoImpl<SysRoleMenu> extends BaseDaoImpl<SysRoleMenu> implements SysRoleMenuDao<SysRoleMenu>{

	/**
	 * Server端菜单
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> secondMenus(int roleId, int pmenuId) {
		String sql  = "select sys_menu.menuId as menuId ,"
				    + "       sys_menu.menuName as menuName ,"
				    + "       sys_menu.menuIcon as menuIcon ,"
				    + "       sys_menu.menuAction as menuAction ,"
				    + "       sys_menu.pmenuId as pmenuId "
					+ "  from sys_role_menu ,sys_menu"
					+ " where sys_role_menu.status > 0 "
					+ "   and sys_menu.status > 0 "
					+ "   and sys_role_menu.roleId = :roleId"
					+ "   and sys_role_menu.menuId = sys_menu.menuId "
					+ "   and sys_menu.pmenuId = :pmenuId ";
		Query query = getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);;
		query.setInteger("roleId", roleId);
		query.setInteger("pmenuId", pmenuId);
		return query.list();
	}
	
	/**
	 * 角色权限
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> rolePrivilege(int roleId) {
		String sql  = "select roleMenuId, "
				    + "       roleId, "
				    + "       menuId, "
				    + "       status  "
					+ "  from sys_role_menu"
					+ " where status >0"
					+ "   and roleId = :roleId";
		Query query = getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);;
		query.setInteger("roleId", roleId);
		return query.list();
	}
	
	/**
	 * 权限修改-删除旧权限
	 */
	@Override
	public int deleteSysRoleMenu(Integer roleId) {
		String sql  = "delete from sys_role_menu"
					+ " where roleId = :roleId";
		Query query = getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);;
		query.setInteger("roleId", roleId);
		return query.executeUpdate();
	}
}