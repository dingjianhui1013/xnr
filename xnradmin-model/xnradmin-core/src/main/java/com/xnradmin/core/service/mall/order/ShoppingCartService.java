/**
 *2012-5-11 上午9:00:02
 */
package com.xnradmin.core.service.mall.order;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.StringHelper;
import com.mongodb.util.JSON;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.dao.mall.order.ShoppingCartDAO;
import com.xnradmin.constant.ViewConstant;
import com.xnradmin.po.business.BusinessGoods;
import com.xnradmin.po.business.BusinessWeight;
import com.xnradmin.po.business.Combo;
import com.xnradmin.po.front.FrontUser;
import com.xnradmin.po.mall.commodity.Goods;
import com.xnradmin.po.mall.order.ShoppingCart;
import com.xnradmin.po.wx.OutPlan;
import com.xnradmin.po.wx.connect.Farmer;
import com.xnradmin.vo.business.ComboVO;
import com.xnradmin.vo.business.OutPlanVO;
import com.xnradmin.vo.front.BusinessGoodsCartVo;
import com.xnradmin.vo.mall.OrderVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Service("ShoppingCartService")
public class ShoppingCartService {

	@Autowired
	private ShoppingCartDAO dao;

	@Autowired
	private CommonDAO commonDao;

	@Autowired
	private StaffService staffService;

	@Autowired
	private StatusService statusService;

	static Log log = LogFactory.getLog(ShoppingCartService.class);

