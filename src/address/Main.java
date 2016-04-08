
package address;

import java.io.IOException;
import java.sql.Connection;

import address.dao.DBConnection;
import address.model.Company;
import address.model.Person;
import address.view.CompanyDetailsOverviewController;
import address.view.MainViewController;
import address.view.StartViewController;
import address.view.HardwareItemOverviewController;
import address.view.RootLayoutController;
import address.view.WorkerOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	private ObservableList<Person> personData = FXCollections.observableArrayList();
	private Connection conn;
	private AnchorPane mainOverview;
	//koment for komit
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("HardwareManager");
		init();
		Scene scene = new Scene(rootLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void init(){
		try {
			conn = DBConnection.createConnection();
			showRootLayout();
			showStartView();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}

	public ObservableList<Person> getPersonData() {
		return personData;
	}


	public void showRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			RootLayoutController controller = loader.getController();
			controller.setMain(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showStartView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/StartView.fxml"));
			AnchorPane startView = (AnchorPane) loader.load();
			rootLayout.setBottom(startView);
			StartViewController controller = loader.getController();
			controller.setMain(this);
			controller.initialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showMainView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/MainView.fxml"));
			mainOverview = (AnchorPane) loader.load();
			rootLayout.setBottom(mainOverview);

			MainViewController controller = loader.getController();
			controller.setMain(this);
			controller.setContext(null);

			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showWorkerOverview(Tab tab) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/WorkerOverview.fxml"));
			AnchorPane workerOverview = (AnchorPane) loader.load();
			WorkerOverviewController controller = loader.getController();
			controller.setMain(this);
			controller.initialize();
			tab.setContent(workerOverview);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showCompanyDetailsOverview(Tab tab) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/CompanyDetailsOverview.fxml"));
			AnchorPane companyDetailsOverview = (AnchorPane) loader.load();
			CompanyDetailsOverviewController controller = loader.getController();
			controller.setMain(this);
			controller.initialize();
			tab.setContent(companyDetailsOverview);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public void showHardwareOverview(Tab tab) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/HardwareItemsOverview.fxml"));
			AnchorPane hardwareItemsOverview = (AnchorPane) loader.load();
			HardwareItemOverviewController controller = loader.getController();
			controller.setMain(this);
			controller.initialize();
			tab.setContent(hardwareItemsOverview);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}





}