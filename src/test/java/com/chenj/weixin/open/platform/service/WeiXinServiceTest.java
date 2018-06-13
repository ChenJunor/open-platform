package com.chenj.weixin.open.platform.service;

import javax.annotation.Resource;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpMaterialArticleUpdate;
import me.chanjar.weixin.mp.bean.WxMpMaterialNews;
import me.chanjar.weixin.mp.bean.result.WxMpMaterialUploadResult;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chenj.weixin.open.platform.constant.BasicConst;
import com.chenj.weixin.open.platform.constant.WeiXinConst;
import com.chenj.weixin.open.platform.util.StringUtils;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations="/applicationContext.xml")  
public class WeiXinServiceTest {
	 private static final Logger logger = LoggerFactory.getLogger(WeiXinServiceTest.class);

	@Resource
	private WxMpService wxService;
	
	@Resource
	private WeiXinService weiXinService;
	
	@Test
	public void testCreateMenu() throws WxErrorException{
//		weiXinService.createMenu(wxService.getAccessToken());
	}
	
	@Test
	public void testAddPicAlts() throws WxErrorException{
		WxMpMaterialUploadResult wir = weiXinService.addPmtMediaMaterial(WxConsts.MEDIA_THUMB,WxConsts.FILE_JPG,"6608733_124943522000_2.jpg");
	    
	    if(null == wir || StringUtils.isBlank(wir.getMediaId())){
	    	logger.error("Can not get thumb media id.");
	    	return;
	    }
	    
	    //Thumb id is DXEm6KdqKiBWf6tDirRN4Gxuruqw5m0VmmoQxtiGwxY,url is https://mmbiz.qlogo.cn/mmbiz/JnvpxIzicXK1ZGPkxjaxhvhl2aXow0YWV5JEFFxon60F7H7aEOFLXVXCfgvcB0Wz9ibwGYrWDddqMZopxoqCaJrQ/0?wx_fmt=jpeg
	    logger.info("Thumb id is " + wir.getMediaId() + ",url is " + wir.getUrl());
	    
	    WxMpMaterialNews wxMpMaterialNewsSingle = new WxMpMaterialNews();
	    WxMpMaterialNews.WxMpMaterialNewsArticle mpMaterialNewsArticleSingle = 
	    	new WxMpMaterialNews.WxMpMaterialNewsArticle();
	    
	    mpMaterialNewsArticleSingle.setAuthor("五虎");
	    
	    mpMaterialNewsArticleSingle.setThumbMediaId(wir.getMediaId());
	    
	    mpMaterialNewsArticleSingle.setTitle("现在的我们");
	    mpMaterialNewsArticleSingle.setContent("五虎理财致力于推动中国信贷行业的市场化、平民化及高效化，搭建中国最大、 用户体验最好的个人及中小企业的互联网信贷平台。 自上线之初即深耕互联网房产金融领域。 同时，不断围绕房地产产业链上的各个主体持续创新， 推出了面对终端购房人的“房易贷”系列产品和面对商业地产运营商的“租金宝”系列产品。 ");
	    mpMaterialNewsArticleSingle.setContentSourceUrl(BasicConst.PUBLIC_URL_PREFIX + "index.html");
	    mpMaterialNewsArticleSingle.setShowCoverPic(true);
	    mpMaterialNewsArticleSingle.setDigest("五虎理财致力于推动中国信贷行业的市场化");
	    
	    wxMpMaterialNewsSingle.addArticle(mpMaterialNewsArticleSingle);
	    
	    WxMpMaterialUploadResult rs = weiXinService.addPmtPicAtls(wxMpMaterialNewsSingle);
	    
	    if(null != rs){
		    //id is DXEm6KdqKiBWf6tDirRN4NQ1r87H_6TS2EwdJIrM4FI url is null
		    logger.info("Upload picture acticle successfully,id is " + rs.getMediaId() + " url is " + rs.getUrl());
	    }else{
	    	logger.error("Failed to upload picture acticle.");
	    }
	    
	}
	
	@Test
	public void getPicAtlById() {
		try {
			WxMpMaterialNews wxMpMaterialNewsSingle = wxService.materialNewsInfo("DXEm6KdqKiBWf6tDirRN4NQ1r87H_6TS2EwdJIrM4FI");
			logger.info("Url is " + wxMpMaterialNewsSingle.getArticles().get(0).getUrl());
		} catch (WxErrorException e) {
	    	logger.error("Failed to get picture acticle.",e);
		}

	}
	
	@Test
	public void testUpdatePicAtl() throws WxErrorException{
		WxMpMaterialNews wxMpMaterialNewsSingle = wxService.materialNewsInfo(WeiXinConst.PICTURE_ARTICLE_MATERICALS_PERMANENT_ID_BRAND_CULTURE);
		
		WxMpMaterialArticleUpdate wxMpMaterialArticleUpdateSingle = new WxMpMaterialArticleUpdate();
	    WxMpMaterialNews.WxMpMaterialNewsArticle articleSingle = wxMpMaterialNewsSingle.getArticles().get(0);
	    articleSingle.setContentSourceUrl("");
	    wxMpMaterialArticleUpdateSingle.setMediaId(WeiXinConst.PICTURE_ARTICLE_MATERICALS_PERMANENT_ID_BRAND_CULTURE);
	    wxMpMaterialArticleUpdateSingle.setArticles(articleSingle);
	    wxMpMaterialArticleUpdateSingle.setIndex(0);
	    
	    boolean resultSingle = wxService.materialNewsUpdate(wxMpMaterialArticleUpdateSingle);
	}

}
