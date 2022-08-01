package com.example.demo.currency;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.Result;
import com.google.gson.Gson;

public class Test04CurrencyNameDeleteByAPi {
	private static String URL = "http://127.0.0.1:8080/currencyName/%d";

	public static void main(String[] args) {
		// entity
		String sendURL = String.format(URL, 1L);

		// restTemplate
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = null;
		try {
			responseEntity = restTemplate.exchange(sendURL, HttpMethod.DELETE, HttpEntity.EMPTY, String.class);
		} catch (Exception e) {
			Assert.state(false, "連線失敗");
		}

		// check statusCode
		Assert.isTrue(200 == responseEntity.getStatusCodeValue(), "API呼叫失敗");
		String jsonString = responseEntity.getBody();
		Gson g = new Gson();
		Result result = g.fromJson(jsonString, Result.class);

		// fail
		Assert.isTrue(result.isSuccess(), "刪除失敗: " + result.getMessage());
		// success
		System.out.println(result.getData().toString());
	}

}
