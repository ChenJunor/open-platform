package com.chenj.weixin.open.platform.entity.respone;

public class AccessToken {
	/**
	 * 获取到的凭证
	 */
	private String accessToken = null;
	
	/**
	 * 凭证有效时间，单位：秒 
	 */
	private long  expiresIn = -1;

	public AccessToken() {
		super();
	}

	public AccessToken(String accessToken, long expiresIn) {
		super();
		this.accessToken = accessToken;
		this.expiresIn = expiresIn;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(long expiresIn) {
		this.expiresIn = expiresIn;
	}

}
