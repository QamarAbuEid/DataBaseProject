package application;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.converter.IntegerStringConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import application.Staff;
import javafx.util.converter.DoubleStringConverter;
import java.sql.PreparedStatement;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static javafx.stage.Modality.NONE;

import java.io.File;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.ArrayList;

public class Main extends Application {

	private ArrayList<Staff> data;
	private ObservableList<Staff> dataList;

	private ArrayList<Customer> data2;
	private ObservableList<Customer> dataList2;
	private ArrayList<Service> data3;
	private ObservableList<Service> dataList3;
	private ArrayList<Staff_Appointment> data4;
	private ObservableList<Staff_Appointment> dataList4;
	private ArrayList<Product> data5;
	private ObservableList<Product> dataList5;
	private ArrayList<Payment> data7;
	private ObservableList<Payment> dataList7;
	Tab tab1 = new Tab("DashBoard");
	Tab tab2 = new Tab("Payment");
	Tab tab3 = new Tab("Staff_Appointment");
	Tab tab4 = new Tab("Customer");
	Tab tab5 = new Tab("Product");
	Tab tab6 = new Tab("Service");
	Tab tab7 = new Tab("Staff");
	Tab tab8 = new Tab("Section");
	VBox chartsContainer = new VBox(10);
	TabPane tabPane = new TabPane();
	TextArea textarea = new TextArea("");
	TextArea text=new TextArea("");
	TextArea text2=new TextArea("");
	HBox textv=new HBox(30);

	private static Connection con;

	// the details that I need for connection

	private String dbURL;
	private String dbUsername = "root";
	private String dbPassword = "112233";
	private String URL = "127.0.0.1";
	private String port = "3306";
	private String dbName = "beautycenter";

