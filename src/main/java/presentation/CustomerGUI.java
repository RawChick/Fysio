package presentation;

import businesslogic.CustomerManager;
import domain.Customer;
import domain.CustomerTable;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class CustomerGUI extends Application {

    private TabPane pane = new TabPane();
    private Tab employeeTab = new Tab("Medewerker");
    private Tab appointmentTab = new Tab("Afspraak");
    private Tab customerTab = new Tab("Patient");
    private Tab manageEmployeeTab = new Tab("Overzicht Werknemers");
    private AnchorPane anchorPane = new AnchorPane();
    private BorderPane borderPane = new BorderPane();
    private HBox hBox = new HBox(20);
    private TextField textField = new TextField();
    private Button button = new Button("Zoek");
    private HBox hBox1 = new HBox(20);
    private GridPane gridPane = new GridPane();
    private static final Label BSN = new Label("BSN:");
    private static final Label NAME = new Label("Naam:");
    private static final Label ADRESS = new Label("Adres:");
    private static final Label ZIPCODE = new Label("Postcode:");
    private static final Label CITY = new Label("Woonplaats:");
    private static final Label DATEOFBIRTH = new Label("Geboorte datum:");
    private static final Label DIAGNOSECODE1 = new Label("Diagnosecode 1:");
    private static final Label DIAGNOSECODE2 = new Label("Diagnosecode 2:");
    private static final Label DIAGNOSECODEN = new Label("Diagnosecode N:");
    private static final Label PHONE = new Label("Telefoon nummer:");
    private static final Label EMAIL = new Label("Email-adres:");
    private Label bsnLabel = new Label();
    private Label nameLabel = new Label();
    private Label adressLabel = new Label();
    private Label zipcodeLabel = new Label();
    private Label cityLabel = new Label();
    private Label dateOfBirthLabel = new Label();
    private Label diagnosecode1Label = new Label();
    private Label diagnosecode2Label = new Label();
    private Label diagnosecodeNLabel = new Label();
    private Label phoneLabel = new Label();
    private Label emailLabel = new Label();
    private TableView<CustomerTable> tableView = new TableView<>();


    @Override
    public void start(Stage primaryStage) throws Exception {

        pane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);


        tableView.setEditable(true);
        TableColumn<CustomerTable, String> dateCol = new TableColumn<>("Datum");
        TableColumn<CustomerTable, String> timeCol = new TableColumn<>("Tijd");
        TableColumn<CustomerTable, String> treatCol = new TableColumn<>("Behandeling");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("dateCol"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("timeCol"));
        treatCol.setCellValueFactory(new PropertyValueFactory<>("treatCol"));
        tableView.getColumns().addAll(dateCol, timeCol, treatCol);

        pane.getSelectionModel().select(customerTab);
        customerTab.setContent(anchorPane);
        anchorPane.getChildren().addAll(borderPane);
        borderPane.setTop(hBox);
        hBox.getChildren().addAll(textField, button);
        hBox.setAlignment(Pos.TOP_RIGHT);
        textField.setPromptText("BSN");
        borderPane.setCenter(hBox1);
        hBox1.getChildren().addAll(gridPane, tableView);
        gridPane.addColumn(0, BSN, NAME, ADRESS, ZIPCODE, CITY, DATEOFBIRTH, DIAGNOSECODE1, DIAGNOSECODE2, DIAGNOSECODEN, PHONE, EMAIL);
        gridPane.addColumn(1, bsnLabel, nameLabel, adressLabel, zipcodeLabel, cityLabel, dateOfBirthLabel, diagnosecode1Label, diagnosecode2Label, diagnosecodeNLabel, phoneLabel, emailLabel);
        bsnLabel.setMinWidth(100);
        nameLabel.setMinWidth(100);
        adressLabel.setMinWidth(100);
        zipcodeLabel.setMinWidth(100);
        cityLabel.setMinWidth(100);
        dateOfBirthLabel.setMinWidth(100);
        diagnosecode1Label.setMinWidth(100);
        diagnosecode2Label.setMinWidth(100);
        diagnosecodeNLabel.setMinWidth(100);
        phoneLabel.setMinWidth(100);
        emailLabel.setMinWidth(100);
        hBox1.setPadding(new Insets(20, 20, 20, 20));
        hBox.setPadding(new Insets(20, 20, 20, 20));
        pane.getTabs().addAll(appointmentTab, employeeTab, customerTab, manageEmployeeTab);

        Scene scene = new Scene(pane);
        scene.getStylesheets().addAll(AppointmentGUI.class.getResource("/Light.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();

        //adding action listeners
        pane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.getText().equals("Medewerker")) {
                EmployeeGUI gui = new EmployeeGUI();
                try {
                    gui.start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (newValue.getText().equals("Afspraak")) {
                AppointmentGUI gui = new AppointmentGUI();
                try {
                    gui.start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (newValue.getText().equals("Overzicht Werknemers")){
                ManageEmployeeGUI gui = new ManageEmployeeGUI();
                try {
                    gui.start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        button.setOnAction(e -> search());
    }
    public void search(){
        int bsn = Integer.parseInt(textField.getText());
        CustomerManager manager = new CustomerManager();
        manager.findCustomer(bsn);
        CustomerTable table = manager.findCustomerTable(bsn);
        final ObservableList<CustomerTable> data = FXCollections.observableArrayList(
                new CustomerTable(LocalDate.now(), LocalTime.now(), "dwdq")
        );
        tableView.setItems(data);
        Customer customers = manager.findCustomer(Integer.parseInt(textField.getText()));
        bsnLabel.setText(Integer.toString(customers.getBsn()));
        nameLabel.setText(customers.getName());
        adressLabel.setText(customers.getAdress());
        zipcodeLabel.setText(customers.getZipcode());
        cityLabel.setText(customers.getCity());
        dateOfBirthLabel.setText(customers.getDateOfBirth().toString());
        diagnosecode1Label.setText(Integer.toString(customers.getDiagnoseCode1()));
        diagnosecode2Label.setText(Integer.toString(customers.getDiagnoseCode2()));
        diagnosecodeNLabel.setText(Integer.toString(customers.getDiagnoseCodeN()));
        phoneLabel.setText(customers.getPhone());
        emailLabel.setText(customers.getEmail());
    }
}