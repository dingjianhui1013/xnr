/**
 *2013-7-17 上午3:07:30
*/
package com.xnradmin.core.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.cntinker.util.StringHelper;
import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;
import com.mongodb.MongoOptions;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.xnradmin.client.action.wx.WXConnectAction;
import com.xnradmin.dto.MongoDBSource;

/**
 * @autohr: bin_liu
 *
 */
@Repository
@Scope("singleton")
public class MongodbClient{

	private static Logger log = Logger.getLogger(MongodbClient.class);
    private static Mongo mongo;

    private static Datastore ds;

    private static Morphia morphia;

    @Autowired
    private MongoDBSource source;

    public Mongo getMongo(){
        return mongo;
    }

    //@PostConstruct
    public void init(){
        try{
            String[] sl = StringHelper.splitStr(source.getAddress(),",");
            String[] pl = StringHelper.splitStr(source.getPort(),",");
            List<ServerAddress> serverList = new ArrayList<ServerAddress>();
            for(int i = 0;i < sl.length;i ++ ){
                ServerAddress s = new ServerAddress(sl[i],new Integer(pl[i]));
                serverList.add(s);
            }

            MongoOptions op = new MongoOptions();
            op.setConnectTimeout(source.getConnectTimeout());
            op.setSocketTimeout(source.getSocketTimeout());
            op.setMaxWaitTime(source.getMaxWaitTime());
            op.setConnectionsPerHost(source.getConnectionsPerHost());
            op.setAutoConnectRetry(source.getAutoConnectRetry());

            mongo = new Mongo(serverList,op);

            if(source.getSlaveOk()){
                mongo.setReadPreference(ReadPreference.SECONDARY);
            }

            morphia = new Morphia();
            morphia.mapPackage(source.getMappackage());
            ds = morphia.createDatastore(mongo,source.getDatabase());

        }catch(Exception e){
            log.debug("connection error:: ");
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void shutdown(){
        mongo.close();        
    }

    public MongoDBSource getSource(){
        return source;
    }

    public void setSource(MongoDBSource source){
        this.source = source;
    }

    public static Datastore getDs(){
        return ds;
    }

    public static Morphia getMorphia(){
        return morphia;
    }
}
