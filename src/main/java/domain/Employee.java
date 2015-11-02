package domain;

import javafx.beans.property.SimpleStringProperty;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by Barrie on 21-10-2015.
 */
@XmlRootElement(name = "employee")
public class Employee {
    //region Attributes and properties
    private SimpleStringProperty employeeNr;
    private SimpleStringProperty employeeName;
    private SimpleStringProperty employeeFunction;
    private SimpleStringProperty employeeBSN;
    private SimpleStringProperty employeeCity;
    private SimpleStringProperty employeeCountry;
    private SimpleStringProperty employeeStreet;
    private SimpleStringProperty employeeAddress;
    private SimpleStringProperty employeeHouseNumber;
    private SimpleStringProperty employeeDateOfBirth;
    private SimpleStringProperty employeeZipCode;
    private SimpleStringProperty employeePhone;
    private SimpleStringProperty employeeMail;

    private ArrayList<Workday> employeeWorkdays;
    //endregion

    //region Methods
    public Employee(String nr, String name, String employeeFunction, String employeeBSN, String employeeCity, String employeeCountry, String employeeStreet, String employeeHouseNumber, String employeeDateOfBirth, String employeeZipCode, String employeePhone, String employeeMail) {
        if (name.equals("Mark van Turnhout") || name.equals("Noureddine Azzagari")) {
            this.employeeFunction = new SimpleStringProperty("Baas!");
        } else {
            this.employeeFunction = new SimpleStringProperty(employeeFunction);
        }
        this.employeeNr = new SimpleStringProperty(this, "employeeNr", nr);
        this.employeeName = new SimpleStringProperty(this, "employeeName", name);
        this.employeeBSN = new SimpleStringProperty(this, "employeeBSN", employeeBSN);
        this.employeeCity = new SimpleStringProperty(this, "employeeCity", employeeCity);
        this.employeeCountry = new SimpleStringProperty(this, "employeeCountry", employeeCountry);
        this.employeeHouseNumber = new SimpleStringProperty(this, "employeeHouseNumber", employeeHouseNumber);
        this.employeeStreet = new SimpleStringProperty(this, "employeeStreet", employeeStreet);
        this.employeeDateOfBirth = new SimpleStringProperty(this, "employeeDateOFBirth", employeeDateOfBirth);
        this.employeeZipCode = new SimpleStringProperty(this, "employeeZipCode", employeeZipCode);
        this.employeePhone = new SimpleStringProperty(this, "employeePhone", employeePhone);
        this.employeeMail = new SimpleStringProperty(this, "employeeMail", employeeMail);
        this.employeeAddress = new SimpleStringProperty(this, "employeeAddress", employeeStreet + " " + employeeHouseNumber + " " + employeeCity);
        this.employeeWorkdays = new ArrayList<>();
    }

    public Workday searchWorkdayByDate(LocalDate workDate) {
        Workday tempWorkday = null;
        for (Workday w : employeeWorkdays) {
            if (w.getWorkDate().equals(workDate)) {
                tempWorkday = w;
            }
        }
        return tempWorkday;
    }

    public boolean addWorkday(Workday workday) {
        boolean tempBool = false;
        if (workday != null) {
            employeeWorkdays.add(workday);
            tempBool = true;
        }
        return tempBool;
    }

    public boolean removeWorkday(Workday workday) {
        boolean tempBool = false;
        if (workday != null) {
            employeeWorkdays.remove(workday);
            tempBool = true;
        }
        return tempBool;
    }

    public String ToString() {
        return String.valueOf(employeeName);
    }
    //endregion

    //region Getters and Setters
    @XmlElement(name = "employeeId")
    public String getEmployeeNr() {
        return employeeNr.get();
    }

    public SimpleStringProperty employeeNrProperty() {
        return employeeNr;
    }

    public void setEmployeeNr(String employeeNr) {
        this.employeeNr.set(employeeNr);
    }

