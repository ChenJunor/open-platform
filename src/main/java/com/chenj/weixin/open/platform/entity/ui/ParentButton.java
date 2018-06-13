package com.chenj.weixin.open.platform.entity.ui;

public class ParentButton extends ClickButton {
	
	public ParentButton(String name, String type, String key) {
		super(name, type, key);
	}

	private Button[] sub_button = null;

	public Button[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(Button[] sub_button) {
		this.sub_button = sub_button;
	}
	
}
