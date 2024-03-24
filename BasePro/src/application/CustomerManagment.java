package application;

import javafx.application.Application;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Optional;

public class CustomerManagment extends Application {
	private TableView<Service> servicesTableView = new TableView<>();
	private TableView<Appointment> appointmentsTableView = new TableView<>();
	private PasswordManager passwordManager;
	private Customer loggedInCustomer; // Track the logged-in customer
	private Connection connection;
	private TextField idTextField;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		passwordManager = new PasswordManager();

		VBox mainVBox = new VBox();
		mainVBox.setAlignment(Pos.TOP_CENTER);
		mainVBox.setSpacing(20);
		mainVBox.setPadding(new Insets(20));

		Label titleLabel = new Label("Welcome to Our Beauty Center ^-^");
		titleLabel.setStyle(
				"-fx-font-family: 'Verdana'; -fx-font-size: 20px; -fx-text-fill: black; -fx-font-weight: bold;");

		HBox titleBox = new HBox(titleLabel);
		titleBox.setAlignment(Pos.CENTER);
		titleBox.setBackground(
				new Background(new BackgroundFill(javafx.scene.paint.Color.PINK, CornerRadii.EMPTY, Insets.EMPTY)));
		BorderPane borderPane = new BorderPane();
		HBox hbox = new HBox();
		VBox topVBox = new VBox(titleBox);
		borderPane.setTop(topVBox);

		VBox loginBox = createLoginBox();
		mainVBox.getChildren().addAll(borderPane, loginBox);

		// Load the background image
		File file = new File("C:\\Users\\user\\Desktop\\java\\DataBase3\\welcomeCustomer.jpg");
		Image backgroundImage = new Image(file.toURI().toString());

		// Set the background
		BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
		mainVBox.setBackground(new Background(background));

		Image icon = new Image(new File("C:\\Users\\user\\Desktop\\java\\DataBase3\\logo.png").toURI().toString());

