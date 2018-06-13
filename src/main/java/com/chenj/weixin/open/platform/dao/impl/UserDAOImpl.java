package com.chenj.weixin.open.platform.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chenj.weixin.open.platform.dao.UserDAO;
import com.chenj.weixin.open.platform.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired  
	private SqlSessionTemplate sqlSessionTemplate; 


//	public int deleteByPrimaryKey(Integer id) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	public int insert(User user) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	public int insertSelective(User record) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	public User selectByPrimaryKey(Integer id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public int updateByPrimaryKey(User user) {
//		// TODO Auto-generated method stub
//		return 0;
//	}

	public List<User> findAll() {
		List<User> users = sqlSessionTemplate.selectList("queryAll");  
		return users;  
	}


	public boolean save(User user) {
		return sqlSessionTemplate.insert("save", user) > 0?true:false;
	}
	
	public User queryByOpenid(String openid) {
		return sqlSessionTemplate.selectOne("queryByOpenid",openid);
	}

	public User queryByPhone(String phone) {
		return sqlSessionTemplate.selectOne("queryByPhone",phone);
	}

}
