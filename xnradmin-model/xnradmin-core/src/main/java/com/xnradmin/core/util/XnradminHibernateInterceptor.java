/**
 *2014年1月16日 下午2:30:15
*/
package com.xnradmin.core.util;

import org.hibernate.EmptyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;

import com.xnradmin.constant.DynamicTable;

/**
 * @author: bin_liu
 *
 */
public class XnradminHibernateInterceptor extends EmptyInterceptor{

    @Autowired
    private DynamicTable table;
    
    @Autowired
    private HbmSqlHelper sqlHelper;
    
    @Override
    public String onPrepareStatement(String sql){
        
        // TODO Auto-generated method stub
        return super.onPrepareStatement(sql);
    }
    
}
