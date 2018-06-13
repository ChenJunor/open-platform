package com.chenj.weixin.open.platform.dao;

import java.util.List;

import com.chenj.weixin.open.platform.entity.Project;

public interface ProjectDAO {

    boolean save(Project project);
    
	List<Project> qryOnlinePjt();
	
	List<Project> qrySuccessPjt();
	
	Project qrtPjt(String id);
}
