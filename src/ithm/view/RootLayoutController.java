package ithm.view;

import ithm.Main;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;

public class RootLayoutController {
	
	private Main main;
	
	@FXML
	private FlowPane mainMenu;

	public RootLayoutController() {
	}

	public void init() {
		//mainMenu.setDisable(true);
		
	}
	public void setMain(Main object) {
		main = object;
	}
}
