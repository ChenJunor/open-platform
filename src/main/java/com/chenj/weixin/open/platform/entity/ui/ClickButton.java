package com.chenj.weixin.open.platform.entity.ui;

public class ClickButton extends Button {
	private String key = null;

	public ClickButton(String name,String type,String key) {
		super(name,type);
		this.key = key;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
