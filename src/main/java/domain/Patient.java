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
    private SimpleStringProperty patientFullName;
    private SimpleStringProperty patientFirstName;
    private SimpleStringProperty patientLastName;
    private SimpleStringProperty patientCity;
    private SimpleStringProperty patientCountry;
    private SimpleStringProperty patientStreet;
    private SimpleStringProperty patientHouseNumber;
    private SimpleStringProperty patientAddress;
    private LocalDate patientDateOfBirth;
    private SimpleStringProperty patientZipCode;
    private SimpleStringProperty patientPhone;
    private SimpleStringProperty patientEmail;

    private ArrayList<Treatment> patientTreatments;
    //endregion

    //region Methods
    public Patient(int bsn, String firstName, String lastName, String city, String country, String street, String houseNumber, LocalDate dateOfBirth, String zipCode, String phone, String email) {
        this.patientBSN = new SimpleIntegerProperty(this, "patientBSN", bsn);
        this.patientFirstName = new SimpleStringProperty(this, "patientFirstName", firstName);
        this.patientLastName = new SimpleStringProperty(this, "patientLastName", lastName);
        this.patientCity = new SimpleStringProperty(this, "patientCity", city);
        this.patientHouseNumber = new SimpleStringProperty(this, "patientHouseNumber", houseNumber);
        this.patientStreet = new SimpleStringProperty(this, "patientStreet", street);
        this.patientCountry = new SimpleStringProperty(this, "patientCountry", country);
        this.patientDateOfBirth = dateOfBirth;
        this.patientZipCode = new SimpleStringProperty(this, "patientZipCode", zipCode);
        this.patientPhone = new SimpleStringProperty(this, "patientPhone", phone);
        this.patientEmail = new SimpleStringProperty(this, "patientEmail", email);
        this.patientFullName = new SimpleStringProperty(this, "patientFullName", firstName + " " + lastName);
        this.patientAddress = new SimpleStringProperty(this, "patientAddress", street + " " + houseNumber + " " + city);
    }
    //endregion

    //region Getters and setters


    public String getPatientAddress() {
        return patientAddress.get();
    }

    public SimpleStringProperty patientAddressProperty() {
        return patientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress.set(patientAddress);
    }

    public String getPatientFullName() {
        return patientFullName.get();
    }

    public SimpleStringProperty patientFullNameProperty() {
        return patientFullName;
    }

    public void setPatientFullName(String patientFullName) {
        this.patientFullName.set(patientFullName);
    }

    @XmlElement(name = "ssn")
    public int getPatientBSN() {
        return patientBSN.get();
    }

    public SimpleIntegerProperty patientBSNProperty() {
        return patientBSN;
    }

    public void setPatientBSN(int patientBSN) {
        this.patientBSN.set(patientBSN);
    }

    @XmlElement(name = "firstName")
    public String getPatientFirstName() {
        return patientFirstName.get();
    }

    public SimpleStringProperty patientFirstNameProperty() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName.set(patientFirstName);
    }

    @XmlElement(name = "lastName")
    public String getPatientLastName() {
        return patientLastName.get();
    }

    public SimpleStringProperty patientLastNameameProperty() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName.set(patientLastName);
    }

    @XmlElement(name = "city")
    public String getPatientCity() {
        return patientCity.get();
    }

    public SimpleStringProperty patientCityProperty() {
        return patientCity;
    }

    public void setPatientCity(String patientCity) {
        this.patientCity.set(patientCity);
    }

    @XmlElement(name = "houseNumber")
    public String getPatientHouseNumber() {
        return patientHouseNumber.get();
    }

    public SimpleStringProperty getPatientHouseNumberProperty() {
        return patientHouseNumber;
    }

    public void setPatientHouseNumber(String patientHouseNumber) {
        this.patientHouseNumber.set(patientHouseNumber);
    }

    @XmlElement(name = "country")
    public String getPatientCountry() {
        return patientCountry.get();
    }

    public SimpleStringProperty patientCountryProperty() {
        return patientCountry;
    }

    public void setPatientCountry(String patientCountry) {
        this.patientCountry.set(patientCountry);
    }

    @XmlElement(name = "street")
    public String getPatientStreet() {
        return patientStreet.get();
    }

    public SimpleStringProperty patientStreetProperty() {
        return patientStreet;
    }

    public void setPatientStreet(String patientStreet) {
        this.patientStreet.set(patientStreet);
    }

    @XmlElement(name = "dateOfBirth")
    public LocalDate getPatientDateOfBirth() {
        return patientDateOfBirth;
    }

    public void setPatientDateOfBirth(LocalDate patientDateOfBirth) {
        this.patientDateOfBirth = patientDateOfBirth;
    }

    @XmlElement(name = "postalCode")
    public String getPatientZipCode() {
        return patientZipCode.get();
    }

    public SimpleStringProperty patientZipCodeProperty() {
        return patientZipCode;
    }

    public void setPatientZipCode(String patientZipCode) {
        this.patientZipCode.set(patientZipCode);
    }

    @XmlElement(name = "phone")
    public String getPatientPhone() {
        return patientPhone.get();
    }

    public SimpleStringProperty patientPhoneProperty() {
        return patientPhone;
    }

    public void setPatientPhone(String patientPhone) {
        this.patientPhone.set(patientPhone);
    }

    @XmlElement(name = "email")
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