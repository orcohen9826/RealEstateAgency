package view;

import javafx.scene.control.Label;

public class ErrorLabel extends Label{

	public ErrorLabel(String text) {
		super(text);
		this.getStyleClass().add(ConstStrings.STYLE_ERROR_LABEL);
	}
	
	public ErrorLabel() {
		super();
		this.setWrapText(true);
		this.getStyleClass().add(ConstStrings.STYLE_ERROR_LABEL);
	}
}
