package com.example.demo.module.gatway.service;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.Result;
import com.example.demo.module.gatway.model.CurrencyPrice;
import com.google.gson.Gson;

@Service
public class CoindeskService {

	private RestTemplate restTemplate;
	private static final String CURRENT_PRICE_URL = "https://api.coindesk.com/v1/bpi/currentprice.json";
	
	public CurrencyPrice getCurrentPrice() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity;
		try {
			responseEntity = restTemplate.exchange(CURRENT_PRICE_URL, HttpMethod.GET, null, String.class);
		} catch (Exception e) {
			throw new Exception("coindesk API 異常，請洽管理員");
		}

		String jsonString = responseEntity.getBody();
		Gson g = new Gson();
		CurrencyPrice price = g.fromJson(jsonString, CurrencyPrice.class);

		// Fail
		if (200 != responseEntity.getStatusCodeValue()) {
			throw new Exception("coindesk API 異常，請洽管理員");
		}

		return price;
	}

}
