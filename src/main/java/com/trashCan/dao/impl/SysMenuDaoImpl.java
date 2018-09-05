package com.trashCan.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.trashCan.dao.SysMenuDao;
import com.trashCan.model.SysMenu;

@Repository("sysMenuDao")
public class SysMenuDaoImpl<SysMenu> extends BaseDaoImpl<SysMenu> implements SysMenuDao<SysMenu>{

	@Override
	public List<Map<String, Object>> getLowMenu(Integer pId) {
		String sql ="select "
					+ "menuId,"
					+ "pmenuId,"
					+ "menuName, "
					+ "menuDesc, "
					+ "menuIcon, "
					+ "menuAction "
				+ "from sys_menu where status > 0 ";
		if (pId!=null) {
			sql += " and pmenuId = " + pId + "";
		}
		Query query = getCurrentSession().createSQLQuery(sql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}
}