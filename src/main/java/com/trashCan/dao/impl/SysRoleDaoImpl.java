package com.trashCan.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.trashCan.dao.SysRoleDao;

@Repository("sysRoleDao")
public class SysRoleDaoImpl<SysRole> extends BaseDaoImpl<SysRole> implements SysRoleDao<SysRole>{

	@Override
	public List<Map<String, Object>> getComboList() {
		String sql = "select * from sys_role where 1=1 ";
		Query query = getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}
	
	@Override
	public List<Map<String, Object>> getList(Integer pageNumber,Integer pageSize,String roleName,boolean isPage) {
		String sql ="select "
					+ "sys_role.roleId,"
					+ "sys_role.roleName,"
					+ "sys_role.roleDesc,"
					+ "sys_role.status, "
					+ "(select u.userName from sys_user u where u.userId = sys_role.modOptr) as modOptr, "
					+ "sys_role.modTime "
				+ "from sys_role "
				+ "where sys_role.status > 0 ";
		if (roleName!=null && roleName!="") {
			sql += " and roleName like '%" + roleName + "%'";
		}
		if (isPage) {
			sql += " and roleId limit " + (pageNumber - 1) * pageSize + "," + pageSize;
		}
		Query query = getCurrentSession().createSQLQuery(sql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}
}