/**
 * 2013年9月29日 下午3:12:57
 */
package com.xnradmin.core.service;


import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.codehaus.commons.compiler.CompileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.cache.MemCachedBase;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.util.Log4jUtil;
import com.xnradmin.core.util.ScriptHelper;
import com.xnradmin.constant.CacheKey;
import com.xnradmin.dto.ScriptDTO;
import com.xnradmin.po.CommonScript;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.vo.client.script.ScriptVO;

/**
 * @autohr: bin_liu
 */
@Service("ScriptService")
public class ScriptService{

    private static Log scriptLog = Log4jUtil.getLog("script");

    @Autowired
    private ScriptHelper scriptHelper;

    @Autowired
    private CommonDAO commonDao;

    @Autowired
    private StaffService staffService;

    @Autowired
    private MemCachedBase cache;

    @Autowired
    private StatusService statusService;

    /**
     * return : <br>
     * -1 : 相同的类名已存在<br>
     * -2 : 编译异常<br>
     * -3 : class未找到异常<br>
     * -4 : 文件IO异常<br>
     * -5 : 脚本缓存异常<br>
     * 
     * @param po
     * @return int
     */
    public int save(CommonScript po,CommonStaff staff){
        CommonScript exist = findByClassNamefromDB(po.getClassName());
        if(exist != null)
            return -1;
        ScriptDTO dto = null;
        po.setCreateTime(new Timestamp(System.currentTimeMillis()));
        po.setCreateStaffId(staff.getId());
        po.setCreateStaffName(staff.getStaffName());

        try{
            dto = scriptHelper.loadByLocal(po.getClassName());

            po.setScriptEncode(dto.getScriptEncode());
            po.setScriptMethods(Arrays.deepToString(dto.getScriptMethods())
                    .replaceAll("\\[","").replaceAll("\\]",""));
            po.setScriptDIR(dto.getScriptDIR());
            po.setSourceFile(dto.getSourceFile());

        }catch(CompileException e){
            scriptLog.error(e.getMessage());
            return -2;
        }catch(ClassNotFoundException e){
            scriptLog.error(e.getMessage());
            return -3;
        }catch(IOException e){
            scriptLog.error(e.getMessage());
            return -4;
        }
        commonDao.save(po);

        try{
            findByClassName(po.getClassName());
        }catch(Exception e){
            scriptLog.error(e.getMessage());
            return -5;
        }
        return 0;
    }

    public void del(Integer id,CommonStaff staff) throws CompileException,
            ClassNotFoundException,IOException{
        //
        CommonScript po = findByIDfromDB(id);
        scriptHelper.del(po.getClassName());
        commonDao.delete(po);
    }

    public void ref(Integer id) throws CompileException,ClassNotFoundException,
            IOException{
        CommonScript po = findByIDfromDB(id);
        reloadVO(po.getClassName());
    }

    public void refAll(){
        reloadVOAll();
    }

    /**
     * return : <br>
     * 0 : 正常<br>
     * -1 : 记录不存在<br>
     * -2 : 编译异常<br>
     * -3 : class未找到异常<br>
     * -4 : 文件IO异常<br>
     * -5 : 已有相同的类名<br>
     * 
     * @param po
     * @return int
     */
    public int modify(CommonScript po,CommonStaff staff){
        CommonScript old = findByIDfromDB(po.getId());
        if(old == null){
            return -1;
        }

        String hql = "from CommonScript where className='" + po.getClassName()
                + "'";
        List<CommonScript> l = commonDao
                .getEntitiesByPropertiesWithHql(hql,0,0);
        for(CommonScript e : l){
            if(e.getClassName().equals(po.getClassName())
                    && !e.getClassName().equals(old.getClassName())){
                return -5;
            }
        }

        po.setModifyTime(new Timestamp(System.currentTimeMillis()));
        po.setModifyStaffId(staff.getId());
        po.setModifyStaffName(staff.getStaffName());
        po.setCreateStaffId(old.getCreateStaffId());
        po.setCreateStaffName(old.getCreateStaffName());
        po.setCreateTime(old.getCreateTime());

        try{
            ScriptDTO dto = reload(po.getClassName());

            ScriptVO v = new ScriptVO();
            v.setScriptClassName(po.getClassName());
            v.setScriptdto(dto);
            v.setScriptpo(po);

            po.setScriptEncode(dto.getScriptEncode());
            po.setScriptMethods(Arrays.deepToString(dto.getScriptMethods())
                    .replaceAll("\\[","").replaceAll("\\]",""));
            po.setScriptDIR(dto.getScriptDIR());
            po.setSourceFile(dto.getSourceFile());

            cache.replace(CacheKey.SCRIPT_VO_CLASSNAME,v);
        }catch(CompileException e){
            scriptLog.error(e.getMessage());
            return -2;
        }catch(ClassNotFoundException e){
            scriptLog.error(e.getMessage());
            return -3;
        }catch(IOException e){
            scriptLog.error(e.getMessage());
            return -4;
        }
        commonDao.modify(po);
        return 0;
    }

