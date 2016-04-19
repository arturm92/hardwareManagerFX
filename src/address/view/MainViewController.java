package address.view;

import address.model.Worker;
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
	@FXML
	private Label userLabel;

	private Worker worker = new Worker();

	public void setContext(Worker worker) {
		try{
			this.worker = worker;
			this.userLabel.setText(worker.getFirstName() + " " + worker.getLastName());
			this.companyLabel.setText("Witaj w HardwareManager");
			initialize();	
		}catch (Exception e){
			
		}
		

	}

	private void initialize() {
		main.showWorkerOverview(workersTab);
		main.showCompanyDetailsOverview(detailsTab);
		main.showHardwareOverview(hardwareTab);
	}


}
