package com.chenj.weixin.open.platform.entity;

import java.util.Date;

public class Project {
	private Integer id = null;
	private String name = "无名氏";
	private String description = null;
	private String progress = "未开始";
	private int invest = 0;
	private int reward = 0;
	private int online = 0;
	private int success = 0;
	private Date createTime = new Date();
	private Date lastModifyTime = new Date();
	private String lastModifyAuthor = null;
	
	public Project() {
		super();
	}
	
	public Project(Integer id, String name, String description,
			String progress, int invest, int reward,int online,int success, Date createTime,
			Date lastModifyTime, String lastModifyAuthor) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.progress = progress;
		this.invest = invest;
		this.reward = reward;
		this.online = online;
		this.success = success;
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
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
	public int getInvest() {
		return invest;
	}
	public void setInvest(int invest) {
		this.invest = invest;
	}
	public int getReward() {
		return reward;
	}
	public void setReward(int reward) {
		this.reward = reward;
	}
	
	public int getOnline() {
		return online;
	}

	public void setOnline(int online) {
		this.online = online;
	}

	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
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
