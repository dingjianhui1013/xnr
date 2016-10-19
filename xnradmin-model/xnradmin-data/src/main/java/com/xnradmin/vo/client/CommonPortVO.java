/**
 * 2014年2月5日 上午11:18:56
 */
package com.xnradmin.vo.client;


import java.io.Serializable;

import com.xnradmin.po.CommonPort;
import com.xnradmin.po.CommonScript;

/**
 * @author: bin_liu
 */
public class CommonPortVO implements Serializable{

    private CommonPort port;

    private CommonScript script;

    public CommonPort getPort(){
        return port;
    }

    public void setPort(CommonPort port){
        this.port = port;
    }

    public CommonScript getScript(){
        return script;
    }

    public void setScript(CommonScript script){
        this.script = script;
    }
}
