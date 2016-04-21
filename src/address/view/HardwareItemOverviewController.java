/*package address.view;

import java.util.Map;

import address.dao.DBConnection;
import address.dao.DataSet;
import address.messages.Messages;
import address.model.HardwareItem;
import address.util.Info;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class HardwareItemOverviewController extends BaseController{

	@FXML
	private TableView<HardwareItem> itemsTable;
	@FXML
	private TableColumn<HardwareItem, String> itemNameColumn;
	@FXML
	private TableColumn<HardwareItem, String> ownerColumn;
	@FXML
	private TableColumn<HardwareItem, String> kitColumn;
	@FXML
	private TableColumn<HardwareItem, String> categoryColumn;
	@FXML
	private TableColumn<HardwareItem, String> valueColumn;
	@FXML
	private TableColumn<HardwareItem, String> descriptionColumn;
	@FXML
	private Label label1;
	@FXML
	private Label label2;
	@FXML
	private Label label3;
	@FXML
	private Label label4;
	@FXML
	private Label label5;
	@FXML
	private Label label6;
	@FXML
	private TextField text1;
	@FXML
	private TextField text2;
	@FXML
	private TextArea area1;
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

	private ObservableList<HardwareItem> itemsDataset;
	private Map<String,String> categoryMapping;
	private HardwareItem item;
	private Messages messages = new Messages();

	public void initialize() throws Exception {
		initializeItems();
		initializeBoxes();
		btnNew.setVisible(true);
		btnAccept.setVisible(true);
		btnDelete.setVisible(true);

	}

	private void initializeItems() {
		try {
			itemNameColumn.setCellValueFactory(new PropertyValueFactory<HardwareItem, String>("name"));
			ownerColumn.setCellValueFactory(new PropertyValueFactory<HardwareItem, String>("ownerDesc"));
			kitColumn.setCellValueFactory(new PropertyValueFactory<HardwareItem, String>("kit_id"));
			categoryColumn.setCellValueFactory(new PropertyValueFactory<HardwareItem, String>("categoryDesc"));
			valueColumn.setCellValueFactory(new PropertyValueFactory<HardwareItem, String>("value"));
			descriptionColumn.setCellValueFactory(new PropertyValueFactory<HardwareItem, String>("description"));
			//itemsDataset = (ObservableList<HardwareItem>) DBConnection.initHardwareItemsData();
			itemsTable.setItems(itemsDataset);
			itemsTable.getSelectionModel().selectedItemProperty()
					.addListener((observableValue, oldValue, newValue) -> {
						if (itemsTable.getSelectionModel().getSelectedItem() != null) {
							item = itemsTable.getSelectionModel().getSelectedItem();
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
		text1.setText(item.getName());
		text2.setText(item.getValue().toString());
		area1.setText(item.getDescription());
		combo1.setValue(item.getCategoryDesc().toString());
		combo2.setValue(item.getOwnerDesc().toString());
		combo3.setValue(item.getKitId().toString());

	}

	private void setVisible(boolean visible) {
		label1.setVisible(visible);
		label2.setVisible(visible);
		label3.setVisible(visible);
		label4.setVisible(visible);
		label5.setVisible(visible);
		label6.setVisible(visible);
		text1.setVisible(visible);
		text2.setVisible(visible);
		combo1.setVisible(visible);
		combo2.setVisible(visible);
		combo3.setVisible(visible);
		area1.setVisible(visible);
	}

	private void initializeBoxes() throws Exception {
		//categoryMapping = DataSet.getCategoryDataSetMap();
		ObservableList<String> categoryCombo = DataSet.getCategoryDataSet();
		//ObservableList<String> ownerDataSet = DataSet.getOwnerDataSet(); 
		combo1.setItems(categoryCombo);
		combo2.setItems(ownerDataSet);
		combo3.setItems(ownerDataSet);
		
	}

	@FXML
	private void btnNew() {
		setVisible(true);
		clear();
		item = new HardwareItem();
	}

	private void clear() {
		text1.setText("");
		text2.setText("");
		combo1.setValue("");
		combo2.setValue("");
		combo3.setValue("");
		area1.setText("");
		item = null;
	}

	@FXML
	private void btnAccept() {
		item.setName(text1.getText());
		item.setValue(Integer.valueOf(text2.getText()));
		item.setCategoryId(categoryMapping.get(combo1.getValue()));
		item.setOwnerId(Integer.valueOf(combo2.getValue()));
		item.setDescription(area1.getText());
		if (item != null) {
			if (item.getId() != 0){
				if (DBConnection.updateItem(item) > 0) {
					Info.createInfo(AlertType.INFORMATION, main.getPrimaryStage(), "UPDATE", "GOOD",
							messages.getUpadteItem());
				}
			}else{
				if (DBConnection.insertItem(item) > 0) {
					Info.createInfo(AlertType.INFORMATION, main.getPrimaryStage(), "INSERT", "GOOD",
							messages.getInsertItem());
				}
			}

			reloadTableView();
		}
	}

	@FXML
	private void btnDelete() {
		if (item == null) {
			Info.createInfo(AlertType.ERROR, main.getPrimaryStage(), "DELETE", "BAD", messages.getSelectItem());
		} else {
			if (DBConnection.deleteItem(this.item) > 0) {
				Info.createInfo(AlertType.INFORMATION, main.getPrimaryStage(), "DELETE", "GOOD",
						messages.getDeleteItem());
			}
			reloadTableView();
		}
	}

	private void reloadTableView() {
		try {
			//itemsDataset = (ObservableList<HardwareItem>) DBConnection.initHardwareItemsData();
			itemsTable.setItems(itemsDataset);
			clear();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
*/