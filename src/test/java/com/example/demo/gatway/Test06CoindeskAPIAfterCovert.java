package com.example.demo.gatway;

import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import com.example.demo.module.gatway.model.CurrencyPriceVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class Test06CoindeskAPIAfterCovert {

	private static String URL = "http://127.0.0.1:8080/coindesk/currentprice/vo";

	public static void main(String[] args) {
		// restTemplate
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = null;
		try {
			responseEntity = restTemplate.getForEntity(URL, String.class);
		} catch (Exception e) {
			Assert.state(false, "連線失敗");
		}

		// check statusCode
		Assertions.assertNotEquals(responseEntity.getStatusCodeValue(), HttpStatus.OK, "API呼叫失敗");
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
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
			CurrencyPriceVo ccyPriceVo = mapper.readValue(root.path("data").toString(), CurrencyPriceVo.class);
			System.out.println(root.path("data"));
			System.out.println(ccyPriceVo.toString());
		} catch (JsonProcessingException e) {
			Assert.state(false, "資料格式異常");
		}
	}
}
