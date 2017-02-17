package com.xnradmin.core.dao.mall.commodity;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.po.mall.commodity.Goods;


@Repository("GoodsDAO")
public class GoodsDAO{
    
    @Autowired
    private CommonDAO commonDao;
    
    private static final Logger log = LoggerFactory
            .getLogger(GoodsDAO.class);

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
    public static final String GOODS_WEIGHT="goodsWeight";//商品重量
    public static final String GOODS_STOCK="goodsStock";//商品库存
    public static final String GOODS_STATUS="goodsStatus";//商品状态上架下架
    public static final String IS_FREELOGISTICS="isFreeLogistics";//包邮不包邮
    public static final String IS_NEW_GOODS="isNewGoods";//是否新商品
    public static final String IS_DISCOUNT_GOODS="isDiscountGoods";//是否优惠商品
    public static final String IS_HOTSALE_GOODS="isHotSaleGoods";//是否热卖商品
    public static final String GOODS_LOGO="goodsLogo";//商品图片
    public static final String PRIMARY_CONFIGURATION_ID="primaryConfigurationId";//对应商城ID
    public static final String STAFF_ID="staffId";	//隶属用户Id
    public static final String CREATE_TIME="createTime";//建立时间
    public static final String CREATE_STAFF_ID="createStaffId";//建立人
    public static final String MODIFY_TIME="modifyTime";//修改时间
    public static final String MODIFY_STAFF_ID="modifyStaffId";//修改人

    protected void initDao(){
        // do nothing
    }

    public Serializable save(Goods goods){
        log.debug("saving Goods instance");
        Serializable cls;
        try{
        	cls = commonDao.save(goods);
            log.debug("save successful");
        }catch(RuntimeException re){
            log.error("save failed",re);
            throw re;
        }
        return cls;
    }

    public void delete(Goods goods){
        log.debug("deleting Goods instance");
        try{
            commonDao.delete(goods);
            log.debug("delete successful");
        }catch(RuntimeException re){
            log.error("delete failed",re);
            throw re;
        }
    }

    public Goods findById(Integer id){
        log.debug("getting Goods instance with id: " + id);
        try{

            return (Goods) commonDao.findById(
            		Goods.class,id);
        }catch(RuntimeException re){
            log.error("get failed",re);
            throw re;
        }
    }

    public List<Goods> findByExample(Goods instance){
        log.debug("finding Goods instance by example");
        try{

            return commonDao.getEntitesForExample(instance,0,0);
        }catch(RuntimeException re){
            log.error("find by example failed",re);
            throw re;
        }
    }

    public List findByProperty(String propertyName,Object value){
        log.debug("finding Goods instance with property: "
                + propertyName + ", value: " + value);
        try{
            String queryString = "from Goods as model where model."
                    + propertyName + "= ?";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0,value);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }

    public List findAll(){
        log.debug("finding all Goods instances");
        try{
            String queryString = "from Goods";
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find all failed",re);
            throw re;
        }
    }

    public Goods merge(Goods detachedInstance){
        log.debug("merging Goods instance");
        try{
        	Goods result = (Goods) commonDao
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }catch(RuntimeException re){
            log.error("merge failed",re);
            throw re;
        }
    }

    public void attachDirty(Goods instance){
        log.debug("attaching dirty Goods instance");
        try{
            commonDao.saveOrUpdate(instance);
            log.debug("attach successful");
        }catch(RuntimeException re){
            log.error("attach failed",re);
            throw re;
        }
    }

    
    public int removeGoodsId(Integer id){

        log.debug("removeGoodsId: " + id);
        try{
            String queryString = "delete  from Goods as model where model.id = "
                    + id;
            return commonDao.executeUpdateOrDelete(queryString);
        }catch(RuntimeException re){
            log.error("removeGoodsId failed",re);
            throw re;
        }
    }
    
    public List findByOlay(String goodsName, String goodsId){
        log.debug("finding findByOlay "
                + goodsName);
        try{
            String queryString = "from Goods where goodsName= '"+goodsName+"'";
            if(!StringHelper.isNull(goodsId)){
            	queryString = queryString + " and id!="+goodsId;
            }
            return commonDao.getEntitiesByPropertiesWithHql(queryString,0,0);
        }catch(RuntimeException re){
            log.error("find by property name failed",re);
            throw re;
        }
    }
    
    public static GoodsDAO getFromApplicationContext(
            ApplicationContext ctx){
        return (GoodsDAO) ctx.getBean("GoodsDAO");
    }
}