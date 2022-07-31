package com.example.demo.gatway;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import com.example.demo.module.gatway.model.CurrencyPrice;
import com.google.gson.Gson;

@SpringBootTest
public class TestCoindesk {
	private String createPersonUrl = "https://api.coindesk.com/v1/bpi/currentprice.json";

	@Test
	void testCallCoindeskApi() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.exchange(createPersonUrl, HttpMethod.GET, null,
				String.class);
		String jsonString = responseEntity.getBody();
		Gson g = new Gson();
		CurrencyPrice price = g.fromJson(jsonString, CurrencyPrice.class);
		Assert.isTrue(200 == responseEntity.getStatusCodeValue(), "讀取失敗");
		System.out.println(price.toString());

	}
}
