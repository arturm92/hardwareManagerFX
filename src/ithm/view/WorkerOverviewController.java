package ithm.view;

import ithm.Main;
import ithm.component.AutoCompleteComboBoxListener;
import ithm.dao.DBConnection;
import ithm.dao.DataSet;
import ithm.messages.Messages;
import ithm.model.HardwareDevice;
import ithm.model.HardwareHistory;
import ithm.model.User;
import ithm.model.Worker;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

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
	@FXML
	private ComboBox<String> nameInputAuto;
	@FXML
	private ComboBox<String> personalInputAuto;
	@FXML
	private ComboBox<String> storageInputAuto;

	
	private ObservableList<User> workersDataset;
	private ObservableList<HardwareDevice> deviceForWorkerDataset;
	private AutoCompleteComboBoxListener<String> nameInputAutoListener;
	private AutoCompleteComboBoxListener<String> personalInputAutoListener;
	private AutoCompleteComboBoxListener<String> storageInputAutoListener;
	private Worker worker;
	private User user;
	private Messages messages = new Messages();
	private Main main;
	private boolean minimalize;
	
	public void init() throws Exception{
		initializeBoxes();
		initializeWorkers();
		initializeButtons();
	}
	
	private void initializeBoxes() throws Exception {
			ObservableList<String> nameDataSet = DataSet.getUserDataSet();
			nameInputAuto.setItems(nameDataSet);
			ObservableList<String> personalDataSet = DataSet.getPersonalNrDataSet();
			personalInputAuto.setItems(personalDataSet);
			ObservableList<String> storageDataSet = DataSet.getStorageDataSet();
			storageInputAuto.setItems(storageDataSet);
			initAutoComboBoxes();
	}

	private void initAutoComboBoxes() {
		nameInputAutoListener = new AutoCompleteComboBoxListener<>(nameInputAuto);
		personalInputAutoListener = new AutoCompleteComboBoxListener<>(personalInputAuto);
		storageInputAutoListener = new AutoCompleteComboBoxListener<>(storageInputAuto);
	}

	private void initializeButtons() {
		userDevices.setLayoutX(main.getPrimaryStage().getWidth()-200);
		userHistory.setLayoutX(userDevices.getLayoutX()-150);
		userDevices.setDisable(true);
		userHistory.setDisable(true);
		plus.setVisible(false);
		minus.setVisible(true);
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
			workersTable.getItems().addListener(new ListChangeListener<User>() {
				  @Override
				  public void onChanged(ListChangeListener.Change<? extends User> change) {
					  int i = 1;
					  for (User user : workersTable.getItems()){
						  user.setNum(i);
						  i++;
					  }
					  
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

	@FXML
	private void showHardwareHistoryForUser() throws Exception{
		main.showHistoryOverviewForUser(user);
	}
	public void setMain(Main object) {
		main = object;
	}
}
