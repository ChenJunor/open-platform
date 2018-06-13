package com.chenj.weixin.open.platform.service.sql;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chenj.weixin.open.platform.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations="/applicationContext.xml")  
public class UserServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

     @Autowired
     private UserService userService;
	 
	@Test
	public void testFindAll() throws ClassNotFoundException{
		logger.info("Begin to query...");
		List<User> users = userService.getUsers();
		
		if(null != users){
			for (User user : users) {
				logger.info(user.toString());
			}
		}
	}

}
