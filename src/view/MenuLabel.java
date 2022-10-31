package view;

import javafx.scene.control.Label;

public class MenuLabel extends Label{

	public MenuLabel(String text) {
		super(text);
		this.getStyleClass().add(ConstStrings.STYLE_MENU_LABEL);

	}
}
