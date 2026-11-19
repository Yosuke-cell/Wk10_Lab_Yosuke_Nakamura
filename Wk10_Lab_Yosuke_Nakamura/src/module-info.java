module week10_Lab {
	requires javafx.controls;
	requires java.sql;
	requires javafx.graphics;
	
	exports application;
	opens application to javafx.fxml;
}