    @XmlElement(name = "name")
    public String getEmployeeName() {
        return employeeName.get();
    }

    public SimpleStringProperty employeeNameProperty() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName.set(employeeName);
    }

    @XmlElement(name = "function")
    public String getEmployeeFunction() {
        return employeeFunction.get();
    }

    public SimpleStringProperty employeeFunctionProperty() {
        return employeeFunction;
    }

    public void setEmployeeFunction(String employeeFunction) {
        this.employeeFunction.set(employeeFunction);
    }

    @XmlElement(name = "ssn")
    public String getEmployeeBSN() {
        return employeeBSN.get();
    }

    public SimpleStringProperty employeeBSNProperty() {
        return employeeBSN;
    }

    public void setEmployeeBSN(String employeeBSN) {
        this.employeeBSN.set(employeeBSN);
    }

    @XmlElement(name = "address")
    public String getEmployeeAddress() {
        return employeeAddress.get();
    }

    public SimpleStringProperty employeeAddressProperty() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress.set(employeeAddress);
    }


    @XmlAttribute(name = "city")
    public String getEmployeeCity() {
        return employeeCity.get();
    }

    public SimpleStringProperty employeeCityProperty() {
        return employeeCity;
    }

    public void setEmployeeCity(String employeeCity) {
        this.employeeCity.set(employeeCity);
    }

    @XmlAttribute(name = "houseNumber")
    public String getEmployeeHouseNumber() {
        return employeeHouseNumber.get();
    }

    public SimpleStringProperty getEmployeeHouseNumberProperty() {
        return employeeHouseNumber;
    }

    public void setEmployeeHouseNumber(String employeeHouseNumber) {
        this.employeeHouseNumber.set(employeeHouseNumber);
    }

    @XmlAttribute(name = "country")
    public String getEmployeeCountry() {
        return employeeCountry.get();
    }

    public SimpleStringProperty employeeCountryProperty() {
        return employeeCountry;
    }

    public void setEmployeeCountry(String employeeCountry) {
        this.employeeCountry.set(employeeCountry);
    }

    @XmlAttribute(name = "street")
    public String getEmployeeStreet() {
        return employeeStreet.get();
    }

    public SimpleStringProperty employeeStreetProperty() {
        return employeeStreet;
    }

    public void setEmployeeStreet(String employeeStreet) {
        this.employeeStreet.set(employeeStreet);
    }

    @XmlAttribute(name = "postalCode")
    public String getEmployeeZipCode() {
        return employeeZipCode.get();
    }

    public SimpleStringProperty employeeZipCodeProperty() {
        return employeeZipCode;
    }

    public void setEmployeeZipCode(String employeeZipCode) {
        this.employeeZipCode.set(employeeZipCode);
    }

    @XmlElement(name = "dateOfBirth")
    public String getEmployeeDateOfBirth() {
        return employeeDateOfBirth.get();
    }

    public SimpleStringProperty employeeDateOfBirthProperty() {
        return employeeDateOfBirth;
    }

    public void setEmployeeDateOfBirth(String employeeDateOfBirth) {
        this.employeeDateOfBirth.set(employeeDateOfBirth);
    }

    @XmlElement(name = "phone")
    public String getEmployeePhone() {
        return employeePhone.get();
    }

    public SimpleStringProperty employeePhoneProperty() {
        return employeePhone;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone.set(employeePhone);
    }

    @XmlElement(name = "mail")
    public String getEmployeeEmail() {
        return employeeMail.get();
    }

    public SimpleStringProperty employeeEmailProperty() {
        return employeeMail;
    }

    public void setEmployeeEmail(String employeeMail) {
        this.employeeMail.set(employeeMail);
    }

    public ArrayList<Workday> getEmployeeWorkdays() {
        return employeeWorkdays;
    }

    public void setEmployeeWorkdays(ArrayList<Workday> employeeWorkdays) {
        this.employeeWorkdays = employeeWorkdays;
    }
    //endregion
}

