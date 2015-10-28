package domain;

import javafx.beans.property.SimpleStringProperty;

public class Appointment2 {

    private final SimpleStringProperty nameCol;
    private final SimpleStringProperty age;
    private final SimpleStringProperty available;
    private final SimpleStringProperty present;

    public Appointment2(String nameCol, int age, boolean available, boolean present) {
        this.nameCol = new SimpleStringProperty(nameCol);
        this.age = new SimpleStringProperty(String.valueOf(age));
        this.available = new SimpleStringProperty(String.valueOf(available));
        this.present = new SimpleStringProperty(String.valueOf(present));
    }

    public String getNameCol() {
        return nameCol.get();
    }

    public SimpleStringProperty nameColProperty() {
        return nameCol;
    }

    public String getAge() {
        return age.get();
    }

    public SimpleStringProperty ageProperty() {
        return age;
    }

    public String getAvailable() {
        return available.get();
    }

    public SimpleStringProperty availableProperty() {
        return available;
    }

    public String getPresent() {
        return present.get();
    }

    public SimpleStringProperty presentProperty() {
        return present;
    }
}
