package com.chenj.weixin.open.platform.entity.respone;

public class WeiXinErrMsg {
	private int errCode = -1;
	
	private String errMsg = null;

	public WeiXinErrMsg() {
		super();
	}

	public WeiXinErrMsg(int errCode, String errMsg) {
		super();
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	@Override
	public String toString() {
		return "WeiXinErrMsg [errCode=" + errCode + ", errMsg=" + errMsg + "]";
	}
	
}
