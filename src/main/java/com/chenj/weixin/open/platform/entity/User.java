package com.chenj.weixin.open.platform.entity;

import java.io.Serializable;

public class User implements Serializable{
	
	private static final long serialVersionUID = -6048234465228610095L;
	
	private Integer id = null;
	private String name = null;
	private String password = null;
	private String email = null;
	private String cellphone = null;
	private String openid = null;
	private Integer level = -1;
	
	public User() {
		super();
	}

	public User(Integer id,String name, String password, String email, String cellphone,
			String openid,Integer level) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.cellphone = cellphone;
		this.openid = openid;
		this.level = level;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password
				+ ", emial=" + email + ", cellphone=" + cellphone + ", level="
				+ level + "]";
	}
	
}
