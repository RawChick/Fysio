package domain;

import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {

    private int appointmentNumber;
    private LocalDate appointmentDate;
    private Employee fysio;
    private Patient patient;

    private String fysioName;
    private String patientName;

    public Appointment(int appointmentNumber, LocalDate appointmentDate, Employee fysio, Patient patient) {
        setAppointmentNumber(appointmentNumber);
        setAppointmentDate(appointmentDate);
        setFysio(fysio);
        setPatient(patient);
        setFysioName(fysio.getName());
        setPatientName(patient.getName());
    }

    public String getFysioName() {
        return fysioName;
    }

    private void setFysioName(String fysioName) {
        this.fysioName = fysioName;
    }

    public String getPatientName() {
        return patientName;
    }

    private void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getAppointmentNumber() {
        return appointmentNumber;
    }

    private void setAppointmentNumber(int appointmentNumber) {
        this.appointmentNumber = appointmentNumber;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    private void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Employee getFysio() {
        return fysio;
    }

    private void setFysio(Employee fysio) {
        this.fysio = fysio;
    }

    public Patient getPatient() {
        return patient;
    }

    private void setPatient(Patient patient) {
        this.patient = patient;
    }
}
