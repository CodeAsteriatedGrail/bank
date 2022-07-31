package com.example.demo.module.gatway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Result;
import com.example.demo.module.gatway.model.CurrencyPrice;
import com.example.demo.module.gatway.service.CoindeskService;

@RequestMapping("coindesk")
@RestController
public class CoindeskController {

	@Autowired
	CoindeskService service;

	/**
	 * call coindesk API原生
	 * 
	 * @return
	 */
	@RequestMapping("/currentprice")
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
     * @return
     */
    @RequestMapping("/currentprice/new")
    public String findForCovert(){
        return "";
    }
}
