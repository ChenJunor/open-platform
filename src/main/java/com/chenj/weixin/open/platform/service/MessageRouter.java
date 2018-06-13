package com.chenj.weixin.open.platform.service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;

import org.springframework.stereotype.Component;

import com.chenj.weixin.open.platform.service.impl.MenuClickHandler;
import com.chenj.weixin.open.platform.service.impl.SubscribeHandler;
import com.chenj.weixin.open.platform.service.impl.WxMessageHandler;

@Component
public class MessageRouter {
	@Resource
	private WxMpMessageRouter wxMpMessageRouter;
	
	@Resource
	private WxMessageHandler wxMessageHandler;
	
	@Resource
	private SubscribeHandler subscribeHandler;
	
	@Resource
	private MenuClickHandler menuClickHandler;
	
	@PostConstruct
	public void init() {
		wxMpMessageRouter
		
		//关注事件处理器
		.rule()
		.async(false)
	    .msgType(WxConsts.XML_MSG_EVENT)
	    .event(WxConsts.EVT_SUBSCRIBE)
	    .handler(subscribeHandler)
	    .end()
		
		.rule()
        .async(false)
        .rContent(".*.") 
        .handler(wxMessageHandler)
        .end()
        
        .rule()
        .async(false)
        .msgType(WxConsts.XML_MSG_EVENT)
        .event(WxConsts.EVT_CLICK)
        .handler(menuClickHandler)
        .end();
	}
	
	public WxMpXmlOutMessage route(final WxMpXmlMessage wxMessage) {
		return wxMpMessageRouter.route(wxMessage);
	}

}
