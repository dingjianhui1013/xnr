/**
 * 2013-7-22 上午2:32:55
 */
package com.xnradmin.core.service.templates;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.cntinker.util.FileHelper;

/**
 * @autohr: bin_liu
 */
public class TemplatesCore{

	private static Logger log = Logger.getLogger(TemplatesCore.class);
    private String tempFile;

    private String[] tempContent;
    
    public TemplatesCore(String tempFile){
        this.tempFile = tempFile;        
    }

    public static void main(String[] args) throws Exception{
        TemplatesCore t = new TemplatesCore("d:/PO.java.templates");
        String[] c = FileHelper.getLine(t.getTempFile());
        for(String e : c){
            log.debug(e);
        }
    }

    public String getTempFile(){
        return tempFile;
    }

    public void setTempFile(String tempFile){
        this.tempFile = tempFile;
    }

    public String[] getTempContent(){
        return tempContent;
    }

    public void setTempContent(String[] tempContent){
        this.tempContent = tempContent;
    }
}
