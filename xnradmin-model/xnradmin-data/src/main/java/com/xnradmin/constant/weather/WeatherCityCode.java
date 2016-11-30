/**
 * 2014年5月11日 下午1:50:54
 */
package com.xnradmin.constant.weather;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.DocumentException;

import com.cntinker.util.PoiExcelHelper;
import com.cntinker.util.StringHelper;
import com.xnradmin.constant.templates.SysGenKeyword;

/**
 * @author: bin_liu
 */
public class WeatherCityCode{
	private static Logger log = Logger.getLogger(WeatherCityCode.class);
    private static void genProperties() throws FileNotFoundException,
            IOException,IllegalAccessException,DocumentException{
        String sourceFile = "F:/temp/areaid_list.xlsx";
        List<ArrayList<String>> dataLst = new PoiExcelHelper().read(sourceFile,
                0);
        log.debug("文件总行数：" + dataLst.size());
        for(int i = 0;i < dataLst.size();i ++ ){
            StringBuffer sb = new StringBuffer();
            List<String> l = dataLst.get(i);
            
            sb.append(l.get(0)+" | "+l.get(1));
            log.debug(sb.toString());
        }
    }

    public static void main(String[] args) throws Exception{
        genProperties();
        log.debug("");
    }
}
