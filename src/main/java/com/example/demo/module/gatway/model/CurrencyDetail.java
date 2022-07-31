package com.example.demo.module.gatway.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.ToString;

@ToString
public class CurrencyDetail {

	@JsonProperty(value = "code")
	private String code;

	@JsonProperty(value = "symbol")
	private String symbol;

	@JsonProperty(value = "rate")
	private String rate;

	@JsonProperty(value = "description")
	private String description;

	@JsonProperty(value = "rate_float")
	private BigDecimal rate_float;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getRate_float() {
		return rate_float;
	}

	public void setRate_float(BigDecimal rate_float) {
		this.rate_float = rate_float;
	}

}
