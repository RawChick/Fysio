package presentation;

import businesslogic.AppointmentManager;
import businesslogic.EmployeeManager;
import domain.Appointment;
import domain.Employee;
import domain.Workday;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EmployeeGUI extends Application {
    //region Attributes and properties
    private TableView<Appointment> table;
    private EmployeeManager employeeManager;
    private AppointmentManager appointmentManager;

    private final HBox hBox = new HBox();
    private final VBox vBox = new VBox();
    private final BorderPane borderPane = new BorderPane();

    private DatePicker dp_AppointmentDate;
    private Label lbl_EmployeeNr;
    private Label lbl_EmployeePhone;
    private Label lbl_workDays;
    private Label lbl_EmployeeDate;
    private Label lbl_EmployeeStartTime;
    private Label lbl_EmployeeStopTime;
    private Label lbl_EmployeeEmail;

    private final String DATE_PATTERN = "dd:MM:yyyy";
    private final String TIME_PATTERN = "HH:mm";
    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(DATE_PATTERN);
    private final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern(TIME_PATTERN);

    private final TableColumn numberCol = new TableColumn("Nummer");
    private final TableColumn fysioCol = new TableColumn("Patient");
    //endregion

    @Override
    public void start(Stage stage) throws Exception {
        employeeManager = new EmployeeManager();
        appointmentManager = new AppointmentManager();
        table = new TableView<>();

        table.setEditable(true);
        Callback<TableColumn, TableCell> cellFactory =
                p -> new EditingCellManageEmployee();

        //region Creating tabs
        TabPane pane = new TabPane();
        Tab appointmentTab = new Tab("Afspraak");
        Tab customerTab = new Tab("Patient");
        Tab employeeTab = new Tab("Medewerker");
        Tab manageEmployeeTab = new Tab("Overzicht Werknemers");

        pane.getSelectionModel().select(employeeTab);

        //adding action listeners
        pane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue.getText()) {
                case "Afspraak":
                    AppointmentGUI guiAppointment = new AppointmentGUI();
                    try {
                        guiAppointment.start(stage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "Medewerker":
                    break;
                case "Patient":
                    PatientGUI guiCustomer = new PatientGUI();
                    try {
                        guiCustomer.start(stage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "Overzicht Werknemers":
                    ManageEmployeeGUI guiManageEmploye = new ManageEmployeeGUI();
                    try {
                        guiManageEmploye.start(stage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        });
        //endregion

        //region Creating columns for table

        numberCol.setCellValueFactory(
                new PropertyValueFactory<Appointment, Integer>("appointmentNumber"));
        fysioCol.setCellValueFactory(
                new PropertyValueFactory<Appointment, String>("appointmentPatientName"));
        //endregion

        //region Creating buttons
        lbl_EmployeeNr = new Label("Medewerkernummer");
        lbl_EmployeePhone = new Label("Telefoonnummer");
        lbl_workDays = new Label("Werktijden");
        lbl_EmployeeDate = new Label();
        lbl_EmployeeStartTime = new Label();
        lbl_EmployeeStopTime = new Label();
        lbl_EmployeeEmail = new Label("Email");
        Line line = new Line(-100, 0, 50, 0);

        ComboBox<String> cb_Employee = new ComboBox<>(employeeManager.getEmployeeNames());
        cb_Employee.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            Employee tempEmployee = employeeManager.searchEmployeeWithName(newValue);
            lbl_EmployeeNr.setText(tempEmployee.getEmployeeNr());
            lbl_EmployeePhone.setText(tempEmployee.getEmployeePhone());
            lbl_EmployeeEmail.setText(tempEmployee.getEmployeeEmail());
            searchAppointments();
        });

        dp_AppointmentDate = new DatePicker();
        dp_AppointmentDate.setPromptText("Kies datum");
        dp_AppointmentDate.valueProperty().addListener((observable, oldValue, newValue) -> searchAppointments());
        //endregion

        table.getColumns().addAll(numberCol, fysioCol);
        hBox.getChildren().addAll(cb_Employee, dp_AppointmentDate);
        hBox.setSpacing(10);
        vBox.getChildren().addAll(lbl_EmployeeNr, lbl_EmployeePhone, lbl_EmployeeEmail, line, lbl_workDays, lbl_EmployeeDate, lbl_EmployeeStartTime, lbl_EmployeeStopTime);
        vBox.setSpacing(5);

        borderPane.setTop(hBox);
        borderPane.setLeft(vBox);
        borderPane.setCenter(table);
        borderPane.setPrefSize(1200,600);
        borderPane.setPadding(new Insets(10, 20, 10, 20));
        BorderPane.setMargin(hBox, new Insets(12, 12, 12, 12));
        BorderPane.setMargin(vBox, new Insets(12, 12, 12, 12));
        BorderPane.setMargin(table, new Insets(12, 12, 12, 12));

        pane.getTabs().addAll(appointmentTab, employeeTab, customerTab, manageEmployeeTab);
        employeeTab.setContent(borderPane);
        pane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Scene scene = new Scene(pane);
        scene.getStylesheets().addAll(AppointmentGUI.class.getResource("/Light.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    //region Methods
    private void searchAppointments() {
        LocalDate localdate = dp_AppointmentDate.getValue();
        String employeeNr = lbl_EmployeeNr.getText();
        Employee tempEmployee = employeeManager.searchEmployeeWithNumber(employeeNr);

        if (tempEmployee == null) {
            AlertBox.display("Foutmelding", "Geen werknemer gevonden met nummer: " + employeeNr);
        } else if (localdate == null) {
            AlertBox.display("Foutmelding", "U heeft geen datum geselecteert");
        } else {
            Workday tempWorkday = employeeManager.searchWorkdayWithDate(employeeNr, localdate);
            if (tempWorkday == null) {
                AlertBox.display("Foutmelding", "Medewerker: " + tempEmployee.getEmployeeName() + " was niet werkzaam op: " + dateFormat.format(localdate));
                lbl_EmployeePhone.setText("");
                lbl_EmployeeEmail.setText("");
                lbl_EmployeeDate.setText("");
                lbl_EmployeeStartTime.setText("");
                lbl_EmployeeStopTime.setText("");
            } else {
                lbl_EmployeePhone.setText((tempEmployee.getEmployeePhone()));
                lbl_EmployeeEmail.setText(tempEmployee.getEmployeeEmail());
                lbl_workDays.setText("Werktijden");
                lbl_EmployeeDate.setText(dateFormat.format(tempWorkday.getWorkDate()));
                lbl_EmployeeStartTime.setText(timeFormat.format(tempWorkday.getStartTime()));
                lbl_EmployeeStopTime.setText(timeFormat.format(tempWorkday.getStopTime()));

                ObservableList<Appointment> data = appointmentManager.searchWithWorkDateAndEmployeeName(localdate, tempEmployee.getEmployeeName());
                table.setItems(data);
                for (Appointment aData : data) {
                    int length = 0;
                    if (aData.getAppointmentNumber().toString().length() > length) {
                        length = aData.getAppointmentNumber().toString().length();
                        numberCol.setMinWidth(length*8.5);
                    }

                }
                for (Appointment aData : data) {
                    int length = 0;
                    if (aData.getAppointmentPatientName().length() > length) {
                        length = aData.getAppointmentPatientName().length();
                        fysioCol.setMinWidth(length*8.5);
                    }
                }
            }
        }
    }
    //endregion
}
