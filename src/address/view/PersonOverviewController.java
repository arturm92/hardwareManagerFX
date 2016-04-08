/*package address.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.Connection;

import address.Main;
import address.dao.DBConnection;
import address.model.Person;
import address.util.DateUtil;
import address.util.Info;

public class PersonOverviewController extends BaseController{
	@FXML
	private TableView<Person> personTable;
	@FXML
	private TableColumn<Person, String> firstNameColumn;
	@FXML
	private TableColumn<Person, String> lastNameColumn;

	@FXML
	private Label firstNameLabel;
	@FXML
	private Label lastNameLabel;
	@FXML
	private Label streetLabel;
	@FXML
	private Label postalCodeLabel;
	@FXML
	private Label cityLabel;
	@FXML
	private Label birthdayLabel;

	// Reference to the main application.

	*//**
	 * The constructor. The constructor is called before the initialize()
	 * method.
	 *//*
	public PersonOverviewController() {
	}

	*//**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 *//*
	@FXML
	private void initialize() {
		// Initialize the person table with the two columns.
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

		// Clear person details.
		showPersonDetails(null);

		// Listen for selection changes and show the person details when
		// changed.
		personTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));
	}

	*//**
	 * Called when the user clicks on the delete button.
	 *//*
	@FXML
	private void handleDeletePerson() {
		int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			personTable.getItems().remove(selectedIndex);
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(main.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Person Selected");
			alert.setContentText("Please select a person in the table.");

			alert.showAndWait();
		}
	}

	*//**
	 * Is called by the main application to give a reference back to itself.
	 *
	 * @param mainApp
	 *//*
	public void setMain(Main main) {
		this.main = main;

		// Add observable list data to the table
		personTable.setItems(main.getPersonData());
	}

	private void showPersonDetails(Person person) {
		if (person != null) {
			// Fill the labels with info from the person object.
			firstNameLabel.setText(person.getFirstName());
			lastNameLabel.setText(person.getLastName());
			streetLabel.setText(person.getStreet());
			postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
			cityLabel.setText(person.getCity());
			birthdayLabel.setText(DateUtil.format(person.getBirthday()));
		} else {
			// Person is null, remove all the text.
			firstNameLabel.setText("");
			lastNameLabel.setText("");
			streetLabel.setText("");
			postalCodeLabel.setText("");
			cityLabel.setText("");
			birthdayLabel.setText("");
		}
	}

	*//**
	 * Called when the user clicks the new button. Opens a dialog to edit
	 * details for a new person.
	 *//*
	@FXML
	private void handleNewPerson() {
		Person tempPerson = new Person();
		boolean okClicked = main.showPersonEditDialog(tempPerson);
		if (okClicked) {
			main.getPersonData().add(tempPerson);
		}
	}

	*//**
	 * Called when the user clicks the edit button. Opens a dialog to edit
	 * details for the selected person.
	 *//*
	@FXML
	private void handleEditPerson() {
		Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
		if (selectedPerson != null) {
			boolean okClicked = main.showPersonEditDialog(selectedPerson);
			if (okClicked) {
				showPersonDetails(selectedPerson);
			}

		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(main.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Person Selected");
			alert.setContentText("Please select a person in the table.");

			alert.showAndWait();
		}
	}


	//moje metody
	@FXML
	private void connectDatabase() {
		try {
			conn = DBConnection.createConnection();
			if (conn != null) {
				Info.createInfo(AlertType.INFORMATION, main.getPrimaryStage(), "Good", "Connection to database",
						"Connected to database");
			} else {
				Info.createInfo(AlertType.ERROR, main.getPrimaryStage(), "Not Good", "Connection to database",
						"Failed to connect to database");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}*/