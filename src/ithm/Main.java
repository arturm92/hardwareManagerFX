
package ithm;

import java.io.IOException;
import java.sql.Connection;

import ithm.dao.DBConnection;
import ithm.model.HardwareDelivery;
import ithm.model.HardwareDeviceToGive;
import ithm.model.User;
import ithm.model.Worker;
import ithm.view.GetDeliveryOverviewController;
import ithm.view.GiveDeviceOverviewController;
import ithm.view.GiveDeviceToUserController;
import ithm.view.HardwareDeviceOverviewController;
import ithm.view.HardwareDeviceOverviewControllerForUser;
import ithm.view.HistoryOverviewController;
import ithm.view.MenuBarController;
import ithm.view.NewHardwareDeviceInDeliveryController;
import ithm.view.ProfileOverviewController;
import ithm.view.ReportOverviewController;
import ithm.view.RootLayoutController;
import ithm.view.StartViewController;
import ithm.view.WorkerOverviewController;
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
	private AnchorPane menuBar;
	private MenuBarController menuBarController;
	private Connection conn;
	public Worker loggedUser;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("HardwareManager");
		this.init();
		rootLayout.setDividerPositions(new double[]{0.2});
		Scene scene = new Scene(rootLayout);
		this.primaryStage.setScene(scene);
		this.primaryStage.setResizable(false);
		this.primaryStage.show();
	}

	public void init() {
		try {
			setConn(DBConnection.createConnection());
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
			controller.init();

			menuBarController = loader2.getController();
			menuBarController.setMain(this);
			menuBarController.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loginUser(Worker loggedWorker) {
		try {
			this.loggedUser = loggedWorker;
			menuBarEnabled();
			showMyHardwareDeviceOverview();
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
			loader.setLocation(Main.class.getResource("view/WorkerOverview2.fxml"));
			AnchorPane workerOverview = (AnchorPane) loader.load();
			rootLayout.getItems().setAll(menuBar, workerOverview);

			WorkerOverviewController controller = loader.getController();
			controller.setMain(this);
			controller.init();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * public void showHardwareOverview() { try { FXMLLoader loader = new
	 * FXMLLoader(); loader.setLocation(Main.class.getResource(
	 * "view/HardwareItemsOverview.fxml")); AnchorPane hardwareItemsOverview =
	 * (AnchorPane) loader.load(); rootLayout.getItems().setAll(menuBar,
	 * hardwareItemsOverview);
	 * 
	 * HardwareItemOverviewController controller = loader.getController();
	 * controller.setMain(this); controller.initialize();
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * }
	 */

	public void showHardwareDeviceOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/HardwareDeviceOverview.fxml"));
			AnchorPane hardwareDeviceOverview = (AnchorPane) loader.load();
			rootLayout.getItems().setAll(menuBar, hardwareDeviceOverview);

			HardwareDeviceOverviewController controller = loader.getController();
			controller.setMain(this);
			controller.init(null);

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
			rootLayout.getItems().setAll(menuBar, profileOverview);
			controller.setMain(this);
			controller.init(loggedUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void menuBarEnabled() {
		menuBarController.getMenuBar().setDisable(false);
		menuBarController.setUserInBar();
	}

	public void showHistoryOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/HistoryOverview.fxml"));
			AnchorPane historyOverview = (AnchorPane) loader.load();
			HistoryOverviewController controller = loader.getController();

			rootLayout.getItems().setAll(menuBar, historyOverview);
			controller.setMain(this);
			controller.init(loggedUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showGetDeliveryOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/GetDeliveryOverview.fxml"));
			AnchorPane getDeliveryOverview = (AnchorPane) loader.load();
			GetDeliveryOverviewController controller = loader.getController();
			rootLayout.getItems().setAll(menuBar, getDeliveryOverview);
			controller.setMain(this);
			controller.init(loggedUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showGiveDeviceOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/GiveDeviceOverview.fxml"));
			AnchorPane giveDeviceOverview = (AnchorPane) loader.load();
			GiveDeviceOverviewController controller = loader.getController();
			rootLayout.getItems().setAll(menuBar, giveDeviceOverview);
			controller.setMain(this);
			controller.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showReportOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/ReportOverview.fxml"));
			AnchorPane reportOverview = (AnchorPane) loader.load();
			ReportOverviewController controller = loader.getController();
			rootLayout.getItems().setAll(menuBar, reportOverview);
			controller.setMain(this);
			controller.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showMyHardwareDeviceOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/HardwareDeviceOverviewForUser.fxml"));
			AnchorPane hardwareDeviceOverviewForUser = (AnchorPane) loader.load();
			rootLayout.getItems().setAll(menuBar, hardwareDeviceOverviewForUser);
			HardwareDeviceOverviewControllerForUser controller = loader.getController();
			controller.setMain(this);
			controller.init(loggedUser);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void showHardwareDeviceOverviewForUser(User user) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/HardwareDeviceOverviewForUser.fxml"));
			AnchorPane hardwareDeviceOverviewForUser = (AnchorPane) loader.load();
			HardwareDeviceOverviewControllerForUser controller = loader.getController();
			controller.setMain(this);
			controller.init(user);
			Stage stage = new Stage();
			stage.setTitle("Sprzêt komputerowy nale¿¹cy do " + user.getUserName());
			stage.setScene(new Scene(hardwareDeviceOverviewForUser));
			stage.setHeight(600);
			stage.setWidth(1000);
			stage.setAlwaysOnTop(true);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void showHardwareDeviceToGiveSmall(HardwareDeviceToGive item,GiveDeviceOverviewController parent) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/GiveDeviceToUserView.fxml"));
			AnchorPane giveDeviceToUserController = (AnchorPane) loader.load();
			GiveDeviceToUserController controller = loader.getController();
			controller.setMain(this);
			Stage stage = new Stage();
			controller.init(item, loggedUser, stage, parent);
			stage.setTitle("Przekazanie zasobu " + item.getInternalNumber());
			stage.setScene(new Scene(giveDeviceToUserController));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Worker getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(Worker loggedUser) {
		this.loggedUser = loggedUser;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public void showNewHardwareDeviceInDelivery(HardwareDelivery delivery , GetDeliveryOverviewController parent) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/NewHardwareDeviceInDelivery.fxml"));
			AnchorPane newHardwareDeviceInDeliveryController = (AnchorPane) loader.load();
			NewHardwareDeviceInDeliveryController controller = loader.getController();
			controller.setMain(this);
			Stage stage = new Stage();
			controller.init(parent, delivery.getId(), stage);
			stage.setTitle("Dodaj urz¹dzenie w dostawie" + delivery.getInvoice() + " od "+ delivery.getCompany());
			stage.setScene(new Scene(newHardwareDeviceInDeliveryController));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showHistoryOverviewForUser(User user) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/HistoryOverview.fxml"));
			AnchorPane historyOverviewForUser = (AnchorPane) loader.load();
			HistoryOverviewController controller = loader.getController();

			controller.setMain(this);
			controller.init(user);
			
			Stage stage = new Stage();
			stage.setTitle("Sprzêt komputerowy nale¿¹cy do " + user.getUserName());
			stage.setScene(new Scene(historyOverviewForUser));
			stage.setHeight(600);
			stage.setWidth(1000);
			stage.setAlwaysOnTop(true);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}