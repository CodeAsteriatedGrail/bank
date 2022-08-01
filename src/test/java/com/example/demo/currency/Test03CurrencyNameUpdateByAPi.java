package com.example.demo.currency;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.Result;
import com.example.demo.module.currency.entity.CurrencyName;
import com.google.gson.Gson;

public class Test03CurrencyNameUpdateByAPi {
	private static String createPersonUrl = "http://127.0.0.1:8080/currencyName";

	public static void main(String[] args) {
		// entity
		CurrencyName ccyName = new CurrencyName();
		ccyName.setId(1L);
		ccyName.setChineseTraditional("大富翁幣");
		ccyName.setCode("USE");
		HttpEntity<CurrencyName> entity = new HttpEntity<>(ccyName);

		// restTemplate
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = null;
		try {
			responseEntity = restTemplate.exchange(createPersonUrl, HttpMethod.PUT, entity, String.class);
		} catch (Exception e) {
			Assert.state(false, "連線失敗");
		}
		
		// check statusCode
		Assert.isTrue(200 == responseEntity.getStatusCodeValue(), "API呼叫失敗");
		String jsonString = responseEntity.getBody();
		Gson g = new Gson();
		Result result = g.fromJson(jsonString, Result.class);

		// fail
		Assert.isTrue(result.isSuccess(), "更新失敗: " + result.getMessage());
		// success
		System.out.println(result.getData().toString());
	}

}
