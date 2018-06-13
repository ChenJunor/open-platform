package com.chenj.weixin.open.platform.entity.ui;

public class ViewButton extends Button {
	private String url = null;

	public ViewButton(String name,String type,String url) {
		super(name,type);
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
