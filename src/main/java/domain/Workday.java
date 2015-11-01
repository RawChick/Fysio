package domain;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by Barrie on 31-10-2015.
 */
public class Workday {
    //region Attributes and properties
    private LocalDate workDate;
    private LocalTime startTime;
    private LocalTime stopTime;
    //endregion

    //region Methods
    public Workday(LocalDate workDate, LocalTime startTime, LocalTime stopTime) {
        this.workDate = workDate;
        this.startTime = startTime;
        this.stopTime = stopTime;
    }
    //endregion

    //region Getters and setters
    public LocalDate getWorkDate() {
        return workDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getStopTime() {
        return stopTime;
    }
    //endregion
}
