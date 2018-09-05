package com.trashCan.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.trashCan.dao.SysUserDao;

@Repository("sysUserDao")
public class SysUserDaoImpl<SysUser> extends BaseDaoImpl<SysUser> implements SysUserDao<SysUser>{

	@Override
	public List<Map<String, Object>> getList(Integer pageNumber,Integer pageSize,String userName,boolean isPage) {
		String sql ="select "
					+ "sys_user.userId,"
					+ "sys_user.loginName,"
					+ "sys_user.userCode,"
					+ "sys_user.userName, "
					+ "sys_user.sex, "
					+ "sys_user.mobileNo, "
					+ "sys_user.eMail, "
					+ "sys_user.address, "
					+ "sys_user.roleId, "
					+ "(select r.roleName from sys_role r where r.roleId = sys_user.roleId) as roleName, "
					+ "sys_user.status, "
					+ "(select u.userName from sys_user u where u.userId = sys_user.modOptr) as modOptr, "
					+ "sys_user.modTime "
				+ "from sys_user "
				+ "where sys_user.status > 0 ";
		if (userName!=null && userName!="") {
			sql += " and userName like '%" + userName + "%'";
		}
		if (isPage) {
			sql += " and userId limit " + (pageNumber - 1) * pageSize + "," + pageSize;
		}
		Query query = getCurrentSession().createSQLQuery(sql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}
}