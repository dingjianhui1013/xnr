/**
 *2014年9月13日 下午9:56:28
 */
package com.xnradmin.client.test;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;

import com.xnradmin.client.action.wx.WXConnectAction;
import com.xnradmin.core.service.common.status.StatusService;
import com.xnradmin.core.service.mall.clientUser.ClientUserInfoService;
import com.xnradmin.core.service.mall.clientUser.ClientUserRegionInfoService;
import com.xnradmin.core.service.mall.order.ShoppingCartService;
import com.xnradmin.core.service.mall.region.AreaService;
import com.xnradmin.core.service.mall.region.CityService;
import com.xnradmin.core.service.mall.region.ProvinceService;
import com.xnradmin.core.service.mall.seting.LogisticsCompanyService;
import com.xnradmin.core.util.SpringBase;
import com.xnradmin.po.client.ClientUserInfo;
import com.xnradmin.po.client.ClientUserRegionInfo;
import com.xnradmin.po.common.status.Status;
import com.xnradmin.po.mall.order.OrderRecord;
import com.xnradmin.po.mall.region.Area;
import com.xnradmin.po.mall.region.City;
import com.xnradmin.po.mall.region.Province;
import com.xnradmin.po.mall.seting.LogisticsCompany;
import com.xnradmin.vo.mall.OrderVO;

/**
 * @author: liubin
 *
 */
public class TestProvince {
	private static Logger log = Logger.getLogger(TestProvince.class);
	private static void testProvinceService() {

		ProvinceService provinceService = (ProvinceService) SpringBase.getCtx()
				.getBean("ProvinceService");
		CityService cityService = (CityService) SpringBase.getCtx().getBean(
				"CityService");
		AreaService areaService = (AreaService) SpringBase.getCtx().getBean(
				"AreaService");
		long a = System.currentTimeMillis();
		List<Province> provinceList = provinceService.listPO(null, null, 0, 0,
				"id", "asc");

		List<City> cityList = cityService.listPO(null, "1", null, 0, 0, "id",
				"asc");
		List<Area> areaList = areaService.listPO(null, null, "1", null, 0, 0,
				"id", "asc");
		log.debug("provinceList : " + provinceList.size());
		log.debug("cityList : " + cityList.size());
		log.debug("areaList : " + areaList.size());
		long b = System.currentTimeMillis();
		long res = (b - a);
		log.debug(res + "ms");
	}

	private static void testWebList() {
		String uid = "ohmUCj1104_W4N7h3c1EcJ3Qlsf0";
		ClientUserInfoService clientUserInfoService = (ClientUserInfoService) SpringBase
				.getCtx().getBean("ClientUserInfoService");
		ShoppingCartService shoppingCartService = (ShoppingCartService) SpringBase
				.getCtx().getBean("ShoppingCartService");

		ClientUserRegionInfoService clientUserRegionInfoService = (ClientUserRegionInfoService) SpringBase
				.getCtx().getBean("ClientUserRegionInfoService");
		StatusService statusService = (StatusService) SpringBase.getCtx()
				.getBean("StatusService");
		LogisticsCompanyService logisticsCompanyService = (LogisticsCompanyService) SpringBase
				.getCtx().getBean("LogisticsCompanyService");

		long clientUserInfoS = System.currentTimeMillis();
		ClientUserInfo clientUserInfo = clientUserInfoService
				.findByWxOpenid(uid);
		long clientUserInfoE = System.currentTimeMillis();
		log.debug("clientUserInfo time : "
				+ (clientUserInfoE - clientUserInfoS) + "ms");

		if (clientUserInfo != null && clientUserInfo.getId() != null) {
			long forS = System.currentTimeMillis();
			List<OrderVO> voList = shoppingCartService.listVO(clientUserInfo
					.getId().toString(), null, null, null, null, null, null,
					null, 0, 0, "a.id", "asc");
			Float totalPrice = 0f;
			for (int i = 0; voList.size() > i; i++) {
				BigDecimal a1 = new BigDecimal(totalPrice);
				BigDecimal a2 = new BigDecimal(voList.get(i)
						.getShoppingCartTotalPrice());
				totalPrice = a1.add(a2).floatValue();
			}
			long forE = System.currentTimeMillis();
			log.debug("for loop time : " + (forE - forS) + "ms");
			// 取得用户所有派送地址
			long regionInfoListS = System.currentTimeMillis();
			List<ClientUserRegionInfo> clientUserRegionInfoList = clientUserRegionInfoService
					.findByProperty("clientUserInfoId", clientUserInfo.getId());
			long regionInfoListE = System.currentTimeMillis();
			log.debug("regionInfoList time : "
					+ (regionInfoListE - regionInfoListS) + "ms");

			long paymentProviderListS = System.currentTimeMillis();
			List<Status> paymentProviderList = statusService.find(
					OrderRecord.class, "paymentProvider");
			long paymentProviderListE = System.currentTimeMillis();
			log.debug("paymentProviderList time : "
					+ (paymentProviderListE - paymentProviderListS) + "ms");

			long logisticsCompanyListS = System.currentTimeMillis();
			List<LogisticsCompany> logisticsCompanyList = logisticsCompanyService
					.listAll();
			long logisticsCompanyListE = System.currentTimeMillis();
			log.debug("logisticsCompanyList time : "
					+ (logisticsCompanyListE - logisticsCompanyListS) + "ms");
		}
	}

	public static void main(String[] args) throws Exception {
		log.debug("----- start ------");
		testWebList();
		log.debug("----- end ------");
	}
}
