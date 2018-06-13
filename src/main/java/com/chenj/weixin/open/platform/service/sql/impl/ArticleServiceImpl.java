package com.chenj.weixin.open.platform.service.sql.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chenj.weixin.open.platform.dao.ArticleDAO;
import com.chenj.weixin.open.platform.entity.Article;
import com.chenj.weixin.open.platform.service.sql.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	private ArticleDAO articleDAO;
	
	public List<Article> getArticlesBySort(String sort) {
		return articleDAO.getArticlesBySort(sort);
	}

}
