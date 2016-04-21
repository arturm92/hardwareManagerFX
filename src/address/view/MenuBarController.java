package address.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class MenuBarController extends BaseController {

	@FXML
	private AnchorPane menuBar;
	@FXML
	private Button userInfoButton;

	public MenuBarController() {
		// initialize();
	}

	public void initialize() {
		menuBar.setDisable(true);
	}

	public AnchorPane getMenuBar() {
		return menuBar;
	}

	public void setUserInBar() {
		userInfoButton.setText("logged" + main.getWorker().getName());
		userInfoButton.setDisable(true);
	}
	
	@FXML
	public void goToProfileOverview(){
		main.showProfileOverview();
	}
	
	@FXML
	public void goToWorkersOverview(){
		main.showWorkerOverview();
	}
	
	@FXML
	public void goToHardwareOverview(){
		main.showHardwareDeviceOverview();
	}
}
