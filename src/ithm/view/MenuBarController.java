package ithm.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ithm.Main;
import ithm.model.Worker;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;

public class MenuBarController {

	@FXML
	private AnchorPane menuBar;
	@FXML
	private Button myDevices;
	@FXML
	private Button allDevices;
	@FXML
	private Button reports;
	@FXML
	private Button myHistory;
	@FXML
	private Button getDelivery;
	@FXML
	private Button giveDevice;
	@FXML
	private Button users;
	@FXML
	private Button myProfile;
	@FXML
	private Button adminPanel;
	@FXML
	private Label userInfo;
	@FXML
	private DatePicker calendar;

	private Main main;
	private List<Button> buttons = new ArrayList<Button>();
	private Worker loggedWorker;

	public void init() {
		menuBar.setDisable(true);
		calendar.setValue(LocalDate.now());
		initButtons();
	}

	private void initButtons() {
		buttons.add(allDevices);
		buttons.add(myDevices);
		buttons.add(reports);
		buttons.add(myHistory);
		buttons.add(getDelivery);
		buttons.add(giveDevice);
		buttons.add(users);
		buttons.add(myProfile);
		buttons.add(adminPanel);
	}

	public MenuBarController() {

	}

	public AnchorPane getMenuBar() {
		return menuBar;
	}

	public void setUserInBar() {
		this.loggedWorker = main.getLoggedUser();
		userInfo.setText("Zalogowano jako: PL/" + loggedWorker.getFirstName() + " " + loggedWorker.getLastName());
		userInfo.setDisable(true);
		switch (loggedWorker.getRoleId()) {
		case 1:
			securityForEmployee();
			break;
		case 2:
			securityForHighEmployee();
			break;
		case 3:
			securityForMasterEmployee();
			break;
		case 4:
			securityForAdmin();
			break;
		default:
			securityForEmployee();
			break;
		}
	}

	private void resetOthers(String buttonName) {
		for (Button button : buttons) {
			if (!button.getId().equals(buttonName))
				button.setEffect(null);
		}

	}

	@FXML
	public void goToProfileOverview() {
		DropShadow shadow = new DropShadow();
		myProfile.setEffect(shadow);
		resetOthers("myProfile");
		main.showProfileOverview();
	}

	@FXML
	public void goToGiveDeviceOverview() {
		DropShadow shadow = new DropShadow();
		giveDevice.setEffect(shadow);
		resetOthers("giveDevice");
		main.showGiveDeviceOverview();
	}

	@FXML
	public void goToWorkersOverview() {
		DropShadow shadow = new DropShadow();
		users.setEffect(shadow);
		resetOthers("users");
		main.showWorkerOverview();
	}

	@FXML
	public void goToHardwareOverview() {
		DropShadow shadow = new DropShadow();
		allDevices.setEffect(shadow);
		resetOthers("allDevices");
		main.showHardwareDeviceOverview();
	}

	@FXML
	public void goToMyHardwareOverview() {
		DropShadow shadow = new DropShadow();
		myDevices.setEffect(shadow);
		resetOthers("myDevices");
		main.showMyHardwareDeviceOverview();
	}

	@FXML
	public void goToHistoryOverview() {
		DropShadow shadow = new DropShadow();
		myHistory.setEffect(shadow);
		resetOthers("myHistory");
		main.showHistoryOverview();
	}

	@FXML
	public void goToDeliveryOverview() {
		DropShadow shadow = new DropShadow();
		getDelivery.setEffect(shadow);
		resetOthers("getDelivery");
		main.showGetDeliveryOverview();
	}

	@FXML
	public void goToReportOverview() {
		DropShadow shadow = new DropShadow();
		reports.setEffect(shadow);
		resetOthers("reports");
		main.showReportOverview();
	}
	
	@FXML
	public void goToAdminPanel() {
		DropShadow shadow = new DropShadow();
		reports.setEffect(shadow);
		resetOthers("adminPanel");
		main.showAdminPanel();
	}

	private void securityForEmployee() { // zwyk³y pracownik
		for (Button button : buttons) {
			if (button.getId().equals("myDevices") || button.getId().equals("myHistory")
					|| button.getId().equals("myProfile")) {
				button.setDisable(false);
			} else {
				button.setDisable(true);
			}
		}

	}

	private void securityForHighEmployee() { // serwisant SD
		for (Button button : buttons) {
			if (button.getId().equals("reports") || button.getId().equals("myDevices")
					|| button.getId().equals("myHistory") || button.getId().equals("myProfile")) {
				button.setDisable(false);
			} else {
				button.setDisable(true);
			}
		}

	}

	private void securityForMasterEmployee() { // kierownik SD
		for (Button button : buttons) {
			if (button.getId().equals("adminPanel")) {
				button.setDisable(true);
			} else {
				button.setDisable(false);
			}
		}
	}

	private void securityForAdmin() { // admin
		for (Button button : buttons) {
			button.setDisable(false);
		}
	}

	public void setMain(Main object) {
		main = object;
	}
}
