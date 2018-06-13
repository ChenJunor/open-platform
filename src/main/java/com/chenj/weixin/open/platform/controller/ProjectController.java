package com.chenj.weixin.open.platform.controller;

import javax.annotation.Resource;

import me.chanjar.weixin.mp.api.WxMpService;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.chenj.weixin.open.platform.entity.Project;
import com.chenj.weixin.open.platform.exception.ResponeResult;
import com.chenj.weixin.open.platform.service.sql.ProjectService;
import com.chenj.weixin.open.platform.service.sql.RoleService;
import com.chenj.weixin.open.platform.util.StringUtils;

@Controller
public class ProjectController {
	private final static Logger logger = LoggerFactory.getLogger(ProjectController.class);
	
	@Resource
	private WxMpService wxMpService;
	
	@Resource
	private RoleService roleService;
		
	@Resource 
	private ProjectService projectService;
	
	@RequestMapping(value="/pjt",method=RequestMethod.POST)
	public ResponeResult saveProject(@RequestBody JSONObject jsObj){
		ResponeResult responeResult = new ResponeResult();
		
		logger.info("Begin to save project.");

		String name = jsObj.getString("name");
		String description = jsObj.getString("description");
		String progress = jsObj.getString("progress");
		String invest = jsObj.getString("invest");
		String reward = jsObj.getString("reward");
		String online = jsObj.getString("online");
		String success = jsObj.getString("success");

		if(StringUtils.isBlank(jsObj.getString("name"))||StringUtils.isBlank(jsObj.getString("description"))||
				StringUtils.isBlank(jsObj.getString("progress"))||StringUtils.isBlank(jsObj.getString("invest"))||
				StringUtils.isBlank(jsObj.getString("reward"))||StringUtils.isBlank(jsObj.getString("online"))||
				StringUtils.isBlank(jsObj.getString("success"))
			){
			responeResult.setMsg("信息未填写完整");
			return responeResult;
		}
		
		try{
			Project project = new Project();
			project.setName(name);
			project.setDescription(description);
			project.setProgress(progress);
			project.setInvest(Integer.parseInt(invest));
			project.setReward(Integer.parseInt(reward));
			project.setOnline(Integer.parseInt(online));
			project.setSuccess(Integer.parseInt(success));
			
			projectService.save(project);
			responeResult.setCode(ResponeResult.CODE_SUCCESS);
			logger.info("Save project successfully.");
		} catch (Exception e) {
			logger.error("Failed to save project info.",e);
			responeResult.setMsg(e.getMessage());
			return responeResult;
		}
		
		return responeResult;
	}
	
	@RequestMapping(value="/pjtdtl",method=RequestMethod.GET)
	public Project getPjt(@RequestParam("id") String id){
		return projectService.qryPjt(id);
	}
		
}
