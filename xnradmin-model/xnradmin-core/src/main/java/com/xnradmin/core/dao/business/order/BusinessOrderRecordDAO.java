package com.xnradmin.core.dao.business.order;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.business.BusinessOrderRecord;


@Repository("BusinessOrderRecordDAO")
public class BusinessOrderRecordDAO{
    
    @Autowired
    private CommonDAO commonDao;
    
    private static final Logger log = LoggerFactory
            .getLogger(BusinessOrderRecordDAO.class);

    // property constants

    public static final String ID = "id";
    public static final String CLIENT_USER_ID = "clientUserId"; //用户ID
    public static final String CLIENT_USER_NAME = "clientUserName"; //用户名称
    public static final String CLIENT_USER_MOBILE = "clientUserMobile"; //用户手机号
    public static final String CLIENT_USER_EMAIL = "clientUserEmail"; //用户邮箱
    public static final String CLIENT_USER_SEX = "clientUserSex"; //用户性别
    public static final String CLIENT_USER_TYPE = "clientUserType"; //用户类型（微信，WEB，APP，电话）
    public static final String CLIENT_USER_TYPE_NAME = "clientUserTypeName"; //用户类型名称（微信，WEB，APP，电话）
    public static final String WX_OPEN_ID = "wxOpenId"; //微信OpenId
    public static final String STAFF_ID = "staffId";	//隶属用户Id
    public static final String USER_REAL_MOBILE = "userRealMobile"; //收货人用户手机号
    public static final String USER_REAL_PLANE = "userRealPlane"; //收货人用户座机
    public static final String USER_REAL_NAME = "userRealName"; //收货人名称
    public static final String USER_REAL_ADDRESS = "userRealAddress"; //收货人地址
    public static final String USER_REAL_POSTCODE = "userRealPostcode"; //邮政编码
    public static final String USER_REAL_TIME = "userRealTime"; //用户收货时间
    public static final String USER_REAL_DESCRIPTION = "userRealDescription"; //收货人备注
    public static final String PAYMENT_TYPE = "paymentType"; //支付类型，充值账户支付，单次支付
    public static final String PAYMENT_TYPE_NAME = "paymentTypeName"; //支付类型名称，充值账户支付，单次支付
    public static final String PAYMENT_STATUS = "paymentStatus"; //支付状态，已支付，待支付，退款
    public static final String PAYMENT_STATUS_NAME = "paymentStatusName"; //支付状态名称，已支付，待支付，退款
    public static final String PAYMENT_PROVIDER = "paymentProvider"; //支付提供者，微信，支付宝，银联
    public static final String PAYMENT_PROVIDER_NAME = "paymentProviderName"; //支付提供者名称，微信，支付宝，银联
    public static final String PAYMENT_TIME = "paymentTime"; //支付时间
    public static final String OPERATE_TIME = "operateTime"; //订单操作时间（待处理，处理中，处理完成，订单退单）
    public static final String OPERATE_STATUS = "operateStatus"; //订单操作状态（待处理，处理中，处理完成，订单退单）
    public static final String OPERATE_STATUS_NAME = "operateStatusName"; //订单操作状态名称（待处理，处理中，处理完成，订单退单）
    public static final String CREATE_TIME = "createTime"; //订单生成时间
    public static final String ORIGINAL_PRICE= "originalPrice"; //原始结算价格
    public static final String TOTAL_PRICE = "totalPrice"; //最终结算价格
    public static final String LOGISTICS_COMPANY_ID = "LogisticsCompanyId"; //配送公司ID
    public static final String LOGISTICS_COMPANY_NAME = "LogisticsCompanyName"; //配送公司名称
    public static final String DELIVERY_STAFF_ID = "deliveryStaffId"; //送货人员ID
    public static final String DELIVERY_STAFF_NAME = "deliveryStaffName"; //送货人员名称
    public static final String DELIVERY_STAFF_MOBILE = "deliveryStaffMobile"; //送货人员手机号
    public static final String DELIVERY_START_TIME = "deliveryStartTime"; //送货起始时间
    public static final String DELIVERY_END_TIME = "deliveryEndTime"; //送货送达时间
    public static final String DELIVERY_STATUS = "deliveryStatusName"; //配送状态（未发货，已发货）
    public static final String DELIVERY_STATUS_NAME = "deliveryStatus"; //配送状态名称（未发货，已发货）
    public static final String SOURCE_ID = "sourceId"; //该订单用户推广来源ID（有可能是用户推广，代理商推广，洗车工推广，线上推广）
    public static final String SOURCE_NAME = "sourceName"; //该订单用户推广来源ID（有可能是用户推广，代理商推广，线上推广）
    public static final String SOURCE_TYPE = "sourceType"; //该订单用户推广来源类型 （有可能是用户推广，代理商推广，线上推广）
    public static final String SOURCE_TYPE_NAME = "sourceTypeName"; //该订单用户推广来源类型 （有可能是用户推广，代理商推广，线上推广）
    public static final String SER_NO = "serNo"; //银行流水号
    public static final String SELLER_ID = "sellerId"; //代理商ID
    public static final String SELLER_NAME = "sellerName"; //代理商名称
    public static final String CUS_ID= "cusId"; //合作方ID
    public static final String CUS_NAME= "cusName"; //合作方名称
    public static final String PRIMARY_CONFIGURATION_ID = "primaryConfigurationId"; //对应商城ID
    public static final String PRIMARY_CONFIGURATION_NAME = "primaryConfigurationName"; //对应商城名称

