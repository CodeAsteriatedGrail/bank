package com.bank.ccy.model;

public class Result {

	Object data;
	boolean isSuccess;
	String message;

	public Result(Object data, boolean isSuccess, String message) {
		super();
		this.data = data;
		this.isSuccess = isSuccess;
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public boolean isSuccess() {
		return isSuccess;
	}
	
	/**
	 * for use gson library avoid bug
	 * @return
	 */
	public boolean getIsSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
