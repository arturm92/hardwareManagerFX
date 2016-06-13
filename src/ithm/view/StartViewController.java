package ithm.view;

import ithm.Main;
import ithm.component.AutoCompleteComboBoxListener;
import ithm.dao.DBConnection;
import ithm.messages.Messages;
import ithm.model.Worker;
import ithm.util.EmptyFieldException;
import ithm.util.Info;
import ithm.util.Validator;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class StartViewController {

	@FXML
	private ComboBox<Worker> workerInputAuto;
	@FXML
	private TextField passwordY;
	@FXML
	private PasswordField passwordN;
	@FXML
	private Button enterButton;
	@FXML
	private CheckBox visiblePassword;
	private Messages messages = new Messages();
	private Worker loggedWorker = new Worker();
	private AutoCompleteComboBoxListener<Worker> workerInputAutoListener;
	private Main main;

	private Validator validator = new Validator();
	
	@FXML
	public void init() {
		try {
			initWorkerDataset();
			initBoxes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void initBoxes() {
		workerInputAuto.setUserData("Pracownik");
		passwordY.setUserData("Has³o");
		passwordY.setVisible(false);
		passwordN.setUserData("Has³o");
		workerInputAuto.setCellFactory(new Callback<ListView<Worker>, ListCell<Worker>>() {
			@Override
			public ListCell<Worker> call(ListView<Worker> l) {
				return new ListCell<Worker>() {
					@Override
					protected void updateItem(Worker item, boolean empty) {
						super.updateItem(item, empty);
						if (item == null || empty) {
							setGraphic(null);
						} else {
							setText(item.getUserName());
						}
					}
				};
			}
		});
		workerInputAuto.setConverter(new StringConverter<Worker>() {
			public String toString(Worker worker) {
				if (worker == null) {
					return null;
				} else {
					return worker.getUserName();
				}
			}

			public Worker fromString(String id) {
				return null;
			}
		});
		workerInputAutoListener = new AutoCompleteComboBoxListener<>(workerInputAuto);
	}

	
	private void initWorkerDataset() throws Exception {
		ObservableList<Worker> workerList = DBConnection.getAllWorkers();
		workerInputAuto.setItems(workerList);
	}

	@FXML
	public void logIn() throws Exception {
		try{
			validator.checkField(workerInputAuto);
			if (!passwordN.getText().isEmpty()){
				passwordY.setText(passwordN.getText());
			}
			if (!passwordY.getText().isEmpty()){
				passwordN.setText(passwordY.getText());
			}
			validator.checkField(passwordN);
			validator.checkField(passwordY);
			loggedWorker = workerInputAuto.getValue();
			String pass = passwordN.getText();
			
			if (checkLogin(loggedWorker, pass)) {
				main.loginUser(loggedWorker);
			} else {
				Info.createInfo(AlertType.ERROR, main.getPrimaryStage(), "B³¹d logowania", "Podaj poprawne has³o",null);
			}
		}catch (EmptyFieldException e){
			Info.createInfo(AlertType.WARNING, main.getPrimaryStage(), "Wpe³nij wymagane pola", "Wype³nij pole "+e.getMessage(),null);
		}
		
	}

	private boolean checkLogin(Worker worker, String pass) throws Exception {
		if (loggedWorker.getPassword().equals(pass)){
			return true;
		}else{
			return false;
		}
	}
	
	

	@FXML
	public void showPassword(){
		if (visiblePassword.isSelected()){
			passwordN.setVisible(false);
			passwordY.setVisible(true);
			passwordY.setText(passwordN.getText());
		}else{
			passwordN.setVisible(true);
			passwordY.setVisible(false);
			passwordN.setText(passwordY.getText());
		}
		
	}

	public void setMain(Main object) {
		main = object;
	}
}
