package presentation;

import businesslogic.PatientManager;
import domain.Patient;
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

public class PatientGUI extends Application {

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
    private PatientManager manager = new PatientManager();


    @Override
    public void start(Stage primaryStage) throws Exception {

        pane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);


        tableView.setEditable(true);

        tableView.getColumns().addAll();

        pane.getSelectionModel().select(customerTab);
        customerTab.setContent(anchorPane);
        anchorPane.getChildren().addAll(borderPane);
        borderPane.setTop(hBox);
        hBox.getChildren().addAll(textField, button);
        hBox.setAlignment(Pos.TOP_RIGHT);
        textField.setPromptText("BSN");
        borderPane.setCenter(hBox1);
        hBox1.getChildren().addAll(gridPane, tableView);
        gridPane.addColumn(0, BSN, NAME, ADRESS, ZIPCODE, CITY, DATEOFBIRTH, PHONE, EMAIL);
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
        Patient tempPatient = manager.searchWithBSN(bsn);

        if (tempPatient != null) {
            bsnLabel.setText(String.valueOf(tempPatient.getBsn()));
            nameLabel.setText(tempPatient.getName());
            adressLabel.setText(tempPatient.getAdress());
            zipcodeLabel.setText(tempPatient.getZipCode());
            dateOfBirthLabel.setText(String.valueOf(tempPatient.getDateOfBirth()));
            phoneLabel.setText(tempPatient.getPhone());
            emailLabel.setText(tempPatient.getEmail());
        }else {
            AlertBox.display("Foutmelding", "Geen patient gevonden met BSN: " + bsn);
        }
    }
}