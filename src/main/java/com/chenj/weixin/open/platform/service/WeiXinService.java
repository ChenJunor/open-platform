package com.chenj.weixin.open.platform.service;

import java.io.File;
import java.io.InputStream;
import java.util.UUID;

import javax.annotation.Resource;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.fs.FileUtils;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpMaterial;
import me.chanjar.weixin.mp.bean.WxMpMaterialNews;
import me.chanjar.weixin.mp.bean.result.WxMpMaterialUploadResult;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.chenj.weixin.open.platform.constant.BasicConst;
import com.chenj.weixin.open.platform.constant.WeiXinConst;
import com.chenj.weixin.open.platform.entity.request.Signature;
import com.chenj.weixin.open.platform.entity.respone.WeiXinErrMsg;
import com.chenj.weixin.open.platform.entity.ui.Button;
import com.chenj.weixin.open.platform.entity.ui.ClickButton;
import com.chenj.weixin.open.platform.entity.ui.Menu;
import com.chenj.weixin.open.platform.entity.ui.ParentButton;
import com.chenj.weixin.open.platform.entity.ui.ViewButton;

@Component
public class WeiXinService {
	 private static final Logger logger = LoggerFactory.getLogger(WeiXinService.class);
		
	@Resource
	private WxMpService wxMpService;
		
	public boolean checkSignature(Signature signature) {
		
//		String[] sigtArr = null;
//		try {
			
			logger.info("Signature:" + signature);
			
			return wxMpService.checkSignature(signature.getTimestamp(), 
					signature.getNonce(),signature.getSignature());

//			sigtArr = new String[]{wxMpService.getAccessToken(true),
//					signature.getTimestamp(),signature.getNonce()};
//		
//			Arrays.sort(sigtArr);
//			
//			StringBuffer content = new StringBuffer();
//			
//			for (String elmt : sigtArr) {
//				content.append(elmt);
//			}
//			
//			String authStr = EncryptUtils.Sha1Encrypt(content.toString());
//			
//			logger.info("AuthString:" + authStr);
//			return authStr.equals(signature.getSignature());
//		} catch (WxErrorException e) {
//			logger.error("Failed to get access token.",e);
//		}
		
//		return false;
	}
		
	public boolean createMenu(String accessToken) {
		ParentButton firstBtn = new ParentButton(WeiXinConst.BUTTON_NAME_FIRST, WeiXinConst.BUTTON_TYPE_CLICK, WeiXinConst.BUTTON_KEY_FIRST);
		Button firstFirstBtn = new ClickButton(WeiXinConst.BUTTON_NAME_FIRST_FIRST, WeiXinConst.BUTTON_TYPE_CLICK, WeiXinConst.BUTTON_KEY_FIRST_FIRST);
		Button firstSecondBtn = new ClickButton(WeiXinConst.BUTTON_NAME_FIRST_SECOND, WeiXinConst.BUTTON_TYPE_CLICK, WeiXinConst.BUTTON_KEY_FIRST_SECOND);
		Button firstThirdBtn = new ClickButton(WeiXinConst.BUTTON_NAME_FIRST_THIRD, WeiXinConst.BUTTON_TYPE_CLICK, WeiXinConst.BUTTON_KEY_FIRST_THIRD);
		Button firstForthBtn = new ClickButton(WeiXinConst.BUTTON_NAME_FIRST_FOURTH, WeiXinConst.BUTTON_TYPE_CLICK, WeiXinConst.BUTTON_KEY_FIRST_FOURTH);
		firstBtn.setSub_button(new Button[]{firstFirstBtn,firstSecondBtn,firstThirdBtn,firstForthBtn});
		
		ParentButton secondBtn = new ParentButton(WeiXinConst.BUTTON_NAME_SECOND, WeiXinConst.BUTTON_TYPE_CLICK, WeiXinConst.BUTTON_KEY_SECOND);
		Button secondFirstBtn = new ClickButton(WeiXinConst.BUTTON_NAME_SECOND_FIRST, WeiXinConst.BUTTON_TYPE_CLICK, WeiXinConst.BUTTON_KEY_SECOND_FIRST);
		Button secondSecondBtn = new ClickButton(WeiXinConst.BUTTON_NAME_SECOND_SECOND, WeiXinConst.BUTTON_TYPE_CLICK, WeiXinConst.BUTTON_KEY_SECOND_SECOND);
		Button secondThirdBtn = new ClickButton(WeiXinConst.BUTTON_NAME_SECOND_THIRD, WeiXinConst.BUTTON_TYPE_CLICK, WeiXinConst.BUTTON_KEY_SECOND_THIRD);
		secondBtn.setSub_button(new Button[]{secondFirstBtn,secondSecondBtn,secondThirdBtn});

		ParentButton thirdBtn = new ParentButton(WeiXinConst.BUTTON_NAME_THIRD, WeiXinConst.BUTTON_TYPE_CLICK, WeiXinConst.BUTTON_KEY_THIRD);
		Button thirdFirstBtn = new ViewButton(WeiXinConst.BUTTON_NAME_THRID_FIRST, WeiXinConst.BUTTON_TYPE_VIEW, BasicConst.PUBLIC_URL_PREFIX + "static/html/news.html");
		Button thirdSecondBtn = new ClickButton(WeiXinConst.BUTTON_NAME_THRID_SECOND, WeiXinConst.BUTTON_TYPE_CLICK, WeiXinConst.BUTTON_KEY_THIRD_SECOND);
		thirdBtn.setSub_button(new Button[]{thirdFirstBtn,thirdSecondBtn});

		Menu menu = new Menu(new Button[]{firstBtn,secondBtn,thirdBtn});
		
		WeiXinErrMsg result = HttpService.doPost(WeiXinConst.URL_CREATE_MENU_PREFIX + accessToken,JSONObject.fromObject(menu).toString());
		if(WeiXinConst.ERROR_CODE_RESPONE_SUCCESS == result.getErrCode()){
			return true;
		}
		
		return false;
	}
	
	public WxMpMaterialUploadResult addPmtPicAtls(WxMpMaterialNews wxMpMaterialNews){
		WxMpMaterialUploadResult resSingle = null;
	    
	    try {
	    	if(null != wxMpMaterialNews){
				resSingle = wxMpService.materialNewsUpload(wxMpMaterialNews);
	    	}
		} catch (WxErrorException e) {
			logger.error("Failed to upload picture acticle material.",e);
		}
	    
	    return resSingle;
	}
	
	public WxMpMaterialUploadResult addPmtMediaMaterial(String mediaType,String fileType,String fileName){
		try {
			WxMpMaterial wxMaterial = new WxMpMaterial();
			
			InputStream inputStream = ClassLoader.getSystemResourceAsStream(fileName);
		    File tempFile = FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(),fileType);
		    wxMaterial.setFile(tempFile);
		    wxMaterial.setName(fileName);
		    
			WxMpMaterialUploadResult res = wxMpService.materialFileUpload(mediaType, wxMaterial); 
			
			return res;
		} catch (Exception e) {
			logger.error("Failed to upload media material.",e);
		}
		
		return null;
	}

}
