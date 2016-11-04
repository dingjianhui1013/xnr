package com.xnradmin.core.dao.business.commodity;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.business.BusinessGoods;


@Repository("BusinessGoodsDAO")
public class BusinessGoodsDAO{
    
    @Autowired
    private CommonDAO commonDao;
    
    private static final Logger log = LoggerFactory
            .getLogger(BusinessGoodsDAO.class);

    // property constants

    public static final String ID="id";
    public static final String SORT_ID="sortId";//排序Id
    public static final String GOODS_PARENT_ID="goodsParentId";//排序Id
    public static final String GOODS_CATEGORY_ID="goodsCategoryId";//商品所属分类
    public static final String GOODS_NAME="goodsName";//商品名称
    public static final String GOODS_DESCRIPTION="goodsDescription";//商品描述
    public static final String GOODS_ORIGINAL_PRICE="goodsOriginalPrice";//商品原价
    public static final String GOODS_PREFERENTIAL_PRICE="goodsPreferentialPrice";//商品优惠价
    public static final String GOODS_BRAND_ID="goodsBrandId";//商品品牌ID
    public static final String GOODS_WEIGHT="goodsWeight";//商品重量或数量
    public static final String GOODS_WEIGHT_ID="goodsWeightId";//商品重量ID
    public static final String GOODS_SOLD_COUNT="goodsSoldCount";//已售数量
    public static final String GOODS_STOCK="goodsStock";//商品库存
    public static final String GOODS_STATUS="goodsStatus";//商品状态上架下架
    public static final String IS_FREELOGISTICS="isFreeLogistics";//包邮不包邮
    public static final String IS_NEW_GOODS="isNewGoods";//是否新商品
    public static final String IS_DISCOUNT_GOODS="isDiscountGoods";//是否优惠商品
    public static final String IS_HOTSALE_GOODS="isHotSaleGoods";//是否热卖商品
    public static final String GOODS_LOGO="goodsLogo";//商品图片
    public static final String GOODS_BIG_LOGO="goodsBigLogo";//商品大图片
    public static final String PRIMARY_CONFIGURATION_ID="primaryConfigurationId";//对应商城ID
    public static final String CREATE_TIME="createTime";//建立时间
    public static final String CREATE_STAFF_ID="createStaffId";//建立人
    public static final String MODIFY_TIME="modifyTime";//修改时间
    public static final String MODIFY_STAFF_ID="modifyStaffId";//修改人

    protected void initDao(){
        // do nothing
    }

    public Serializable save(BusinessGoods businessGoods){
        log.debug("saving BusinessGoods instance");
        Serializable cls;
        try{
        	cls = commonDao.save(businessGoods);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
        return cls;
    }

    public void delete(BusinessGoods businessGoods){
        log.debug("deleting BusinessGoods instance");
        try{
            commonDao.delete(businessGoods);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public BusinessGoods findById(Integer id){
        log.debug("getting BusinessGoods instance with id: " + id);
        try{

            return (BusinessGoods) commonDao.findById(
            		BusinessGoods.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<BusinessGoods> findByExample(BusinessGoods instance){
        log.debug("finding BusinessGoods instance by example");
        try{

            return commonDao.getEntitesForExample(instance,0,0);
        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List findByProperty(String propertyName,Object value){
        log.debug("finding BusinessGoods instance with property: "
                + propertyName + ", value: " + value);
        try{
            String queryString = "from BusinessGoods as model where model."
                    + propertyName + "= ?";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public List findAll(){
        log.debug("finding all BusinessGoods instances");
        try{
            String queryString = "from BusinessGoods";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public BusinessGoods merge(BusinessGoods detachedInstance){
        log.debug("merging BusinessGoods instance");
        try{
        	BusinessGoods result = (BusinessGoods) commonDao
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(BusinessGoods instance){
        log.debug("attaching dirty BusinessGoods instance");
        try{
            commonDao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    
    public int removeBusinessGoodsId(Integer id){

        log.debug("removeBusinessGoodsId: " + id);
        try{
            String queryString = "delete  from BusinessGoods as model where model.id = "
                    + id;
            return commonDao.executeUpdateOrDelete(queryString);
        }catch(RuntimeException re){
            log.error("removeBusinessGoodsId failed",re);
            throw re;
        }
    }
    
    public List findByOlay(String BusinessGoodsName, String BusinessGoodsId){
        log.debug("finding findByOlay "
                + BusinessGoodsName);
        try{
            String queryString = "from BusinessGoods where BusinessGoodsName= '"+BusinessGoodsName+"'";
            if(!StringHelper.isNull(BusinessGoodsId)){
            	queryString = queryString + " and id!="+BusinessGoodsId;
            }
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }
    
    public static BusinessGoodsDAO getFromApplicationContext(
            ApplicationContext ctx){
        return (BusinessGoodsDAO) ctx.getBean("BusinessGoodsDAO");
    }
}