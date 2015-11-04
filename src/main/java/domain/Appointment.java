package domain;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


@XmlRootElement(name = "appointment")
public class Appointment {

    public Appointment() {
    }

    //region Attributes and properties
    private SimpleIntegerProperty appointmentNumber;
    private LocalDate appointmentDate;
    private LocalTime appointmentStartTime;
    private LocalTime appointmentStopTime;
    private Employee appointmentFysio;
    private Patient appointmentPatient;

    private String appointmentFysioName;
    private String appointmentPatientName;
    private String appointmentStartTimeString;
    private String appointmentStopTimeString;
    //endregion


    //region Methods
    public Appointment(int appointmentNumber, LocalDate appointmentDate, LocalTime startTime, LocalTime stopTime, Employee fysio, Patient patient) {
        this.appointmentNumber = new SimpleIntegerProperty(this, "appointmentNumber", appointmentNumber);
        this.appointmentDate = appointmentDate;
        this.appointmentStartTime = startTime;
        this.appointmentStopTime = stopTime;
        this.appointmentFysio = fysio;
        this.appointmentPatient = patient;

        setAppointmentFysioName(fysio.getEmployeeName());
        setAppointmentPatientName(patient.getPatientFullName());

        String TIME_PATTERN = "HH:mm";
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern(TIME_PATTERN);
        setAppointmentStartTimeString(timeFormat.format(startTime));
        setAppointmentStopTimeString(timeFormat.format(stopTime));
    }

    public String ToString() {
        return appointmentNumber + " op: " + appointmentDate + " heeft Fysiotherapeut: " +
                appointmentFysio.getEmployeeName() + " van: " + appointmentStartTime + " tot: " + appointmentStopTime + " patient: " + appointmentPatient.getPatientFullName() + " behandeld";
    }
    //endregion

    //region Getters and setters
    @XmlElement(name = "appointmentNr")
    public Integer getAppointmentNumber() {
        return appointmentNumber.get();
    }

    public IntegerProperty appointmentNumberProperty() {
        return appointmentNumber;
    }

    public void setAppointmentNumber(int appointmentNumber) {
        this.appointmentNumber.set(appointmentNumber);
    }

    @XmlElement(name = "appointmentDate")
    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    @XmlElement(name = "startTime")
    public LocalTime getAppointmentStartTime() {
        return appointmentStartTime;
    }

    public void setAppointmentStartTime(LocalTime appointmentStartTime) {
        this.appointmentStartTime = appointmentStartTime;
    }

    @XmlElement(name = "stopTime")
    public LocalTime getAppointmentStopTime() {
        return appointmentStopTime;
    }

    public void setAppointmentStopTime(LocalTime appointmentStopTime) {
        this.appointmentStopTime = appointmentStopTime;
    }

    public Employee getAppointmentFysio() {
        return appointmentFysio;
    }

    public void setAppointmentFysio(Employee appointmentFysio) {
        this.appointmentFysio = appointmentFysio;
    }

    public Patient getAppointmentPatient() {
        return appointmentPatient;
    }

    public void setAppointmentPatient(Patient appointmentPatient) {
        this.appointmentPatient = appointmentPatient;
    }

    public String getAppointmentFysioName() {
        return appointmentFysioName;
    }

    public void setAppointmentFysioName(String appointmentFysioName) {
        this.appointmentFysioName = appointmentFysioName;
    }

    public String getAppointmentPatientName() {
        return appointmentPatientName;
    }

    public void setAppointmentPatientName(String appointmentPatientName) {
        this.appointmentPatientName = appointmentPatientName;
    }

    public String getAppointmentStartTimeString() {
        return appointmentStartTimeString;
    }

    public void setAppointmentStartTimeString(String appointmentStartTimeString) {
        this.appointmentStartTimeString = appointmentStartTimeString;
    }

    public String getAppointmentStopTimeString() {
        return appointmentStopTimeString;
    }

    public void setAppointmentStopTimeString(String appointmentStopTimeString) {
        this.appointmentStopTimeString = appointmentStopTimeString;
    }

    //endregion
}
