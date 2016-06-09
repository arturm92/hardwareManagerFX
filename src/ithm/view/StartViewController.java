package ithm.view;

import ithm.Main;
import ithm.component.AutoCompleteComboBoxListener;
import ithm.dao.DBConnection;
import ithm.messages.Messages;
import ithm.model.Worker;
import ithm.util.Info;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class StartViewController {

	@FXML
	private ComboBox<Worker> workerInputAuto;
	
	@FXML
	private TextField password;
	@FXML
	private javafx.scene.control.Button enterButton;
	private Messages messages = new Messages();
	private Worker loggedWorker = new Worker();
	private AutoCompleteComboBoxListener<Worker> workerInputAutoListener;
	private Main main;
	
	@FXML
	public void init() {
		try {
			initWorkerDataset();
			initWorkerInputComboBoxAuto();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void initWorkerInputComboBoxAuto() {
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
		loggedWorker = workerInputAuto.getValue();
		String pass = password.getText();
		if (checkLogin(loggedWorker, pass)) {
			main.loginUser(loggedWorker);
		} else {
			Info.createInfo(AlertType.ERROR, main.getPrimaryStage(), "LOGIN", "LOGIN", messages.getLoginError());
		}
	}

	private boolean checkLogin(Worker worker, String pass) throws Exception {
		if (loggedWorker.getPassword().equals(pass)){
			return true;
		}else{
			return false;
		}
	}

	public void setMain(Main object) {
		main = object;
	}
}
