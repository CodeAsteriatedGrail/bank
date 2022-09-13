package com.bank.ccy.module.gatway.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SimpleCurrencyDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty(value = "code")
	private String code;

	@JsonProperty(value = "rate")
	private BigDecimal rate;
	
	@JsonProperty(value = "name")
	private String name;

}
