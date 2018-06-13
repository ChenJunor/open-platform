package com.chenj.weixin.open.platform.service.sql.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chenj.weixin.open.platform.dao.UserDAO;
import com.chenj.weixin.open.platform.entity.User;
import com.chenj.weixin.open.platform.service.sql.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO userDAO;

	public List<User> getUsers() {
		return userDAO.findAll();
	}

	public boolean save(User user) {
		return userDAO.save(user);
	}

	public User queryUserByOpenid(String openid) {
		return userDAO.queryByOpenid(openid);
	}

	public User queryByPhone(String phone) {
		return userDAO.queryByPhone(phone);
	}

}
