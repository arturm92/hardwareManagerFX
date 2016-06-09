package ithm.view;

import ithm.Main;
import ithm.model.Worker;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ProfileOverviewController {
	private Main main;

	@FXML
	private TextField passwordVisible;
	@FXML
	private PasswordField password;
	@FXML
	private TextField user;
	@FXML
	private CheckBox visiblePassword;
	@FXML 
	private ComboBox<String> role;
	
	public void init(Worker worker) {
		passwordVisible.setText(worker.getPassword());
		password.setText(worker.getPassword());
		user.setText(worker.getUserName());
		role.setValue(String.valueOf(worker.getRoleId()));
	}

	public void setMain(Main object) {
		main = object;
	}
	
	@FXML
	public void showPassword(){
		if (visiblePassword.isSelected()){
			password.setVisible(false);
			passwordVisible.setVisible(true);
		}else{
			password.setVisible(true);
			passwordVisible.setVisible(false);
		}
		
	}
}
