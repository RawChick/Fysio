package domain;

import javafx.beans.property.SimpleStringProperty;

import java.time.LocalTime;

public class EmployeeTable {
    private SimpleStringProperty timeCol;
    private SimpleStringProperty nameColl;
    private SimpleStringProperty treatCol;
    private SimpleStringProperty therapistCol;

    public EmployeeTable(LocalTime timeCol, String nameCol, String treatCol, String therapistCol) {
        this.timeCol = new SimpleStringProperty(timeCol.toString());
        this.nameColl = new SimpleStringProperty(nameCol);
        this.treatCol = new SimpleStringProperty(treatCol);
        this.therapistCol = new SimpleStringProperty(therapistCol);
    }

    public String getTimeCol() {
        return timeCol.get();
    }

    public SimpleStringProperty timeColProperty() {
        return timeCol;
    }

    public String getNameColl() {
        return nameColl.get();
    }

    public SimpleStringProperty nameCollProperty() {
        return nameColl;
    }

    public String getTreatCol() {
        return treatCol.get();
    }

    public SimpleStringProperty treatColProperty() {
        return treatCol;
    }

    public String getTherapistCol() {
        return therapistCol.get();
    }

    public SimpleStringProperty therapistColProperty() {
        return therapistCol;
    }
}
