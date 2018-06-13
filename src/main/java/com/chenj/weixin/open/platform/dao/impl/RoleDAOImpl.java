package com.chenj.weixin.open.platform.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chenj.weixin.open.platform.dao.RoleDAO;
import com.chenj.weixin.open.platform.entity.Role;

@Repository
public class RoleDAOImpl implements RoleDAO {

	@Autowired  
	private SqlSessionTemplate sqlSessionTemplate; 
	
	public List<Role> getRoles() {
		List<Role> roles = sqlSessionTemplate.selectList("getRoles");  
		return roles; 
	}

	public Role getRoleById(String id) {
		return sqlSessionTemplate.selectOne("getRoleById",id);
	}

	public int saveUserRole(Map<String, Object> paraMap) {
		return sqlSessionTemplate.insert("saveUserRole", paraMap);
	}

	public int delUserRole(Map<String, Object> paraMap) {
		return sqlSessionTemplate.delete("delUserRole", paraMap);
	}

	public int hasPvl(Map<String, Object> paraMap) {
		return sqlSessionTemplate.selectOne("hasPvl", paraMap);
	}

}
