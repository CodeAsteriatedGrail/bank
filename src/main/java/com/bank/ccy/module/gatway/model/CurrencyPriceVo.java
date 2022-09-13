package com.bank.ccy.module.gatway.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class CurrencyPriceVo implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private LocalDateTime updateTime;

	private Map<String, SimpleCurrencyDetail> currencyMap = new HashMap<>();

	/**
	 * for ISO DATE String
	 * 
	 * @param cyyDetail
	 */
	public void setUpdateDate(String date) throws DateTimeParseException {
		this.setUpdateDate(LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME));
	}

	public void setUpdateDate(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	public Map<String, SimpleCurrencyDetail> getCyyDetail() {
		Map<String, SimpleCurrencyDetail> copy = new HashMap<>(currencyMap);
		return copy;
	}

	public void setCyyDetail(Map<String, SimpleCurrencyDetail> cyyDetail) {
		this.currencyMap = cyyDetail;
	}

	public Map<String, SimpleCurrencyDetail> getCurrencyMap() {
		Map<String, SimpleCurrencyDetail> copy = new HashMap<>(currencyMap);
		return copy;
	}
}
