package domain;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by Barrie on 31-10-2015.
 */
public class Workday {

    LocalDate workDate;
    LocalTime startTime;
    LocalTime stopTime;

    public Workday(LocalDate workDate, LocalTime startTime, LocalTime stopTime) {
        this.workDate = workDate;
        this.startTime = startTime;
        this.stopTime = stopTime;
    }

    public LocalDate getWorkDate() {
        return workDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getStopTime() {
        return stopTime;
    }

    public String toStringDate() {
        return workDate.toString();
    }
}