    protected void initDao(){
        // do nothing
    }

    public Serializable save(BusinessOrderRecord businessOrderRecord){
        log.debug("saving BusinessOrderRecord instance");
        Serializable cls;
        try{
        	cls = commonDao.save(businessOrderRecord);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
        return cls;
    }

    public void delete(BusinessOrderRecord businessOrderRecord){
        log.debug("deleting BusinessOrderRecord instance");
        try{
            commonDao.delete(businessOrderRecord);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public BusinessOrderRecord findById(Long id){
        log.debug("getting BusinessOrderRecord instance with id: " + id);
        try{

            return (BusinessOrderRecord) commonDao.findById(
            		BusinessOrderRecord.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<BusinessOrderRecord> findByExample(BusinessOrderRecord instance){
        log.debug("finding BusinessOrderRecord instance by example");
        try{

            return commonDao.getEntitesForExample(instance,0,0);
        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List findByProperty(String propertyName,Object value){
        log.debug("finding BusinessOrderRecord instance with property: "
                + propertyName + ", value: " + value);
        try{
            String queryString = "from BusinessOrderRecord as model where model."
                    + propertyName + "= ?";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }
    
    public List findAll(){
        log.debug("finding all BusinessOrderRecord instances");
        try{
            String queryString = "from BusinessOrderRecord";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public BusinessOrderRecord merge(BusinessOrderRecord detachedInstance){
        log.debug("merging BusinessOrderRecord instance");
        try{
        	BusinessOrderRecord result = (BusinessOrderRecord) commonDao
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(BusinessOrderRecord instance){
        log.debug("attaching dirty BusinessOrderRecord instance");
        try{
            commonDao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    
    public int removeBusinessOrderRecordId(Long id){

        log.debug("removeBusinessOrderRecordId: " + id);
        try{
            String queryString = "delete  from BusinessOrderRecord as model where model.id = "
                    + id;
            return commonDao.executeUpdateOrDelete(queryString);
        }catch(RuntimeException re){
            log.error("removeBusinessOrderRecordId failed",re);
            throw re;
        }
    }
    
    
    public static BusinessOrderRecordDAO getFromApplicationContext(
            ApplicationContext ctx){
        return (BusinessOrderRecordDAO) ctx.getBean("BusinessOrderRecordDAO");
    }
}