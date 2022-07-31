package com.example.demo.module.gatway.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.ToString;

@ToString
public class CurrencyPrice {

	@JsonProperty(value = "time")
	private Map<String, String> time;

	@JsonProperty(value = "bpi")
	private Map<String, CurrencyDetail> bpi;

	@JsonProperty(value = "chartName")
	private String chartName;

	@JsonProperty(value = "disclaimers")
	private String disclaimer;

	public Map<String, String> getTime() {
		Map<String, String> copy = new HashMap<>(time);
		return copy;
	}

	public void setTime(Map<String, String> time) {
		this.time = time;
	}

	public String getChartName() {
		return chartName;
	}

	public void setChartName(String chartName) {
		this.chartName = chartName;
	}

	public String getDisclaimer() {
		return disclaimer;
	}

	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}

	public Map<String, CurrencyDetail> getBpi() {
		Map<String, CurrencyDetail> copy = new HashMap<>(bpi);
		return copy;
	}

	public void setBpi(Map<String, CurrencyDetail> bpi) {
		this.bpi = bpi;
	}

}
