package com.chenj.weixin.open.platform.service.sql;

import java.util.List;

import com.chenj.weixin.open.platform.entity.User;

public interface UserService {
	List<User> getUsers();
	
	boolean save(User user);
	
	User queryUserByOpenid(String openid);
	
	User queryByPhone(String phone);
}
