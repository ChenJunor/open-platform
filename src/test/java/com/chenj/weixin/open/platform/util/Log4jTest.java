package com.chenj.weixin.open.platform.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4jTest {
    private static final Logger logger = LoggerFactory.getLogger(Log4jTest.class);
	
    @Test
	public void testLogPrint(){
		logger.info("ok1");
	}

}
