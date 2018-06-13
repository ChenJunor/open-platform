package com.chenj.weixin.open.platform.service.sql;

import java.util.List;

import com.chenj.weixin.open.platform.entity.Article;

public interface ArticleService {
	List<Article> getArticlesBySort(String sort);
}
