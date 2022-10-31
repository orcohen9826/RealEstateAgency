package view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;

public class HeaderLabel extends Label{

	public HeaderLabel(String text) {
		super(text);
		this.setAlignment(Pos.CENTER);		
		this.getStyleClass().add(ConstStrings.STYLE_HEADER_LABEL);
	}	
}
