/**
 * 2013年10月4日 下午2:37:56
 */
package com.xnradmin.core.cache;


import java.util.List;
import java.util.Vector;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.util.SpringBase;
import com.xnradmin.constant.CacheKey;
import com.xnradmin.dto.ScriptDTO;

/**
 * 脚本缓存
 * 
 * @autohr: bin_liu
 */
public class ScriptCacheManager{

    private static MemCachedBase memcachedBase;

    static{
        memcachedBase = (MemCachedBase) SpringBase.getCtx().getBean(
                "MemCachedBase");
    }

    /**
     * 添加一个脚本到缓存，如存在则替换,KEY=className<br>
     * 
     * @param script
     */
    public static synchronized void add(ScriptDTO script){
        memcachedBase.set(CacheKey.SCRIPT_PLATFORM + script.getClassName(),
                script.getLastModifyTime());
        addToKeyIndex(script.getClassName());
    }
    
    public static synchronized void remove(ScriptDTO script){
        memcachedBase.delete(CacheKey.SCRIPT_PLATFORM + script.getClassName());
        Object o = memcachedBase.get(CacheKey.SCRIPT_KEY_LIST);
        if(o != null){
            List<String> index = (List<String>) o;
            if(index.contains(script.getClassName())){                
                index.remove(script.getClassName());
                memcachedBase.add(CacheKey.SCRIPT_KEY_LIST,index);                
            }
        }
    }
    
    /**
     * 所有已缓存脚本KEY的索引列表
     * 
     * @return List<String>
     */
    public static List<String> getKeyIndex(){
        Object o = memcachedBase.get(CacheKey.SCRIPT_KEY_LIST);
        return o == null ? null : (List<String>) o;
    }

    /**
     * 脚本是否已缓存
     * 
     * @param script
     * @return boolean
     */
    public static boolean isCached(String scriptClassName){
        if(StringHelper.isNull(scriptClassName))
            return false;
        Long d = get(scriptClassName);
        return d == null ? false : true;
    }

    /**
     * 所有已缓存脚本KEY的索引列表
     * 
     * @param script
     */
    private synchronized static void addToKeyIndex(String scriptClassName){
        Object o = memcachedBase.get(CacheKey.SCRIPT_KEY_LIST);
        if(o == null){
            List<String> index = new Vector<String>();
            index.add(scriptClassName);
            memcachedBase.add(CacheKey.SCRIPT_KEY_LIST,index);
        }else{
            List<String> index = (List) o;
            if(index.contains(scriptClassName)){
                return;
            }else{
                index.add(scriptClassName);
                memcachedBase.add(CacheKey.SCRIPT_KEY_LIST,index);
            }
        }
    }

    /**
     * 从缓存取脚本，KEY=className<br>
     * 
     * @param script
     * @return ScriptDTO
     */
    public static Long get(String scriptClassName){
        Object obj = memcachedBase.get(CacheKey.SCRIPT_PLATFORM
                + scriptClassName);
        if(obj == null)
            return null;
        return (Long) obj;
    }
}
