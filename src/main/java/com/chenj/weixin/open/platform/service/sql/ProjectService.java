package com.chenj.weixin.open.platform.service.sql;

import java.util.List;

import com.chenj.weixin.open.platform.entity.Project;

public interface ProjectService {
	
	boolean save(Project project);
	
	List<Project> qryOnlinePjt();
	
	List<Project> qrySuccessPjt();
	
	Project qryPjt(String id);
}
