package domain;

import javafx.beans.property.SimpleStringProperty;
import java.time.LocalTime;

public class Appointment1 {

    public String getTime() {
        return time.get();
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getTreatment() {
        return treatment.get();
    }

    public SimpleStringProperty treatmentProperty() {
        return treatment;
    }

    public String getTherapist() {
        return therapist.get();
    }

    public SimpleStringProperty therapistProperty() {
        return therapist;
    }

    public Appointment1(LocalTime time, String name, String treatment, String therapist) {
        this.time = new SimpleStringProperty(time.toString());
        this.name = new SimpleStringProperty(name);
        this.treatment = new SimpleStringProperty(treatment);
        this.therapist = new SimpleStringProperty(therapist);
    }

    private final SimpleStringProperty time;
    private final SimpleStringProperty name;
    private final SimpleStringProperty treatment;
    private final SimpleStringProperty therapist;
}
