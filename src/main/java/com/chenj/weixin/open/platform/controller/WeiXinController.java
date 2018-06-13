package com.chenj.weixin.open.platform.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chenj.weixin.open.platform.entity.Article;
import com.chenj.weixin.open.platform.entity.Role;
import com.chenj.weixin.open.platform.entity.User;
import com.chenj.weixin.open.platform.entity.request.Signature;
import com.chenj.weixin.open.platform.exception.ResponeResult;
import com.chenj.weixin.open.platform.service.MessageRouter;
import com.chenj.weixin.open.platform.service.WeiXinService;
import com.chenj.weixin.open.platform.service.sql.ArticleService;
import com.chenj.weixin.open.platform.service.sql.RoleService;
import com.chenj.weixin.open.platform.service.sql.UserService;
import com.chenj.weixin.open.platform.util.StringUtils;

@Controller
public class WeiXinController {
	private final static Logger logger = LoggerFactory.getLogger(WeiXinController.class);
	
	@Resource
	private WxMpConfigStorage wxMpConfigStorage;
	
	@Resource
	private WeiXinService weiXinService;
	
	@Resource
	private ArticleService articleService;
	
	@Resource
	private RoleService roleService;
	
	@Resource
	private WxMpService wxMpService;
	
	@Resource 
	private MessageRouter messageRouter;
	
	@Resource 
	private UserService userService;
	
	@RequestMapping(value="/check",method=RequestMethod.GET)
	public void check(Signature signature,PrintWriter out){
		if(weiXinService.checkSignature(signature)){
			out.printf(signature.getEchostr());
		}
	}
	
	@RequestMapping(value="/check",method=RequestMethod.POST)
	@ResponseBody
	public String dealWeiXinMsg(HttpServletRequest request){
		logger.info("Get weixin message.");
		
		try {
		    String nonce = request.getParameter("nonce");
		    String timestamp = request.getParameter("timestamp");
		    String encryptType = request.getParameter("encrypt_type");
		 
		    encryptType = StringUtils.isBlank(encryptType) ? "raw" :encryptType;
		    
		    if ("raw".equals(encryptType)) {
	            // 明文传输的消息
		    	WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(request.getInputStream());
	            WxMpXmlOutMessage outMessage = messageRouter.route(inMessage);
	            
	            if (outMessage == null) {
					return "success";
				}
	            
	            return outMessage.toXml();
	          }

	          if ("aes".equals(encryptType)) {
	            // 是aes加密的消息
	            String msgSignature = request.getParameter("msg_signature");
	            WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(request.getInputStream(), wxMpConfigStorage, timestamp, nonce, msgSignature);
	            WxMpXmlOutMessage outMessage = messageRouter.route(inMessage);
	            return outMessage.toEncryptedXml(wxMpConfigStorage);
	          }
	          return "不可识别的加密类型";

		} catch (Exception e) {
			logger.error("Failed to deal weixin message.",e);
			return "系统异常";
		}
	}
	
	@RequestMapping(value="/signup",method=RequestMethod.POST)
	public ResponeResult signup(@RequestBody JSONObject jsObj){
		ResponeResult responeResult = new ResponeResult();
		
		logger.info("Begin to signup.");
		if(StringUtils.isBlank(jsObj.getString("code"))||StringUtils.isBlank(jsObj.getString("name"))||
				StringUtils.isBlank(jsObj.getString("cellphone"))||StringUtils.isBlank(jsObj.getString("email"))){
			responeResult.setMsg("信息未填写完整");
			return responeResult;
		}
		
		String code = jsObj.getString("code");
		logger.info("Code is " + code);
		String name = jsObj.getString("name");
		String cellphone = jsObj.getString("cellphone");
		String email = jsObj.getString("email");
		
		try {
			WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
			String openid = wxMpOAuth2AccessToken.getOpenId();
			
			if(StringUtils.isNotBlank(openid)){
				User user = new User();
				user.setName(name);
				user.setCellphone(cellphone);
				user.setEmail(email);
				user.setOpenid(openid);
				
				userService.save(user);
				responeResult.setCode(ResponeResult.CODE_SUCCESS);
				logger.info("Save user successfully.");
			}else{
				responeResult.setMsg("未能绑定微信账户");
			}
		} catch (WxErrorException e) {
			logger.error("Failed to save user info.",e);
			responeResult.setMsg(e.getMessage());
			return responeResult;
		}
		
		return responeResult;
	}
	
	@RequestMapping(value="/news",method=RequestMethod.GET)
	public List<Article> getNews(){
		return articleService.getArticlesBySort("NEWS");
	}
	
	@RequestMapping(value="/roles",method=RequestMethod.GET)
	public List<Role> getRoles(){
		return roleService.getRoles();
	}
	
	@RequestMapping(value="/role",method=RequestMethod.GET)
	public Role getRoleById(@RequestParam("id") String id){
		return roleService.getRoleById(id);
	}
	
	@RequestMapping(value="/mrgur",method=RequestMethod.POST)
	public ResponeResult addUserForRole(@RequestBody JSONObject jsObj){
		ResponeResult responeResult = new ResponeResult();
		
		logger.info("Begin to add or remove user for role.");
		String phone = jsObj.getString("cellphone");
		String roleId = jsObj.getString("roleId");
		String type = jsObj.getString("type");
		
		if(StringUtils.isBlank(phone)
				||StringUtils.isBlank(roleId)){
			responeResult.setMsg("参数未填写完整");
			return responeResult;
		}
		
		logger.info("Cellphone number is " + phone);
		logger.info("Role ID is " + roleId);
		
		try {
			int userId = userService.queryByPhone(phone).getId();
			
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("userId", userId);
			paraMap.put("roleId", roleId);
			
			if("remove".equals(type)){
				roleService.delUserRole(paraMap);
			}else{
				roleService.saveUserRole(paraMap);
			}
			
			responeResult.setCode(ResponeResult.CODE_SUCCESS);
			logger.info("Save user role successfully.");
			
		} catch (Exception e) {
			logger.error("Failed to save user role.",e);
			responeResult.setMsg(e.getMessage());
			return responeResult;
		}
		
		return responeResult;
	}
	
}
