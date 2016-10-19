/**
 * 2012-5-31 下午4:12:34
 */
package com.xnradmin.core.service;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.cache.MemCachedBase;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.dao.PhoneLocalDAO;
import com.xnradmin.core.util.SpringBase;
import com.xnradmin.constant.CacheKey;
import com.xnradmin.po.PhoneLocal;
import com.xnradmin.vo.PhoneLocalVO;

/**
 * @autohr: bin_liu
 */
@Service("PhoneLocalService")
public class PhoneLocalService{

    static Log log = LogFactory.getLog(PhoneLocalService.class);

    @Autowired
    private CommonDAO commonDao;

    @Autowired
    private PhoneLocalDAO dao;

    @Autowired
    private MemCachedBase memcachedBase;

    public List<PhoneLocalVO> findVOByJsonArray(String source)
            throws JSONException{
        if(StringHelper.isNull(source))
            return null;
        JSONArray array = new JSONArray(source);
        // Map<String, PhoneLocal> res = new HashMap<String, PhoneLocal>();
        List<PhoneLocalVO> res = new ArrayList<PhoneLocalVO>();
        for(int i = 0;i < array.length();i ++ ){
            JSONObject o = array.getJSONObject(i);
            PhoneLocalVO local = new PhoneLocalVO();
            local.setLocal(o.getString("local"));
            local.setLocalcode(o.getString("localcode"));
            local.setMaxCount(o.getString("maxCount"));
            local.setSerno(new Integer(i).toString());

            PhoneLocal l = getByLocal(local.getLocal());
            if(l != null)
                local.setId(l.getId().toString());

            res.add(local);
        }
        return res;
    }

    public boolean isBlockCityInJsonArray(PhoneLocal ph,String source)
            throws JSONException{
        List<PhoneLocalVO> l = findVOByJsonArray(source);
        for(PhoneLocalVO e : l){
            if(e.getLocalcode().equals(ph.getLocalcode()))
                return true;
        }
        return false;
    }

    public boolean isMaxCount(int count,PhoneLocal ph,String source)
            throws JSONException{
        List<PhoneLocalVO> l = findVOByJsonArray(source);
        for(PhoneLocalVO e : l){
            if(e.getLocalcode().equals(ph.getLocalcode())){
                if(count > new Integer(e.getMaxCount()).intValue())
                    return true;
            }
        }
        return false;
    }

    private PhoneLocal getByLocal(String local){
        String hql = "from PhoneLocal where local='" + local
                + "' group by local";
        List<PhoneLocal> l = this.commonDao.getEntitiesByPropertiesWithHql(
                hql.toString(),1,1);
        if(l == null || l.size() <= 0)
            return null;

        return l.get(0);
    }

    public PhoneLocal getLocalByMobile(String mobile){
        Object o = memcachedBase.get(CacheKey.PHONE_LOCAL
                + mobile.substring(0,7));
        PhoneLocal ph = null;
        if(o == null){
            ph = getLocalByMobileDB(mobile);
        }else{
            ph = (PhoneLocal) o;
        }
        if(ph == null)
            return null;
        else{
            memcachedBase.add(CacheKey.PHONE_LOCAL + mobile.substring(0,7),ph);
        }
        return ph;
    }

    public PhoneLocal getLocalByMobileDB(String mobile){
        StringBuffer hql = new StringBuffer();
        hql.append(" from PhoneLocal where");
        hql.append(" substr('").append(mobile);
        hql.append("',1,7)=phonehead");

        List<PhoneLocal> l = commonDao.getEntitiesByPropertiesWithHql(
                hql.toString(),1,1);

        if(l == null || l.size() <= 0)
            return null;
        else
            return l.get(0);
    }

    public boolean localIsExistInList(String local,String[] localList){
        for(String e : localList){
            if(e.equals(local))
                return true;
        }
        return false;
    }

