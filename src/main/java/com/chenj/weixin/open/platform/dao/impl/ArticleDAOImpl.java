package com.chenj.weixin.open.platform.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chenj.weixin.open.platform.dao.ArticleDAO;
import com.chenj.weixin.open.platform.entity.Article;

@Repository
public class ArticleDAOImpl implements ArticleDAO {
	
	@Autowired  
	private SqlSessionTemplate sqlSessionTemplate; 
	
	public List<Article> getArticlesBySort(String sort) {
		List<Article> articles = sqlSessionTemplate.selectList("getArticlesBySort",sort);  
		return articles;  
	}

}