	/**
	 * 
	 * @param po
	 * @return int
	 */
	public int save(ShoppingCart po) {
		if(po.getGoodsId()!=null)
		{
			if (this.dao.findByOlay(po.getGoodsId().toString(), po.getClientUserId().toString()).size() > 0) {
				return 1;
			}
		}
		if(po.getComboId()!=null)
		{
			if (this.dao.findByOlayC(po.getComboId().toString(), po.getClientUserId().toString()).size() > 0) {
				return 1;
			}
		}
		dao.save(po);
		return 0;
	}
	/**
	 * 保存cookie中的购物车信息
	 */
	public boolean saveCookieCart(String cart,FrontUser frontUser){
		try {
			JSONArray json = JSONArray.fromObject(cart );
			if(json.size()>0){
			   for(int i=0;i<json.size();i++){
				   JSONObject job = json.getJSONObject(i);
				   if (this.dao.findByCookieId(job.get("cookieId").toString(),frontUser.getId().toString()).size() > 0) {
						continue;
				   }
				   ShoppingCart po = new ShoppingCart();
					po.setClientUserId(Integer.parseInt(frontUser.getId().toString()));
					if(!job.get("goodsId").toString().equals("null"))
					{
						po.setGoodsId(Integer.parseInt(job.get("goodsId").toString()));
					}
					if(!job.get("comboId").toString().equals("null"))
					{
						po.setComboId(Integer.parseInt(job.get("comboId").toString()));
					}
					po.setGoodsCount(Integer.parseInt(job.get("goodsCount").toString()));
					po.setCookieCartId(job.get("cookieId").toString());
					
					po.setCurrentPrice(Float.valueOf(job.get("price").toString()));
					BigDecimal price = new BigDecimal(job.get("price").toString());
				    BigDecimal totalprice = price.multiply(new BigDecimal(job.get("goodsCount").toString()));
//					po.setTotalPrice(Float.valueOf(job.get("price").toString())*Integer.parseInt(job.get("goodsCount").toString()));
				    po.setTotalPrice(totalprice.floatValue());
					po.setCurrentPriceType(121);
					
					po.setOriginalPrice(Float.valueOf(job.get("price").toString()));
//					po.setOriginalTotalPrice(Float.valueOf(job.get("price").toString())*Integer.parseInt(job.get("goodsCount").toString()));
					po.setOriginalTotalPrice(totalprice.floatValue());
					po.setPrimaryConfigurationId(1);
					po.setShoppingCartTime(new Timestamp(System.currentTimeMillis()));
					
					dao.save(po);
			  }
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public ShoppingCart findByid(String id) {
		return dao.findById(Integer.parseInt(id));
	}
	
	
	
	public List<BusinessGoodsCartVo>findByUserId(Integer userId){
		String hql = "from ShoppingCart cart , BusinessGoods good  where cart.goodsId = good.id and cart.clientUserId = " + userId+" and cart.comboId=null";
		List<Object[]> list =commonDao.getEntitiesByPropertiesWithHql(hql, 0, 0);
		List<BusinessGoodsCartVo> resList = new LinkedList<BusinessGoodsCartVo>();
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			ShoppingCart cart = (ShoppingCart) obj[0];
			BusinessGoods goods = (BusinessGoods) obj[1];
			
			BusinessGoodsCartVo businessGoodsCartVo = new BusinessGoodsCartVo();
			businessGoodsCartVo.setCart(cart);
			businessGoodsCartVo.setGoods(goods);;
			
			resList.add(businessGoodsCartVo);
		}
		return resList;

	}
	public List<ComboVO> findByUserIdAndComboId(Integer userId){
		String hql = "from ShoppingCart cart , Combo combo  where cart.comboId = combo.id and cart.clientUserId = " + userId;
		List<Object[]> list =commonDao.getEntitiesByPropertiesWithHql(hql, 0, 0);
		List<ComboVO> resList = new LinkedList<ComboVO>();
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			ShoppingCart cart = (ShoppingCart) obj[0];
			Combo combo = (Combo) obj[1];
			
			ComboVO comboVO = new ComboVO();
			comboVO.setCombo(combo);
			comboVO.setShoppingCart(cart);
			resList.add(comboVO);
		}
		return resList;

	}
	
	public List<BusinessGoodsCartVo> cookieToVo(String cookie){
		List<BusinessGoodsCartVo> resList = new LinkedList<BusinessGoodsCartVo>();
//		List<BusinessGoodsCartVo> resLists = new ArrayList<BusinessGoodsCartVo>();
//		Set<String> goodId = new HashSet<String>();
		JSONArray json = JSONArray.fromObject(cookie);
		if(json.size()>0){
		   for(int i=0;i<json.size();i++){
		    JSONObject job = json.getJSONObject(i);  // 
		    BusinessGoodsCartVo businessGoodsCartVo = new BusinessGoodsCartVo();
		    if(!(job.get("goodsId").toString()).equals("null"))
		    {
//		    	goodId.add(job.get("goodsId").toString());
		    	businessGoodsCartVo = addBgcv(job);
		    	resList.add(businessGoodsCartVo);
		    }
		  }
//		  Object[] goodIds = goodId.toArray();
//		  for (int i = 0; i < goodIds.length; i++) {
//			int goodsCount = 0;
//			Float totalPrice = 0F;
//			String cookieId  = "";
//			BusinessGoodsCartVo bgcv= new BusinessGoodsCartVo();
//			ShoppingCart sCart = new ShoppingCart();
//			BusinessGoods goods = new BusinessGoods();
//			for (int j = 0; j < resList.size(); j++) {
//				if(goodIds[i].toString().equals(resList.get(j).getCart().getGoodsId().toString()))
//				{
//					goodsCount+=resList.get(j).getCart().getGoodsCount();
//					
//					totalPrice=new BigDecimal(Float.toString(totalPrice)).add(new BigDecimal(Float.toString(resList.get(j).getCart().getTotalPrice()))).floatValue();
//					goods = resList.get(j).getGoods();
//					cookieId = resList.get(j).getCart().getCookieCartId();
//				}
//			}
//			sCart.setGoodsCount(goodsCount);
//			sCart.setTotalPrice(totalPrice);
//			sCart.setCookieCartId(cookieId);
//			bgcv.setCart(sCart);
//			bgcv.setGoods(goods);
//			resLists.add(bgcv);
//		}
		}
		return resList;

	}
	public List<ComboVO> cookieToComboVo(String cookie){
		List<ComboVO> resList = new LinkedList<ComboVO>();
//		List<ComboVO> resLists = new LinkedList<ComboVO>();
//		Set<String> comboId = new HashSet<String>();
		JSONArray json = JSONArray.fromObject(cookie );
		if(json.size()>0){
		   for(int i=0;i<json.size();i++){
		    JSONObject job = json.getJSONObject(i);  // 
		    ComboVO comboVO = new ComboVO();
		    if(!(job.get("comboId").toString()).equals("null"))
		    {
//		    	comboId.add(job.get("comboId").toString());
    			comboVO = addComboVo(job);
			    resList.add(comboVO);
		    }
		  }
//		  Object[] comboIds = comboId.toArray();
//		  for (int i = 0; i < comboIds.length; i++) {
//			    int goodsCount = 0;
//				Float totalPrice = 0F;
//				String cookieId  = "";
//				ComboVO cv= new ComboVO();
//				ShoppingCart sCart = new ShoppingCart();
//				Combo combo = new Combo();
//			for (int j = 0; j < resList.size(); j++) {
//				if(comboIds[i].toString().equals(resList.get(j).getCombo().getId().toString()))
//				{
//					goodsCount+=resList.get(j).getShoppingCart().getGoodsCount();
//					totalPrice=new BigDecimal(Float.toString(totalPrice)).add(new BigDecimal(Float.toString(resList.get(j).getShoppingCart().getTotalPrice()))).floatValue();
//					combo = resList.get(j).getCombo();
//					cookieId = resList.get(j).getShoppingCart().getCookieCartId();
//				}
//			}
//			sCart.setGoodsCount(goodsCount);
//			sCart.setCookieCartId(cookieId);
//			sCart.setTotalPrice(totalPrice);
//			cv.setShoppingCart(sCart);
//			cv.setCombo(combo);
//			resLists.add(cv);
//		}
//		  
//		   
		}
		return resList;

	}
	public BusinessGoodsCartVo addBgcv(JSONObject job)
	{
		BusinessGoodsCartVo businessGoodsCartVo = new BusinessGoodsCartVo();
		ShoppingCart shoppingCart = new ShoppingCart();
	    shoppingCart.setGoodsId(Integer.parseInt(job.get("goodsId").toString()));
	    shoppingCart.setGoodsCount(Integer.parseInt(job.get("goodsCount").toString()));
	    shoppingCart.setCookieCartId(job.get("cookieId").toString());
	    String hql = "from BusinessGoods good  where id= "+job.get("goodsId");
	    List<BusinessGoods> list =commonDao.getEntitiesByPropertiesWithHql(hql, 0, 0);
	    BusinessGoods businessGoods = list.get(0);
	    BigDecimal price = new BigDecimal(businessGoods.getGoodsOriginalPrice().toString());
	    BigDecimal totalprice = price.multiply(new BigDecimal(job.get("goodsCount").toString()));
	    shoppingCart.setTotalPrice(businessGoods.getGoodsOriginalPrice()*Integer.parseInt(job.get("goodsCount").toString()));
//	    shoppingCart.setTotalPrice(totalprice.floatValue());
	    businessGoodsCartVo.setCart(shoppingCart);
	    businessGoodsCartVo.setGoods(businessGoods);
	    return businessGoodsCartVo;
	}
	public ComboVO addComboVo(JSONObject job)
	{
		ComboVO comboVO = new ComboVO();
		ShoppingCart shoppingCart = new ShoppingCart();
	    shoppingCart.setComboId(Integer.parseInt(job.get("comboId").toString()));
	    shoppingCart.setGoodsCount(Integer.parseInt(job.get("goodsCount").toString()));
	    shoppingCart.setCookieCartId(job.get("cookieId").toString());
	    String hql = "from Combo  where id= "+job.get("comboId");
	    List<Combo> list =commonDao.getEntitiesByPropertiesWithHql(hql, 0, 0);
	    Combo combo = list.get(0);
	    BigDecimal price = new BigDecimal(combo.getComboPrice().toString());
	    BigDecimal totalprice = price.multiply(new BigDecimal(job.get("goodsCount").toString()));
	    shoppingCart.setTotalPrice(combo.getComboPrice()*Integer.parseInt(job.get("goodsCount").toString()));
//	    shoppingCart.setTotalPrice(totalprice.floatValue());
	    comboVO.setShoppingCart(shoppingCart);
	    comboVO.setCombo(combo);
	    return comboVO;
	}
	public List<ShoppingCart> findBygoodsCount(String goodsId,Integer userId){
		String hql = "from ShoppingCart cart  where cart.goodsId = '"+goodsId+"' and cart.clientUserId = " + userId;
		List<ShoppingCart> list =commonDao.getEntitiesByPropertiesWithHql(hql, 0, 0);
		return list;

	}
	public List<ShoppingCart> findBycomboCount(String comboId,Integer userId){
		String hql = "from ShoppingCart cart  where cart.comboId = '"+comboId+"' and cart.clientUserId = " + userId;
		List<ShoppingCart> list =commonDao.getEntitiesByPropertiesWithHql(hql, 0, 0);
		return list;

	}
	
	

	/**
	 * @param po
	 * @return int
	 */
	public int modify(ShoppingCart po) {
		ShoppingCart old = new ShoppingCart();
		old = findByid(po.getId().toString());
		if (po.getGoodsId() == null) {
			po.setGoodsId(old.getGoodsId());
		}
		if (po.getClientUserId() == null) {
			po.setClientUserId(old.getClientUserId());
		}
		if (po.getGoodsCount() == null) {
			po.setGoodsCount(old.getGoodsCount());
		}
		if (po.getCurrentPrice() == null) {
			po.setCurrentPrice(old.getCurrentPrice());
		}
		if (po.getCurrentPriceType() == null) {
			po.setCurrentPriceType(old.getCurrentPriceType());
		}
		if (po.getTotalPrice() == null) {
			po.setTotalPrice(old.getTotalPrice());
		}
		if (po.getOriginalPrice() == null) {
			po.setOriginalPrice(old.getOriginalPrice());
		}
		if (po.getOriginalTotalPrice() == null) {
			po.setOriginalTotalPrice(old.getOriginalTotalPrice());
		}
		if (po.getPrimaryConfigurationId() == null) {
			po.setPrimaryConfigurationId(old.getPrimaryConfigurationId());
		}
		if (StringHelper.isNull(po.getStaffId())) {
			po.setStaffId(old.getStaffId());
		}
		po.setShoppingCartTime(new Timestamp(System.currentTimeMillis()));
		this.dao.merge(po);
		return 0;
	}

	public void del(String id) {
		ShoppingCart po = this.dao.findById(Integer.parseInt(id));
		this.dao.delete(po);
	}

	public int removeShoppingCartId(String id) {
		return dao.removeShoppingCartId(Long.valueOf(id));
	}
	/**
	 * 删除购物车
	 */
	public boolean removeShoppingCartById(String id){
		String hql = "delete from ShoppingCart where id="+id;
		try {
			int executeUpdateOrDelete = commonDao.executeUpdateOrDelete(hql);
			if(executeUpdateOrDelete>0){
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * @param brandname
	 * @return int
	 */
	public int getCount(String clientUserId,String goodsId, String goodsCount, 
			String primaryConfigurationId, String staffId, String currentPriceType, 
			String shoppingCartStartTime, String shoppingCartEndTime) {
		String hql = "select count(*) from ShoppingCart a, Goods b where "
				+ " a.goodsId=b.id";
		if (!StringHelper.isNull(goodsId)) {
			hql = hql + " and a.goodsId = " + goodsId;
		}
		if (!StringHelper.isNull(clientUserId)) {
			hql = hql + " and a.clientUserId = " + clientUserId;
		}
		if (!StringHelper.isNull(goodsCount)) {
			hql = hql + " and a.goodsCount = " + goodsCount;
		}
		if (!StringHelper.isNull(currentPriceType)) {
			hql = hql + " and a.currentPriceType = " + currentPriceType;
		}
		if (!StringHelper.isNull(primaryConfigurationId)) {
			hql = hql + " and a.primaryConfigurationId = "
					+ primaryConfigurationId;
		}
		if (!StringHelper.isNull(staffId)) {
			hql = hql + " and a.staffId = '" + staffId + "'";
		}
		if (!StringHelper.isNull(shoppingCartStartTime)) {
			hql = hql + " and a.shoppingCartTime >='" + shoppingCartStartTime
					+ "'";
		}
		if (!StringHelper.isNull(shoppingCartEndTime)) {
			hql = hql + " and a.shoppingCartTime <='" + shoppingCartEndTime
					+ "'";
		}
		return this.commonDao.getNumberOfEntitiesWithHql(hql);
	}

	/**
	 * 
	 * @param firstletter
	 * @param curPage
	 * @param pageSize
	 * @param orderField
	 * @param direction
	 * @return List<GetOrgListVO>
	 */
	public List<OrderVO> listVO(String clientUserId,String goodsId, String goodsCount, 
			String primaryConfigurationId, String staffId, String currentPriceType, 
			String shoppingCartStartTime, String shoppingCartEndTime, 
			int curPage, int pageSize, String orderField, String direction) {
		String hql = "from ShoppingCart a, Goods b where "
				+ " a.goodsId=b.id";
		if (!StringHelper.isNull(goodsId)) {
			hql = hql + " and a.goodsId = " + goodsId;
		}
		if (!StringHelper.isNull(clientUserId)) {
			hql = hql + " and a.clientUserId = " + clientUserId;
		}
		if (!StringHelper.isNull(goodsCount)) {
			hql = hql + " and a.goodsCount = " + goodsCount;
		}
		if (!StringHelper.isNull(currentPriceType)) {
			hql = hql + " and a.currentPriceType = " + currentPriceType;
		}
		if (!StringHelper.isNull(primaryConfigurationId)) {
			hql = hql + " and a.primaryConfigurationId = "
					+ primaryConfigurationId;
		}
		if (!StringHelper.isNull(staffId)) {
			hql = hql + " and a.staffId = '" + staffId + "'";
		}
		if (!StringHelper.isNull(shoppingCartStartTime)) {
			hql = hql + " and a.shoppingCartTime >='" + shoppingCartStartTime
					+ "'";
		}
		if (!StringHelper.isNull(shoppingCartEndTime)) {
			hql = hql + " and a.shoppingCartTime <='" + shoppingCartEndTime
					+ "'";
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		} else {
			hql += " order by a.id";
		}
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage,
				pageSize); // .findByQuerySplitPage(hql,curPage,pageSize);
		List<OrderVO> resList = new ArrayList<OrderVO>();
		for (int i = 0; i < l.size(); i++) {
			Object[] obj = (Object[]) l.get(i);
			ShoppingCart shoppingCart = (ShoppingCart) obj[0];
			Goods goods = (Goods) obj[1];
			OrderVO vo = new OrderVO();
			// 订单商品对应表
			if (shoppingCart.getId() != null) {
				vo.setShoppingCartId(shoppingCart.getId()
						.toString());
			}
			if (shoppingCart.getClientUserId() != null) {
				vo.setShoppingCartClientUserId(shoppingCart
						.getClientUserId().toString());
			}
			if (shoppingCart.getStaffId() != null) {
				vo.setShoppingCartStaffId(shoppingCart.getStaffId()
						.toString());
			}
			if (shoppingCart.getGoodsId() != null) {
				vo.setShoppingCartGoodsId(shoppingCart.getGoodsId()
						.toString());
			}
			if (shoppingCart.getGoodsCount() != null) {
				vo.setShoppingCartGoodsCount(shoppingCart
						.getGoodsCount().toString());
			}
			if (shoppingCart.getCurrentPrice() != null) {
				vo.setShoppingCartCurrentPrice(shoppingCart
						.getCurrentPrice().toString());
			}
			if (shoppingCart.getCurrentPriceType() != null) {
				vo.setShoppingCartCurrentPriceType(shoppingCart
						.getCurrentPriceType().toString());
			}
			if (shoppingCart.getTotalPrice() != null) {
				vo.setShoppingCartTotalPrice(shoppingCart
						.getTotalPrice().toString());
			}
			if (shoppingCart.getOriginalPrice() != null) {
				vo.setShoppingCartOriginalPrice(shoppingCart
						.getOriginalPrice().toString());
			}
			if (shoppingCart.getOriginalTotalPrice() != null) {
				vo.setShoppingCartOriginalTotalPrice(shoppingCart
						.getOriginalTotalPrice().toString());
			}
			if (shoppingCart.getPrimaryConfigurationId() != null) {
				vo.setShoppingCartPrimaryConfigurationId(shoppingCart
						.getPrimaryConfigurationId().toString());
			}
			if (shoppingCart.getShoppingCartTime() != null) {
				vo.setShoppingCartTime(StringHelper.getSystime(
						ViewConstant.PAGE_DATE_FORMAT_SEC, shoppingCart
								.getShoppingCartTime().getTime()));
			}
			vo.setGoods(goods);
			resList.add(vo);
		}
		return resList;
	}

	/**
	 * 
	 * @param shoppingCartId
	 * @return int
	 */
	public int removeShoppingCartId(Integer shoppingCartId) {

		log.debug("removeShoppingCartId: " + shoppingCartId);
		try {
			String queryString = "delete from ShoppingCart as model where model.id = "
					+ shoppingCartId;
			return commonDao.executeUpdateOrDelete(queryString);
		} catch (RuntimeException re) {
			log.error("removeDishId failed", re);
			throw re;
		}
	}
	
	/**
	 * 
	 * @param clientUserId
	 * @return int
	 */
	public int removeClientUserId(Integer clientUserId) {

		log.debug("removeClientUserId: " + clientUserId);
		try {
			String queryString = "delete from ShoppingCart as model where model.clientUserId = "
					+ clientUserId;
			return commonDao.executeUpdateOrDelete(queryString);
		} catch (RuntimeException re) {
			log.error("removeDishId failed", re);
			throw re;
		}
	}

	
	/**
	 * 
	 * @param firstletter
	 * @param curPage
	 * @param pageSize
	 * @param orderField
	 * @param direction
	 * @return List<OrderVO>
	 */
	public List<ShoppingCart> webList(String clientUserId,String goodsId, String goodsCount, 
			String primaryConfigurationId, String staffId, String currentPriceType, 
			int curPage, int pageSize, String orderField, String direction) {
		String hql = "from ShoppingCart where 1=1";
		if (!StringHelper.isNull(goodsId)) {
			hql = hql + " and goodsId = " + goodsId;
		}
		if (!StringHelper.isNull(clientUserId)) {
			hql = hql + " and clientUserId = " + clientUserId;
		}
		if (!StringHelper.isNull(goodsCount)) {
			hql = hql + " and goodsCount = " + goodsCount;
		}
		if (!StringHelper.isNull(currentPriceType)) {
			hql = hql + " and currentPriceType = " + currentPriceType;
		}
		if (!StringHelper.isNull(primaryConfigurationId)) {
			hql = hql + " and primaryConfigurationId = "
					+ primaryConfigurationId;
		}
		if (!StringHelper.isNull(staffId)) {
			hql = hql + " and staffId = '" + staffId + "'";
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		} else {
			hql += " order by id";
		}
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage,
				pageSize); // .findByQuerySplitPage(hql,curPage,pageSize);
		List<ShoppingCart> resList = new ArrayList<ShoppingCart>();
		for (int i = 0; i < l.size(); i++) {
			ShoppingCart po = (ShoppingCart) l.get(i);
			resList.add(po);
		}
		return resList;
	}

	
	/**
	 * @return List<OrderGoodsRelation>
	 */
	public List<ShoppingCart> listAll() {
		return dao.findAll();
	}
	public Map<String, Integer> getCartMoney(String userId)
	{
		Float count = 0F;
		Integer number = 0;
		Map<String, Integer> count_number = new HashMap<String, Integer>();
		String hql = "from ShoppingCart where clientUserId = '"+userId+"'";
		List<ShoppingCart> list = commonDao.getEntitiesByPropertiesWithHql(hql,0,0);
		if(list.isEmpty())
		{
			count_number.put("0.00", number);
		}else
		{
			for (ShoppingCart shoppingCart : list) {
				count+=shoppingCart.getTotalPrice();
				number+=shoppingCart.getGoodsCount();
			}
			DecimalFormat df = new DecimalFormat("#.00");
			count_number.put(df.format(count).toString(), number);
		}
		
		return count_number;
	}
	public boolean modifyCount(String cartId,Integer goodsCount,Float totalPrice){
		String hql = "update ShoppingCart set goodsCount="+goodsCount+",totalPrice="+totalPrice+" where id="+cartId;
		try {
			commonDao.executeUpdateOrDelete(hql);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
