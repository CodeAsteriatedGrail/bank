package com.example.demo.module.gatway.service;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.module.gatway.model.CurrencyPrice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CoindeskService {

	private RestTemplate restTemplate = new RestTemplate();
	private static final String CURRENT_PRICE_URL = "https://api.coindesk.com/v1/bpi/currentprice.json";

	public CurrencyPrice getCurrentPrice() throws Exception {
		ResponseEntity<String> responseEntity;
		try {
			responseEntity = restTemplate.exchange(CURRENT_PRICE_URL, HttpMethod.GET, null, String.class);
		} catch (Exception e) {
			throw new Exception("coindesk API 異常，請洽管理員");
		}

		// Fail
		if (200 != responseEntity.getStatusCodeValue()) {
			throw new Exception("coindesk API 異常，請洽管理員");
		}

		String jsonString = responseEntity.getBody();

		ObjectMapper mapper = new ObjectMapper();

		CurrencyPrice price;
		try {
			price = mapper.readValue(jsonString, CurrencyPrice.class);
		} catch (JsonProcessingException e) {
			System.err.println("資料格式異常");
			throw new Exception("coindesk API 異常，資料格式異常");
		}

		return price;
	}

}
