package presentation;

import businesslogic.TreatmentManager;
import domain.Planning;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by ids1 on 21-10-2015.
 */
public class NewAppointmentGUI {
    private static final Label bsn = new Label("BSN");
    private static final Label name = new Label("Naam");
    private static final Label treatment = new Label("Behandeling");
    private static final Label date = new Label("Datum, Tijd");
    private static final Label therapist = new Label("Fysiotherapeut");

    public static void display(){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("nieuwe afspraak");

        TextField bsnText = new TextField();
        bsnText.setPromptText("BSN");
        TextField nameText = new TextField();
        nameText.setPromptText("Naam");
        TextField treatText = new TextField();
        treatText.setPromptText("Behandeling");
        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Datum");
        TextField time = new TextField();
        time.setPromptText("Tijd");
        TextField therapistText = new TextField();
        therapistText.setPromptText("Fysiotherapeut");

        Button add = new Button("Voeg toe");
        Button close = new Button("Anuleren");
        GridPane gridPane = new GridPane();
        VBox vBox = new VBox(20);
        vBox.getChildren().add(gridPane);
        gridPane.addColumn(0, bsn, name, treatment, date, therapist, close);
        gridPane.addColumn(1, bsnText, nameText, treatText, datePicker, therapistText, add);
        gridPane.addColumn(2,new Label(""),new Label(""),new Label(""),time);
        vBox.setPadding(new Insets(20,20,20,20));
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setMinWidth(500);
        window.setResizable(false);

        close.setOnAction(e -> window.close());

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.showAndWait();

        add.setOnAction(e -> addAppointment(bsnText.getText(), nameText.getText(), treatText.getText(), datePicker.getValue().toString(),
                time.getText(), therapistText.getText()));
    }
    public static void addAppointment(String bsn, String name, String treatment, String date, String time, String therapist){
        TreatmentManager manager = new TreatmentManager();

    }
}