		// Set the icon for the stage
		primaryStage.getIcons().add(icon);
		Scene scene = new Scene(mainVBox, 400, 400);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Hey Customers");
		primaryStage.show();
	}

	private VBox createLoginBox() {
		VBox loginBox = new VBox();
		loginBox.setAlignment(Pos.CENTER);
		loginBox.setSpacing(10);

		TextField userIdField = new TextField();
		PasswordField passwordField = new PasswordField();
		Button signInButton = new Button("Sign In");
		Button cancelButton = new Button("Cancel");

		double buttonWidth = 150; // Adjust the width as needed
		double buttonHeight = 30; // Adjust the height as needed

		signInButton.setMinSize(buttonWidth, buttonHeight);
		cancelButton.setMinSize(buttonWidth, buttonHeight);

		Label userIdLabel = new Label("User ID : ");
		Label passwordLabel = new Label("Password : ");

		// Set styles for labels
		userIdLabel.setStyle("-fx-font-size: 14; -fx-font-weight: bold;");
		passwordLabel.setStyle("-fx-font-size: 14; -fx-font-weight: bold;");

		HBox userIdBox = new HBox(userIdLabel, userIdField);
		userIdBox.setAlignment(Pos.CENTER);
		userIdBox.setMaxWidth(Double.MAX_VALUE);
		userIdBox.setPadding(new Insets(10, 0, 0, 0)); // Increase padding to move it lower

		HBox passwordBox = new HBox(passwordLabel, passwordField);
		passwordBox.setAlignment(Pos.CENTER);
		passwordBox.setMaxWidth(Double.MAX_VALUE);
		passwordBox.setPadding(new Insets(10, 0, 0, 0)); // Increase padding to move it lower

		signInButton.setStyle("-fx-font-weight: bold; -fx-text-fill: white; -fx-background-color: #FFB6C1;");
		cancelButton.setStyle("-fx-font-weight: bold; -fx-text-fill: white; -fx-background-color: #FFB6C1;");

		loginBox.getChildren().addAll(userIdBox, passwordBox, signInButton, cancelButton);
		loginBox.setSpacing(10);
		loginBox.setPadding(new Insets(10));

		signInButton.setOnAction(
				event -> signIn(userIdField.getText(), passwordField.getText(), userIdField, passwordField));
		cancelButton.setOnAction(event -> clearLoginFields(userIdField, passwordField));

		return loginBox;
	}

	private void signIn(String userId, String password, TextField userIdField, PasswordField passwordField) {
		if (passwordManager.authenticateUser(userId, password)) {
			showMainScreen();
			// Clear the text fields after successful authentication
			userIdField.clear();
			passwordField.clear();
		} else {
			showAlert("Error", "Incorrect User ID or Password 🙁");
			userIdField.clear();
			passwordField.clear();
		}
	}

	private void showMainScreen() {
		BorderPane mainPane = new BorderPane();

		// Adding welcome label
		Label welcomeLabel = new Label("Welcome to Our Beauty Center");
		welcomeLabel.setStyle(
				"-fx-font-family: 'Verdana'; -fx-font-size: 20px; -fx-text-fill: black; -fx-font-weight: bold;");

		HBox titleBox = new HBox(welcomeLabel);
		titleBox.setAlignment(Pos.CENTER);
		titleBox.setBackground(
				new Background(new BackgroundFill(javafx.scene.paint.Color.PINK, CornerRadii.EMPTY, Insets.EMPTY)));

		// Centering welcome label at the top
		BorderPane.setAlignment(titleBox, Pos.TOP_CENTER);

		mainPane.setTop(titleBox);

		VBox buttonsBox = new VBox(); // Use VBox for vertical alignment
		buttonsBox.setAlignment(Pos.CENTER); // Center align the buttons

		Button servicesButton = createStyledButton("Our Services");
		Button productsButton = createStyledButton("Products");
		Button reserveButton = createStyledButton("Reserve Appointment");
		Button myAppointmentsButton = createStyledButton("My Appointments");
		Button myPaymentsButton = createStyledButton("My Payments");

		// Set button size and style
		double buttonWidth = 150; // Adjust the width as needed
		double buttonHeight = 30; // Adjust the height as needed

		servicesButton.setMinSize(buttonWidth, buttonHeight);
		productsButton.setMinSize(buttonWidth, buttonHeight);
		reserveButton.setMinSize(buttonWidth, buttonHeight);
		myAppointmentsButton.setMinSize(buttonWidth, buttonHeight);
		myPaymentsButton.setMinSize(buttonWidth, buttonHeight);

		buttonsBox.getChildren().addAll(servicesButton, productsButton, reserveButton, myAppointmentsButton,
				myPaymentsButton);
		buttonsBox.setSpacing(30);

		mainPane.setCenter(buttonsBox); // Set the buttons in the center
		servicesButton.setOnAction(e -> new ServicesTable2().start(new Stage()));

		// Load the background image
		File file = new File("C:\\Users\\user\\Desktop\\java\\DataBase3\\beauty.jpg");
		Image backgroundImage = new Image(file.toURI().toString());

		// Set the background
		BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
		mainPane.setBackground(new Background(background));

		Image icon = new Image(new File("C:\\Users\\user\\Desktop\\java\\DataBase3\\logo.png").toURI().toString());

		Scene mainScene = new Scene(mainPane, 600, 350);
		Stage mainStage = new Stage();
		// Set the icon for the stage
		mainStage.getIcons().add(icon);
		mainStage.setScene(mainScene);
		mainStage.setTitle("Customer Management - Main Screen");
		mainStage.show();
	}

	private Button createStyledButton(String text) {
		Button button = new Button(text);
		button.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
		return button;
	}

	private Tab createAppointmentsTab() {
		appointmentsTableView.setEditable(true);

		TableColumn<Appointment, Integer> appIdColumn = new TableColumn<>("ID");
		appIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointment_id"));

		TableColumn<Appointment, String> appTimeColumn = new TableColumn<>("Appointment Time");
		appTimeColumn.setCellValueFactory(new PropertyValueFactory<>("appointment_time"));

		TableColumn<Appointment, Integer> serviceIdColumn = new TableColumn<>("Service ID");
		serviceIdColumn.setCellValueFactory(new PropertyValueFactory<>("Service_id"));

		appointmentsTableView.getColumns().addAll(appIdColumn, appTimeColumn, serviceIdColumn);

		Tab appointmentsTab = new Tab("My Appointments", appointmentsTableView);
		return appointmentsTab;
	}

	private void loadAppointments() {
		// TODO Auto-generated method stub

	}

	private void loadServices() {
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/customer_database_schema",
				"rasha", "1234")) {
			String query = "SELECT * FROM services";
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				ResultSet resultSet = preparedStatement.executeQuery();

				ObservableList<Service> servicesList = FXCollections.observableArrayList();

				while (resultSet.next()) {
					int id = resultSet.getInt("id");
					String name = resultSet.getString("name");
					String description = resultSet.getString("description");
					int duration = resultSet.getInt("duration");
					double price = resultSet.getDouble("price");

					Service service = new Service(id, name, description, duration, price);
					servicesList.add(service);
				}

				servicesTableView.setItems(servicesList);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Tab createServicesTab() {
		servicesTableView.setEditable(true);

		TableColumn<Service, Integer> idColumn = new TableColumn<>("ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

		TableColumn<Service, String> serviceNameColumn = new TableColumn<>("Name");
		serviceNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<Service, String> serviceDescriptionColumn = new TableColumn<>("Description");
		serviceDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

		TableColumn<Service, Integer> serviceDurationColumn = new TableColumn<>("Duration");
		serviceDurationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));

		TableColumn<Service, Double> servicePriceColumn = new TableColumn<>("Price");
		servicePriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

		servicesTableView.getColumns().addAll(idColumn, serviceNameColumn, serviceDescriptionColumn,
				serviceDurationColumn, servicePriceColumn);

		Tab servicesTab = new Tab("Our Services", servicesTableView);

		VBox buttonsBox = new VBox();
		Button reserveButton = new Button("Reserve Appointment");
		Button myAppointmentsButton = new Button("My Appointments");
		Button myPaymentsButton = new Button("My Payments");

		reserveButton.setOnAction(event -> showReservationDialog());
		myAppointmentsButton.setOnAction(event -> showMyAppointments());
		myPaymentsButton.setOnAction(event -> showMyPayments());

		buttonsBox.getChildren().addAll(reserveButton, myAppointmentsButton, myPaymentsButton);
		buttonsBox.setSpacing(10);
		buttonsBox.setPadding(new Insets(10));

		BorderPane servicesTabPane = new BorderPane();
		servicesTabPane.setCenter(servicesTableView);
		servicesTabPane.setBottom(buttonsBox);

		servicesTab.setContent(servicesTabPane);
		return servicesTab;
	}

	private Tab createProductsTab() {
		// Placeholder for the Products tab - you can add functionality later
		TableView<Product> productsTableView = new TableView<>();
		Tab productsTab = new Tab("Products", productsTableView);
		return productsTab;
	}

	private void showReservationDialog() {
		// Placeholder for showing reservation dialog
	}

	private void showMyAppointments() {
		// Placeholder for showing user's appointments
	}

	private void showMyPayments() {
		// Placeholder for showing user's payments
	}

	private void showAlert(String title, String content) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);
		alert.showAndWait();
	}

	private void clearLoginFields(TextField userIdField, PasswordField passwordField) {
		userIdField.clear();
		passwordField.clear();
	}

	// Placeholder class for ServiceReservation
	private static class ServiceReservation {
		private int id;
		private String serviceName;
		private String time;

		// Constructors, getters, and setters can be added as needed
	}

	// Placeholder class for Product
	private static class Product {
		// Add necessary fields, constructors, getters, and setters
	}

	private static class PasswordManager {
		private static final String JDBC_URL = "jdbc:mysql://localhost:3306/customer_database_schema";
		private static final String DB_USER = "rasha";
		private static final String DB_PASSWORD = "1234";

		public boolean authenticateUser(String userId, String password) {
			// Convert the provided password to lowercase for case-insensitive comparison
			String lowercasePassword = password.toLowerCase();

			try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
				String query = "SELECT COUNT(*) FROM customer WHERE id = ? AND LOWER(name) = ?";
				try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
					preparedStatement.setString(1, userId);
					preparedStatement.setString(2, lowercasePassword);

					ResultSet resultSet = preparedStatement.executeQuery();
					if (resultSet.next()) {
						int count = resultSet.getInt(1);
						return count > 0; // If count is greater than 0, the user is authenticated
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false; // Return false in case of any errors
		}
	}

}