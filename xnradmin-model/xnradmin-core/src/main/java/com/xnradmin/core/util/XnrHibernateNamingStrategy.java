/**
 *2015年2月4日 下午6:53:25
*/
package com.xnradmin.core.util;

import org.hibernate.cfg.DefaultNamingStrategy;

/**
 * @author: liubin
 *
 */
public class XnrHibernateNamingStrategy extends DefaultNamingStrategy{

	@Override
	public String classToTableName(String className) {
		// TODO Auto-generated method stub
		return super.classToTableName(className);
	}
}
