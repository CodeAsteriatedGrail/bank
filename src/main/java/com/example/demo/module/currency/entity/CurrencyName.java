package com.example.demo.module.currency.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
@Table(name = "CURRENCY_NAME")
@Entity
public class CurrencyName implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, insertable = false, updatable = false)
	Long id;

	@Column(nullable = false, length = 10, unique = true)
	String chineseTraditional;

	/**
	 * ISO 4217
	 */
	@Column(nullable = false, length = 3, unique = true)
	String code;

	public CurrencyName(String chineseTraditional, String code) {
		super();
		this.setChineseTraditional(chineseTraditional);
		this.setCode(code);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setChineseTraditional(String chineseTraditional) {
		if (StringUtils.isEmpty(chineseTraditional) || !chineseTraditional.matches("[\\u4E00-\\u9FA5]{1,10}")) {
			throw new IllegalArgumentException("幣別中文名須為繁體中文1~10字");
		}
		this.chineseTraditional = chineseTraditional;
	}

	public void setCode(String code) {
		if (StringUtils.isEmpty(code) || !code.matches("[A-Z]{3}")) {
			throw new IllegalArgumentException("幣別代碼不符ISO 4217");
		}
		this.code = code;
	}
}
