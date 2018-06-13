package com.chenj.weixin.open.platform.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chenj.weixin.open.platform.dao.ProjectDAO;
import com.chenj.weixin.open.platform.entity.Project;

@Repository
public class ProjectDAOImpl implements ProjectDAO {
	
	@Autowired  
	private SqlSessionTemplate sqlSessionTemplate; 
	
	public boolean save(Project project) {
		return sqlSessionTemplate.insert("saveProject", project) > 0?true:false;
	}

	public List<Project> qryOnlinePjt() {
		return sqlSessionTemplate.selectList("qryOnlinePjt");
	}

	public List<Project> qrySuccessPjt() {
		return sqlSessionTemplate.selectList("qrySuccessPjt");
	}

	public Project qrtPjt(String id) {
		return sqlSessionTemplate.selectOne("qryPjt",id);
	}

}