    public boolean localIsExistInList(String local,List<String> localList){
        for(String e : localList){
            if(e.equals(local))
                return true;
        }
        return false;
    }

    public static boolean localIsExistInList(String local,String localList){
        if(StringHelper.isNull(localList) || localList.indexOf(",") < 0)
            return false;

        String[] lst = StringHelper.splitStr(localList,",");
        for(String e : lst){
            if(e.equals(local))
                return true;
        }
        return false;
    }

    public String[] getLocalCode(String local){
        return getLocalCodeList(local).toArray(new String[0]);
    }

    public PhoneLocalVO findPhoneLocalVOByRule(PhoneLocal ph,String jsonLocal)
            throws JSONException{
        if(ph == null || StringHelper.isNull(jsonLocal))
            return null;
        PhoneLocalVO res = new PhoneLocalVO();
        JSONArray array = new JSONArray(jsonLocal);
        for(int i = 0;i < array.length();i ++ ){
            JSONObject o = array.getJSONObject(i);
            PhoneLocalVO temp = new PhoneLocalVO();
            temp.setLocal(o.getString("local"));
            temp.setLocalcode(o.getString("localcode"));
            temp.setMaxCount(o.getString("maxCount"));

            if(ph.getLocal().equals(res.getLocal())){
                res = temp;
                break;
            }
        }
        return res;
    }

    public PhoneLocal findById(Integer id){
        Object obj = dao.findById(id);
        if(obj == null)
            return null;

        return (PhoneLocal) obj;
    }

    public List<String> getLocalCodeList(String local){
        String hql = "select distinct localcode from PhoneLocal where local='"
                + local + "' order by localcode";
        List<String> l = this.commonDao.getEntitiesByPropertiesWithHql(
                hql.toString(),0,0);
        return l;
    }

    public String[] getAllLocal(){
        // String[] local = new String[] { "河北", "山东", "北京", "广东", "上海", "天津",
        // "重庆", "辽宁", "江苏", "浙江", "四川", "陕西", "安徽", "福建", "海南", "广西",
        // "湖北", "江西", "甘肃", "山西", "湖南", "河南", "青海", "贵州", "宁夏", "云南",
        // "黑龙江", "吉林", "内蒙古", "新疆", "西藏" };
        return getAllLocalList().toArray(new String[0]);
    }
    
    public String[] getAllCity(){
        String hql = "select distinct localcode from PhoneLocal order by localcode";
        List<String> l = this.commonDao.getEntitiesByPropertiesWithHql(
                hql.toString(),0,0);
        return (String[])l.toArray(new String[0]);
    }

    public List<PhoneLocal> getAllLocalPO(){
        String hql = "from PhoneLocal group by local";
        List<PhoneLocal> l = this.commonDao.getEntitiesByPropertiesWithHql(
                hql.toString(),0,0);
        return l;
    }
    
    public List<String> getAllLocalList(){
        String hql = "select distinct local from PhoneLocal order by local";
        List<String> l = this.commonDao.getEntitiesByPropertiesWithHql(
                hql.toString(),0,0);
        return l;
    }

    public List<String> getAllLocalCodeByLocalId(String id){
        if(StringHelper.isNull(id) || !StringHelper.isDigit(id))
            return null;
        PhoneLocal p = findById(new Integer(id));
        if(p == null)
            return null;
        String hql = "select distinct localcode from PhoneLocal where local='"
                + p.getLocal() + "'";
        hql += " order by localcode";
        List<String> l = this.commonDao.getEntitiesByPropertiesWithHql(
                hql.toString(),0,0);
        return l;
    }

    public List<String> getAllLocalCodeByLocal(String local){
        String hql = "select distinct localcode from PhoneLocal ";
        if(!StringHelper.isNull(local))
            hql += " where local='" + local + "'";
        hql += " order by localcode";
        List<String> l = this.commonDao.getEntitiesByPropertiesWithHql(
                hql.toString(),0,0);
        return l;
    }

}
