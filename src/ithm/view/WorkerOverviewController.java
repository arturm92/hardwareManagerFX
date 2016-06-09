package ithm.view;

import ithm.Main;
import ithm.dao.DBConnection;
import ithm.messages.Messages;
import ithm.model.User;
import ithm.model.Worker;
import ithm.tmp.HardwareDevice;
import ithm.util.Info;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class WorkerOverviewController {

	@FXML
	private TableView<User> workersTable;
	@FXML
	private TableView<HardwareDevice> deviceForWorkerTable;
	@FXML
	private TableColumn<HardwareDevice, String> deviceCodeColumn;
	@FXML
	private TableColumn<HardwareDevice, String> deviceModelColumn;
	@FXML
	private TableColumn<HardwareDevice, String> deviceTypeColumn;
	
	@FXML
	private TableColumn<User, String> numColumn;
	@FXML
	private TableColumn<User, String> userNameColumn;
	@FXML
	private TableColumn<User, String> storageColumn;
	@FXML
	private TableColumn<User, String> personalNumberColumn;
	@FXML
	private TableColumn<User, String> userDevicesColumn;
	@FXML
	private Label label1;
	@FXML
	private Label label2;
	@FXML
	private Label label4;
	@FXML
	private Label label5;
	@FXML
	private Label label6;
	@FXML
	private Label label7;
	@FXML
	private TextField text1;
	@FXML
	private Button btnNew;
	@FXML
	private Button btnAccept;
	@FXML
	private Button btnDelete;
	@FXML
	private ComboBox<String> combo1;
	@FXML
	private ComboBox<String> combo2;
	@FXML
	private ComboBox<String> combo3;
	@FXML
	private ComboBox<String> combo4;
	@FXML
	private Pane panel;
	@FXML
	private ImageView plus;
	@FXML
	private ImageView minus;
	@FXML
	private Button userDevices;
	@FXML
	private Button userHistory;
	

	
	private ObservableList<User> workersDataset;
	private ObservableList<HardwareDevice> deviceForWorkerDataset;
	private Worker worker;
	private User user;
	private Messages messages = new Messages();
	private Main main;
	private boolean minimalize;
	
	public void init() {
		initializeWorkers();
		initializeButtons();
		//setVisible(false);
	}
	
	private void initializeButtons() {
		userDevices.setLayoutX(main.getPrimaryStage().getWidth()-200);
		userHistory.setLayoutX(userDevices.getLayoutX()-150);
		userDevices.setDisable(true);
		userHistory.setDisable(true);
	}

	private void initializeWorkers() {
		try {
			numColumn.setCellValueFactory(new PropertyValueFactory<User, String>("num"));
			userNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
			storageColumn.setCellValueFactory(new PropertyValueFactory<User, String>("storage"));
			personalNumberColumn.setCellValueFactory(new PropertyValueFactory<User, String>("personalNumber"));
			userDevicesColumn.setCellValueFactory(new PropertyValueFactory<User, String>("userDevices"));
			workersDataset = (ObservableList<User>) DBConnection.getUserData();
			workersTable.setItems(workersDataset);
			workersTable.getSelectionModel().selectedItemProperty()
					.addListener((observableValue, oldValue, newValue) -> {
						if (workersTable.getSelectionModel().getSelectedItem() != null) {
							user = workersTable.getSelectionModel().getSelectedItem();
							//setDetails();
							setEnabled();
						}
					});
			//setVisible(false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setEnabled() {
		userDevices.setDisable(false);
		userHistory.setDisable(false);
		
	}

	private void setDetails() {
	/*	text1.setText(worker.getName());
		combo1.setValue(worker.getSection().toString());
		combo2.setValue(worker.getJob().toString());
		combo3.setValue(worker.getMasterSection().toString());
		combo4.setValue(worker.getRoom().toString());*/
		setHardwareDeviceForWorker();
	}

	@FXML
	private void minimalizeFilter(){ 
		if(minimalize){
			panel.setPrefHeight(30);
			plus.setVisible(true);
			minus.setVisible(false);
			changeState();
		}else{
			panel.setPrefHeight(100);
			plus.setVisible(false);
			minus.setVisible(true);
			changeState();
		}
		
	}
	
	private void changeState(){
		if (minimalize == true){
			minimalize = false;
		}else{
			minimalize = true;
		}
	}

	@FXML
	private void showHardwareDeviceForUser() throws Exception{
		main.showHardwareDeviceOverviewForUser(user);
	}
	
	private void setHardwareDeviceForWorker() {
		try {
			deviceCodeColumn.setCellValueFactory(new PropertyValueFactory<HardwareDevice, String>("code"));
			deviceTypeColumn.setCellValueFactory(new PropertyValueFactory<HardwareDevice, String>("typeName"));
			deviceModelColumn.setCellValueFactory(new PropertyValueFactory<HardwareDevice, String>("modelName"));
			deviceForWorkerDataset = null; /*(ObservableList<HardwareDevice>) DBConnection.getHardwareDeviceDataForWorker(worker.getId());*/
			deviceForWorkerTable.setItems(deviceForWorkerDataset);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setVisible(boolean visible) {
		userDevices.setVisible(visible);
		userHistory.setVisible(visible);
	}

	private void initializeBoxes() {
		combo1.getItems().addAll("2", "3");
		combo2.getItems().addAll("1", "2");
		combo3.getItems().addAll("0", "1");
		combo4.getItems().addAll("9", "8");

	}

	@FXML
	private void btnNew() {
		setVisible(true);
		worker = new Worker();
		clear();
	}

	private void clear() {
		text1.setText("");
		combo1.setValue("");
		combo2.setValue("");
		combo3.setValue("");
		combo4.setValue("");
	}

	@FXML
	private void btnAccept() {
		/*if (worker != null) {
			worker.setName(text1.getText());
			worker.setSection(Integer.valueOf(combo1.getValue()));
			worker.setJob(Integer.valueOf(combo2.getValue()));
			worker.setRoom(Integer.valueOf(combo3.getValue()));
			worker.setMasterSection(Integer.valueOf(combo4.getValue()));
			if (worker.getId() != null){
				if (DBConnection.updateWorker(worker) > 0) {
					Info.createInfo(AlertType.INFORMATION, main.getPrimaryStage(), "UPDATE", "GOOD",
							messages.getUpadteworker());
				}
			}else{
				if (DBConnection.insertWorker(worker) > 0) {
					Info.createInfo(AlertType.INFORMATION, main.getPrimaryStage(), "INSERT", "GOOD",
							messages.getInsertworker());
				}
			}

			reloadTableView();
		}*/
	}

	@FXML
	private void btnDelete() {
		/*if (worker == null) {
			Info.createInfo(AlertType.ERROR, main.getPrimaryStage(), "DELETE", "BAD", messages.getSelectworker());
		} else {
			if (DBConnection.deleteWorker(this.worker) > 0) {
				Info.createInfo(AlertType.INFORMATION, main.getPrimaryStage(), "DELETE", "GOOD",
						messages.getDeleteworker());
			}
			reloadTableView();
		}*/
	}

	private void reloadTableView() {
		try {
		/*	workersDataset = (ObservableList<Worker>) DBConnection.initWorkersData();*/
			workersTable.setItems(workersDataset);
			clear();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void setMain(Main object) {
		main = object;
	}
}
