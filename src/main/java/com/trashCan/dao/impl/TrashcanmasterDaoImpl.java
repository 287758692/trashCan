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
    public List<Map<String, Object>> getList(String lat,String Lng,String code) {
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
        if (lat!=null && lat!="") {
            sql += " and lat between " + lat+ "-0.01 and "+lat+ "+0.01";
        }
        if (Lng!=null && Lng!="") {
            sql += " and Lng between " + Lng+ "-0.01 and "+Lng+ "+0.01";
        }
        if (code!=null && code!="") {
            sql += " and code like '%" +code+ "%'";
        }
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.list();
    }

}