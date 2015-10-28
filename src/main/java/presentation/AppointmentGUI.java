package presentation;

import businesslogic.EmployeeManager;
import businesslogic.TreatmentManager;
import domain.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

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
    private TableView<Appointment1> appointmentTable = new TableView<>();
    private TableView<Appointment2> therapistTable = new TableView<>();
    private VBox box = new VBox(20);
    private Button button = new Button("Nieuwe Afspraak");
    private Button changeAppointment = new Button("Afspraak wijzigen");
    private Button removeAppointment = new Button("Afspraak verwijderen");
    private final static Label planningLabel = new Label("Planning voor deze datum");
    private final static Label availability = new Label("Beschikbaarheid voor deze datum");

    @Override
    public void start(Stage primaryStage) throws Exception {
        pane.getSelectionModel().select(appointmentTab);

        appointmentTable.setEditable(true);
        therapistTable.setEditable(true);
        TableColumn<Appointment1, String> time = new TableColumn<>("Tijd");
        TableColumn<Appointment1, String> name = new TableColumn<>("Naam");
        TableColumn<Appointment1, String> treatment = new TableColumn<>("Behandeling");
        TableColumn<Appointment1, String> therapist = new TableColumn<>("Fysiotherapeut");
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        treatment.setCellValueFactory(new PropertyValueFactory<>("treatment"));
        therapist.setCellValueFactory(new PropertyValueFactory<>("therapist"));

        TableColumn<Appointment2, String> nameCol = new TableColumn<>("Naam");
        TableColumn<Appointment2, String> age = new TableColumn<>("Leeftijd");
        TableColumn<Appointment2, String> available = new TableColumn<>("Vrij");
        TableColumn<Appointment2, String> present = new TableColumn<>("Aanwezig");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("nameCol"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        available.setCellValueFactory(new PropertyValueFactory<>("available"));
        present.setCellValueFactory(new PropertyValueFactory<>("present"));

        appointmentTable.getColumns().addAll(time, name, treatment, therapist);
        therapistTable.getColumns().addAll(nameCol, age, available, present);

        pane.getTabs().addAll(appointmentTab, employeeTab, customerTab, manageEmployeeTab);
        appointmentTab.setContent(anchorPane);
        anchorPane.getChildren().addAll(hBox);
        hBox.getChildren().addAll(vBox, box);
        box.getChildren().addAll(button, availability, therapistTable, changeAppointment);
        vBox.getChildren().addAll(datePicker, planningLabel, appointmentTable, removeAppointment);
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
                CustomerGUI gui = new CustomerGUI();
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
            searchByDate(newValue);
        });
        changeAppointment.setOnAction(e -> changeAppointment());
        removeAppointment.setOnAction(e -> removeAppointment());
        button.setOnAction(e -> newAppointment());
    }
    public void changeAppointment(){
        ObservableList<Appointment1> appointment1s = appointmentTable.getSelectionModel().getSelectedItems();
        ChangeAppointmentGUI.display(appointment1s);
        searchByDate(datePicker.getValue());

    }
    public void removeAppointment(){
        Component component = new Component() {
            @Override
            public String getName() {
                return super.getName();
            }
        };
        ObservableList<Appointment1> appointment1s = appointmentTable.getSelectionModel().getSelectedItems();
        int remove = JOptionPane.showConfirmDialog(component, "weet u zeker dat u de afspraak van " + appointment1s.get(0).getName() +
                " om " + appointment1s.get(0).getTime() + " voor de behandeling: " + appointment1s.get(0).getTreatment()
        + " wilt verwijderen?", "verwijderen", JOptionPane.YES_NO_OPTION);
        if (remove == 1){
            TreatmentManager manager = new TreatmentManager();
            manager.removeAppointment(appointment1s.get(0).getName(),
                    appointment1s.get(0).getTime(), appointment1s.get(0).getTreatment(), appointment1s.get(0).getTherapist());
        }
    }
    public void newAppointment() {
        NewAppointmentGUI.display();
        searchByDate(datePicker.getValue());
    }
    public void searchByDate(LocalDate newValue){
        TreatmentManager manager1 = new TreatmentManager();
        ArrayList<Appointment1> appointment1s = manager1.getAppointmet1ByDate(newValue);
        final ObservableList<Appointment1> data = FXCollections.observableArrayList();
        for (Appointment1 appointment1 : appointment1s){
            data.add(appointment1);
            appointmentTable.setItems(data);
        }

        TreatmentManager manager = new TreatmentManager();
        ArrayList<Appointment2> appointment2s = manager.getAppointmet2ByDate(newValue);
        final ObservableList<Appointment2> data2 = FXCollections.observableArrayList();
        for (Appointment2 appointment2 : appointment2s) {
            data2.add(appointment2);
            therapistTable.setItems(data2);
        }

    }
}