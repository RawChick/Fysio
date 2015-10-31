package domain;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Barrie on 21-10-2015.
 */
public class ManageEmployee {
    private final SimpleStringProperty employeeNr;
    private final SimpleStringProperty name;
    private final SimpleStringProperty function;
    private final SimpleStringProperty bsn;
    private final SimpleStringProperty city;
    private final SimpleStringProperty address;
    private final SimpleStringProperty dateOfBirth;
    private final SimpleStringProperty zipCode;
    private final SimpleStringProperty phone;
    private final SimpleStringProperty email;

    public ManageEmployee(String nr, String name, String function, String bsn, String city, String address, String dateOfBirth, String zipCode, String phone, String email) {
        if (name.equals("Mark van Turnhout") || name.equals("Noureddine Azzagari")) {
            this.function = new SimpleStringProperty("Baas!");
        }else {
            this.function = new SimpleStringProperty(function);
        }
        this.employeeNr = new SimpleStringProperty(nr);
        this.name = new SimpleStringProperty(name);
        this.bsn = new SimpleStringProperty(bsn);
        this.city = new SimpleStringProperty(city);
        this.address = new SimpleStringProperty(address);
        this.dateOfBirth = new SimpleStringProperty(dateOfBirth);
        this.zipCode = new SimpleStringProperty(zipCode);
        this.phone = new SimpleStringProperty(phone);
        this.email = new SimpleStringProperty(email);
    }

    //region Getters and Setters
    public String getEmployeeNr() {
        return employeeNr.get();
    }

    public SimpleStringProperty employeeNrProperty() {
        return employeeNr;
    }

    public void setEmployeeNr(String employeeNr) {
        this.employeeNr.set(employeeNr);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getFunction() {
        return function.get();
    }

    public SimpleStringProperty functionProperty() {
        return function;
    }

    public void setFunction(String function) {
        this.function.set(function);
    }

    public String getBsn() {
        return bsn.get();
    }

    public SimpleStringProperty bsnProperty() {
        return bsn;
    }

    public void setBsn(String bsn) {
        this.bsn.set(bsn);
    }

    public String getCity() {
        return city.get();
    }

    public SimpleStringProperty cityProperty() {
        return city;
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getDateOfBirth() {
        return dateOfBirth.get();
    }

    public SimpleStringProperty dateOfBirthProperty() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth.set(dateOfBirth);
    }

    public String getZipCode() {
        return zipCode.get();
    }

    public SimpleStringProperty zipCodeProperty() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode.set(zipCode);
    }

    public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }
    //endregion
}

