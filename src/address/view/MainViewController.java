package address.view;

import address.model.Company;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class MainViewController extends BaseController {

	@FXML
	private Label companyLabel;
	@FXML
	private TabPane tabPanel;
	@FXML
	private Tab workersTab;
	@FXML
	private Tab detailsTab;
	@FXML
	private Tab hardwareTab;
	@FXML
	private Tab reportsTab;


	private Company company;

	public void setContext(Company company) {
		this.company = company;
		this.companyLabel.setText("Witaj w HardwareManager");
		initialize();

	}

	private void initialize() {
		main.showWorkerOverview(workersTab);
		main.showCompanyDetailsOverview(detailsTab);
		main.showHardwareOverview(hardwareTab);
	}


}