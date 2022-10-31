package view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;


public class PopupLabel extends Label{

	public PopupLabel(String text) {
		super(text);
		this.getStyleClass().add(ConstStrings.STYLE_POPUP_LABEL);
		this.setWrapText(true);
		this.setPadding(new Insets(5));
	}
}
