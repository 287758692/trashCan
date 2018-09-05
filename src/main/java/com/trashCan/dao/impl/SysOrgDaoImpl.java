package com.trashCan.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.trashCan.dao.SysOrgDao;

@Repository("sysOrgDao")
public class SysOrgDaoImpl<SysOrg> extends BaseDaoImpl<SysOrg> implements SysOrgDao<SysOrg>{

	@Override
	public List<Map<String, Object>> getList(Integer pageNumber,Integer pageSize,String orgName,boolean isPage) {
		String sql ="select "
					+ "sys_org.orgId,"
					+ "sys_org.porgId,"
					+ "sys_org.orgCode,"
					+ "sys_org.orgName, "
					+ "sys_org.orgDesc, "
					+ "sys_org.status, "
					+ "(select u.userName from sys_user u where u.userId = sys_org.modOptr) as modOptr, "
					+ "sys_org.modTime "
				+ "from sys_org "
				+ "where status > 0 ";
		if (orgName!=null && orgName!="") {
			sql += " and orgName like '%" + orgName + "%'";
		}
		if (isPage) {
			sql += " and orgId limit " + (pageNumber - 1) * pageSize + "," + pageSize;
		}
		Query query = getCurrentSession().createSQLQuery(sql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}
	
	@Override
	public List<Map<String, Object>> getOrgUserList(Integer pageNumber,Integer pageSize,Integer orgId,boolean isPage) {
		String sql ="select "
					+ "sys_user_org.userOrgId,"
					+ "sys_user.userName,"
					+ "(select u.roleName from sys_role u where u.roleId = sys_user.roleId) as roleName,"
					+ "sys_user.userCode,"
					+ "sys_user.mobileNo "
				+ "from sys_user ,sys_user_org "
				+ "where sys_user.status > 0 "
				+ "  and sys_user_org.status > 0"
				+ "  and sys_user.userId = sys_user_org.userId";
		if (orgId!=null && orgId!=0) {
			sql += "  and sys_user_org.orgId = " + orgId;
		}
		if (isPage) {
			sql += " and sys_user_org.userOrgId limit " + (pageNumber - 1) * pageSize + "," + pageSize;
		}
		Query query = getCurrentSession().createSQLQuery(sql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}
	
	@Override
	public List<Map<String, Object>> getOrgUserInList(Integer pageNumber,Integer pageSize,boolean isPage) {
		String sql ="select "
					+ "sys_user.userId,"
					+ "sys_user.userName,"
					+ "(select u.roleName from sys_role u where u.roleId = sys_user.roleId) as roleName,"
					+ "sys_user.userCode,"
					+ "sys_user.mobileNo "
				+ "from sys_user "
				+ "where sys_user.status > 0 "
				+ "  and sys_user.userId not in(select userId from sys_user_org where sys_user_org.status > 0)";
		if (isPage) {
			sql += " and userId limit " + (pageNumber - 1) * pageSize + "," + pageSize;
		}
		Query query = getCurrentSession().createSQLQuery(sql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}
}