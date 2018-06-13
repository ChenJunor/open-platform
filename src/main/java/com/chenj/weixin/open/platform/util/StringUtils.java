package com.chenj.weixin.open.platform.util;

import java.net.URLEncoder;

import com.chenj.weixin.open.platform.constant.BasicConst;

public class StringUtils {
	
	public static boolean isBlank(String str){
		if(null == str || "".equals(str.trim())){
			return true;
		}
		
		return false;
	}
	
	public static boolean isNotBlank(String str){
		return !isBlank(str);
	}
	
	@SuppressWarnings("deprecation")
	public static String wxToWebUrl(String simpleUrl, String appId, String state) {
		String redirectUrl = BasicConst.PUBLIC_URL_PREFIX + simpleUrl;
		return "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
				+ appId + "&redirect_uri=" + URLEncoder.encode(redirectUrl)
				+ "&response_type=code&scope=snsapi_base&state="
				+ state+"#wechat_redirect";
	}

}
