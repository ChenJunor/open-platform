package com.chenj.weixin.open.platform.exception;

public class ResponeResult {
	public static final int CODE_SUCCESS = 1; 
	public static final int CODE_FAILED = 0; 

	private int code = CODE_FAILED;
	private String msg = null;
	private Object data = null;
	
	public ResponeResult() {
		super();
	}
	
	public ResponeResult(int code, String msg,Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	public ResponeResult(String msg) {
		super();
		this.msg = msg;
	}
	
	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponeResult [code=" + code + ", msg=" + msg + ", data="
				+ data + "]";
	}
	
}
