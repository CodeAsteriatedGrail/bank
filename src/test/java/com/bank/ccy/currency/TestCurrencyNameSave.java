package com.bank.ccy.currency;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bank.ccy.model.Result;
import com.bank.ccy.module.currency.controller.CurrencyNameController;
import com.bank.ccy.module.currency.entity.CurrencyName;

@SpringBootTest
public class TestCurrencyNameSave {

	@Autowired
	CurrencyNameController ccyController;
	
	@Test
	public void usingController() {
		CurrencyName ccyName = new CurrencyName();
		ccyName.setChineseTraditional("美");
		ccyName.setCode("USE");
		Result res = ccyController.save(ccyName);
		// fail
		assertEquals(res.isSuccess(), true, "新增失敗: " + res.getMessage());

		// success
		System.out.println(res.getData().toString());
	}
	
	@Test
	public void bath() {
		List<CurrencyName> ccyNameList = Arrays.asList(
			new CurrencyName("美金", "ERR"), // ERR for double chineseTradition
			new CurrencyName("有幣", "USD")
		);
		
		List<String> errList = new ArrayList<>();
		// process
		for(CurrencyName ccyName:ccyNameList) {
			try {
				ccyController.save(ccyName);
			} catch (Exception e) {
				errList.add(ccyName.getCode() + e.getMessage());
			}
		}
		
		// res
		System.out.println(errList.toString());
	}
}
