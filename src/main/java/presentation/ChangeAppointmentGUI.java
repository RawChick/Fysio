package presentation;

import businesslogic.TreatmentManager;
import domain.Appointment;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by ids1 on 26-10-2015.
 */
public class ChangeAppointmentGUI {

    private static final Label time = new Label("Tijd");
    private static final Label name = new Label("Naam");
    private static final Label treatment = new Label("Behandeling");
    private static final Label therapist = new Label("Fysiotherapeut");

    public static void display(ObservableList<Appointment> list){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("verander afspraak");

        TextField timeText = new TextField();
        TextField nameText = new TextField();
        TextField treatText = new TextField();
        TextField therapistText = new TextField();
        /*timeText.setText(list.get(0).getTime());
        nameText.setText(list.get(0).getName());
        treatText.setText(list.get(0).getTreatment());
        therapistText.setText(list.get(0).getTherapist());*/

        Button change = new Button("Akkoord");
        Button close = new Button("Anuleren");
        GridPane gridPane = new GridPane();
        VBox vBox = new VBox(20);
        vBox.getChildren().add(gridPane);
        gridPane.addColumn(0,  time, name, treatment,  therapist, close);
        gridPane.addColumn(1, timeText, nameText, treatText,  therapistText, change);
        vBox.setPadding(new Insets(20,20,20,20));
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setMinWidth(500);
        window.setResizable(false);

        close.setOnAction(e -> window.close());

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.showAndWait();

        change.setOnAction(e -> changeAppointment(timeText.getText(), nameText.getText(), treatText.getText(),
                therapistText.getText()));
    }
    public static void changeAppointment(String time, String name, String treatment, String therapist){
        TreatmentManager manager = new TreatmentManager();

    }
}
