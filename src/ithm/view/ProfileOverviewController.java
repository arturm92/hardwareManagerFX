package ithm.view;

import ithm.Main;
import ithm.dao.DBConnection;
import ithm.model.HardwareView;
import ithm.model.Worker;
import ithm.util.EmptyFieldException;
import ithm.util.Info;
import ithm.util.Validator;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProfileOverviewController {
	private Main main;

	@FXML
	private TextField passwordY;
	@FXML
	private PasswordField passwordN;
	@FXML
	private TextField newPassword;
	@FXML
	private TextField newPassword2;
	@FXML
	private CheckBox visiblePassword;
	@FXML
	private TableView<Worker> loggedWorkerTable;
	@FXML
	private TableColumn<Worker, String> personalNbColumn;
	@FXML
	private TableColumn<Worker, String> userNameColumn;
	@FXML
	private TableColumn<Worker, String> emailColumn;
	@FXML
	private TableColumn<Worker, String> organisationColumn;
	@FXML
	private TableColumn<Worker, String> passwordColumn;
	@FXML
	private TableColumn<Worker, String> roleColumn;
	private Worker worker;
	private ObservableList<Worker> workerDataset;
	private Validator validator = new Validator();

	public void init(Worker worker) {
		this.worker = worker;
		initDevices();
	}

	private void initDevices() {
		try {
			newPassword.setUserData("Nowe has³o");
			newPassword2.setUserData("Powtórz has³o");
			userNameColumn.setCellValueFactory(new PropertyValueFactory<Worker, String>("userName"));
			personalNbColumn.setCellValueFactory(new PropertyValueFactory<Worker, String>("personalNumber"));
			emailColumn.setCellValueFactory(new PropertyValueFactory<Worker, String>("email"));
			organisationColumn.setCellValueFactory(new PropertyValueFactory<Worker, String>("organisationName"));
			passwordColumn.setCellValueFactory(new PropertyValueFactory<Worker, String>("password"));
			roleColumn.setCellValueFactory(new PropertyValueFactory<Worker, String>("roleName"));
			if (worker != null) {
				workerDataset = (ObservableList<Worker>) DBConnection.getLoggedWorker(worker.getPersonalNumber());
				loggedWorkerTable.setItems(workerDataset);
				initBoxes();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void initBoxes() {
		showPassword();
	}

	public void setMain(Main object) {
		main = object;
	}

	@FXML
	public void showPassword() {
		if (visiblePassword.isSelected()) {
			passwordN.setVisible(false);
			passwordY.setVisible(true);
			passwordY.setText(worker.getPassword());
		} else {
			passwordN.setVisible(true);
			passwordY.setVisible(false);
			passwordN.setText(worker.getPassword());
		}

	}

	@FXML
	private void changePassword() {
		try {
			validator.checkField(newPassword);
			validator.checkField(newPassword2);
			if (newPassword.getText().equals(newPassword2.getText())) {
				if(DBConnection.updatePassword(worker, newPassword.getText())>0){
					Info.createInfo(AlertType.INFORMATION, main.getPrimaryStage(), "Has³a zosta³o zmienione", null,null);
				}
			}else{
				Info.createInfo(AlertType.WARNING, main.getPrimaryStage(), "Has³a nie s¹ zgodne", "Podane has³a musz¹ byæ takie same ",null);
			}
		} catch (EmptyFieldException e) {

		}

	}
}
