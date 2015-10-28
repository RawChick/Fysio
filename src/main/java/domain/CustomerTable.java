package domain;

import javafx.beans.property.SimpleStringProperty;
import java.time.LocalDate;
import java.time.LocalTime;

public class CustomerTable {
    private SimpleStringProperty dateCol;
    private SimpleStringProperty timeCol;
    private SimpleStringProperty treatCol;


    public CustomerTable(LocalDate dateCol, LocalTime timeCol, String treatCol) {
        this.dateCol = new SimpleStringProperty(dateCol.toString());
        this.timeCol = new SimpleStringProperty(timeCol.toString());
        this.treatCol = new SimpleStringProperty(treatCol);
    }

    public String getDateCol() {
        return dateCol.get();
    }

    public SimpleStringProperty dateColProperty() {
        return dateCol;
    }

    public String getTimeCol() {
        return timeCol.get();
    }

    public SimpleStringProperty timeColProperty() {
        return timeCol;
    }

    public String getTreatCol() {
        return treatCol.get();
    }

    public SimpleStringProperty treatColProperty() {
        return treatCol;
    }
}
