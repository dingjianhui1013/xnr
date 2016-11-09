/**
 * 2012-5-15 下午1:54:58
 */
package com.xnradmin.core.service;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonContactDAO;
import com.xnradmin.core.dao.CommonCustomerContactDAO;
import com.xnradmin.core.dao.CommonCustomerDAO;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.CommonContact;
import com.xnradmin.po.CommonCustomer;
import com.xnradmin.po.CommonCustomerContact;
import com.xnradmin.po.CommonOrganization;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.vo.ContactVO;

/**
 * @author: bin_liu
 */
@Service("CustomerService")
public class CustomerService{
    @Autowired
    private CommonDAO commonDao;

    @Autowired
    private CommonCustomerDAO customerDao;

    @Autowired
    private CommonCustomerContactDAO relationDao;

    @Autowired
    private CommonContactDAO contactDao;

    @Autowired
    private OrganizationService orgService;

    static Log log = LogFactory.getLog(CustomerService.class);

    /**
     * 新增一个客户
     * 
     * @param po
     */
    public Integer saveCustomer(CommonStaff staff,CommonCustomer po){
        po.setCreateTime(new Timestamp(System.currentTimeMillis()));
        po.setCreateStaffId(staff.getId());
        po.setCreateStaffName(staff.getStaffName());
        CommonOrganization org = orgService.findByid(staff.getOrganizationId()
                .toString());
        po.setCreateStaffOrgId(org.getId());
        po.setCreateStaffOrgName(org.getOrganizationName());
        return this.customerDao.save(po);
    }

    public CommonCustomer findCustomerByID(String id){
        return this.customerDao.findById(new Integer(id));
    }

    /**
     * 新增一个联系人
     * 
     * @param po
     */
    public void saveContact(String customerId,CommonContact po){
        Integer cid = this.contactDao.save(po);

        CommonCustomerContact rel = new CommonCustomerContact();
        rel.setContactId(cid);
        rel.setCustomerId(new Integer(customerId));

        this.relationDao.save(rel);

    }

    public List<CommonCustomer> findByCusName(String cusName){
        String hql = "from CommonCustomer where customerName like '%" + cusName
                + "%'";
        List<CommonCustomer> l = commonDao.getEntitiesByPropertiesWithHql(hql,
                0,0);
        return l;
    }

    public void modifyCustomer(CommonCustomer po){
        this.customerDao.merge(po);
    }

    public void modifyContact(CommonContact po){
        // CommonContact old = this.contactDao.findById(po.getId());
        // if (!StringHelper.isNull(po.getContactName()))
        // old.setContactName(po.getContactName());
        // if (!StringHelper.isNull(po.getEmail()))
        // old.setEmail(po.getEmail());
        // if (!StringHelper.isNull(po.getMobile()))
        // old.setMobile(po.getMobile());
        // if (!StringHelper.isNull(po.getPosition()))
        // old.setPosition(po.getPosition());
        // if (!StringHelper.isNull(po.getStatus()))
        // old.setStatus(po.getStatus());

        this.contactDao.merge(po);
    }

    /**
     * 客户信息返回查询总数
     * 
     * @param customerName
     * @return int
     */
    public int getCount(String customerName){
        String hql = "select count(*) from CommonCustomer";
        if(!StringHelper.isNull(customerName)){
            hql += " where customerName like '%" + customerName + "%'";
        }
        return this.commonDao.getNumberOfEntitiesWithHql(hql);
    }

    /**
     * 页面返回客户信息数据
     * 
     * @param customerName
     * @param curPage
     * @param pageSize
     * @param orderField
     * @param direction
     * @return List<CommonCustomer>
     */
    public List<CommonCustomer> listVO(String customerName,int curPage,
            int pageSize,String orderField,String direction){
        String hql = "from CommonCustomer";
        if(!StringHelper.isNull(customerName)){
            hql += " where customerName like '%" + customerName + "%'";
        }
        if(!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)){
            hql += " order by " + orderField + " " + direction;
        }else{
            hql += " order by id desc";
        }

        List<CommonCustomer> l = commonDao.getEntitiesByPropertiesWithHql(hql,
                curPage,pageSize);

