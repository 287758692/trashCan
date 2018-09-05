package com.trashCan.dao.impl;

import com.trashCan.dao.BaseDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.internal.util.config.ConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> implements BaseDao<T>{

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.openSession();
	}
	
	protected Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public T get(Class<T> clazz, Integer id) {
		Session session = getSession();
		T result = (T) session.get(clazz, id);
		session.close();
		return result;
	}
	
//	@Override
//	public T get(Class<T> clazz, Integer id) {
//		return (T) getSession().get(clazz, id);
//	}

	@Override
	public T get(String hql) {
		List<T> l = createQuery(hql).list();
		if (l != null && l.size() > 0)
			return l.get(0);
		return null;
	}

	@Override
	public T get(String hql, Map<String, Object> params) {
		Query q = hqlQueryParams(hql, params);
		List<T> l = q.list();
		if (l != null && l.size() > 0)
			return l.get(0);
		return null;
	}

	@Override
	public List<T> find(String hql) {
		return createQuery(hql).list();
	}

	@Override
	public List<T> find(String hql, Map<String, Object> params) {
		Query q = hqlQueryParams(hql, params);
		return q.list();
	}

	@Override
	public List<T> find(String hql, int page, int rows) {
		Query q = createQuery(hql);
		return q.setFirstResult((page -1)* rows).setMaxResults(rows).list();
	}

	@Override
	public List<T> find(String hql, Map<String, Object> params, int page, int rows) {
		Query q = hqlQueryParams(hql, params);
		return q.setFirstResult((page -1)* rows).setMaxResults(rows).list();
	}

	@Override
	public int count(String hql) {
		Query q =  createQuery(hql);
		return ((Long)q.uniqueResult()).intValue();
	}

	@Override
	public int count(String hql, Map<String, Object> params) {
		Query q = hqlQueryParams(hql, params);
		return ((Long)q.uniqueResult()).intValue();
	}
	
	public Double countDouble(String hql, Map<String, Object> params) {
		Query q = sqlQueryParams(hql, params);
		return ((BigDecimal)q.uniqueResult()).doubleValue();
	}

	@Override
	public Serializable save(T o) {
		return getSession().save(o);
	}

	@Override
	public void update(T o) {
		getSession().update(o);
	}

	@Override
	public void delete(T o) {
		getSession().delete(o);
	}

	
	@Override
	public void updateCondition(String hql, Map<String, Object> params) {
		Query q = hqlQueryParams(hql, params);
		q.executeUpdate();
	}

	@Override
	public void deleteCondition(String hql, Map<String, Object> params) {
		Query q = hqlQueryParams(hql, params);
		q.executeUpdate();
	}
	
	@Override
	public Query createQuery(String hql) {
		return getSession().createQuery(hql);
	}

	@Override
	public Query hqlQueryParams(String hql, Map<String, Object> params) {
		Query q = createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet())
				q.setParameter(key, params.get(key));
		}
		return q;
	}

	@Override
	public List<T> getAllList(Class<T> clazz) {
		return getSession().createCriteria(clazz).list();
	}

	@Override
	public boolean saveOrUpdate(T o) {
		try {
			Session session = getSession();
			Transaction tx = session.beginTransaction();
			session.saveOrUpdate(o);
			tx.commit();
			session.close();
			return true;
		} catch (ConfigurationException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Query sqlQueryParams(String sql, Map<String, Object> params) {
		Query q = getSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet())
				q.setParameter(key, params.get(key));
		}
		return q;
	}

	@Override
	public List<T> sqlQueryList(String sql, Map<String, Object> params) {
		Query q = getSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet())
				q.setParameter(key, params.get(key));
		}
		return q.list();
	}

	@Override
	public List<T> hqlQueryList(String hql, Map<String, Object> params) {
		Query q = createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet())
				q.setParameter(key, params.get(key));
		}
		return q.list();
	}


}
