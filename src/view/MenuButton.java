package view;

import javafx.scene.control.Button;

public class MenuButton extends Button {
	
	public MenuButton(String label) {
		super(label);
		this.getStyleClass().add(ConstStrings.STYLE_MENU_BUTTON);
		this.setPrefWidth(250);
		this.setPrefHeight(40);
	}
}
