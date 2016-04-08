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

public class StartViewController extends BaseController {

	@FXML
	private ComboBox<String> worker;
	@FXML
	private TextField password;
	@FXML
	private javafx.scene.control.Button enterButton;

	private Messages messages = new Messages();

	@FXML
	public void initialize() throws Exception {
		initWorkerDataset();
	}

	private void initWorkerDataset() throws Exception {
		ObservableList<String> workerList = DBConnection.getWorkersName();
		worker.setItems(workerList);
	}

	@FXML
	public void logIn() {
		String user = worker.getValue();
		String pass = password.getText();
		if (checkLogin(user, pass)) {
			Info.createInfo(AlertType.CONFIRMATION, main.getPrimaryStage(), "LOGIN", "LOGIN", messages.getLoginOk());
			main.showMainView();
		} else {
			Info.createInfo(AlertType.ERROR, main.getPrimaryStage(), "LOGIN", "LOGIN", messages.getLoginError());
		}
	}

	private boolean checkLogin(String user, String pass) {
		// TODO Auto-generated method stub
		return true;
	}

}
