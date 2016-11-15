/**
 *2012-5-11 上午9:00:02
 */
package com.xnradmin.core.service.mall.order;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.StringHelper;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.dao.mall.order.SearchDAO;
import com.xnradmin.core.dao.mall.order.ShoppingCartDAO;
import com.xnradmin.constant.ViewConstant;
import com.xnradmin.po.mall.commodity.Goods;
import com.xnradmin.po.mall.order.Search;
import com.xnradmin.po.mall.order.ShoppingCart;
import com.xnradmin.vo.mall.OrderVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Service("SearchService")
public class SearchService {

	@Autowired
	private SearchDAO dao;

	@Autowired
	private CommonDAO commonDao;

	static Log log = LogFactory.getLog(SearchService.class);

	/**
	 * 
	 * @param po
	 * @return int
	 */
	public int save(Search po) {
		dao.save(po);
		return 0;
	}

	public Search findByid(String id) {
		return dao.findById(Long.valueOf(id));
	}

	/**
	 * @param po
	 * @return int
	 */
	public int modify(Search po) {
		Search old = new Search();
		old = findByid(po.getId().toString());
		if (po.getClientUserId() == null) {
			po.setClientUserId(old.getClientUserId());
		}
		if (po.getSearchValue() == null) {
			po.setClientUserId(old.getClientUserId());
		}
		this.dao.merge(po);
		return 0;
	}

	public void del(String id) {
		Search po = this.dao.findById(Long.valueOf(id));
		this.dao.delete(po);
	}

	public int removeSearchId(String id) {
		return dao.removeSearchId(Long.valueOf(id));
	}

	/**
	 * @param brandname
	 * @return int
	 */
	public int getCount(String clientUserId, String searchValue, 
			String searchStartTime, String searchEndTime) {
		String hql = "select count(*) from Search where 1=1";
		if (!StringHelper.isNull(clientUserId)) {
			hql = hql + " and clientUserId = " + clientUserId;
		}
		if (!StringHelper.isNull(searchValue)) {
			hql = hql + " and searchValue like '%" + searchValue + "%'";
		}
		if (!StringHelper.isNull(searchStartTime)) {
			hql = hql + " and a.searchTime >='" + searchStartTime
					+ "'";
		}
		if (!StringHelper.isNull(searchEndTime)) {
			hql = hql + " and a.searchTime <='" + searchEndTime
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
	public List<OrderVO> listVO(String clientUserId, String searchValue, 
			String searchStartTime, String searchEndTime, 
			int curPage, int pageSize, String orderField, String direction) {
		String hql = "from Search where 1=1";
		if (!StringHelper.isNull(clientUserId)) {
			hql = hql + " and clientUserId = " + clientUserId;
		}
		if (!StringHelper.isNull(searchValue)) {
			hql = hql + " and searchValue like '%" + searchValue + "%'";
		}
		if (!StringHelper.isNull(searchStartTime)) {
			hql = hql + " and a.searchTime >='" + searchStartTime
					+ "'";
		}
		if (!StringHelper.isNull(searchEndTime)) {
			hql = hql + " and a.searchTime <='" + searchEndTime
					+ "'";
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		} else {
			hql += " order by id";
		}
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage,
				pageSize); // .findByQuerySplitPage(hql,curPage,pageSize);
		List<OrderVO> resList = new ArrayList<OrderVO>();
		for (int i = 0; i < l.size(); i++) {
			Search search = (Search) l.get(i);
			OrderVO vo = new OrderVO();
			if (search.getId() != null) {
				vo.setSearchId(search.getId().toString());
			}
			if (search.getClientUserId() != null) {
				vo.setSearchClientUserInfoId(search
						.getClientUserId().toString());
			}
			if (search.getSearchValue() != null) {
				vo.setSearchValue(search.getSearchValue());
			}
			if (search.getSearchTime() != null) {
				vo.setShoppingCartTime(StringHelper.getSystime(
						ViewConstant.PAGE_DATE_FORMAT_SEC, search.getSearchTime().getTime()));
			}
			resList.add(vo);
		}
		return resList;
	}

	/**
	 * 
	 * @param shoppingCartId
	 * @return int
	 */
	public int removeSearchId(Long searchId) {

		log.debug("removeSearchId: " + searchId);
		try {
			String queryString = "delete from Search as model where model.id = "
					+ searchId;
			return commonDao.executeUpdateOrDelete(queryString);
		} catch (RuntimeException re) {
			log.error("removeSearchId failed", re);
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
			String queryString = "delete from Search as model where model.clientUserId = "
					+ clientUserId;
			return commonDao.executeUpdateOrDelete(queryString);
		} catch (RuntimeException re) {
			log.error("removeClientUserId failed", re);
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
	public List<Search> webList(String clientUserId, String searchValue, 
			String searchStartTime, String searchEndTime, 
			int curPage, int pageSize, String orderField, String direction) {
		String hql = "from ShoppingCart where 1=1";
		if (!StringHelper.isNull(clientUserId)) {
			hql = hql + " and clientUserId = " + clientUserId;
		}
		if (!StringHelper.isNull(searchValue)) {
			hql = hql + " and searchValue like '%" + searchValue + "%'";
		}
		if (!StringHelper.isNull(searchStartTime)) {
			hql = hql + " and a.searchTime >='" + searchStartTime
					+ "'";
		}
		if (!StringHelper.isNull(searchEndTime)) {
			hql = hql + " and a.searchTime <='" + searchEndTime
					+ "'";
		}
		if (!StringHelper.isNull(orderField) && !StringHelper.isNull(direction)) {
			hql = hql + " order by " + orderField + " " + direction;
		} else {
			hql += " order by id";
		}
		List l = commonDao.getEntitiesByPropertiesWithHql(hql, curPage,
				pageSize); // .findByQuerySplitPage(hql,curPage,pageSize);
		List<Search> resList = new ArrayList<Search>();
		for (int i = 0; i < l.size(); i++) {
			Search po = (Search) l.get(i);
			resList.add(po);
		}
		return resList;
	}

	
	/**
	 * @return List<OrderGoodsRelation>
	 */
	public List<Search> listAll() {
		return dao.findAll();
	}

}
