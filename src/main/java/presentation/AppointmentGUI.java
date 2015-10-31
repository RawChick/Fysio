package presentation;

import businesslogic.AppointmentManager;
import domain.*;
import javafx.application.Application;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AppointmentGUI extends Application {
    private TableView<Appointment> table;
    private AppointmentManager appointmentManager;

    private HBox hBox = new HBox(20);
    private TabPane pane;
    private Tab appointmentTab;
    private Tab employeeTab;
    private Tab patientTab;
    private Tab manageEmployeeTab;
    private BorderPane borderPane = new BorderPane();


    @Override
    public void start(Stage stage) throws Exception {
        appointmentManager = new AppointmentManager();
        table = new TableView<>();

        stage.setTitle("Fysio App");

        table.setEditable(true);
        Callback<TableColumn, TableCell> cellFactory =
                p -> new EditingCell();

        //region Creating tabs
        pane = new TabPane();
        appointmentTab = new Tab("Afspraak");
        employeeTab = new Tab("Medewerker");
        patientTab = new Tab("Patient");
        manageEmployeeTab = new Tab("Overzicht Werknemers");

        pane.getSelectionModel().select(appointmentTab);

        //adding action listeners
        pane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue.getText()) {
                case "Afspraak":
                    break;
                case "Medewerker":
                    EmployeeGUI guiEmployee = new EmployeeGUI();
                    try {
                        guiEmployee.start(stage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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
        TableColumn datumCol = new TableColumn("Datum");
        datumCol.setCellValueFactory(
                new PropertyValueFactory<Appointment, LocalDate>("appointmentDate"));
        TableColumn fysioCol = new TableColumn("Fysio");
        fysioCol.setCellValueFactory(
                new PropertyValueFactory<Appointment, String>("fysioName"));
        TableColumn patientCol = new TableColumn("Patient");
        patientCol.setCellValueFactory(
                new PropertyValueFactory<Appointment, String>("patientName"));
        //endregion

        //region Refreshing of the list
        FilteredList<Appointment> filteredData = new FilteredList<>(appointmentManager.getData());
        SortedList<Appointment> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
        //endregion

        //region Creating buttons
        Button btn_NewAppointment = new Button("Nieuwe Afspraak");
        btn_NewAppointment.setOnAction(e -> {});

        Button btn_ChangeAppointment = new Button("Afspraak wijzigen");
        btn_ChangeAppointment.setOnAction(e -> {});

        Button btn_RemoveAppointment = new Button("Afspraak verwijderen");
        btn_RemoveAppointment.setOnAction(e -> {});

        DatePicker dp_AppointmentDate = new DatePicker();
        dp_AppointmentDate.valueProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(appointment -> {
                // If filter text is empty, display all appointments.
                if (newValue == null) {
                    return true;
                }

                if (appointment.getAppointmentDate().equals(newValue)) {
                    return true; //Filter matched date
                }
                return false;
            });
        });
        String DATE_PATTERN = "dd:MM:yyyy";
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(DATE_PATTERN);
        dp_AppointmentDate.setPromptText(dateFormat.format(LocalDate.now()));
        //endregion

        table.getColumns().addAll(numberCol, datumCol, fysioCol, patientCol);
        hBox.getChildren().addAll(dp_AppointmentDate, table, btn_NewAppointment, btn_ChangeAppointment, btn_RemoveAppointment);
        hBox.setSpacing(3);

        borderPane.setTop(dp_AppointmentDate);
        borderPane.setCenter(table);
        borderPane.setBottom(hBox);

        pane.getTabs().addAll(appointmentTab, employeeTab, patientTab, manageEmployeeTab);
        appointmentTab.setContent(borderPane);
        pane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Scene scene = new Scene(pane);
        scene.getStylesheets().addAll(AppointmentGUI.class.getResource("/Light.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}