package com.chenj.weixin.open.platform.executor.processor;

import javax.annotation.Resource;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.chenj.weixin.open.platform.service.WeiXinService;

public class InstantiationTracingBeanPostProcessor implements
		ApplicationListener<ContextRefreshedEvent> {
	
	@Resource
	private WxMpService wxService;
	
	@Resource
	private WeiXinService weiXinService;
	
    private static final Logger logger = LoggerFactory.getLogger(InstantiationTracingBeanPostProcessor.class);

	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(event.getApplicationContext().getParent() == null){//root application context 没有parent，他就是老大.
	       //需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。

			try {
				logger.info("Get access token when init:" + wxService.getAccessToken());
				
				weiXinService.createMenu(wxService.getAccessToken());
			} catch (WxErrorException e) {
				logger.error("Failed to get access token when init.",e);
			}
			
	      }
		
	}

}
