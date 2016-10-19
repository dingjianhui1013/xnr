/**
 * 2013-8-4 上午2:58:47
 */
package com.xnradmin.core.util;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cntinker.util.StringHelper;
import com.xnradmin.constant.DynamicTable;

/**
 * @autohr: bin_liu
 */
@Repository("HbmSqlHelper")
public class HbmSqlHelper{

    @Autowired
    private DynamicTable dtable;

    /**
     * 取一个sql句中的所有表名
     * 
     * @param sql
     * @return String[]
     */
    public String[] getTableName(String sql){
        String temp = sql;
        temp = temp.substring(temp.indexOf("from") + 4,temp.length()).trim();
        temp = temp.indexOf("where") > -1 ? temp.substring(0,
                temp.indexOf("where")) : temp;

        if(temp.indexOf(",") > -1){
            List<String> l = new ArrayList<String>();
            String[] r = StringHelper.splitStr(temp,",");
            for(String e : r){
                String rtable = StringHelper
                        .splitContentByUnicodeWithUntilSpace(e,0);
                l.add(rtable.trim());
            }
            return (String[]) l.toArray(new String[0]);
        }
        return new String[]{StringHelper.splitContentByUnicodeWithUntilSpace(
                temp,0).trim()};
    }

    /**
     * 根据配置文件中指定的动态表过滤，替换，组装新的指定表名<br>
     * 
     * @param sql
     * @param tablePara
     *            : key=表名,value=需要添加的后缀<br>
     * @return String
     */
    public String addParaToTable(String sql,Map<String, String> tablePara){
        String temp = sql;
        String[] tables = getTableName(temp);

        for(String e : tables){
            Iterator<String> it = tablePara.keySet().iterator();
            while(it.hasNext()){
                String table = it.next();
                if(!table.equals(e))
                    continue;
                if(dtable.exist(table)){
                    String t = table + tablePara.get(table);
                    temp = temp.replaceAll(table,t);
                }
            }
        }

        return temp;
    }

//    public static String addAlias(String sql){
//       
//        String temp = sql;
//        String[] tables = getTableName(sql);
//        for(String e : tables){
//            
//        }
//    }
    
    public static void main(String[] args) throws Exception{
        // String s1 = "from phoneno a,phone_local b,mobile d where a.id=b.id";
        // String s2 = "from phone a";
        //
        // System.out.println(Arrays.deepToString(getTableName(s2)));
        // System.out.println(addParaToTable(s1,new String[]{"1","2","3"}));
    }
}