    /**
     * 物理REALOAD<br>
     * return : <br>
     * 0 : 正常<br>
     * -2 : 编译异常<br>
     * -3 : class未找到异常<br>
     * -4 : 文件IO异常<br>
     * 
     * @param className
     * @return int
     */
    public int reloadVO(String className){
        try{
            ScriptVO res = new ScriptVO();

            CommonScript po = findByClassNamefromDB(className);

            reload(className);

            ScriptDTO dto = get(className);

            res.setScriptClassName(className);
            res.setScriptdto(dto);
            res.setScriptpo(po);

            cache.replace(CacheKey.SCRIPT_VO_CLASSNAME + className,res);
        }catch(CompileException e){
            scriptLog.error(e.getMessage());
            return -2;
        }catch(ClassNotFoundException e){
            scriptLog.error(e.getMessage());
            return -3;
        }catch(IOException e){
            scriptLog.error(e.getMessage());
            return -4;
        }
        return 0;
    }

    public void reloadVOAll() throws IllegalArgumentException{
        List<CommonScript> all = findAll();
        for(CommonScript e : all){
            int res = reloadVO(e.getClassName());
            if(res != 0)
                throw new IllegalArgumentException("重载发生错误: " + res);
        }
    }

    public ScriptVO findByClassName(String className) throws CompileException,
            ClassNotFoundException,IOException{
        Object o = cache.get(CacheKey.SCRIPT_VO_CLASSNAME + className);
        if(o != null)
            return (ScriptVO) o;

        CommonScript po = findByClassNamefromDB(className);
        if(po == null)
            return null;
        ScriptDTO dto = get(className);
        if(dto == null)
            return null;

        ScriptVO v = new ScriptVO();
        v.setScriptClassName(className);
        v.setScriptdto(dto);
        v.setScriptpo(po);

        cache.add(CacheKey.SCRIPT_VO_CLASSNAME,v);
        return v;
    }

    public List<CommonScript> findAll(){
        String hql = "from CommonScript ";
        return commonDao.getEntitiesByPropertiesWithHql(hql,0,0);
    }

    private CommonScript findByClassNamefromDB(String className){
        String hql = "from CommonScript where className='" + className + "'";
        List<CommonScript> l = commonDao
                .getEntitiesByPropertiesWithHql(hql,0,0);
        if(l == null || l.size() <= 0)
            return null;
        return l.get(0);
    }

    public CommonScript findByIDfromDB(Integer id){
        Object o = commonDao.findById(CommonScript.class,id);
        if(o == null)
            return null;
        return (CommonScript) o;
    }

    private void reloadAll() throws CompileException,ClassNotFoundException,
            IOException{
        scriptHelper.reload();
    }

    private ScriptDTO reload(String scriptClassName) throws CompileException,
            ClassNotFoundException,IOException{
        return scriptHelper.reload(scriptClassName);
    }

    private ScriptDTO get(String className) throws CompileException,
            ClassNotFoundException,IOException{
        ScriptDTO d = scriptHelper.find(className);
        if(d == null)
            d = scriptHelper.loadByLocal(className);
        if(d == null){
            d = scriptHelper.reload(d.getClassName());
        }
        return d;
    }

    public Object executeMethod(String methodName,ScriptDTO script,
            Object... parameter){
        return scriptHelper.executeMethod(methodName,script,parameter);
    }

    public List<ScriptVO> listVO(ScriptVO query,int pageNo,int pageSize)
            throws CompileException,ClassNotFoundException,IOException{
        String hql = getHql(query);
        List<CommonScript> l = commonDao.getEntitiesByPropertiesWithHql(hql,
                pageNo,pageSize);

        List<ScriptVO> res = new java.util.LinkedList<ScriptVO>();
        for(CommonScript e : l){
            ScriptVO v = new ScriptVO();
            v.setScriptClassName(e.getClassName());
            v.setScriptpo(e);
            ScriptDTO dto = get(e.getClassName());
            v.setScriptdto(dto);
            res.add(v);
        }

        return res;
    }

    public int getCount(ScriptVO query){
        String hql = "select count(*) " + getHql(query);
        return commonDao.getNumberOfEntitiesWithHql(hql);
    }

    public String getHql(ScriptVO query){
        StringBuffer hql = new StringBuffer("from CommonScript");
        boolean isWhere = false;
        int flag = 0;
        if(query != null && !StringHelper.isNull(query.getScriptClassName())){
            isWhere = true;
        }

        if(isWhere){
            hql.append(" where ");
        }
        if(query != null && !StringHelper.isNull(query.getScriptClassName())){
            if(flag > 0)
                hql.append(" and ");
            hql.append(" className like '%").append(query.getScriptClassName())
                    .append("%'");
            flag ++ ;
        }
        hql.append(" order by id desc");
        return hql.toString();
    }

}
