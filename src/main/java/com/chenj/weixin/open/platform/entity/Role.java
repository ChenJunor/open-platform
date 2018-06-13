package com.chenj.weixin.open.platform.entity;

import java.util.Date;

public class Role {
	private Integer id = null;
	private String name = "无名氏";
	private String description = null;
	private Date createTime = new Date();
	private Date lastModifyTime = new Date();
	private String lastModifyAuthor = null;
	
	public Role() {
		super();
	}

	public Role(Integer id, String name, String description, Date createTime,
			Date lastModifyTime, String lastModifyAuthor) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
