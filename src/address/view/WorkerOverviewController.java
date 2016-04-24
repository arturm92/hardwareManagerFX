package address.view;

import address.dao.DBConnection;
import address.messages.Messages;
import address.model.HardwareDevice;
import address.model.Worker;
import address.util.Info;
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

public class WorkerOverviewController extends BaseController {

	@FXML
	private TableView<Worker> workersTable;
	@FXML
	private TableView<HardwareDevice> deviceForWorkerTable;
	@FXML
	private TableColumn<HardwareDevice, String> deviceCodeColumn;
	@FXML
	private TableColumn<HardwareDevice, String> deviceModelColumn;
	@FXML
	private TableColumn<HardwareDevice, String> deviceTypeColumn;
	@FXML
	private TableColumn<Worker, String> workerNameColumn;
	@FXML
	private TableColumn<Worker, String> workerSectionColumn;
	@FXML
	private TableColumn<Worker, String> workerJobColumn;
	@FXML
	private TableColumn<Worker, String> workerMasterSectionColumn;
	@FXML
	private TableColumn<Worker, String> workerRoomColumn;
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

	private ObservableList<Worker> workersDataset;
	private ObservableList<HardwareDevice> deviceForWorkerDataset;
	private Worker worker;
	private Messages messages = new Messages();

	public void initialize() {
		initializeWorkers();
		initializeBoxes();
		btnNew.setVisible(true);
	}

	private void initializeWorkers() {
		try {
			workerNameColumn.setCellValueFactory(new PropertyValueFactory<Worker, String>("name"));
			workerSectionColumn.setCellValueFactory(new PropertyValueFactory<Worker, String>("section"));
			workerJobColumn.setCellValueFactory(new PropertyValueFactory<Worker, String>("job"));
			workerMasterSectionColumn.setCellValueFactory(new PropertyValueFactory<Worker, String>("masterSection"));
			workerRoomColumn.setCellValueFactory(new PropertyValueFactory<Worker, String>("room"));
			workersDataset = (ObservableList<Worker>) DBConnection.initWorkersData();
			workersTable.setItems(workersDataset);
			workersTable.getSelectionModel().selectedItemProperty()
					.addListener((observableValue, oldValue, newValue) -> {
						if (workersTable.getSelectionModel().getSelectedItem() != null) {
							worker = workersTable.getSelectionModel().getSelectedItem();
							setDetails();
							setVisible(true);
						}
					});
			setVisible(false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setDetails() {
		text1.setText(worker.getName());
		combo1.setValue(worker.getSection().toString());
		combo2.setValue(worker.getJob().toString());
		combo3.setValue(worker.getMasterSection().toString());
		combo4.setValue(worker.getRoom().toString());
		setHardwareDeviceForWorker();
	}

	private void setHardwareDeviceForWorker() {
		try {
			deviceCodeColumn.setCellValueFactory(new PropertyValueFactory<HardwareDevice, String>("code"));
			deviceTypeColumn.setCellValueFactory(new PropertyValueFactory<HardwareDevice, String>("typeName"));
			deviceModelColumn.setCellValueFactory(new PropertyValueFactory<HardwareDevice, String>("modelName"));
			deviceForWorkerDataset = (ObservableList<HardwareDevice>) DBConnection.getHardwareDeviceDataForWorker(worker.getId());
			deviceForWorkerTable.setItems(deviceForWorkerDataset);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setVisible(boolean visible) {
		label1.setVisible(visible);
		label2.setVisible(visible);
		label4.setVisible(visible);
		label5.setVisible(visible);
		label6.setVisible(visible);
		label7.setVisible(visible);
		text1.setVisible(visible);
		combo1.setVisible(visible);
		combo2.setVisible(visible);
		combo3.setVisible(visible);
		combo4.setVisible(visible);
		btnAccept.setVisible(visible);
		btnDelete.setVisible(visible);
		deviceForWorkerTable.setVisible(visible);
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
		if (worker != null) {
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
		}
	}

	@FXML
	private void btnDelete() {
		if (worker == null) {
			Info.createInfo(AlertType.ERROR, main.getPrimaryStage(), "DELETE", "BAD", messages.getSelectworker());
		} else {
			if (DBConnection.deleteWorker(this.worker) > 0) {
				Info.createInfo(AlertType.INFORMATION, main.getPrimaryStage(), "DELETE", "GOOD",
						messages.getDeleteworker());
			}
			reloadTableView();
		}
	}

	private void reloadTableView() {
		try {
			workersDataset = (ObservableList<Worker>) DBConnection.initWorkersData();
			workersTable.setItems(workersDataset);
			clear();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