	@Override
	public void start(Stage primaryStage) {
		Font font = Font.font("Comic Sans MS", FontPosture.ITALIC, 30);
		BorderPane root = new BorderPane();
		BorderPane root1 = new BorderPane();
		BorderPane root2 = new BorderPane();
		TabPane tabPane = new TabPane();
		Stage managerStage = new Stage();
		Stage managerStage2 = new Stage();
		Scene managerscene = new Scene(root1, 600, 700);
		VBox vbox = new VBox(20);
		HBox h1 = new HBox(10);
		HBox h = new HBox(10);
		HBox h2 = new HBox(10);
		 
		Font font2 = Font.font("Comic Sans MS", FontPosture.ITALIC, 30);
		Label welcom = new Label("WELCOM TO OUR BEAUTY CENTER");
		// Set text color (textFill) to white
		welcom.setTextFill(Color.WHITE);

		// Set the background color to blue
		welcom.setStyle("-fx-background-color: lightpink;");

		// Set other label properties
		welcom.setAlignment(Pos.CENTER);
		// titleLabel.setStyle("-fx-font-size: 40px;"); // Set font size (adjust as
		// needed)

		Font customFont = new Font("Comic Sans MS", 35);
		Font font3 = Font.font("Comic Sans MS", FontPosture.ITALIC, 15);
		Button click = new Button("Next Page");
		click.setTextFill(Color.WHITE);
		click.setStyle("-fx-background-color: lightpink");
		click.setFont(font3);

		welcom.setFont(font2);

		TextField id = new TextField("");
		TextField password = new TextField("");
		Label ID = new Label("ID            ");
		Label Password = new Label("Password");
		Button login = new Button("LOG IN");
		Button Signup = new Button("Sign UP");
		Button cancel = new Button("CANCEL");
		h.getChildren().addAll(login, Signup);

		Font font1 = Font.font("Comic Sans MS", FontPosture.ITALIC, 20);
		login.setStyle("-fx-background-color: PINK");
		Signup.setStyle("-fx-background-color: PINK");
		cancel.setStyle("-fx-background-color: palevioletred");
		login.setFont(font1);
		Signup.setFont(font1);
		cancel.setFont(font1);
		h1.getChildren().addAll(ID, id);
		h2.getChildren().addAll(Password, password);
		Image icon = new Image(new File(
				"C:\\Users\\DELL\\eclipse-workspace\\BasePro\\403648947_244347272023983_6374207351074528467_n.png")
				.toURI().toString());

		// Set the icon for the stage
		primaryStage.getIcons().add(icon);
		Image image2 = new Image("C:\\Users\\DELL\\Downloads\\400991643_222181100931601_3449842467092079839_n.jpg");
		ImageView imageView2 = new ImageView(image2);
		imageView2.setPreserveRatio(true);

		BackgroundImage backgroundImage = new BackgroundImage(imageView2.getImage(), null, null, null, null);

		Background background2 = new Background(backgroundImage);

		data = new ArrayList<>();
		data5 = new ArrayList<>();
		data4=new ArrayList<>(); 
		try {

			getData();
			getData2();
            getData3();
			dataList = FXCollections.observableArrayList(data);
			dataList5 = FXCollections.observableArrayList(data5);
			dataList4= FXCollections.observableArrayList(data4);

			// really bad Method
			// tableView(primaryStage);
			// root.setLeft(imageView);
			// root.setRight(vbox);
			root.setBackground(background2);
			root.setTop(welcom);
			BorderPane.setAlignment(welcom, Pos.TOP_CENTER);

			root.setBottom(click);
			BorderPane.setAlignment(click, Pos.BOTTOM_RIGHT);

			click.setOnAction(event -> {
				VBox vbox1 = new VBox(10);
				Label who = new Label("Who Are You");
				who.setFont(font2);
				who.setTextFill(Color.WHITE);

				// Set the background color to blue
				who.setStyle("-fx-background-color: lightpink;");

				// Set other label properties

				Button manager = new Button("Manager");
				manager.setStyle("-fx-background-color: pink; -fx-text-fill: white;");
				Button Staff = new Button("Staff");
				Staff.setStyle("-fx-background-color: pink; -fx-text-fill: white;");
				Button Customer = new Button("Customer");
				Customer.setStyle("-fx-background-color: pink; -fx-text-fill: white;");

				vbox1.getChildren().addAll(who, manager, Staff, Customer);
				Stage stage = new Stage();
				// Assuming root2 is a VBox or another layout you've defined

				Scene scene = new Scene(root2, 600, 700);

				// Use the correct image path
				try {
					String imagePath = "C:\\Users\\DELL\\Downloads\\375702597_2972191316248508_8712443534165614089_n.jpg";

					Image image3 = new Image(imagePath);
					ImageView imageView3 = new ImageView(image3);
					imageView2.setPreserveRatio(true);

					BackgroundImage backgroundImage2 = new BackgroundImage(imageView3.getImage(), null, null, null,
							null);

					BackgroundFill backgroundFill2 = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY);
					// Create a Background with the BackgroundFill
					Background background = new Background(backgroundImage2);
					// Add the image view to the root2 layout
					root2.setBackground(background);
					root2.setCenter(vbox1);
					vbox1.setPadding(new Insets(300, 200, 200, 200));

					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					////////////////////////////////////////////////////////////////////////////
					manager.setOnAction(even -> {
						Stage stage2 = new Stage();

						BorderPane root3 = new BorderPane();
						String imagePath1 = "file:///C:/Users/DELL/Downloads/375702597_2972191316248508_8712443534165614089_n.jpg";

						Image image4 = new Image(imagePath1);
						ImageView imageView4 = new ImageView(image4);
						imageView4.setPreserveRatio(true);

						BackgroundImage backgroundImage3 = new BackgroundImage(imageView4.getImage(), null, null, null,
								null);

						BackgroundFill backgroundFill3 = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY,
								Insets.EMPTY);
						// Create a Background with the BackgroundFill
						Background background1 = new Background(backgroundImage3);
						Label m = new Label("Welcome Manager");
						Label l1 = new Label("Please Enter Your Password");
						l1.setStyle("-fx-background-color: lightpink;");
						TextField t1 = new TextField("");

						m.setFont(font2);
						root3.setBackground(background1);
						HBox passwordBox = new HBox(15, l1, t1);
						VBox vv = new VBox(20);
						vv.getChildren().addAll(m, passwordBox);

						vv.setAlignment(Pos.CENTER);
						vv.setMaxWidth(Double.MAX_VALUE);
						vv.setPadding(new Insets(80, 10, 10, 10)); // Increase top padding to 50 units
						passwordBox.setPadding(new Insets(50, 50, 50, 150));

						Button loginButton = new Button("Login");
						loginButton.setStyle("-fx-background-color: pink; -fx-text-fill: white;");
						loginButton.setOnAction(e -> {

							if (t1.getText().equals("112233")) {
								tabPane.getTabs().addAll(tab1, tab2, tab3, tab4, tab5, tab6, tab7, tab8);
								Stage n = new Stage();
								Scene s = new Scene(tabPane, 600, 700);
								tab7.setOnSelectionChanged(EventHandler -> {
									if (tab7.isSelected()) {
									tableView(stage);
										

									}
								});
								tab5.setOnSelectionChanged(EventHandler -> {
									if (tab5.isSelected()) {
										tableView8(managerStage);
									}
								});
								tab1.setOnSelectionChanged(EventHandler -> {
								    if (tab1.isSelected()) {
								    	showCombinedCharts(primaryStage);
								       
								    }
								});
								tab3.setOnSelectionChanged(EventHandler -> {
								    if (tab3.isSelected()) {
								    	tableView4(managerStage);
								       
								    }
								});
								

								s.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
								n.setScene(s);
								n.show();

							} else {
								showAlert("Error", "Incorrect User ID or Password 🙁");
							}
						});

						vv.getChildren().add(loginButton);

						root3.setCenter(vv);

						Scene scene2 = new Scene(root3, 600, 700);
						stage2.setScene(scene2);
						stage2.show();
					});

					Staff.setOnAction(even -> {

					});

					Customer.setOnAction(e -> new CustomerManagment().start(new Stage()));
					stage.setScene(scene);
					stage.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			});

			login.setOnAction(even -> {
				String validId = "111111";
				String validPassword = "123123";
				if (password.getText().equals(validPassword) && id.getText().equals(validId)) {
					root1.setTop(tabPane);

					managerStage.setScene(managerscene);
					managerStage.show();

				}

			});

			Signup.setOnAction(even -> {
				HBox h4 = new HBox(10);
				HBox h5 = new HBox(10);
				VBox vv = new VBox(10);
				Label Managerid = new Label("Manager ID");
				TextField managerid = new TextField("");
				h4.getChildren().addAll(Managerid, managerid);
				Label Managerpass = new Label("Password");
				TextField managerpass = new TextField("");
				h5.getChildren().addAll(Managerpass, managerpass);
				vv.getChildren().addAll(h4, h5);

				Stage newStage = new Stage();
				Scene scene = new Scene(vv, 300, 100);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

				newStage.setScene(scene);
				newStage.show();

			});
			cancel.setOnAction(even -> {

			});

			Scene scene = new Scene(root, 600, 700);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	



	private void tableView(Stage primaryStage) {
            
		TableView<Staff> myDataTable = new TableView<Staff>();
		Font font2 = Font.font("Comic Sans MS", FontPosture.ITALIC, 10);
		Button s = new Button("Get The Data");
		Label name = new Label("Enter a specific Staff Name To Get all The Information About him ");
		TextField Name = new TextField("");

		// Set text color
		name.setTextFill(Color.WHITE);
		name.setFont(font2);
		// Set background color using CSS
		name.setStyle("-fx-background-color: lightpink;");
		HBox hBox = new HBox(10);
		hBox.getChildren().addAll(name, Name);
		VBox v = new VBox(10);
		v.getChildren().addAll(hBox, s);
		Label label = new Label("Staff Table");
		label.setFont(new Font("Brush Script MT", 40));
		label.setTextFill(Color.PINK);
		label.setStyle("-fx-background-color: #FAEBD7;");

		myDataTable.setEditable(true);
		myDataTable.setMaxHeight(200);
		myDataTable.setMaxWidth(600);
		// new

		TableColumn<Staff, Integer> idCol = new TableColumn<Staff, Integer>("Staff_id");
		idCol.setMinWidth(50);
		idCol.setCellValueFactory(new PropertyValueFactory<Staff, Integer>("Staff_id"));
		//////////////////////////////////////////////////////////////////////////////
		TableColumn<Staff, String> firstNameCol = new TableColumn<Staff, String>("firstName");
		firstNameCol.setMinWidth(100);
		firstNameCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("firstName"));

		firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		firstNameCol.setOnEditCommit((CellEditEvent<Staff, String> t) -> {
			t.getRowValue().setFirstName(t.getNewValue());
			updateName(t.getRowValue().getStaff_id(), t.getNewValue());
		});
		///////////////////////////////////////////////////////////////////////////////
		TableColumn<Staff, String> lastNameCol = new TableColumn<Staff, String>("lastName");
		lastNameCol.setMinWidth(100);
		lastNameCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("lastName"));
		lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		// if the user make a double click
		lastNameCol.setOnEditCommit((CellEditEvent<Staff, String> t) -> {
			t.getRowValue().setLastName(t.getNewValue());
			updateLastName(t.getRowValue().getStaff_id(), t.getNewValue());
		});
		///////////////////////////////////////////////////////////////////////////////////
		TableColumn<Staff, Integer> phoneCol = new TableColumn<>("phone_number");
		phoneCol.setMinWidth(100);
		phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
		phoneCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		// if the user make a double click
		phoneCol.setOnEditCommit((CellEditEvent<Staff, Integer> t) -> {
			t.getRowValue().setPhone_number(t.getNewValue());
			updatePhone(t.getRowValue().getPhone_number(), t.getNewValue());
		});
		///////////////////////////////////////////////////////////////////////////////////
		TableColumn<Staff, String> emailCol = new TableColumn<Staff, String>("Email");
		emailCol.setMinWidth(100);
		emailCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("Email"));
		emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
		// if the user make a double click this mean that he want to make a update on
		// the Email
		emailCol.setOnEditCommit((CellEditEvent<Staff, String> t) -> {
			t.getRowValue().setEmail(t.getNewValue());
			updateEmail(t.getRowValue().getStaff_id(), t.getNewValue());
		});
		/////////////////////////////////////////////////////////////////////////////////
		TableColumn<Staff, String> dateOfBirthCol = new TableColumn<Staff, String>("dateOfBirth");
		dateOfBirthCol.setMinWidth(100);
		dateOfBirthCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("dateOfBirth"));

		dateOfBirthCol.setCellFactory(TextFieldTableCell.<Staff>forTableColumn());
		// the user want to update on the value of dateOfBirth
		dateOfBirthCol.setOnEditCommit((CellEditEvent<Staff, String> t) -> {
			((Staff) t.getTableView().getItems().get(t.getTablePosition().getRow())).setDateOfBirth(t.getNewValue());
			updateDateOfBirth(t.getRowValue().getStaff_id(), t.getNewValue());
		});
		////////////////////////////////////////////////////////////////////////////////////
		TableColumn<Staff, String> hireDateCol = new TableColumn<Staff, String>("HireDate");
		hireDateCol.setMinWidth(100);
		hireDateCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("HireDate"));

		hireDateCol.setCellFactory(TextFieldTableCell.<Staff>forTableColumn());
		hireDateCol.setOnEditCommit((CellEditEvent<Staff, String> t) -> {
			((Staff) t.getTableView().getItems().get(t.getTablePosition().getRow())).getHireDate();
			updateHireDate(t.getRowValue().getStaff_id(), t.getNewValue());
		});
		///////////////////////////////////////////////////////////////////////////////////////
		TableColumn<Staff, String> genderCol = new TableColumn<Staff, String>("gendr");
		genderCol.setMinWidth(100);
		genderCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("gendr"));

		genderCol.setCellFactory(TextFieldTableCell.<Staff>forTableColumn());

		genderCol.setOnEditCommit((CellEditEvent<Staff, String> t) -> {
			((Staff) t.getTableView().getItems().get(t.getTablePosition().getRow())).setGendr(t.getNewValue());
			updateGender(t.getRowValue().getStaff_id(), t.getNewValue());
		});
		///////////////////////////////////////////////////////////////////////////////////////
		TableColumn<Staff, Double> salaryCol = new TableColumn<>("Salary");
		salaryCol.setMinWidth(100);
		salaryCol.setCellValueFactory(new PropertyValueFactory<>("salry"));

		salaryCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

		salaryCol.setOnEditCommit((TableColumn.CellEditEvent<Staff, Double> t) -> {
			Staff staff = t.getRowValue();
			staff.setSalry(t.getNewValue());
			updateSalary(staff.getStaff_id(), t.getNewValue());
		});

		//////////////////////////////////////////////////////////////////////////////////////////
		TableColumn<Staff, String> sectionCol = new TableColumn<Staff, String>("section");
		sectionCol.setMinWidth(100);
		sectionCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("section"));

		sectionCol.setCellFactory(TextFieldTableCell.<Staff>forTableColumn());
		sectionCol.setOnEditCommit((CellEditEvent<Staff, String> t) -> {
			((Staff) t.getTableView().getItems().get(t.getTablePosition().getRow())).setSection(t.getNewValue());
			updateSection(t.getRowValue().getStaff_id(), t.getNewValue());
		});
		// the end of the column
		//////////////////////////////////////////////////////////////////////////////////////////////

		myDataTable.setItems(dataList);
		myDataTable.getColumns().addAll(idCol, firstNameCol, lastNameCol, phoneCol, emailCol, dateOfBirthCol,
				hireDateCol, genderCol, salaryCol, sectionCol);

		TextField addid = new TextField();
		addid.setPromptText("Staff_id");
		addid.setMaxWidth(idCol.getPrefWidth());

		TextField addfirstName = new TextField();
		addfirstName.setMaxWidth(firstNameCol.getPrefWidth());
		addfirstName.setPromptText("firstName");

		TextField addlastName = new TextField();
		addlastName.setMaxWidth(lastNameCol.getPrefWidth());
		addlastName.setPromptText("lastName");

		TextField addphone = new TextField();
		addphone.setMaxWidth(phoneCol.getPrefWidth());
		addphone.setPromptText("phone");

		TextField addEmail = new TextField();
		addEmail.setMaxWidth(emailCol.getPrefWidth());
		addEmail.setPromptText("Email");

		TextField addDateOfBirth = new TextField();
		addDateOfBirth.setMaxWidth(dateOfBirthCol.getPrefWidth());
		addDateOfBirth.setPromptText("DateOfBirth");
		TextField addHireDate = new TextField();
		addHireDate.setMaxWidth(hireDateCol.getPrefWidth());
		addHireDate.setPromptText("addHireDate");
		TextField addgender = new TextField();
		addgender.setMaxWidth(genderCol.getPrefWidth());
		addgender.setPromptText("gender");
		TextField addsalary = new TextField();
		addsalary.setMaxWidth(genderCol.getPrefWidth());
		addsalary.setPromptText("Salary");
		TextField addsection = new TextField();
		addsection.setMaxWidth(genderCol.getPrefWidth());
		addsection.setPromptText("Section");

		Button addButton = new Button("Add");
		addButton.setOnAction((ActionEvent e) -> {
			Staff newStaff = new Staff(Integer.valueOf(addid.getText()), addfirstName.getText(), addlastName.getText(),
					Integer.valueOf(addphone.getText()), addEmail.getText(), addDateOfBirth.getText(),
					addHireDate.getText(), addgender.getText(), Double.valueOf(addsalary.getText()),
					addsection.getText());
			dataList.add(newStaff);
			insertData(newStaff);
			addid.clear();
			addfirstName.clear();
			addlastName.clear();
			addphone.clear();
			addEmail.clear();
			addDateOfBirth.clear();
			addHireDate.clear();
			addgender.clear();
			addsalary.clear();
			addsection.clear();
		});
		
		s.setOnAction(event -> {
			try {
				String selectedNam = (Name.getText());
				getStaffInformation(selectedNam);
			} catch (NumberFormatException e) {
				System.out.println("Please enter a valid staff ID");
				e.printStackTrace();
			}
		});



		// Other parts of your code remain unchanged

		HBox hb = new HBox();

		Button deleteButton = new Button("Delete");
		deleteButton.setOnAction((ActionEvent e) -> {
			ObservableList<Staff> selectedRows = myDataTable.getSelectionModel().getSelectedItems();
			ArrayList<Staff> rows = new ArrayList<>(selectedRows);
			rows.forEach(row -> {
				myDataTable.getItems().remove(row);
				deleteRow(row);
				myDataTable.refresh();
			});
		});
		
		
		hb.getChildren().addAll(addid, addfirstName, addlastName, addphone, addEmail, addDateOfBirth, addHireDate,
				addgender, addsalary, addsection, addButton, deleteButton);
		hb.setSpacing(3);

		Button refreshButton = new Button("Refresh");
		refreshButton.setOnAction((ActionEvent e) -> {
			myDataTable.refresh();
		});
		Button clearButton = new Button("Clear All");
		clearButton.setOnAction((ActionEvent e) -> {
			showDialog(primaryStage, NONE, myDataTable);

		});

		final HBox hb2 = new HBox();
		hb2.getChildren().addAll(clearButton, refreshButton);
		hb2.setAlignment(Pos.CENTER_RIGHT);
		hb2.setSpacing(3);
		VBox vbox = new VBox();
		Scene scene = new Scene(new Group());
		vbox.getChildren().addAll(label, myDataTable, hb, hb2, v, textarea); /////////////////////
		GridPane gp = new GridPane();
		gp.add(vbox, 0, 0);
		((Group) scene.getRoot()).getChildren().addAll(gp);
		tab7.setContent(gp);
		
		// Scene scene = new Scene(tab7, 1450, 790);
		primaryStage.setScene(scene);

		primaryStage.show();
	}

	private void tableView8(Stage primaryStage) {
		
		TableView<Product> product = new TableView<Product>();
		Font font1 = Font.font("Comic Sans MS", FontPosture.ITALIC, 20);
		Button Show=new Button("the product Price more 50 Nis ");
		Button Show2=new Button("the product Price Smaller 50 Nis ");
		
		Button addButton = new Button("Add");
		Button deleteButton = new Button("Delete");
		Button refreshButton = new Button("Refresh");
		Button clearButton = new Button("Clear All");
		Show.setFont(font1);
		Show2.setFont(font1);
		addButton.setFont(font1);
		deleteButton.setFont(font1);
		refreshButton.setFont(font1);
		clearButton.setFont(font1);
		Label label4 = new Label("Product Table");
		label4.setFont(new Font("Brush Script MT", 40));
		label4.setTextFill(Color.PINK);
		label4.setStyle("-fx-background-color: #FAEBD7;");
		
		product.setEditable(true);
		product.setMaxHeight(200);
		product.setMaxWidth(600);

		/////////////////////////////////////////////////////////////////////////
		TableColumn<Product, Integer> idCol = new TableColumn<Product, Integer>("Product_id");
		idCol.setMinWidth(50);
		idCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("produtc_id"));
		//////////////////////////////////////////////////////////////////////////////
		TableColumn<Product, String> NameCol = new TableColumn<>("Product_Name");
		NameCol.setMinWidth(100);
		NameCol.setCellValueFactory(new PropertyValueFactory<>("Product_Name"));

		NameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		NameCol.setOnEditCommit((TableColumn.CellEditEvent<Product, String> t) -> {

			t.getRowValue().setProduct_Name(t.getNewValue());

			updateName2(t.getRowValue().produtc_id, t.getNewValue());
		});

		///////////////////////////////////////////////////////////////////////////////
		TableColumn<Product, Double> priceCol = new TableColumn<>("Product_Price");
		priceCol.setMinWidth(100);
		priceCol.setCellValueFactory(new PropertyValueFactory<>("Product_Price"));

		// Use a CellFactory with DoubleStringConverter
		priceCol.setCellFactory(TextFieldTableCell.<Product, Double>forTableColumn(new DoubleStringConverter()));

		// If the user makes a double click
		priceCol.setOnEditCommit((TableColumn.CellEditEvent<Product, Double> t) -> {
			t.getRowValue().setProduct_Price(t.getNewValue());
			updateProductPrice(t.getRowValue().getProduct_Price(), t.getNewValue());
		});

		///////////////////////////////////////////////////////////////////////////////////
		TableColumn<Product, String> productdescripitonCol = new TableColumn<>("Product_descreption");
		productdescripitonCol.setMinWidth(100);
		productdescripitonCol.setCellValueFactory(new PropertyValueFactory<>("Product_descreption"));
		productdescripitonCol.setCellFactory(TextFieldTableCell.<Product>forTableColumn());
		// if the user make a double click
		productdescripitonCol.setOnEditCommit((CellEditEvent<Product, String> t) -> {
			t.getRowValue().setProduct_descreption(t.getNewValue());
			updatedescription(t.getRowValue().produtc_id, t.getNewValue());
		});
	
		


		product.setItems(dataList5);
		product.getColumns().addAll(idCol, NameCol, priceCol, productdescripitonCol);

		TextField addid = new TextField();
		addid.setPromptText("Product_id");
		addid.setMaxWidth(idCol.getPrefWidth());

		TextField addproductname = new TextField();
		addproductname.setMaxWidth(NameCol.getPrefWidth());
		addproductname.setPromptText("Product_Name");

		TextField addproductPrice = new TextField();
		addproductPrice.setMaxWidth(priceCol.getPrefWidth());
		addproductPrice.setPromptText("Product_Price");

		TextField addproductDescription = new TextField();
		addproductDescription.setMaxWidth(productdescripitonCol.getPrefWidth());
		addproductDescription.setPromptText("Product_descreption");
		
		
		
		addButton.setOnAction((ActionEvent event) -> {
			Product newProduct = new Product(Integer.valueOf(addid.getText()), addproductname.getText(),
					Double.valueOf(addproductPrice.getText()), addproductDescription.getText());

			dataList5.add(newProduct);
			insertData(newProduct);

			addid.clear();
			addproductname.clear();
			addproductPrice.clear();
			addproductDescription.clear();
			
		});
		textv.getChildren().addAll(text,text2);
		Show.setOnAction(event -> {
		    // Call the getProductsGreaterThan20 method when the Show button is clicked
		    getProductsGreaterThan50();
		});
		Show2.setOnAction(event -> {
		    // Call the getProductsGreaterThan20 method when the Show button is clicked
			getProductsSmallerThan50();
		});
		
		HBox hb = new HBox();
		HBox hb2 = new HBox();
		HBox hb4 = new HBox();
		VBox v2=new VBox();
		
		deleteButton.setOnAction((ActionEvent e) -> {
			ObservableList<Product> selectedRows = product.getSelectionModel().getSelectedItems();
			ArrayList<Product> rows = new ArrayList<>(selectedRows);
			rows.forEach(row -> {
				product.getItems().remove(row);
				deleteRow(row);
			});
			product.refresh();
		});
         hb2.getChildren().addAll(deleteButton ,addButton ,refreshButton ,clearButton );
         hb4.getChildren().addAll(Show,Show2);
         v2.getChildren().addAll(hb2,hb4,textv);
		hb.getChildren().addAll(addid, addproductname, addproductPrice, addproductDescription );
		hb.setSpacing(3);

		
		refreshButton.setOnAction((ActionEvent e) -> {
			product.refresh();
		});

		
		clearButton.setOnAction((ActionEvent e) -> {
			showDialog1(primaryStage, NONE, product);
		});

		

		VBox vbox = new VBox();
		vbox.getChildren().addAll(label4, product, hb, v2);

		// Use VBox as the root of the Scene
		Scene scene = new Scene(vbox);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	
	private void updateProductAmmount(double product_Price, Double newValue) {
		
		try {

			System.out.println(" UPDATE product SET Product_Ammount = "+newValue+ "Product_Ammount"+ newValue );
			connectDB();
			ExecuteStatement(" UPDATE product SET Product_Ammount = "+newValue+ "Product_Ammount"+ newValue);
			con.close();
			System.out.println("Connection closed");

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
	}

	
	


	   private void tableView4(Stage primaryStage) {
	        TableView<Staff_Appointment> staffappointment = new TableView<>();
	        staffappointment.setEditable(true);
	        staffappointment.setMaxHeight(200);
	        staffappointment.setMaxWidth(600);

	        Button addButton = new Button("Add");
	        Button deleteButton = new Button("Delete");
	        Button refreshButton = new Button("Refresh");
	        Button clearButton = new Button("Clear All");

	        Label label4 = new Label("Staff Appointment Table");

	        // TableColumn for appointment_id
	        TableColumn<Staff_Appointment, Integer> appointmentIdCol = new TableColumn<>("appointment_id");
	        appointmentIdCol.setMinWidth(50);
	        appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointment_id"));

	        // TableColumn for Staff_id
	        TableColumn<Staff_Appointment, Integer> staffIdCol = new TableColumn<>("Staff_id");
	        staffIdCol.setMinWidth(100);
	        staffIdCol.setCellValueFactory(new PropertyValueFactory<>("Staff_id"));

	        // TableColumn for firstName (assuming it's a String)
	        TableColumn<Staff_Appointment, String> firstNameCol = new TableColumn<>("firstName");
	        firstNameCol.setMinWidth(100);
	        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

	        // TableColumn for appointment_Time (assuming it's a String)
	        TableColumn<Staff_Appointment, String> appointmentTimeCol = new TableColumn<>("appointment_Time");
	        appointmentTimeCol.setMinWidth(100);
	        appointmentTimeCol.setCellValueFactory(new PropertyValueFactory<>("appointment_Time"));

	        // Set cell factories and edit commit actions here

	        staffappointment.setItems(dataList4);
	        staffappointment.getColumns().addAll(appointmentIdCol, staffIdCol, firstNameCol, appointmentTimeCol);

	        // TextFields for input
	        TextField addIdField = new TextField();
	        addIdField.setPromptText("Appointment ID");
	        addIdField.setMaxWidth(appointmentIdCol.getPrefWidth());

	        TextField addStaffId = new TextField();
	        addStaffId.setMaxWidth(staffIdCol.getPrefWidth());
	        addStaffId.setPromptText("Staff ID");

	        TextField addStaffName = new TextField();
	        addStaffName.setMaxWidth(firstNameCol.getPrefWidth());
	        addStaffName.setPromptText("Staff Name");

	        TextField addAppointmentTime = new TextField();
	        addAppointmentTime.setMaxWidth(appointmentTimeCol.getPrefWidth());
	        addAppointmentTime.setPromptText("Appointment Time");
	        
	       
	        
	        
	        // Add button action
	        addButton.setOnAction((ActionEvent event) -> {
	    Staff_Appointment newAppointment = new Staff_Appointment(
	                    Integer.valueOf(addIdField.getText()),
	                    Integer.valueOf(addStaffId.getText()),
	                    addStaffName.getText(),
	                    addAppointmentTime.getText()
	            );

	            dataList4.add(newAppointment);
	            insertData3(newAppointment);
	            // Add your insertData method here if needed

	            addIdField.clear();
	            addStaffId.clear();
	            addStaffName.clear();
	            addAppointmentTime.clear();
	        });

	        textv.getChildren().addAll(text,text2);
			
			
			HBox hb = new HBox();
			HBox hb2 = new HBox();
			HBox hb4 = new HBox();
			VBox v2=new VBox();
			
			deleteButton.setOnAction((ActionEvent e) -> {
				ObservableList<Staff_Appointment> selectedRows = staffappointment.getSelectionModel().getSelectedItems();
				ArrayList<Staff_Appointment> rows = new ArrayList<>(selectedRows);
				rows.forEach(row -> {
					staffappointment.getItems().remove(row);
					deleteRow(row);
				});
				staffappointment.refresh();
			});
	         hb2.getChildren().addAll(deleteButton ,addButton ,refreshButton ,clearButton );
	        
	         v2.getChildren().addAll(hb2,hb4,textv);
			hb.getChildren().addAll(addIdField, addStaffId, addStaffName, addAppointmentTime );
			hb.setSpacing(3);

			
			refreshButton.setOnAction((ActionEvent e) -> {
				staffappointment.refresh();
			});

			
			clearButton.setOnAction((ActionEvent e) -> {
				showDialog2(primaryStage, NONE, staffappointment);
			});

	        VBox vbox = new VBox();
	        vbox.getChildren().addAll(label4, staffappointment, new HBox(addIdField, addStaffId, addStaffName, addAppointmentTime, addButton));

	        Scene scene = new Scene(vbox);
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    }
	

	private void insertData3(Staff_Appointment newAppointment) {
		 try {
		        System.out.println(
		                "Insert into Staff_Appointment (appointment_id, Staff_id, firstName, appointment_Time) values ("
		                        + newAppointment.getAppointment_id() + ",'" + newAppointment.getStaff_id() + "','" + newAppointment.getFirstName() + "','"
		                        + newAppointment.getAppointment_Time() + "');");

		        connectDB();
		        ExecuteStatement(
		                "Insert into Staff_Appointment (product_id, Product_Name, Product_Price, Product_description) values ("
		                        + newAppointment.getAppointment_id() + ",'" + newAppointment.getStaff_id() + "','" + newAppointment.getFirstName() + "','"
		                        + newAppointment.getAppointment_Time() + "');");
		        con.close();
		        System.out.println("Connection closed");
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } catch (ClassNotFoundException e) {
		        e.printStackTrace();
		    }
		
	}



	private void deleteRow(Staff_Appointment row) {
		// TODO Auto-generated method stub
		
	}



	private void showDialog2(Stage primaryStage, Modality none, TableView<Staff_Appointment> staffappointment) {
		// TODO Auto-generated method stub
		
	}



	private void updateAppointmentTime(int appointment_id, String newValue) {
		// TODO Auto-generated method stub
		
	}



	private void updatStaffId(int appointment_id, Integer newValue) {
		// TODO Auto-generated method stub
		
	}



	private void updateFirstName(String firstName, String newValue) {
		// TODO Auto-generated method stub
		
	}



	


	private void tableView5(Stage primaryStage) {
		TableView<Product> product = new TableView<Product>();
		product.setEditable(true);
		product.setMaxHeight(200);
		product.setMaxWidth(600);

	}

	

	private void tableView7(Stage primaryStage) {
		TableView<Payment> section = new TableView<Payment>();
		section.setEditable(true);
		section.setMaxHeight(200);
		section.setMaxWidth(600);

	}

	public static void main(String[] args) {
		launch(args);
	}

	private void getData() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub

		String SQL;

		connectDB();
		System.out.println("Connection established ");

		SQL = "select Staff_id,firstName,lastName,phone_number,Email,dateOfBirth,HireDate,Gendr,salry,section\r\n"
				+ "from staff;";

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);

		while (rs.next())
			data.add(new Staff(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3),
					Integer.parseInt(rs.getString(4)), rs.getString(5), rs.getString(6), rs.getString(7),
					rs.getString(8), Double.parseDouble(rs.getString(9)), rs.getString(10)));

		rs.close();
		stmt.close();

		con.close();
		System.out.println("Connection closed" + data.size());

	}
	private void showCombinedCharts(Stage primaryStage) {
	    try {
	        VBox chartsContainer = new VBox();

	        // Staff Salary Chart
	        String salaryQuery = "SELECT Staff_id, salry FROM staff;";
	        BarChart<String, Number> salaryBarChart = createBarChart(salaryQuery, "Staff Salary Bar Chart", "Staff_id", "Salary");
	        chartsContainer.getChildren().add(salaryBarChart);

	        // Product Price Chart
	        String productQuery = "SELECT product_Id, Product_Price FROM product;";
	        BarChart<String, Number> productBarChart = createBarChart(productQuery, "Product Price Chart", "product_Id", "Product_Price");
	        chartsContainer.getChildren().add(productBarChart);

	        // Create a new stage with VBox as the root
	        Stage stage = new Stage();
	        Scene scene = new Scene(new Group(chartsContainer), 800, 800);
	        stage.setScene(scene);
	        stage.setTitle("Combined Charts");

	        // Set the new stage as a child of the primary stage
	        stage.initOwner(primaryStage);

	        // Show the new stage
	        stage.show();

	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}

	private BarChart<String, Number> createBarChart(String query, String chartTitle, String xAxisLabel, String yAxisLabel) throws SQLException, ClassNotFoundException {
	    connectDB();

	    CategoryAxis xAxis = new CategoryAxis();  // Use CategoryAxis for X-axis
	    NumberAxis yAxis = new NumberAxis();      // Use NumberAxis for Y-axis
	    BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
	    barChart.setTitle(chartTitle);
	    xAxis.setLabel(xAxisLabel);
	    yAxis.setLabel(yAxisLabel);
	    XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();

	    try (PreparedStatement preparedStatement = con.prepareStatement(query);
	         ResultSet resultSet = preparedStatement.executeQuery()) {

	        while (resultSet.next()) {
	            String xValue = Integer.toString(resultSet.getInt(1));
	            double yValue = resultSet.getDouble(2);
	            dataSeries.getData().add(new XYChart.Data<>(xValue, yValue));
	        }
	    }

	    barChart.getData().add(dataSeries);

	    con.close();
	    System.out.println("Connection closed");

	    return barChart;
	}


	private void getData2() throws SQLException, ClassNotFoundException {
	    String SQL;

	    connectDB();
	    System.out.println("Connection established ");

	    SQL = "SELECT product_Id, Product_Name, Product_Price, Product_description FROM product";

	    Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(SQL); // Use executeQuery instead of execute

	    while (rs.next()) {
	        data5.add(new Product(
	            Integer.parseInt(rs.getString(1)),
	            rs.getString(2),
	            Double.parseDouble(rs.getString(3)),
	            rs.getString(4)
	        ));
	    }

	    rs.close();
	    stmt.close();

	    con.close();
	    System.out.println("Connection closed" + data5.size());
	}

	private void getData3() throws SQLException, ClassNotFoundException {
	    String SQL;

	    connectDB();
	    System.out.println("Connection established ");

	    SQL = "select  appointment_id ,Staff_id ,firstName ,appointment_Time from Staff_Appointment;";

	    Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(SQL);

	    while (rs.next()) {
	        data4.add(new Staff_Appointment(
	            Integer.parseInt(rs.getString("appointment_id")),
	            Integer.parseInt(rs.getString("Staff_id")),
	            rs.getString("firstName"),
	            rs.getString("appointment_Time")
	        ));
	    }

	    rs.close();
	    stmt.close();

	    con.close();
	    System.out.println("Connection closed" + data4.size());
	}


	private void connectDB() throws ClassNotFoundException, SQLException {

		dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?verifyServerCertificate=false";
		Properties p = new Properties();
		p.setProperty("user", dbUsername);
		p.setProperty("password", dbPassword);
		p.setProperty("useSSL", "false");
		p.setProperty("autoReconnect", "true");
		Class.forName("com.mysql.jdbc.Driver");

		con = DriverManager.getConnection(dbURL, p);

	}

	private void updateName(int i, String newValue) {
		try {

			System.out.println("update staff  set firstName = " + newValue + "staff_id =" + i);
			connectDB();
			ExecuteStatement("update staff  set firstName = " + newValue + "staff_id =" + i);
			con.close();
			System.out.println("Connection closed");

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	private void updateLastName(int i, String newValue) {
		try {

			System.out.println("update staff  set lastName = " + newValue + "staff_id =" + i);
			connectDB();
			ExecuteStatement("update staff  set lastName = " + newValue + "staff_id =" + i);
			con.close();
			System.out.println("Connection closed");

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	private void updatePhone(int i, int newValue) {
		try {

			System.out.println("update staff  set phone_number = " + newValue + "staff_id =" + i);
			connectDB();
			ExecuteStatement("update staff  set phone_number = " + newValue + "staff_id =" + i);
			con.close();
			System.out.println("Connection closed");

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	private void updateEmail(int i, String newValue) {
		try {

			System.out.println("update staff  set Email = " + newValue + "staff_id =" + i);
			connectDB();
			ExecuteStatement("update staff  set Email = " + newValue + "staff_id =" + i);
			con.close();
			System.out.println("Connection closed");

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	private void updateDateOfBirth(int i, String newValue) {
		try {

			System.out.println("update staff  set dateOfBirth = " + newValue + "staff_id =" + i);
			connectDB();
			ExecuteStatement("update staff  set dateOfBirth = " + newValue + "staff_id =" + i);
			con.close();
			System.out.println("Connection closed");

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	private void updateHireDate(int i, String newValue) {
		try {

			System.out.println("update staff  set HireDate = " + newValue + "staff_id =" + i);
			connectDB();
			ExecuteStatement("update staff  set HireDate = " + newValue + "staff_id =" + i);
			con.close();
			System.out.println("Connection closed");

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	private void updateGender(int i, String newValue) {
		try {
			System.out.println("UPDATE staff SET Gendr = '" + newValue + "' WHERE staff_id = " + i);
			connectDB();
			ExecuteStatement("UPDATE staff SET Gendr = '" + newValue + "' WHERE staff_id = " + i);
			con.close();
			System.out.println("Connection closed");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void updateSalary(int i, Double newValue) {
		try {
			System.out.println("update staff SET salry = " + newValue + " WHERE staff_id = " + i);
			connectDB();
			ExecuteStatement("update staff SET salry = " + newValue + " WHERE staff_id = " + i);
			con.close();
			System.out.println("Connection closed");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void updateSection(int i, String newValue) {
		try {

			System.out.println("update staff  set section = " + newValue + "staff_id =" + i);
			connectDB();
			ExecuteStatement("update staff  set section = " + newValue + "staff_id =" + i);
			con.close();
			System.out.println("Connection closed");

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
	private void deleteRow(Product row) {
		try {
			System.out.println("delete from staff where staff_id = " + row.getProdutc_id());
			connectDB();
			ExecuteStatement("delete from  staff where staff_id=" + row.getProdutc_id() + ";");
			con.close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		

	}

	private void insertData(Product newProduct) {
	    try {
	        System.out.println(
	                "Insert into Product (product_id, Product_Name, Product_Price, Product_description) values ("
	                        + newProduct.getProdutc_id() + ",'" + newProduct.getProduct_Name() + "','" + newProduct.getProduct_Price() + "','"
	                        + newProduct.getProduct_descreption() + "');");

	        connectDB();
	        ExecuteStatement(
	                "Insert into Product (product_id, Product_Name, Product_Price, Product_description) values ("
	                        + newProduct.getProdutc_id() + ",'" + newProduct.getProduct_Name() + "','" + newProduct.getProduct_Price() + "','"
	                        + newProduct.getProduct_descreption() + "');");
	        con.close();
	        System.out.println("Connection closed");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}


	private void updatedescription(int i, String newValue) {
	    try {
	        System.out.println("UPDATE product SET Product_description = '" + newValue + "' WHERE product_Id = " + i);
	        connectDB();
	        ExecuteStatement("UPDATE product SET Product_description = '" + newValue + "' WHERE product_Id = " + i);
	        con.close();
	        System.out.println("Connection closed");
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}


	private void updateProductPrice(double i, Double newValue) {
	    try {
	        System.out.println("UPDATE product SET Product_Price = " + newValue + " WHERE Product_Price = " + i);
	        connectDB();
	        ExecuteStatement("UPDATE product SET Product_Price = " + newValue + " WHERE Product_Price = " + i);
	        con.close();
	        System.out.println("Connection closed");
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}



	private void updateName2(int i, String newValue) {
	    try {
	        System.out.println("UPDATE product SET Product_description = '" + newValue + "' WHERE product_Id = " + i);
	        connectDB();
	        ExecuteStatement("UPDATE product SET Product_description = '" + newValue + "' WHERE product_Id = " + i);
	        con.close();
	        System.out.println("Connection closed");
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}


	public void ExecuteStatement(String SQL) throws SQLException {

		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(SQL);
			stmt.close();

		} catch (SQLException s) {
			s.printStackTrace();
			System.out.println("SQL statement is not executed!");

		}

	}
//Client Table
	private void insertData(Staff staff) {
		try {
			int id=staff.getStaff_id();
			String checkIdQuery = "SELECT Staff_id FROM Staff WHERE Staff_id = id";
			
			System.out.println(
					"Insert into staff (staff_id, firstName, lastName, Phone_Number, Email, DateOfBirth, HireDate, Gendr, Salary, Section) values ("
							+ staff.getStaff_id() + ",'" + staff.getFirstName() + "','" + staff.getLastName() + "',"
							+ staff.getPhone_number() + ",'" + staff.getEmail() + "','" + staff.getDateOfBirth() + "','"
							+ staff.getHireDate() + "','" + staff.getGendr() + "'," + staff.getSalry() + ",'"
							+ staff.getSection() + "');");

			connectDB();
			ExecuteStatement(
					"Insert into staff (Staff_id, firstName, lastName, phone_number, Email, dateOfBirth, HireDate, Gendr, salry, section) values ("
							+ staff.getStaff_id() + ",'" + staff.getFirstName() + "','" + staff.getLastName() + "',"
							+ staff.getPhone_number() + ",'" + staff.getEmail() + "','" + staff.getDateOfBirth() + "','"
							+ staff.getHireDate() + "','" + staff.getGendr() + "'," + staff.getSalry() + ",'"
							+ staff.getSection() + "');");
			con.close();
			System.out.println("Connection closed");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void deleteRow(Staff row) {
	    try {
	        System.out.println("delete from staff where staff_id = " + row.getStaff_id());
	        connectDB();

	        String deleteQuery = "DELETE FROM staff WHERE staff_id = ?";
	        try (PreparedStatement preparedStatement = con.prepareStatement(deleteQuery)) {
	            preparedStatement.setInt(1, row.getStaff_id());
	            preparedStatement.executeUpdate();
	        }

	        System.out.println("Row deleted successfully");
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (con != null) {
	                con.close();
	                System.out.println("Connection closed");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	private void updateProductId(int productId, Integer newValue) {
	    try {
	        System.out.println("UPDATE product SET Product_id = " + newValue + " WHERE Product_id = " + productId);
	        connectDB();
	        ExecuteStatement("UPDATE product SET Product_id = " + newValue + " WHERE Product_id = " + productId);
	        con.close();
	        System.out.println("Connection closed");
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}
	private void getStaffInformation(String selectedName) {
		try {

			// Construct the SQL query with the actual value of selectedName using
			// PreparedStatement
			String query = "SELECT * FROM staff WHERE firstName = ?";
			System.out.println(query);

			connectDB();

			// Use PreparedStatement to set the parameter
			try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
				preparedStatement.setString(1, selectedName);

				// Execute the query and get the result set
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					// Process the result set
					while (resultSet.next()) {
						// Retrieve data from the result set
						int staffId = resultSet.getInt("staff_id");
						String firstName = resultSet.getString("firstName");
						String lastName = resultSet.getString("lastName");
						int phone_number = resultSet.getInt("phone_number");
						String Email = resultSet.getString("Email");
						String dateOfBirth = resultSet.getString("dateOfBirth");
						String HireDate = resultSet.getString("HireDate");
						String Gendr = resultSet.getString("Gendr");
						double salry = resultSet.getDouble("salry");
						String section = resultSet.getString("section");

						// Print or process the retrieved data as needed
						System.out.println("Staff ID: " + staffId);
						System.out.println("First Name: " + firstName);
						System.out.println("Last Name: " + lastName);
						System.out.println("Phone Number :" + phone_number);
						System.out.println("Email :" + Email);
						System.out.println("dateOfBirth :" + dateOfBirth);
						System.out.println("HireDate :" + HireDate);
						System.out.println("Gendr :" + Gendr);
						System.out.println("salry :" + salry);
						System.out.println("section :" + section);
						textarea.setText("Staff ID: " + staffId + "\n" + "First Name: " + firstName + "\n"
								+ "Last Name: " + lastName + "\n" + "Phone Number: " + phone_number + "\n" + "Email: "
								+ Email + "\n" + "Date of Birth: " + dateOfBirth + "\n" + "Hire Date: " + HireDate
								+ "\n" + "Gender: " + Gendr + "\n" + "Salary: " + salry + "\n" + "Section: " + section);

						// ... (repeat for other columns)
					}
				}
			}

			// Close the connection after executing the query
			con.close();
			System.out.println("Connection closed");

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	private void showDialog(Window owner, Modality modality, TableView<Staff> table) {
		// Create a Stage with specified owner and modality
		Stage stage = new Stage();
		stage.initOwner(owner);
		stage.initModality(modality);
//	Label modalityLabel = new Label(modality.toString());

		Button yesButton = new Button("Confirm");
		yesButton.setOnAction(e -> {
			for (Staff row : dataList) {
				deleteRow(row);
				table.refresh();
			}
			table.getItems().removeAll(dataList);
			stage.close();

		});

		Button noButton = new Button("Cancel");
		noButton.setOnAction(e -> stage.close());

		HBox root = new HBox();
		root.setPadding(new Insets(10, 10, 10, 10));
		root.setAlignment(Pos.CENTER);
		root.setSpacing(10);

		root.getChildren().addAll(yesButton, noButton);
		Scene scene = new Scene(root, 200, 100);
		stage.setScene(scene);
		stage.setTitle("Confirm Delete?");
		stage.show();
	}

	private void showDialog1(Window owner, Modality modality, TableView<Product> table) {
		// Create a Stage with specified owner and modality
		Stage stage = new Stage();
		stage.initOwner(owner);
		stage.initModality(modality);
//	Label modalityLabel = new Label(modality.toString());

		Button yesButton = new Button("Confirm");
		yesButton.setOnAction(e -> {
			for (Staff row : dataList) {
				deleteRow(row);
				table.refresh();
			}
			table.getItems().removeAll(dataList);
			stage.close();

		});

		Button noButton = new Button("Cancel");
		noButton.setOnAction(e -> stage.close());

		HBox root = new HBox();
		root.setPadding(new Insets(10, 10, 10, 10));
		root.setAlignment(Pos.CENTER);
		root.setSpacing(10);

		root.getChildren().addAll(yesButton, noButton);
		Scene scene = new Scene(root, 200, 100);
		stage.setScene(scene);
		stage.setTitle("Confirm Delete?");
		stage.show();
	}
	private void getProductsGreaterThan50() {
	    try {
	        // Construct the SQL query with the condition for price greater than 50
	        String query = "SELECT * FROM product WHERE Product_Price > ?";
	        System.out.println(query);

	        connectDB();

	        // Use PreparedStatement to set the parameter for the minimum price (50)
	        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
	            preparedStatement.setDouble(1, 50.0);

	            // Execute the query and get the result set
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                StringBuilder resultText = new StringBuilder(); // StringBuilder to store results

	                // Process the result set
	                while (resultSet.next()) {
	                    // Retrieve data from the result set
	                    int productId = resultSet.getInt("product_Id");
	                    String productName = resultSet.getString("product_Name");
	                    double productPrice = resultSet.getDouble("Product_Price");
	                    String productDescription = resultSet.getString("Product_description");

	                    // Append information for each product to the StringBuilder
	                    resultText.append("Product ID: ").append(productId)
	                            .append("\nProduct Name: ").append(productName)
	                            .append("\nProduct Price: ").append(productPrice)
	                            .append("\nProduct Description: ").append(productDescription)
	                            .append("\n--------------\n");
	                }

	                // Display the concatenated results in the text TextArea
	                text.setText(resultText.toString());
	            }
	        }

	        // Close the connection after executing the query
	        con.close();
	        System.out.println("Connection closed");

	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}

	private void getProductsSmallerThan50() {
	    try {
	        // Construct the SQL query with the condition for price smaller than 50
	        String query = "SELECT * FROM product WHERE Product_Price < ?";
	        System.out.println(query);

	        connectDB();

	        // Use PreparedStatement to set the parameter for the maximum price (50)
	        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
	            preparedStatement.setDouble(1, 50.0);

	            // Execute the query and get the result set
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                StringBuilder resultText = new StringBuilder(); // StringBuilder to store results

	                // Process the result set
	                while (resultSet.next()) {
	                    // Retrieve data from the result set
	                    int productId = resultSet.getInt("product_Id");
	                    String productName = resultSet.getString("product_Name");
	                    double productPrice = resultSet.getDouble("Product_Price");
	                    String productDescription = resultSet.getString("Product_description");

	                    // Append information for each product to the StringBuilder
	                    resultText.append("Product ID: ").append(productId)
	                            .append("\nProduct Name: ").append(productName)
	                            .append("\nProduct Price: ").append(productPrice)
	                            .append("\nProduct Description: ").append(productDescription)
	                            .append("\n--------------\n");
	                }

	                // Display the concatenated results in the text2 TextArea
	                text2.setText(resultText.toString());
	            }
	        }

	        // Close the connection after executing the query
	        con.close();
	        System.out.println("Connection closed");

	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}


	private void showAlert(String title, String content) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);
		alert.showAndWait();
	}

}