package presentation;

import businesslogic.EmployeeManager;
import domain.Employee2;
import domain.EmployeeTable;
import javafx.application.Application;
import javafx.collections.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.ArrayList;

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
    private ComboBox<String> comboBox = new ComboBox<>();
    private Button button = new Button("Zoek");
    private HBox hBox1 = new HBox(20);
    private ScrollPane scrollPane = new ScrollPane();
    private AnchorPane anchorPane1 = new AnchorPane();
    private VBox vBox = new VBox(20);
    private Label label1 = new Label();
    private Label label2 = new Label();
    private Label label3 = new Label();
    private Line line = new Line(-100, 0, 50, 0);
    private Label label4 = new Label();
    private Label label5 = new Label();
    private Label label6 = new Label();
    private Label label7 = new Label();
    private Label label8 = new Label();
    private Label label9 = new Label();
    private TableView<EmployeeTable> tableView = new TableView<>();

    @Override
    public void start(Stage primaryStage) throws Exception {

        tableView.setEditable(true);
        TableColumn<EmployeeTable, String> timeCol = new TableColumn<>("Tijd");
        TableColumn<EmployeeTable, String> nameCol = new TableColumn<>("Naam");
        TableColumn<EmployeeTable, String> treatCol = new TableColumn<>("Behandeling");
        TableColumn<EmployeeTable, String> therapistCol = new TableColumn<>("Fysiotherapeut");
        timeCol.setCellValueFactory(new PropertyValueFactory<>("timeCol"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("nameCol"));
        treatCol.setCellValueFactory(new PropertyValueFactory<>("treatCol"));
        therapistCol.setCellValueFactory(new PropertyValueFactory<>("therapistCol"));
        tableView.getColumns().addAll(timeCol, nameCol, treatCol, therapistCol);

        employeeTab.setContent(anchorPane);
        anchorPane.getChildren().addAll(borderPane);
        borderPane.setTop(hBox);
        hBox.getChildren().addAll(date, text, comboBox, button);
        borderPane.setCenter(hBox1);
        hBox1.getChildren().addAll(scrollPane, tableView);
        scrollPane.setContent(anchorPane1);
        anchorPane1.getChildren().addAll(vBox);
        vBox.getChildren().addAll(label1, label2, label3, line, label4, label5, label6, label7, label8, label9);
        comboBox.getItems().addAll("werknemer nummer", "BSN");
        comboBox.setPromptText("maak een keuze");
        therapistCol.setMinWidth(100);
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

        button.setOnAction(e -> search());
    }
    public void search(){
        LocalDate localdate = date.getValue();
        String name = text.getText();
        String function = comboBox.getValue();
        EmployeeManager manager = new EmployeeManager();
        ArrayList<EmployeeTable> employees = manager.getEmployees(localdate, name, function);
        final ObservableList<EmployeeTable> data = FXCollections.observableArrayList();
        for (EmployeeTable employee : employees){
            data.add(employee);
        }
        tableView.setItems(data);
        ArrayList<Employee2> employee2s = manager.getEmployee2(localdate, name, function);
        label1.setText(employee2s.get(0).getName());
        label2.setText(employee2s.get(0).getPhone());
        label3.setText(employee2s.get(0).getEmail());
        line.setVisible(true);
        label4.setText("Werktijden");
        label5.setText("Maandag: " + employee2s.get(0).getMonday());
        label6.setText("Dinsdag: " + employee2s.get(0).getTuesday());
        label7.setText("Woensdag: " + employee2s.get(0).getWednesday());
        label8.setText("Donderdag: " + employee2s.get(0).getThursday());
        label9.setText("Vrijdag: " + employee2s.get(0).getFriday());
    }
}
