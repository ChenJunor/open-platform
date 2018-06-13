package com.chenj.weixin.open.platform.service.sql.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chenj.weixin.open.platform.dao.ProjectDAO;
import com.chenj.weixin.open.platform.entity.Project;
import com.chenj.weixin.open.platform.service.sql.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	private ProjectDAO projectDAO;
	
	public boolean save(Project project) {
		return projectDAO.save(project);
	}

	public List<Project> qryOnlinePjt() {
		return projectDAO.qryOnlinePjt();
	}

	public List<Project> qrySuccessPjt() {
		return projectDAO.qrySuccessPjt();
	}

	public Project qryPjt(String id) {
		return projectDAO.qrtPjt(id);
	}

}
