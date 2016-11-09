/**
 * 2013-8-4 上午2:51:56
 */
package com.xnradmin.constant;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

/**
 * @autohr: bin_liu
 */
@Repository("DynamicTable")
public class DynamicTable{

    @Value(value = "${platform.dynamictable}")
    private String[] tables;

    public boolean exist(String tableName){
        for(String e : tables){
            if(tableName.startsWith(e))
                return true;
        }
        return false;
    }

    public String[] getTables(){
        return tables;
    }

    public void setTables(String[] tables){
        this.tables = tables;
    }
}
