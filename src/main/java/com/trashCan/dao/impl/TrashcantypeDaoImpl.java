package com.trashCan.dao.impl;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.trashCan.dao.TrashcantypeDao;

import java.util.List;
import java.util.Map;

@Repository("trashcantypeDao")
public class TrashcantypeDaoImpl<Trashcantype> extends BaseDaoImpl<Trashcantype> implements TrashcantypeDao<Trashcantype>{

    @Override
    public List<Map<String, Object>> getList(Integer pageNumber,Integer pageSize,String name,boolean isPage) {
        String sql ="select "
                + "trashcantype.id,"
                + "trashcantype.name,"
                + "trashcantype.size,"
                + "trashcantype.color, "
                + "trashcantype.status, "
                + "(select u.userName from sys_user u where u.userId = trashcantype.modOptr) as modOptr, "
                + "trashcantype.modTime "
                + "from trashcantype "
                + "where trashcantype.status > 0 ";
        if (name!=null && name!="") {
            sql += " and name like '%" + name + "%'";
        }
        if (isPage) {
            sql += " and id limit " + (pageNumber - 1) * pageSize + "," + pageSize;
        }
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.list();
    }

    @Override
    public List<Map<String, Object>> getComboList() {
        String sql = "select * from trashcantype where 1=1 ";
        Query query = getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.list();
    }
}