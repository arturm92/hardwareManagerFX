
package address;

import java.io.IOException;
import java.sql.Connection;

import address.dao.DBConnection;
import address.model.Person;
import address.model.Worker;
import address.view.MenuBarController;
import address.view.ProfileOverviewController;
import address.view.StartViewController;
import address.view.HardwareDeviceOverviewController;
import address.view.RootLayoutController;
import address.view.WorkerOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage primaryStage;
	private SplitPane rootLayout;
	private ObservableList<Person> personData = FXCollections.observableArrayList();
	private AnchorPane menuBar;
	private MenuBarController menuBarController;
	private Worker worker;
	private Connection conn;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("HardwareManager");
		//this.primaryStage.getIcons().add(new Image("https://example.com/javaicon.png"));
		this.init();
		Scene scene = new Scene(rootLayout);
		this.primaryStage.setScene(scene);
		this.primaryStage.setResizable(false);
		this.primaryStage.show();
	}

	public void init() {
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
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
			rootLayout = (SplitPane) loader.load();
			rootLayout.setDividerPosition(0, 2.0f);
			RootLayoutController controller = loader.getController();
			controller.setMain(this);
			controller.init();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showStartView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/StartView.fxml"));
			AnchorPane startView = (AnchorPane) loader.load();

			FXMLLoader loader2 = new FXMLLoader();
			loader2.setLocation(Main.class.getResource("view/MenuBar.fxml"));
			menuBar = (AnchorPane) loader2.load();

			rootLayout.getItems().setAll(menuBar, startView);

			StartViewController controller = loader.getController();
			controller.setMain(this);
			controller.initialize();

			menuBarController = loader2.getController();
			menuBarController.setMain(this);
			menuBarController.initialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * public void showMainView(Worker worker) { try { FXMLLoader loader = new
	 * FXMLLoader();
	 * loader.setLocation(Main.class.getResource("view/MainView.fxml"));
	 * mainOverview = (AnchorPane) loader.load(); //
	 * rootLayout.setCenter(mainOverview);
	 * 
	 * MainViewController controller = loader.getController();
	 * controller.setMain(this); controller.setContext(worker); //
	 * primaryStage.setFullScreen(true); // primaryStage.show(); } catch
	 * (Exception e) { e.printStackTrace(); } }
	 */

	public void showWorkerOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/WorkerOverview.fxml"));
			AnchorPane workerOverview = (AnchorPane) loader.load();
			rootLayout.getItems().setAll(menuBar, workerOverview);

			WorkerOverviewController controller = loader.getController();
			controller.setMain(this);
			controller.initialize();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

/*	public void showHardwareOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/HardwareItemsOverview.fxml"));
			AnchorPane hardwareItemsOverview = (AnchorPane) loader.load();
			rootLayout.getItems().setAll(menuBar, hardwareItemsOverview);

			HardwareItemOverviewController controller = loader.getController();
			controller.setMain(this);
			controller.initialize();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}*/
	
	public void showHardwareDeviceOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/HardwareDeviceOverview.fxml"));
			AnchorPane hardwareDeviceOverview = (AnchorPane) loader.load();
			rootLayout.getItems().setAll(menuBar, hardwareDeviceOverview);

			HardwareDeviceOverviewController controller = loader.getController();
			controller.setMain(this);
			controller.initialize();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void loginUser(Worker loggedWorker) {
		try {
			worker = loggedWorker;
			showProfileOverview();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void showProfileOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/ProfileOverview.fxml"));
			AnchorPane profileOverview = (AnchorPane) loader.load();
			ProfileOverviewController controller = loader.getController();
			menuBarController.getMenuBar().setDisable(false);
			menuBarController.setUserInBar();
			rootLayout.getItems().setAll(menuBar, profileOverview);
			controller.setMain(this);
			controller.initialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

}