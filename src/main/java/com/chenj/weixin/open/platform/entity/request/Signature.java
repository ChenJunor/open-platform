package com.chenj.weixin.open.platform.entity.request;

/**
 * 微信加密签名<br>
 * signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。 
 * @author think
 *
 */
public class Signature {
	private String signature = null;
	
	private String timestamp = null;
	
	/**
	 * 随机数 
	 */
	private String nonce = null;
	
	/**
	 * 随机字符串  
	 */
	private String echostr = null;

	public Signature() {
		super();
	}

	public Signature(String signature, String timestamp, String nonce,
			String echostr) {
		super();
		this.signature = signature;
		this.timestamp = timestamp;
		this.nonce = nonce;
		this.echostr = echostr;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getEchostr() {
		return echostr;
	}

	public void setEchostr(String echostr) {
		this.echostr = echostr;
	}

	@Override
	public String toString() {
		return "Signature [signature=" + signature + ", timestamp=" + timestamp
				+ ", nonce=" + nonce + ", echostr=" + echostr + "]";
	}
	
}
