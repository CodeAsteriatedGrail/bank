package com.bank.ccy.module.gatway.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.ccy.model.Result;
import com.bank.ccy.module.currency.dao.CurrencyNameDao;
import com.bank.ccy.module.currency.entity.CurrencyName;
import com.bank.ccy.module.gatway.model.CurrencyDetail;
import com.bank.ccy.module.gatway.model.CurrencyPrice;
import com.bank.ccy.module.gatway.model.CurrencyPriceVo;
import com.bank.ccy.module.gatway.model.SimpleCurrencyDetail;
import com.bank.ccy.module.gatway.service.CoindeskService;

@RequestMapping("coindesk")
@RestController
public class CoindeskController {

	@Autowired
	CoindeskService service;

	@Autowired
	CurrencyNameDao dao;

	/**
	 * call coindesk API原生
	 * 
	 * @return
	 */
	@GetMapping(path="/currentprice", name="API-coindesk-currentprice")
	public Result find() {
		CurrencyPrice price;
		try {
			price = service.getCurrentPrice();
		} catch (Exception e) {
			return new Result("", false, "coindesk API 異常，請洽管理員");
		}

		// success
		return new Result(price, true, "");
	}

	/**
	 * TODO: 呼叫 coindesk 的 API，並進行資料轉換
	 * 
	 * @return
	 */
	@RequestMapping("/currentprice/vo")
	public Result findForCovert() {
		// get coindesk data
		CurrencyPrice price;
		try {
			price = service.getCurrentPrice();
		} catch (Exception e) {
			return new Result("", false, "coindesk API 異常，請洽管理員");
		}
		
		// covert vo
		CurrencyPriceVo vo = new CurrencyPriceVo();
		vo.setUpdateDate(price.getTime().get("updatedISO"));

		Map<String, SimpleCurrencyDetail> simpleMap = vo.getCyyDetail();
		Map<String, CurrencyDetail> dataMap = price.getBpi();
		for (String key : dataMap.keySet()) {
			CurrencyDetail dtl = dataMap.get(key);
			CurrencyName cyyName = dao.findByCode(dtl.getCode());
			String name = Optional.ofNullable(cyyName)
					.map(CurrencyName::getChineseTraditional)
					.orElse("尚未建檔");
			
			SimpleCurrencyDetail simpleCurrencyDetail = new SimpleCurrencyDetail();
			simpleCurrencyDetail.setCode(dtl.getCode());
			simpleCurrencyDetail.setRate(dtl.getRate_float());
			simpleCurrencyDetail.setName(name);

			simpleMap.put(key, simpleCurrencyDetail);
		}
		vo.setCyyDetail(simpleMap);
		
		// result
		return new Result(vo, true, "");
	}
}
