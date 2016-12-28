/**
 *2012-5-11 上午9:00:02
 */
package com.xnradmin.core.service.mall.commodity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntinker.util.StringHelper;
import com.xnradmin.constant.ViewConstant;
import com.xnradmin.core.dao.CommonDAO;
import com.xnradmin.core.dao.mall.commodity.GoodsAllocationShowDAO;
import com.xnradmin.core.service.StaffService;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.po.CommonStaff;
import com.xnradmin.po.dishes.Dish;
import com.xnradmin.po.mall.commodity.Goods;
import com.xnradmin.po.mall.commodity.GoodsAllocationShow;
import com.xnradmin.po.mall.commodity.GoodsDishRelation;
import com.xnradmin.vo.mall.CommodityVO;

/**
 * @autohr: xiang_liu
 * 
 */
@Service("GoodsAllocationShowService")
public class GoodsAllocationShowService {

	@Autowired
	private GoodsAllocationShowDAO dao;
	
	@Autowired
    private CommonDAO commonDao;

	@Autowired
	private StaffService staffService;
	
	@Autowired
	private StatusService statusService;
	
	static Log log = LogFactory.getLog(GoodsAllocationShowService.class);

	/**
	 * 
	 * @param po
	 * @return int
	 */
	public int save(GoodsAllocationShow po) {
		if (this.dao.findById(Long.valueOf(po.getId()))!=null) {
			return 1;
		}
		dao.save(po);
		return 0;
	}

	public GoodsAllocationShow findByid(String id) {
		return dao.findById(Long.valueOf(id));
	}
	
	public List<GoodsAllocationShow> findByGoodsid(String goodsId) {
		List<GoodsAllocationShow> list = this.dao.findByOlay(goodsId, "");
		for(GoodsAllocationShow goodsAllocationShow:list){
			goodsAllocationShow.setStartString(goodsAllocationShow.getStartTime().toString().substring(0,10));
			goodsAllocationShow.setEndString(goodsAllocationShow.getEndTime().toString().substring(0,10));
		}
		return list;
	}
	
	public GoodsAllocationShow findByGoodsidToday(String goodsId) {
		List<GoodsAllocationShow> list = this.dao.findByOlayToday(goodsId, "");
		if(list!=null&&list.size()>0){
			for(GoodsAllocationShow goodsAllocationShow:list){
				goodsAllocationShow.setStartString(goodsAllocationShow.getStartTime().toString().substring(0,10));
				goodsAllocationShow.setEndString(goodsAllocationShow.getEndTime().toString().substring(0,10));
			}
			return list.get(0);
		}else{
			return null;
		}
	}

	/**	  
	 * @param po
	 * @return int
	 */
	public int modify(List<GoodsAllocationShow> newlist,Integer goodsId) {
		if(goodsId!=null&&newlist.size()>0){
			//删除以前所有的
			List<GoodsAllocationShow> list= this.dao.findByOlay(goodsId.toString(), "");
			this.dao.deleteBach(list);
			//添加
			this.dao.saveBach(newlist);
			return 1;
		}
		return 0;
	}

	public void del(String goodsId){
		if(goodsId!=null){
			List<GoodsAllocationShow> list = this.dao.findByOlay(goodsId, "");
			if(list!=null){
				for(GoodsAllocationShow goodsAllocationShow:list){
					this.dao.delete(goodsAllocationShow);
				}				
			}
		}
    }
	
	public int removeGoodsAllocationShowId(String id) {
		return dao.removeGoodsAllocationShowId(Long.valueOf(id));
	}
	
	/**
	 * @return List<PayUpmp>
	 */
	public List<GoodsDishRelation> listAll() {
		return dao.findAll();
	}

}