        return l;

    }

    /**
     * 找个联系人呗
     * 
     * @param customerid
     * @param contid
     * @return ContactVO
     */
    public ContactVO findContactByID(String customerid,String contid){
        String hql = getHql(customerid,contid,null,null,null);

        List l = commonDao.getEntitiesByPropertiesWithHql(hql,1,1);

        ContactVO vo = new ContactVO();
        Object[] obj = (Object[]) l.get(0);
        CommonCustomer cus = (CommonCustomer) obj[0];
        CommonContact cont = (CommonContact) obj[1];
        CommonCustomerContact rel = (CommonCustomerContact) obj[2];

        vo.setCity(cus.getCity());
        vo.setContactid(cont.getId().toString());
        vo.setContactName(cont.getContactName());
        vo.setCountry(cus.getCountry());
        vo.setCustomerAddress(cus.getCustomerAddress());
        vo.setCustomerid(cus.getId().toString());
        vo.setCustomerName(cus.getCustomerName());
        vo.setEmail(cont.getEmail());
        vo.setExtent(cus.getExtent());
        vo.setLevel(cus.getLevel());
        vo.setMobile(cont.getMobile());
        vo.setPosition(cont.getPosition());
        vo.setProvince(cus.getProvince());
        vo.setContactstatus(cont.getStatus());

        return vo;
    }

    /**
     * 一个客户里所有的联系人
     * 
     * @param customerid
     * @param contactName
     * @param curPage
     * @param pageSize
     * @param orderField
     * @param direction
     * @return List<ContactVO>
     */
    public List<ContactVO> listVo(String customerid,String contactName,
            int curPage,int pageSize,String orderField,String direction){
        String hql = getHql(customerid,null,contactName,orderField,direction);

        List l = commonDao.getEntitiesByPropertiesWithHql(hql,curPage,pageSize);
        List<ContactVO> res = new ArrayList<ContactVO>();
        for(int i = 0;i < l.size();i ++ ){
            ContactVO vo = new ContactVO();
            Object[] obj = (Object[]) l.get(i);
            CommonCustomer cus = (CommonCustomer) obj[0];
            CommonContact cont = (CommonContact) obj[1];
            CommonCustomerContact rel = (CommonCustomerContact) obj[2];

            vo.setCity(cus.getCity());
            vo.setContactid(cont.getId().toString());
            vo.setContactName(cont.getContactName());
            vo.setCountry(cus.getCountry());
            vo.setCustomerAddress(cus.getCustomerAddress());
            vo.setCustomerid(cus.getId().toString());
            vo.setCustomerName(cus.getCustomerName());
            vo.setEmail(cont.getEmail());
            vo.setExtent(cus.getExtent());
            vo.setLevel(cus.getLevel());
            vo.setMobile(cont.getMobile());
            vo.setPosition(cont.getPosition());
            vo.setProvince(cus.getProvince());
            vo.setContactstatus(cont.getStatus());
            res.add(vo);
        }
        return res;
    }

    public int getCount(String customerid,String contactName){
        String hql = "select count(*) "
                + getHql(customerid,null,contactName,null,null);
        return this.commonDao.getNumberOfEntitiesWithHql(hql);
    }

    private String getHql(String customerid,String contid,String contactName,
            String orderField,String direction){
        StringBuffer sb = new StringBuffer();
        sb.append(" from CommonCustomer a,CommonContact b,CommonCustomerContact c");
        sb.append(" where a.id=c.customerId and b.id=c.contactId");
        if(!StringHelper.isNull(customerid))
            sb.append(" and a.id=").append(customerid);
        if(!StringHelper.isNull(contid)){
            sb.append(" and b.id=");
            sb.append(contid);
        }
        if(!StringHelper.isNull(contactName)){
            sb.append(" and b.contactName like '%");
            sb.append(contactName).append("%'");
        }
        if(!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)){
            sb.append(" order by ").append(orderField).append(" ")
                    .append(direction);
        }else{
            sb.append(" order by b.id desc");
        }
        return sb.toString();
    }

}
