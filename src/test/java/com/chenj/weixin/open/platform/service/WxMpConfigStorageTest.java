package com.chenj.weixin.open.platform.service;

import javax.annotation.Resource;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations="/applicationContext.xml") 
public class WxMpConfigStorageTest {
    private static final Logger logger = LoggerFactory.getLogger(WxMpConfigStorageTest.class);
    
    @Resource
	private WxMpService wxService;

	@Resource
	private WxMpConfigStorage wxMpConfigStorage;
	
	@Test
	public void testGetAppId() throws WxErrorException{
		logger.info("App token from wxService: " + wxService.getAccessToken());
		logger.info("App token from wxService: " + wxService.getAccessToken());
		logger.info("App token from wxMpConfigStorage: " + wxMpConfigStorage.getAccessToken());
		
		logger.info("App scret:" + wxMpConfigStorage.getSecret());
	}
	
}
