/**
 * 2013-7-18 上午3:15:28
 */
package com.xnradmin.core.dao;


import java.io.Serializable;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.query.Query;
import com.mongodb.Mongo;

/**
 * @autohr: bin_liu
 */
@Repository("CommonMongodbDAO")
public class CommonMongodbDAO<T> {

    @Autowired
    private MongodbClient client;

    private Datastore dataStore;

    private Mongo mongo;

    private Morphia morphia;

    //@PostConstruct
    public void init(){
        dataStore = client.getDs();
        mongo = client.getMongo();
        morphia = client.getMorphia();
    }

    public void save(Object obj){
        dataStore.save(obj);
    }

    public Serializable saveAndGetId(Object obj) throws JSONException{
        return save(obj,"id");
    }

    public Serializable save(Object obj,String idKey) throws JSONException{
        JSONObject o = new JSONObject(dataStore.save(obj));
        return (Serializable) o.get(idKey);
    }

    public List findPara(Class cls,String para,String value){
        return findParaToQuery(cls,para,value).asKeyList();
    }

    public Long findParaCount(Class cls,String para,String value){
        return findParaToQuery(cls,para,value).countAll();
    }

    private Query findParaToQuery(Class cls,String para,String value){
        Pattern pattern = Pattern.compile("^.*" + value + ".*$",
                Pattern.CASE_INSENSITIVE);
        Query q = dataStore.createQuery(cls).filter(para,pattern);
        return q;
    }

    public Datastore getDataStore(){
        return dataStore;
    }

}
