package presentation;

import businesslogic.EmployeeManager;
import domain.Employee;
import domain.Workday;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EmployeeGUI extends Application {

    private TabPane pane = new TabPane();
    private Tab appointmentTab = new Tab("Afspraak");
    private Tab customerTab = new Tab("Patient");
    private Tab employeeTab = new Tab("Medewerker");
    private Tab manageEmployeeTab = new Tab("Overzicht Werknemers");
    private AnchorPane anchorPane = new AnchorPane();
    private BorderPane borderPane = new BorderPane();
    private HBox hBox = new HBox(20);
    private DatePicker date = new DatePicker();
    private TextField text = new TextField();
    private Label lbl_medewerkerNr = new Label("Medewerkernummer");
    private Button button = new Button("Zoek");
    private HBox hBox1 = new HBox(20);
    private ScrollPane scrollPane = new ScrollPane();
    private AnchorPane anchorPane1 = new AnchorPane();
    private VBox vBox = new VBox(20);
    private Label lbl_employeeName = new Label("Naam");
    private Label lbl_employeePhone = new Label("Telefoonnummer");
    private Label lbl_employeeEmail = new Label("Email");
    private Line line = new Line(-100, 0, 50, 0);
    private Label lbl_werktijden = new Label("Werktijden");
    private Label lbl_employeeDatum = new Label();
    private Label lbl_employeeStartTijd = new Label();
    private Label lbl_employeeStopTijd = new Label();
    private Label label8 = new Label();
    private Label label9 = new Label();
    private TableView<Employee> tableView = new TableView<>();
    private EmployeeManager manager = new EmployeeManager();


    @Override
    public void start(Stage primaryStage) throws Exception {

        tableView.setEditable(true);


        tableView.setItems(manager.getData());

        employeeTab.setContent(anchorPane);
        anchorPane.getChildren().addAll(borderPane);
        borderPane.setTop(hBox);
        hBox.getChildren().addAll(date, text, lbl_medewerkerNr, button);
        borderPane.setCenter(hBox1);
        hBox1.getChildren().addAll(scrollPane, tableView);
        scrollPane.setContent(anchorPane1);
        anchorPane1.getChildren().addAll(vBox);
        vBox.getChildren().addAll(lbl_employeeName, lbl_employeePhone, lbl_employeeEmail, line, lbl_werktijden, lbl_employeeDatum, lbl_employeeStartTijd, lbl_employeeStopTijd, label8, label9);
        hBox1.setPadding(new Insets(30, 10, 20, 10));
        line.setVisible(false);
        date.setPromptText("Kies een datum");
        borderPane.setPadding(new Insets(20, 20, 20, 20));

        pane.getTabs().addAll(appointmentTab, employeeTab, customerTab, manageEmployeeTab);
        pane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Scene scene = new Scene(pane);
        scene.getStylesheets().addAll(AppointmentGUI.class.getResource("/Light.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
        pane.getSelectionModel().select(employeeTab);

        pane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.getText().equals("Afspraak")) {
                AppointmentGUI gui = new AppointmentGUI();
                try {
                    gui.start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (newValue.getText().equals("Patient")) {
                PatientGUI gui = new PatientGUI();
                try {
                    gui.start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (newValue.getText().equals("Overzicht Werknemers")) {
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

    public void search() {
        LocalDate localdate = date.getValue();
        String employeeNr = text.getText();
        Employee tempEmployee = manager.searchEmployeeWithNumber(employeeNr);
        String DATE_PATTERN = "dd.MM.yyyy";
        String TIME_PATTERN = "HH:mm";
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(DATE_PATTERN);
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern(TIME_PATTERN);

        if (tempEmployee == null) {
            AlertBox.display("Foutmelding", "Geen werknemer gevonden met nummer: " + employeeNr);
        } else {
            lbl_employeeName.setText(tempEmployee.getName());
            lbl_employeePhone.setText((tempEmployee.getPhone()));
            lbl_employeeEmail.setText(tempEmployee.getEmail());
            lbl_werktijden.setText("Werktijden");
            Workday tempWorkday = manager.searchWorkdayWithDate(employeeNr, localdate);

            if (tempWorkday == null) {
                AlertBox.display("Foutmelding", "Medewerker: " + tempEmployee.getName() + " was niet werkzaam op: " + dateFormat.format(localdate));
                lbl_employeeName.setText("");
                lbl_employeePhone.setText("");
                lbl_employeeEmail.setText("");
                lbl_employeeDatum.setText("");
                lbl_employeeStartTijd.setText("");
                lbl_employeeStopTijd.setText("");
            } else {
                lbl_employeeDatum.setText(dateFormat.format(tempWorkday.getWorkDate()));
                lbl_employeeStartTijd.setText(timeFormat.format(tempWorkday.getStartTime()));
                lbl_employeeStopTijd.setText(timeFormat.format(tempWorkday.getStopTime()));
            }
        }
    }
}
