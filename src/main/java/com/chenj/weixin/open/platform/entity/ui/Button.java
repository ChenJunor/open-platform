package com.chenj.weixin.open.platform.entity.ui;

public class Button {
	private String name = null;
	
	private String type = null;

	public Button() {
		super();
	}

	public Button(String name, String type, Button[] subButton) {
		super();
		this.name = name;
		this.type = type;
	}
	
	public Button(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
