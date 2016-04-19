package address.view;

import address.dao.DBConnection;
import address.messages.Messages;
import address.model.Worker;
import address.util.Info;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

public class StartViewController extends BaseController {

	@FXML
	private ComboBox<String> worker;
	@FXML
	private TextField password;
	@FXML
	private javafx.scene.control.Button enterButton;
	@FXML
	private FlowPane mainMenu;

	private Messages messages = new Messages();
	private Worker loggedWorker = new Worker();
	
	@FXML
	public void initialize() throws Exception {
		mainMenu.setDisable(true);
		initWorkerDataset();
	}

	private void initWorkerDataset() throws Exception {
		ObservableList<String> workerList = DBConnection.getWorkersName();
		worker.setItems(workerList);
	}

	@FXML
	public void logIn() throws Exception {
		String user = worker.getValue();
		String pass = password.getText();
		if (checkLogin(user, pass)) {
			Info.createInfo(AlertType.CONFIRMATION, main.getPrimaryStage(), "LOGIN", "LOGIN", messages.getLoginOk());
			mainMenu.setDisable(false);
			main.showMainView(loggedWorker);
		} else {
			Info.createInfo(AlertType.ERROR, main.getPrimaryStage(), "LOGIN", "LOGIN", messages.getLoginError());
		}
	}

	private boolean checkLogin(String user, String pass) throws Exception {
		loggedWorker = DBConnection.checkLoginProperties(user, pass);
		if (loggedWorker.getId() != null){
			return true;
		}else{
			return false;
		}
	}

}
