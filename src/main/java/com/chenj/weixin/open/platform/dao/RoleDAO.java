package com.chenj.weixin.open.platform.dao;

import java.util.List;
import java.util.Map;

import com.chenj.weixin.open.platform.entity.Role;

public interface RoleDAO {
	List<Role> getRoles();
	Role getRoleById(String id);
	
	int saveUserRole(Map<String,Object> paraMap);
	
	int delUserRole(Map<String,Object> paraMap);
	
	int hasPvl(Map<String,Object> paraMap);
}
