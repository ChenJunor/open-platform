package com.chenj.weixin.open.platform.entity.ui;

public class Menu {
	private Button[] button = null;

	public Menu() {
		super();
	}

	public Menu(Button[] button) {
		super();
		this.button = button;
	}

	public Button[] getButton() {
		return button;
	}

	public void setButtons(Button[] button) {
		this.button = button;
	}
	
}
