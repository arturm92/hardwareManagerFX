package ithm.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Info {

	public static void createInfo(AlertType type,Stage stage, String title, String header, String text){
		Alert alert = new Alert(type);
		alert.initOwner(stage);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(text);
		alert.showAndWait();
	}

}
