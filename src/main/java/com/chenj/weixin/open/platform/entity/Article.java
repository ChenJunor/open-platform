package com.chenj.weixin.open.platform.entity;

import java.util.Date;

public class Article {
	private Integer id = null;
	private String author = "无名氏";
	private String title = null;
	private String description = null;
	private String thumbImage = null;
	private String url = null;
	private String content = null;
	private boolean enabled = true;
	private Date createTime = new Date();
	private Date lastModifyTime = new Date();
	private String lastModifyAuthor = null;
	
	public Article() {
		super();
	}

	public Article(Integer id, String author, String title, String description,
			String thumbImage, String url, String content, boolean enabled,
			Date createTime, Date lastModifyTime, String lastModifyAuthor) {
		super();
		this.id = id;
		this.author = author;
		this.title = title;
		this.description = description;
		this.thumbImage = thumbImage;
		this.url = url;
		this.content = content;
		this.enabled = enabled;
		this.createTime = createTime;
		this.lastModifyTime = lastModifyTime;
		this.lastModifyAuthor = lastModifyAuthor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getThumbImage() {
		return thumbImage;
	}

	public void setThumbImage(String thumbImage) {
		this.thumbImage = thumbImage;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public String getLastModifyAuthor() {
		return lastModifyAuthor;
	}

	public void setLastModifyAuthor(String lastModifyAuthor) {
		this.lastModifyAuthor = lastModifyAuthor;
	}
	
}
