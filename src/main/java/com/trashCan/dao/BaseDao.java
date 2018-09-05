package com.trashCan.dao;

import org.hibernate.Query;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseDao<T> {
	T  get(Class<T> clazz, Integer id);

	T  get(String hql);

	T  get(String hql, Map<String, Object> params);

	List<T> getAllList(Class<T> clazz);

	List<T> find(String hql);

	List<T> find(String hql, Map<String, Object> params);

	List<T> find(String hql, int page, int rows);

	List<T> find(String hql, Map<String, Object> params, int page, int rows);

	int count(String hql);

	int count(String hql, Map<String, Object> params);

	Double countDouble(String hql, Map<String, Object> params);

	Serializable save(T o);

	void update(T o);

	boolean saveOrUpdate(T o);

	void updateCondition(String hql, Map<String, Object> params);

	void delete(T o);

	void deleteCondition(String hql, Map<String, Object> params);

	Query createQuery(String hql);

	Query hqlQueryParams(String hql, Map<String, Object> params);

	List<T> hqlQueryList(String hql, Map<String, Object> params);

	Query sqlQueryParams(String sql, Map<String, Object> params);

	List<T> sqlQueryList(String sql, Map<String, Object> params);
}
