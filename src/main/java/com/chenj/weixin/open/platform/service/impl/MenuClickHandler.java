package com.chenj.weixin.open.platform.service.impl;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpMaterialNews;
import me.chanjar.weixin.mp.bean.WxMpMaterialNews.WxMpMaterialNewsArticle;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutNewsMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.chenj.weixin.open.platform.constant.BasicConst;
import com.chenj.weixin.open.platform.constant.WeiXinConst;
import com.chenj.weixin.open.platform.entity.Project;
import com.chenj.weixin.open.platform.entity.User;
import com.chenj.weixin.open.platform.service.sql.ProjectService;
import com.chenj.weixin.open.platform.service.sql.RoleService;
import com.chenj.weixin.open.platform.service.sql.UserService;
import com.chenj.weixin.open.platform.util.StringUtils;

@Component
public class MenuClickHandler implements WxMpMessageHandler {
	 private static final Logger logger = LoggerFactory.getLogger(MenuClickHandler.class);
	 
	 private static final int RES_ID_PROJECT_ADD = 1;
	 private static final int RES_ID_PROJECT_MODIFY = 1;

	@Resource
	private WxMpService wxMpService;
	
	@Resource
	private WxMpConfigStorage wxMpConfigStorage;
	
	@Resource
	private UserService userService;
	
	@Resource
	private RoleService roleService;
	
