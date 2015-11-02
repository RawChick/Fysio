package domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by Barrie on 31-10-2015.
 */
@XmlRootElement(name = "Workday")
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

    @XmlElement(name = "workDate")
    public LocalDate getWorkDate() {
        return workDate;
    }

    @XmlElement(name = "startTime")
    public LocalTime getStartTime() {
        return startTime;
    }

    @XmlElement(name = "stopTime")
    public LocalTime getStopTime() {
        return stopTime;
    }
    //endregion
}
