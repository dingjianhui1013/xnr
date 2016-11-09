/**
 * 2014年2月4日 下午5:22:53
 */
package com.xnradmin.core.service;


import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.po.CommonPort;
import com.xnradmin.po.CommonScript;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.vo.client.CommonPortVO;

/**
 * @author: bin_liu
 */
@Service("CommonPortService")
public class CommonPortService{

    @Autowired
    private CommonDAO dao;

    @Autowired
    private ScriptService scriptService;

    @Autowired
    private StatusService statusService;

    /**
     * code: <br>
     * -1 : 参数错误<br>
     * -2 : 已存在的接口名称<br>
     * -3 : 接口名称必须为英文，数字，下划线<br>
     * 
     * @param vo
     * @param staff
     * @return int
     */
    public int save(CommonPortVO vo,CommonStaff staff){
        if(vo == null || vo.getScript() == null || vo.getPort() == null)
            return -1;

        List<CommonPort> l = findByName(vo.getPort().getPortName());
        if(l != null && l.size() > 0){
            return -2;
        }

        if(!StringHelper.isAlphanumeric(vo.getPort().getPortName())){
            return -3;
        }

        CommonPort po = vo.getPort();
        po.setScriptId(vo.getScript().getId());
        po.setScriptClassName(vo.getScript().getClassName());
        po.setCreateStaffId(staff.getId());
        po.setCreateStaffName(staff.getStaffName());
        po.setCreateTime(new Timestamp(System.currentTimeMillis()));

        Status s = statusService.findByid(po.getStatus().toString());
        po.setStatusName(s.getStatusName());

        Status intype = statusService.findByid(po.getInputType().toString());
        po.setIntputTypeDesc(intype.getStatusName());
        Status outtype = statusService.findByid(po.getInputType().toString());
        po.setOutputTypeDesc(outtype.getStatusName());

        dao.save(po);
        return 0;
    }

    /**
     * code: <br>
     * -1 : 参数错误<br>
     * -2 : 已存在的接口名称<br>
     * -3 : 接口名称必须为英文，数字，下划线<br>
     * 
     * @param vo
     * @param staff
     * @return int
     */
    public int modify(CommonPortVO vo,CommonStaff staff){
        if(vo == null || vo.getScript() == null || vo.getPort() == null)
            return -1;
        if(!StringHelper.isAlphanumeric(vo.getPort().getPortName())){
            return -3;
        }
        CommonPortVO old = findById(vo.getPort().getId());
        List<CommonPort> l = findByName(vo.getPort().getPortName());
        for(CommonPort e : l){
            if(e.getPortName().equals(vo.getPort().getPortName())
                    && !old.getPort().getPortName()
                            .equals(vo.getPort().getPortName())){
                return -2;
            }
        }
        CommonPort po = vo.getPort();
        po.setScriptId(vo.getScript().getId());
        po.setScriptClassName(vo.getScript().getClassName());
        po.setCreateStaffId(old.getPort().getCreateStaffId());
        po.setCreateStaffName(old.getPort().getCreateStaffName());
        po.setCreateTime(old.getPort().getCreateTime());

        po.setModifyStaffId(staff.getId());
        po.setModifyStaffName(staff.getStaffName());
        po.setModifyTime(new Timestamp(System.currentTimeMillis()));

        Status s = statusService.findByid(po.getStatus().toString());
        po.setStatusName(s.getStatusName());
        Status intype = statusService.findByid(po.getInputType().toString());
        po.setIntputTypeDesc(intype.getStatusName());
        Status outtype = statusService.findByid(po.getInputType().toString());
        po.setOutputTypeDesc(outtype.getStatusName());

        dao.modify(po);

        return 0;
    }

    public CommonPortVO findById(Integer id){
        Object o = dao.findById(CommonPort.class,id);
        if(o == null)
            return null;
        CommonPort po = (CommonPort) o;
        CommonScript spo = scriptService.findByIDfromDB(po.getScriptId());
        CommonPortVO v = new CommonPortVO();
        v.setPort(po);
        v.setScript(spo);
        return v;
    }

    public List<CommonPortVO> listVO(CommonPortVO query,int pageNo,int pageSize){
        String hql = getHql(query);
        List<CommonPort> l = dao.getEntitiesByPropertiesWithHql(hql,pageNo,
                pageSize);

        List<CommonPortVO> res = new LinkedList<CommonPortVO>();
        for(CommonPort e : l){
            CommonPortVO v = new CommonPortVO();
            CommonScript s = scriptService.findByIDfromDB(e.getScriptId());
            v.setPort(e);
            v.setScript(s);
            res.add(v);
        }
        return res;
    }
    
    /**
     * 0 - 成功<br>
     * 1 - 未查询到该接口<br>
     * 2 - 传入参数不能为空<br>
     * 
     * @param query
     * @return int
     */
    public int delte(CommonPortVO query){
    	if(query==null || query.getPort()==null || query.getPort().getId()==null)
    		return 2;
    	CommonPortVO v = this.findById(query.getPort().getId());
    	if(v==null)
    		return 1;
    	this.dao.delete(v.getPort());
    	return 0;
    }

    public int getCount(CommonPortVO query){
        String hql = "select count(*) " + getHql(query);
        return dao.getNumberOfEntitiesWithHql(hql);
    }

    public String getHql(CommonPortVO query){
        StringBuffer hql = new StringBuffer("from CommonPort");
        boolean isWhere = false;
        int flag = 0;
        if(query != null && !StringHelper.isNull(query.getPort().getPortName())){
            isWhere = true;
        }

        if(isWhere){
            hql.append(" where ");
        }
        if(query != null && !StringHelper.isNull(query.getPort().getPortName())){
            if(flag > 0)
                hql.append(" and ");
            hql.append(" portName like '%")
                    .append(query.getPort().getPortName()).append("%'");
            flag ++ ;
        }

        return hql.toString();
    }

    public List<CommonPort> findByName(String portName){
        String hql = "from CommonPort where portName ='" + portName + "'";
        return dao.getEntitiesByPropertiesWithHql(hql,0,0);
    }

    public CommonPort findByNameOne(String portName){

        List<CommonPort> l = findByName(portName);
        if(l == null || l.size() <= 0)
            return null;
        return l.get(0);
    }

}
