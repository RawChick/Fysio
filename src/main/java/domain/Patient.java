package domain;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "client")
public class Patient {
    //region Attributes and properties
    private SimpleIntegerProperty patientBSN;
    private SimpleStringProperty patientName;
    private SimpleStringProperty patientCity;
    private SimpleStringProperty patientAddress;
    private LocalDate patientDateOfBirth;
    private SimpleStringProperty patientZipCode;
    private SimpleStringProperty patientPhone;
    private SimpleStringProperty patientEmail;

    private ArrayList<Treatment> patientTreatments;
    //endregion

    //region Methods
    public Patient(int bsn, String name,String city, String address,  LocalDate dateOfBirth, String zipCode, String phone, String email) {
        this.patientBSN = new SimpleIntegerProperty(this, "patientBSN", bsn);
        this.patientName = new SimpleStringProperty(this, "patientName", name);
        this.patientCity = new SimpleStringProperty(this, "patientCity", city);
        this.patientAddress = new SimpleStringProperty(this, "patientAddress", address);
        this.patientDateOfBirth = dateOfBirth;
        this.patientZipCode = new SimpleStringProperty(this, "patientZipCode", zipCode);
        this.patientPhone = new SimpleStringProperty(this, "patientPhone", phone);
        this.patientEmail = new SimpleStringProperty(this, "patientEmail", email);
    }
    //endregion

    //region Getters and setters
    public int getPatientBSN() {
        return patientBSN.get();
    }

    public SimpleIntegerProperty patientBSNProperty() {
        return patientBSN;
    }

    public void setPatientBSN(int patientBSN) {
        this.patientBSN.set(patientBSN);
    }

    public String getPatientName() {
        return patientName.get();
    }

    public SimpleStringProperty patientNameProperty() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName.set(patientName);
    }

    public String getPatientCity() {
        return patientCity.get();
    }

    public SimpleStringProperty patientCityProperty() {
        return patientCity;
    }

    public void setPatientCity(String patientCity) {
        this.patientCity.set(patientCity);
    }

    public String getPatientAddress() {
        return patientAddress.get();
    }

    public SimpleStringProperty patientAddressProperty() {
        return patientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress.set(patientAddress);
    }

    public LocalDate getPatientDateOfBirth() {
        return patientDateOfBirth;
    }

    public void setPatientDateOfBirth(LocalDate patientDateOfBirth) {
        this.patientDateOfBirth = patientDateOfBirth;
    }

    public String getPatientZipCode() {
        return patientZipCode.get();
    }

    public SimpleStringProperty patientZipCodeProperty() {
        return patientZipCode;
    }

    public void setPatientZipCode(String patientZipCode) {
        this.patientZipCode.set(patientZipCode);
    }

    public String getPatientPhone() {
        return patientPhone.get();
    }

    public SimpleStringProperty patientPhoneProperty() {
        return patientPhone;
    }

    public void setPatientPhone(String patientPhone) {
        this.patientPhone.set(patientPhone);
    }

    public String getPatientEmail() {
        return patientEmail.get();
    }

    public SimpleStringProperty patientEmailProperty() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail.set(patientEmail);
    }

    public ArrayList<Treatment> getPatientTreatments() {
        return patientTreatments;
    }

    public void setPatientTreatments(ArrayList<Treatment> patientTreatments) {
        this.patientTreatments = patientTreatments;
    }
    //endregion
}