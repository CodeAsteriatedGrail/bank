package com.example.demo.module.currency.controller;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Result;
import com.example.demo.module.currency.dao.CurrencyNameDao;
import com.example.demo.module.currency.entity.CurrencyName;

@RequestMapping("currencyName")
@RestController
public class CurrencyNameController {
	@Autowired
	private CurrencyNameDao ccyDao;
	
	@PostMapping(path = "", name = "API-CurrencyName-CREATE")
	public Result save(@RequestBody @NonNull CurrencyName dataCcyName) {
		// check data
		String chinese = dataCcyName.getChineseTraditional();
		String code = dataCcyName.getCode();
		boolean isAnyBlank = StringUtils.isAnyBlank(chinese, code);
		if (isAnyBlank) {
			return new Result("", false, "資料不足");
		}

		// process
		dataCcyName.setId(null);
		CurrencyName ccyName;
		try {
			ccyName = ccyDao.save(dataCcyName);
		} catch (DataIntegrityViolationException e) {
			boolean isConstraint = e.getCause().getClass().equals(ConstraintViolationException.class);
			String err = isConstraint ? "資料重複" : "其他異常";
			return new Result("", false, err);
		} catch (Exception e) {
			// CURRENCY_NAME
			return new Result("", false, "幣別名存檔失敗，請洽管理員");
		}

		Result res = new Result(ccyName, true, "");
		return res;
	}

	@PutMapping(path = "", name = "API-CurrencyName-UPDATE")
	public Result update(@RequestBody @NonNull CurrencyName dataCcyName) {
		// check data
		Long id = dataCcyName.getId();
		if(null == id) {
			return new Result("", false, "資料不足");
		}else if(false == ccyDao.existsById(id)) {
			return new Result("", false, "不存在");
		}
				
		String chinese = dataCcyName.getChineseTraditional();
		String code = dataCcyName.getCode();
		boolean isAnyBlank = StringUtils.isAnyBlank(chinese, code);
		if (isAnyBlank) {
			return new Result("", false, "資料不足");
		}
		
		// process	
		CurrencyName ccyName;
		try {
			ccyName = ccyDao.save(dataCcyName);
		} catch (DataIntegrityViolationException e) {
			boolean isConstraint = e.getCause().getClass().equals(ConstraintViolationException.class);
			String err = isConstraint ? "資料重複" : "其他異常";
			return new Result("", false, err);
		} catch (Exception e) {
			// CURRENCY_NAME
			return new Result("", false, "幣別名更新失敗，請洽管理員");
		}

		Result res = new Result(ccyName, true, "");
		return res;
	}

	 // TODO: get, , delete
}
