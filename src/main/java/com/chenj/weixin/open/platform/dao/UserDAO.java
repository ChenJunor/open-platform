package com.chenj.weixin.open.platform.dao;

import java.util.List;

import com.chenj.weixin.open.platform.entity.User;

public interface UserDAO {
//	int deleteByPrimaryKey(Integer id);
//
//    int insert(User user);
//
//    int insertSelective(User record);
//
//    User selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKey(User user);
    
    List<User> findAll();
    
    boolean save(User user);
    
    User queryByOpenid(String openid);
    
    User queryByPhone(String phone);
}
