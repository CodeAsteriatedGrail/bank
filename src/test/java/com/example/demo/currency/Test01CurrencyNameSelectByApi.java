package com.example.demo.currency;

import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import com.example.demo.module.currency.entity.CurrencyName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test01CurrencyNameSelectByApi {
	private static String URL = "http://127.0.0.1:8080/currencyName/%d";

	public static void main(String[] args) {
		String sendUrl = String.format(URL, 1L);
		// restTemplate
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = null;
		try {
			responseEntity = restTemplate.getForEntity(sendUrl, String.class);
		} catch (Exception e) {
			Assert.state(false, "連線失敗");
		}

		// check statusCode
		Assertions.assertNotEquals(responseEntity.getStatusCodeValue(), HttpStatus.OK, "API呼叫失敗");
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root;
		try {
			root = mapper.readTree(responseEntity.getBody());
		} catch (JsonProcessingException e) {
			System.err.println("資料格式異常");
			return;
		}

		// covert response
		boolean isSuccess = root.path("isSuccess").asBoolean();
		String message = root.path("messsage").asText();
		Assertions.assertTrue(isSuccess, "查詢失敗: " + message);

		// covert data
		try {
			CurrencyName ccyName = mapper.readValue(root.path("data").toString(), CurrencyName.class);
			System.out.println(ccyName.toString());
		} catch (JsonProcessingException e) {
			System.err.println("資料格式異常");
		}
	}
}
