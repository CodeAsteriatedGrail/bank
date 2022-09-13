package com.bank.ccy.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CommonException extends RuntimeException {
	private String message;
}