	@Resource
	private ProjectService projectService;
	
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
			Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) throws WxErrorException {
		
		if(WeiXinConst.BUTTON_KEY_FIRST_SECOND.equals(wxMessage.getEventKey())||
				WeiXinConst.BUTTON_KEY_FIRST_THIRD.equals(wxMessage.getEventKey())){
			return forwardToSignup(wxMessage,context,wxMpService,sessionManager);
		}
		else if(WeiXinConst.BUTTON_KEY_FIRST_FIRST.equals(wxMessage.getEventKey())){
			return showMyPjt(wxMessage, context, wxMpService, sessionManager);
		}
		else if(WeiXinConst.BUTTON_KEY_FIRST_FOURTH.equals(wxMessage.getEventKey())){
			return showServiceHall(wxMessage, context, wxMpService, sessionManager);
		}
		else if(WeiXinConst.BUTTON_KEY_SECOND_FIRST.equals(wxMessage.getEventKey())){
			return showOnlinePjt(wxMessage, context, wxMpService, sessionManager);
		}
		else if(WeiXinConst.BUTTON_KEY_SECOND_SECOND.equals(wxMessage.getEventKey())){
			return showSuccessPjt(wxMessage, context, wxMpService, sessionManager);
		}
		else if(WeiXinConst.BUTTON_KEY_FIRST_FOURTH.equals(wxMessage.getEventKey())||
				WeiXinConst.BUTTON_KEY_SECOND_THIRD.equals(wxMessage.getEventKey())){
			return WxMpXmlOutMessage.TEXT()
					.content("目前该功能暂未开放，敬请期待。")
					.fromUser(wxMessage.getToUserName())
					.toUser(wxMessage.getFromUserName())
					.build();
		}else if(WeiXinConst.BUTTON_KEY_THIRD_SECOND.equals(wxMessage.getEventKey())){
			
			WxMpXmlOutNewsMessage.Item item = new WxMpXmlOutNewsMessage.Item();
			
			WxMpMaterialNews wxMpMaterialNewsSingle = wxMpService.materialNewsInfo(WeiXinConst.PICTURE_ARTICLE_MATERICALS_PERMANENT_ID_BRAND_CULTURE);
			WxMpMaterialNewsArticle articleMaterialNews = wxMpMaterialNewsSingle.getArticles().get(0);
			
			item.setDescription(articleMaterialNews.getDigest());
			
			item.setPicUrl(WeiXinConst.THUMB_MATERICALS_PERMANENT_URL_NOW_US);
			item.setTitle(articleMaterialNews.getTitle());
			item.setUrl(articleMaterialNews.getUrl());

			WxMpXmlOutNewsMessage m = WxMpXmlOutMessage.NEWS()
			   .fromUser(wxMessage.getToUserName())
			   .toUser(wxMessage.getFromUserName())
			   .addArticle(item)
			   .build();
			
			return m;
		}
		
		return null;
	}
	
	private WxMpXmlOutMessage forwardToSignup(WxMpXmlMessage wxMessage,
			Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager){
		User user = userService.queryUserByOpenid(wxMessage.getFromUserName());

		String content = "";
		if(null != user && StringUtils.isNotBlank(user.getName())){
			content = "尊敬的" + user.getName() + "，系统暂无相关信息。";
			logger.info(user.getName() + " clcik a menu.");
		}else{
			logger.info("User is not register.");
			content = "您还没有注册成为会员，<a href=\"" + StringUtils.wxToWebUrl(
					"static/html/signup.html", wxMpConfigStorage.getAppId(), "signup") + "\">点击注册</a>。";
		}
		
		return WxMpXmlOutMessage.TEXT()
				.content(content)
				.fromUser(wxMessage.getToUserName())
				.toUser(wxMessage.getFromUserName())
				.build();
	}
	
	private WxMpXmlOutMessage showMyPjt(WxMpXmlMessage wxMessage,
			Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager){
		StringBuilder content = new StringBuilder("我的项目：").append("\n");
		List<Project> pjts = projectService.qryOnlinePjt();
		
		if(null == pjts || pjts.size() == 0){
			content.append("暂无相关项目");
		}else{
			int i = 0;
			for (Project project : pjts) {
				i ++;
				content.append(i).append("名称：").append(project.getName()).append("，");
				content.append("描述：").append(project.getDescription()).append("，");
				content.append("进度：").append(project.getProgress()).append("\n");
				
				content.append(" <a href=\"" + StringUtils.wxToWebUrl(
						"static/html/project/dtl.html", wxMpConfigStorage.getAppId(),project.getId().toString()) + "\">详情</a>");
				
				if(roleService.hasPvl(wxMessage.getFromUserName(),RES_ID_PROJECT_MODIFY) > 0){
					content.append(" <a href=\"" + StringUtils.wxToWebUrl(
							"static/html/project/mdf.html", wxMpConfigStorage.getAppId(),project.getId().toString()) + "\">更新</a>");
				}
				
				content.append("\n");
			}
		}
		
		return WxMpXmlOutMessage.TEXT()
				.content(content.toString())
				.fromUser(wxMessage.getToUserName())
				.toUser(wxMessage.getFromUserName())
				.build();
	}
	
	private WxMpXmlOutMessage showServiceHall(WxMpXmlMessage wxMessage,
			Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager){
		StringBuilder content = new StringBuilder("欢迎来到小虎服务厅：").append("\n");
		int i = 1;
		content.append("01  反馈").append("\n");
		
		if(roleService.hasPvl(wxMessage.getFromUserName(),RES_ID_PROJECT_ADD) > 0){
			i ++;
			content.append("0").append(i).append(" <a href=\"" + 
					StringUtils.wxToWebUrl(
							"static/html/role/role.html", wxMpConfigStorage.getAppId(),"role")
					+ "\">角色管理</a>").append("\n");
			
			i ++;			
			content.append("0").append(i).append(" <a href=\"" + 
					StringUtils.wxToWebUrl(
							"static/html/project/crt.html", wxMpConfigStorage.getAppId(),"pjtcrt") + 
					"\">添加项目</a>").append("\n");
		}
		
		return WxMpXmlOutMessage.TEXT()
				.content(content.toString())
				.fromUser(wxMessage.getToUserName())
				.toUser(wxMessage.getFromUserName())
				.build();
	}
	
	private WxMpXmlOutMessage showOnlinePjt(WxMpXmlMessage wxMessage,
			Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager){
		
		List<Project> pjts = projectService.qryOnlinePjt();
		return showPjt(wxMessage, context, wxMpService, sessionManager,pjts);
	}
	
	private WxMpXmlOutMessage showSuccessPjt(WxMpXmlMessage wxMessage,
			Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager){
		List<Project> pjts = projectService.qrySuccessPjt();
		return showPjt(wxMessage, context, wxMpService, sessionManager,pjts);
	}
	
	private WxMpXmlOutMessage showPjt(WxMpXmlMessage wxMessage,
			Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager,List<Project> pjts){
		StringBuilder content = new StringBuilder("查询到以下项目：").append("\n");
		if(null == pjts || pjts.size() == 0){
			content.append("暂无相关项目");
		}else{
			int i = 0;
			for (Project project : pjts) {
				i ++;
				content.append(i).append("名称：").append(project.getName()).append("，");
				content.append("描述：").append(project.getDescription()).append("，");
				content.append("进度：").append(project.getProgress()).append("\n");
			}
		}
		
		return WxMpXmlOutMessage.TEXT()
				.content(content.toString())
				.fromUser(wxMessage.getToUserName())
				.toUser(wxMessage.getFromUserName())
				.build();
	}

}
