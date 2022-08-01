package com.example.demo.currency;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.module.currency.dao.CurrencyNameDao;
import com.example.demo.module.currency.entity.CurrencyName;

@SpringBootTest
public class TestCurrency {
	@Autowired
	CurrencyNameDao dao;

	
	// TODO: CRUD currency API
	@Test
	void testInsert() {
		long oldTotal = dao.count();

		// save
		CurrencyName currency = new CurrencyName();
		currency.setCode("UED");
		currency.setChineseTraditional("大富翁幣");
		currency = dao.save(currency);

		// result
		long newTotal = dao.count();
		System.out.printf("old total:%d, new total:%d, currency:%s\n", oldTotal, newTotal, currency.toString());
	}

	@Test
	void testFind() {
		CurrencyName c = dao.getReferenceById(1L);
		System.out.println(c);
	}
}
