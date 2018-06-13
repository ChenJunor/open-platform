package com.chenj.weixin.open.platform.service.impl;

import java.util.Map;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutTextMessage;

import org.springframework.stereotype.Component;

@Component
public class WxMessageHandler implements WxMpMessageHandler {
//	private static final Logger logger = LoggerFactory.getLogger(WxMpMessageHandler.class);
	
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
			Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) throws WxErrorException {
				
		WxMpXmlOutTextMessage m= WxMpXmlOutMessage.TEXT()
				.content("您好，请点击下方菜单体验我们的服务。")
				.fromUser(wxMessage.getToUserName())
				.toUser(wxMessage.getFromUserName())
				.build();
		
		return m;
	}

}
