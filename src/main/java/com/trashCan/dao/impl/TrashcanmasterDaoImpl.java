package com.trashCan.dao.impl;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.trashCan.dao.TrashcanmasterDao;

import java.util.List;
import java.util.Map;

@Repository("trashcanmasterDao")
public class TrashcanmasterDaoImpl<Trashcanmaster> extends BaseDaoImpl<Trashcanmaster> implements TrashcanmasterDao<Trashcanmaster>{

    @Override
    public List<Map<String, Object>> getList(String city,String code) {
        String sql ="select "
                + "trashcanmaster.id,"
                + "trashcanmaster.code,"
                + "trashcanmaster.type,"
                + "(select r.color from trashcantype r where r.id = trashcanmaster.type) as color ,"
                + "trashcanmaster.lat, "
                + "trashcanmaster.lng, "
                + "trashcanmaster.address, "
                + "trashcanmaster.useDate, "
                + "trashcanmaster.status "
                + "from trashcanmaster "
                + "where trashcanmaster.status > 0 ";
        if (city!=null && city!="") {
            sql += " and address like '%" +city+ "%'";
        }
        if (code!=null && code!="") {
            sql += " and code like '%" +code+ "%'";
        }
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.list();
    }

    @Override
    public List<Map<String, Object>> getPageList(Integer pageNumber,Integer pageSize,String code,boolean isPage) {
        String sql ="select "
                + "trashcanmaster.id,"
                + "trashcanmaster.code,"
                + "trashcanmaster.type,"
                + "(select r.color from trashcantype r where r.id = trashcanmaster.type) as color ,"
                + "trashcanmaster.lat, "
                + "trashcanmaster.lng, "
                + "trashcanmaster.address, "
                + "trashcanmaster.useDate, "
                + "trashcanmaster.status "
                + "from trashcanmaster "
                + "where trashcanmaster.status > 0 ";
        if (code!=null && code!="") {
            sql += " and code like '%" +code+ "%'";
        }
        if (isPage) {
            sql += " and id limit " + (pageNumber - 1) * pageSize + "," + pageSize;
        }
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.list();
    }

}