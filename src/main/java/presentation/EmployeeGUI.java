package presentation;

import businesslogic.AppointmentManager;
import businesslogic.EmployeeManager;
import domain.Appointment;
import domain.Employee;
import domain.Workday;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EmployeeGUI extends Application {
    private TableView<Appointment> table;
    private EmployeeManager employeeManager;
    private AppointmentManager appointmentManager;

    private HBox hBox = new HBox();
    private VBox vBox = new VBox();
    private TabPane pane;
    private Tab appointmentTab;
    private Tab customerTab;
    private Tab employeeTab;
    private Tab manageEmployeeTab;
    private BorderPane borderPane = new BorderPane();

    ComboBox<String> cb_Employee;
    DatePicker dp_AppointmentDate;
    Label lbl_EmployeeNr;
    Label lbl_EmployeeName;
    Label lbl_EmployeePhone;
    Label lbl_workDays;
    Label lbl_EmployeeDate;
    Label lbl_EmployeeStartTime;
    Label lbl_EmployeeStopTime;
    Label lbl_EmployeeEmail;


    String DATE_PATTERN = "dd:MM:yyyy";
    String TIME_PATTERN = "HH:mm";
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(DATE_PATTERN);
    DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern(TIME_PATTERN);

    @Override
    public void start(Stage stage) throws Exception {
        employeeManager = new EmployeeManager();
        appointmentManager = new AppointmentManager();
        table = new TableView<>();

        stage.setTitle("Fysio App");

        table.setEditable(true);
        Callback<TableColumn, TableCell> cellFactory =
                p -> new EditingCell();

        //region Creating tabs
        pane = new TabPane();
        appointmentTab = new Tab("Afspraak");
        customerTab = new Tab("Patient");
        employeeTab = new Tab("Medewerker");
        manageEmployeeTab = new Tab("Overzicht Werknemers");

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
        TableColumn numberCol = new TableColumn("Nummer");
        numberCol.setCellValueFactory(
                new PropertyValueFactory<Appointment, Integer>("appointmentNumber"));
        TableColumn fysioCol = new TableColumn("Patient");
        fysioCol.setCellValueFactory(
                new PropertyValueFactory<Appointment, String>("patientName"));
        //endregion

        //region Creating buttons
        dp_AppointmentDate = new DatePicker();
        dp_AppointmentDate.setPromptText(dateFormat.format(LocalDate.now()));
        cb_Employee = new ComboBox<>(employeeManager.getEmployeeNumbers());
        lbl_EmployeeNr = new Label("Medewerkernummer");
        lbl_EmployeeName = new Label("Naam");
        lbl_EmployeePhone = new Label("Telefoonnummer");
        lbl_workDays = new Label("Werktijden");
        lbl_EmployeeDate = new Label();
        lbl_EmployeeStartTime = new Label();
        lbl_EmployeeStopTime = new Label();
        lbl_EmployeeEmail = new Label("Email");
        Line line = new Line(-100, 0, 50, 0);

        Button btn_Search = new Button("Zoek");
        btn_Search.setOnAction(e -> searchForEmployee());
        //endregion

        table.getColumns().addAll(numberCol, fysioCol);
        hBox.getChildren().addAll(cb_Employee, dp_AppointmentDate);
        vBox.getChildren().addAll(lbl_EmployeeNr, lbl_EmployeeName, lbl_EmployeePhone, lbl_EmployeeEmail, line, lbl_workDays, lbl_EmployeeDate, lbl_EmployeeStartTime, lbl_EmployeeStopTime);
        vBox.setSpacing(2);

        borderPane.setTop(hBox);
        borderPane.setLeft(vBox);
        borderPane.setCenter(table);
        borderPane.setBottom(btn_Search);

        pane.getTabs().addAll(appointmentTab, employeeTab, customerTab, manageEmployeeTab);
        employeeTab.setContent(borderPane);
        pane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Scene scene = new Scene(pane);
        scene.getStylesheets().addAll(AppointmentGUI.class.getResource("/Light.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }


    private void searchForEmployee() {
        LocalDate localdate = dp_AppointmentDate.getValue();
        String employeeNr = cb_Employee.getValue();
        Employee tempEmployee = employeeManager.searchEmployeeWithNumber(employeeNr);

        if (tempEmployee == null) {
            AlertBox.display("Foutmelding", "Geen werknemer gevonden met nummer: " + employeeNr);
        } else if (localdate == null) {
            AlertBox.display("Foutmelding", "U heeft geen datum geselecteert");
        } else {
            Workday tempWorkday = employeeManager.searchWorkdayWithDate(employeeNr, localdate);
            if (tempWorkday == null) {
                AlertBox.display("Foutmelding", "Medewerker: " + tempEmployee.getName() + " was niet werkzaam op: " + dateFormat.format(localdate));
                lbl_EmployeeName.setText("");
                lbl_EmployeePhone.setText("");
                lbl_EmployeeEmail.setText("");
                lbl_EmployeeDate.setText("");
                lbl_EmployeeStartTime.setText("");
                lbl_EmployeeStopTime.setText("");
            } else {
                lbl_EmployeeName.setText(tempEmployee.getName());
                lbl_EmployeePhone.setText((tempEmployee.getPhone()));
                lbl_EmployeeEmail.setText(tempEmployee.getEmail());
                lbl_workDays.setText("Werktijden");
                lbl_EmployeeDate.setText(dateFormat.format(tempWorkday.getWorkDate()));
                lbl_EmployeeStartTime.setText(timeFormat.format(tempWorkday.getStartTime()));
                lbl_EmployeeStopTime.setText(timeFormat.format(tempWorkday.getStopTime()));

                table.setItems(appointmentManager.searchWithWorkDateAndEmployeeName(localdate, tempEmployee.getName()));
            }
        }
    }
}
