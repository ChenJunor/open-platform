package com.chenj.weixin.open.platform.dao;

import java.util.List;

import com.chenj.weixin.open.platform.entity.Article;

public interface ArticleDAO {
	List<Article> getArticlesBySort(String sort);
}
