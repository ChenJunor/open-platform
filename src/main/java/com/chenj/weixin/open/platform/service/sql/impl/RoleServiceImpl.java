package com.chenj.weixin.open.platform.service.sql.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chenj.weixin.open.platform.dao.RoleDAO;
import com.chenj.weixin.open.platform.entity.Role;
import com.chenj.weixin.open.platform.service.sql.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDAO roleDAO;
		
	public List<Role> getRoles() {
		return roleDAO.getRoles();
	}

	public Role getRoleById(String id) {
		return roleDAO.getRoleById(id);
	}

	public int saveUserRole(Map<String, Object> paraMap) {
		return roleDAO.saveUserRole(paraMap);
	}

	public int delUserRole(Map<String, Object> paraMap) {
		return roleDAO.delUserRole(paraMap);
	}

	public int hasPvl(Map<String, Object> paraMap) {
		return roleDAO.hasPvl(paraMap);
	}

	public int hasPvl(String openid, int resId) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("openid",openid);
		paraMap.put("resId", resId);
		
		return hasPvl(paraMap);
	}

}
