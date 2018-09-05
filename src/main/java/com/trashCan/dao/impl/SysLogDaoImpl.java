package com.trashCan.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.trashCan.dao.SysLogDao;

@Repository("sysLogDao")
public class SysLogDaoImpl<SysLog> extends BaseDaoImpl<SysLog> implements SysLogDao<SysLog>{

	@Override
	public List<Map<String, Object>> getList(Integer pageNumber,Integer pageSize,String status,boolean isPage) {
		String sql ="select "
					+ "sys_log.logId,"
					+ "sys_log.description,"
					+ "sys_log.method,"
					+ "sys_log.Type, "
					+ "sys_log.RequestIp, "
					+ "sys_log.ExceptionCode, "
					+ "REPLACE(sys_log.ExceptionDetail,'\\\\','/') as ExceptionDetail, "
					+ "(select u.userName from sys_user u where u.userId = sys_log.crtOptr) as crtOptr, "
					+ "sys_log.crtTime "
				+ "from sys_log "
				+ "where 1=1 ";
		if (status!=null && status!="") {
			sql += " and Type = " + Integer.parseInt(status);
		}
		sql += "order by crtTime desc ";

		if (isPage) {
			sql += " limit " + (pageNumber - 1) * pageSize + "," + pageSize;
		}
		Query query = getCurrentSession().createSQLQuery(sql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}
}