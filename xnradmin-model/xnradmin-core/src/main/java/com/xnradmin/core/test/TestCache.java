/**
 * 2014年2月3日 下午3:38:28
 */
package com.xnradmin.core.test;


import org.apache.log4j.Logger;

import com.xnradmin.core.service.PhoneLocalService;
import com.xnradmin.core.util.SpringBase;
import com.xnradmin.po.PhoneLocal;

/**
 * @author: bin_liu
 */
public class TestCache{
	private static Logger log = Logger.getLogger(TestCache.class);
    private static void testCache1(){
        PhoneLocalService service = (PhoneLocalService) SpringBase.getCtx()
                .getBean("PhoneLocalService");
        PhoneLocal ph = service.getLocalByMobile("13811968624");
        log.debug(ph);
    }

    public static void main(String[] args) throws Exception{
        testCache1();
    }
}
