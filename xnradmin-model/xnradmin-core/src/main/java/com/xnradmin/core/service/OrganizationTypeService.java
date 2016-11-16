/**
 * 2012-5-11 下午4:40:23
 */
package com.xnradmin.core.service;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.dao.CommonOrganizationTypeDAO;
import com.xnradmin.po.CommonOrganizationType;

/**
 * @author: bin_liu
 */
@Service("OrganizationTypeService")
public class OrganizationTypeService{

    static Log log = LogFactory.getLog(OrganizationTypeService.class);

    @Autowired
    private CommonOrganizationTypeDAO dao;
    
    @Autowired
    private CommonDAO commonDao;

    /**
     * return 0=成功<br>
     * 1=已存在的部门名称<br>
     * 
     * @param po
     * @return int
     */
    public int save(CommonOrganizationType po){
        if(this.dao.findByOrganizationTypeName(po.getOrganizationTypeName())
                .size() > 0){
            return 1;
        }
        dao.save(po);
        return 0;
    }

    public CommonOrganizationType findByid(String id){
        return dao.findById(new Integer(id));
    }

    /**
     * 数据总数
     * 
     * @param porgid
     * @return int
     */
    public int getCountByPorgid(String orgTypeName){
        String hql = "select count(*) from CommonOrganizationType";
        if(!StringHelper.isNull(orgTypeName)){
            hql = hql + " where ORGANIZATION_TYPE_NAME like '%" + orgTypeName
                    + "%'";
        }
        return this.commonDao.getNumberOfEntitiesWithHql(hql);
    }

    /**
     * return int :<br>
     * 0=成功 ,1=已存在的组织类型名称
     * 
     * @param po
     * @return int
     */
    public int modify(CommonOrganizationType po){
        List l = this.dao.findByOrganizationTypeName(po
                .getOrganizationTypeName());
        log.info("service modify: " + po.getId());
        if(l.size() > 0){
            CommonOrganizationType old = this.dao.findById(po.getId());
            if(!old.getOrganizationTypeName().equals(
                    po.getOrganizationTypeName()))
                return 1;
        }
        this.dao.merge(po);
        return 0;
    }

    /**
     * 页面返回
     * 
     * @param orgTypeName
     * @param curPage
     * @param pageSize
     * @param orderField
     * @param direction
     * @return List<CommonOrganizationType>
     */
    public List<CommonOrganizationType> listVO(String orgTypeName,int curPage,
            int pageSize,String orderField,String direction){
        String hql = "from CommonOrganizationType";
        if(!StringHelper.isNull(orgTypeName)){
            hql += " where ORGANIZATION_TYPE_NAME like '%" + orgTypeName + "%'";
        }
        if(!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)){
            hql += " order by " + orderField + " " + direction;
        }else{
            hql += " order by id desc";
        }

        List l = commonDao.getEntitiesByPropertiesWithHql(hql,curPage,pageSize);

        return l;

    }

    /**
     * 得到所有组织类型
     * 
     * @return List<CommonOrganizationType>
     */
    public List<CommonOrganizationType> listAll(){
        return dao.findAll();
    }
}
