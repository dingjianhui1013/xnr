/**
 * 2013-3-20 下午1:01:35
 */
package com.xnradmin.core.dao;


import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.cntinker.util.ReflectHelper;
import com.cntinker.util.StringHelper;

/**
 * session和connection靠hibernate,spring来维护
 * 
 * @autohr: bin_liu
 */
@Repository("CommonJDBCMasterDAO")
public class CommonJDBCMasterDAO<T> extends BaseHibernateMasterDAO{

    private static final Logger log = LoggerFactory
            .getLogger(CommonJDBCMasterDAO.class);

    public List<String> findColumnNames(final String tableName){
        return findColumnPro(tableName,"COLUMN_NAME");
    }

    // has bug
    // public List<Object[]> findBySQL(Object[] obj,String sql,int pageNo,int
    // numPerPage)
    // throws IllegalAccessException,IllegalArgumentException,
    // InvocationTargetException{
    // if(obj != null && obj.length == 1)
    // return findBySQL(obj[0],sql,pageNo,numPerPage);
    //
    // List<Map> l = findBySQL(sql,pageNo,numPerPage).setResultTransformer(
    // Transformers.ALIAS_TO_ENTITY_MAP).list();
    // List rst = new ArrayList();
    // for(Map e : l){
    // Iterator it = e.keySet().iterator();
    // Object[] temp = obj;
    //
    // while(it.hasNext()){
    // String key = it.next().toString();
    // Object value = e.get(key);
    //
    // for(int i=0;i<temp.length;i++){
    // temp[i] = ReflectHelper.setValue(temp[i],key.trim(),value);
    // }
    //
    // }
    //
    // rst.add(temp);
    // }
    // return rst;
    // }

    public List findBySQL(final Object obj,final String sql,final int pageNo,
            final int numPerPage) throws IllegalAccessException,
            IllegalArgumentException,InvocationTargetException,
            ClassNotFoundException,InstantiationException{

        if(obj instanceof Object[])
            throw new IllegalArgumentException("unsupported type: Object[]");

        List<Map> l = findBySQL(sql,pageNo,numPerPage).setResultTransformer(
                Transformers.ALIAS_TO_ENTITY_MAP).list();
        List rst = new ArrayList();

        for(Map e : l){
            Iterator it = e.keySet().iterator();
            Object temp = Class.forName(obj.getClass().getName()).newInstance();
            while(it.hasNext()){
                String key = it.next().toString();
                Object value = e.get(key);
                temp = ReflectHelper.setValue(temp,key.trim(),value);
            }
            rst.add(temp);
        }

        return rst;
    }

    public Object findUniqueResult(final String sql){
        return (Object) (T) getSession().doReturningWork(new ReturningWork(){
            public Object execute(Connection connection) throws SQLException{
                SQLQuery sqlQuery = getSession().createSQLQuery(sql);

                return sqlQuery.uniqueResult();
            }

        });
    }

    public SQLQuery findBySQL(final String sql,final int pageNo,
            final int numPerPage){
        return (SQLQuery) (T) getSession().doReturningWork(new ReturningWork(){
            public SQLQuery execute(Connection connection) throws SQLException{
                SQLQuery sqlQuery = getSession().createSQLQuery(sql);
                if(pageNo > 0){
                    sqlQuery.setFirstResult( ( pageNo - 1 ) * numPerPage);
                    sqlQuery.setMaxResults(numPerPage);
                }

                return sqlQuery;
            }
        });
    }

    public void exeSql(final String sql){
        getSession().doReturningWork(new ReturningWork(){
            public SQLQuery execute(Connection connection) throws SQLException{
                getSession().createSQLQuery(sql).executeUpdate();
                return null;
            }
        });
    }

    /**
     * @param tableName
     * @param tableType
     *            : 0=MyISAM,1=MEMORY
     */
    public void createPhonenoTable(String tableName,int tableType){
        String type = tableType == 0 ? "MyISAM" : "MEMORY";
        String sql = "CREATE TABLE IF NOT EXISTS `"
                + tableName
                + "` (`id` varchar(255) NOT NULL,`mobile` varchar(255) DEFAULT NULL,PRIMARY KEY (`id`)) ENGINE="
                + type + " DEFAULT CHARSET=utf8;";
        exeSql(sql);
    }

    // public void createTable(String tableName,int tableType,Object... param){
    // String type = tableType == 0 ? "MyISAM" : "MEMORY";
    // StringBuffer sb = new StringBuffer();
    // sb.append("CREATE TABLE IF NOT EXISTS `").append(tableName).append("` ");
    // //sb.append("(").append();
    //
    // for(int i=0;i<param.length;i++){
    // Object o = param[i];
    // }
    // }

