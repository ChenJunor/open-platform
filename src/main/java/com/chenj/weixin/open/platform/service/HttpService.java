package com.chenj.weixin.open.platform.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chenj.weixin.open.platform.constant.BasicConst;
import com.chenj.weixin.open.platform.constant.WeiXinConst;
import com.chenj.weixin.open.platform.entity.respone.WeiXinErrMsg;

public class HttpService {
    private static final Logger logger = LoggerFactory.getLogger(HttpService.class);
	
	public static WeiXinErrMsg doPost(String urlStr,String jsonStr){
		logger.info("Post parameter: " + jsonStr);
		WeiXinErrMsg weiXinErrMsg = new WeiXinErrMsg();
		weiXinErrMsg.setErrCode(-1);
		weiXinErrMsg.setErrMsg("系统异常");
		
        try {
           URL url = new URL(urlStr);
           HttpURLConnection http = (HttpURLConnection) url.openConnection();    

           http.setRequestMethod("POST");        
           http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");    
           http.setDoOutput(true);        
           http.setDoInput(true);
           System.setProperty("sun.net.client.defaultConnectTimeout", String.valueOf(WeiXinConst.TIME_OUT_CONNECTION_DEFAULT));//连接超时30秒
           System.setProperty("sun.net.client.defaultReadTimeout", String.valueOf(WeiXinConst.TIME_OUT_READ_DEFAULT)); //读取超时30秒

           http.connect();
           OutputStream os= http.getOutputStream();    
           os.write(jsonStr.getBytes(BasicConst.DEFAULT_CHARACTER_ENCODE));//传入参数    
           os.flush();
           os.close();

           InputStream is =http.getInputStream();
           int size =is.available();
           byte[] jsonBytes =new byte[size];
           is.read(jsonBytes);
           String message = new String(jsonBytes,BasicConst.DEFAULT_CHARACTER_ENCODE);
           
           logger.info("Post result:" + message);
           
           JSONObject rsltJO = JSONObject.fromObject(message);
           
           weiXinErrMsg.setErrCode(rsltJO.getInt("errcode"));
           weiXinErrMsg.setErrMsg(rsltJO.getString("errmsg"));
           } catch (MalformedURLException e) {
               e.printStackTrace();
           } catch (IOException e) {
               e.printStackTrace();
           }    
        return weiXinErrMsg;
	}
	
	public static String doGet(String urlStr){
		String msg = null;
		
        try {
           URL url = new URL(urlStr);
           HttpURLConnection http = (HttpURLConnection) url.openConnection();    

           http.setRequestMethod("GET");        
           http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");    
           http.setDoOutput(true);        
           http.setDoInput(true);
           System.setProperty("sun.net.client.defaultConnectTimeout", String.valueOf(WeiXinConst.TIME_OUT_CONNECTION_DEFAULT));//连接超时30秒
           System.setProperty("sun.net.client.defaultReadTimeout", String.valueOf(WeiXinConst.TIME_OUT_READ_DEFAULT)); //读取超时30秒

           http.connect();

           InputStream is =http.getInputStream();
           int size =is.available();
           byte[] jsonBytes =new byte[size];
           is.read(jsonBytes);
           String message = new String(jsonBytes,BasicConst.DEFAULT_CHARACTER_ENCODE);
           
           logger.info("Get result:" + message);
           
           return message;
           } catch (MalformedURLException e) {
               e.printStackTrace();
           } catch (IOException e) {
               e.printStackTrace();
           }
        
        return msg;
	}
	
}
