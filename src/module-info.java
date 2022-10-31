module RealEstateAgency {
	requires javafx.controls;
	requires javafx.graphics;
    requires javafx.media;
    requires javafx.base;
    requires javafx.web;
    requires javafx.swing;
    requires javafx.fxml;

    exports view;
	opens application to javafx.graphics, javafx.fxml;
}