    public List<String> findPros(){
        return (List<String>) (T) getSession().doReturningWork(
                new ReturningWork(){
                    public List<String> execute(Connection connection)
                            throws SQLException{
                        DatabaseMetaData m = connection.getMetaData();
                        ResultSet rst = m.getCatalogs();
                        return findPro(rst);
                    }
                });
    }

    public List<String> findPros(final String[] types){
        return (List<String>) (T) getSession().doReturningWork(
                new ReturningWork(){
                    public List<String> execute(Connection connection)
                            throws SQLException{
                        DatabaseMetaData m = connection.getMetaData();
                        ResultSet rst = m.getCatalogs();
                        rst = m.getTables(null,null,null,types);
                        return findPro(rst);
                    }
                });
    }

    public List<String> findPros(final String tableName){
        return (List<String>) (T) getSession().doReturningWork(
                new ReturningWork(){
                    public List<String> execute(Connection connection)
                            throws SQLException{
                        DatabaseMetaData m = connection.getMetaData();
                        ResultSet columnSet = m.getColumns(null,"%",tableName,
                                "%");
                        return findPro(columnSet);
                    }
                });
    }

    public List<String> findPros(final String tableName,final String proName){
        List<String> r = (List<String>) (T) getSession().doReturningWork(
                new ReturningWork(){
                    public List<String> execute(Connection connection)
                            throws SQLException{

                        DatabaseMetaData m = connection.getMetaData();
                        ResultSet columnSet = m.getColumns(null,"%",tableName,
                                "%");
                        List<String> res = new ArrayList<String>();
                        while(columnSet.next()){
                            List<String> pro = (List<String>) findPro(columnSet);
                            for(String e : pro){
                                if(e.equals(proName)){
                                    String p = columnSet.getString(proName);
                                    res.add(p);
                                }
                            }
                        }
                        return res;
                    }
                });
        return r;
    }

    public List<String> findColumnPro(final String tableName,
            final String proName){
        return findPros(tableName,proName);
    }

    public List<String> findPro(final ResultSet rst){
        return (List<String>) (T) getSession().doReturningWork(
                new ReturningWork(){
                    public List<String> execute(Connection connection)
                            throws SQLException{
                        ResultSetMetaData rm = rst.getMetaData();
                        int l = rm.getColumnCount();
                        List<String> res = new ArrayList<String>();
                        for(int i = 0;i < l;i ++ ){
                            res.add(rm.getColumnName(i + 1));
                        }
                        return res;
                    }
                });
    }

    public List<String> findAllTableNames(){
        return findAllTableNames(super.getDbname(),null,null);
    }

    public List<String> findAllTableNames(final String with,final Integer type){
        return findAllTableNames(super.getDbname(),with,type);
    }

    /**
     * get all tables by this connection
     * 
     * @param with
     *            : with parameter name <br>
     * @param type
     *            : 1=startWith, 2=endWith<br>
     * @return List<String>
     */
    public List<String> findAllTableNames(final String dbname,
            final String with,final Integer type){
        List<String> tableNames = (List<String>) (T) getSession()
                .doReturningWork(new ReturningWork(){
                    public List<String> execute(Connection connection)
                            throws SQLException{
                        DatabaseMetaData m = connection.getMetaData();
                        ResultSet rst = m.getCatalogs();

                        rst = m.getTables(dbname,null,null,
                                new String[]{"TABLE"});

                        List<String> tnames = new ArrayList<String>();
                        while(rst.next()){
                            String temp = rst.getString("TABLE_NAME");
                            if(!StringHelper.isNull(with)){
                                switch(type){
                                    case 1:
                                        if(temp.startsWith(with))
                                            tnames.add(temp);
                                    break;
                                    case 2:
                                        if(temp.endsWith(with))
                                            tnames.add(temp);
                                    break;
                                }
                            }else{
                                tnames.add(temp);
                            }
                        }
                        return tnames;
                    }
                });
        return tableNames;
    }

    private void test(){
        getSession().doReturningWork(new ReturningWork(){
            public List<String> execute(Connection connection)
                    throws SQLException{
                DatabaseMetaData m = connection.getMetaData();
                ResultSet rst = m.getCatalogs();
                String dbname = "";
                while(rst.next()){
                    log.debug("db name-" + rst.getString("TABLE_CAT")
                            + " | ");
                    dbname = rst.getString("TABLE_CAT");
                }

                rst = m.getTables(dbname,null,null,new String[]{"TABLE"});

                findPro(rst);
                List<String> tnames = new ArrayList<String>();
                while(rst.next()){
                    log.debug("");
                    String temp = rst.getString("TABLE_NAME");
                    tnames.add(temp);
                    ResultSet columnSet = m.getColumns(null,"%",temp,"%");
                    log.debug("-----------");
                    findPro(columnSet);
                    while(columnSet.next()){
                    	log.debug("|"
                                + columnSet.getString("COLUMN_NAME"));
                    }

                }
                return tnames;
            }
        });
    }
}
