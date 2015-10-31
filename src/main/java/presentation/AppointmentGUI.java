package presentation;

import businesslogic.AppointmentManager;
import domain.*;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AppointmentGUI extends Application {

    //appointment
    private TabPane pane = new TabPane();
    private Tab appointmentTab = new Tab("Afspraak");
    private Tab employeeTab = new Tab("Medewerker");
    private Tab customerTab = new Tab("Patient");
    private Tab manageEmployeeTab = new Tab("Overzicht Werknemers");
    private AnchorPane anchorPane = new AnchorPane();
    private HBox hBox = new HBox(20);
    private VBox vBox = new VBox(20);
    private DatePicker datePicker = new DatePicker();
    private VBox box = new VBox(20);
    private Button button = new Button("Nieuwe Afspraak");
    private Button changeAppointment = new Button("Afspraak wijzigen");
    private Button removeAppointment = new Button("Afspraak verwijderen");
    private AppointmentManager manager = new AppointmentManager();
    private TableView<Appointment> appointmentTableView = new TableView<>();
    private TableView<Appointment> appointmentsTodayTable = new TableView<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        pane.getSelectionModel().select(appointmentTab);

        appointmentTableView.setEditable(true);
        appointmentsTodayTable.setEditable(true);

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


        FilteredList<Appointment> filteredData = new FilteredList<>(manager.getData());
        SortedList<Appointment> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(appointmentTableView.comparatorProperty());
        appointmentTableView.setItems(sortedData);
        appointmentTableView.getColumns().addAll(numberCol, datumCol, fysioCol, patientCol);

        pane.getTabs().addAll(appointmentTab, employeeTab, customerTab, manageEmployeeTab);
        appointmentTab.setContent(anchorPane);
        anchorPane.getChildren().addAll(hBox);
        hBox.getChildren().addAll(vBox, box);
        box.getChildren().addAll(button, changeAppointment);
        vBox.getChildren().addAll(datePicker, appointmentTableView, removeAppointment);
        pane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        hBox.setPadding(new Insets(20, 20, 20, 20));

        Scene scene = new Scene(pane);
        scene.getStylesheets().addAll(AppointmentGUI.class.getResource("/Light.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();

        //adding action listeners
        pane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.getText().equals("Medewerker")){
                EmployeeGUI gui = new EmployeeGUI();
                try {
                    gui.start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (newValue.getText().equals("Patient")){
                PatientGUI gui = new PatientGUI();
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

        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
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
        changeAppointment.setOnAction(e -> changeAppointment());
        removeAppointment.setOnAction(e -> removeAppointment());
        button.setOnAction(e -> newAppointment());
    }
    public void changeAppointment(){


    }
    public void removeAppointment(){

    }
    public void newAppointment() {

    }
    public void searchByDate(LocalDate workDate){
        ObservableList<Appointment> appointmentList = manager.searchWithWorkDate(workDate);
        String DATE_PATTERN = "dd.MM.yyyy";
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(DATE_PATTERN);

        if (appointmentList == null) {
            AlertBox.display("Foutmelding", "Geen afspraken op: " + dateFormat.format(workDate));
        } else {
            appointmentTableView.setItems(appointmentList);
        }
    }
}