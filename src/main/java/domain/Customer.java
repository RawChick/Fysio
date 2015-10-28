package domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.util.ArrayList;

@XmlRootElement(name = "client")
public class Customer {

	private int bsn;
	private String name;
	private String adress;
	private String zipcode;
	private String city;
	private LocalDate dateOfBirth;
	private int DiagnoseCode1;
	private int DiagnoseCode2;
	private int DiagnoseCodeN;
	private String phone;
	private String email;

    public Customer(int bsn, String name, String adress, String zipcode) {
        this.bsn = bsn;
        this.name = name;
        this.adress = adress;
        this.zipcode = zipcode;
    }

    @XmlElement(name = "ssn")
    public int getBsn() {

        return bsn;
    }

    public void setBsn(int bsn) {
        this.bsn = bsn;
    }

    @XmlElement(name = "firstName" + " lastName")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "address")
    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @XmlElement(name = "postalCode")
    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @XmlElement(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @XmlElement(name = "dateOfBirth")
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getDiagnoseCode1() {
        return DiagnoseCode1;
    }

    public void setDiagnoseCode1(int diagnoseCode1) {
        DiagnoseCode1 = diagnoseCode1;
    }

    public int getDiagnoseCode2() {
        return DiagnoseCode2;
    }

    public void setDiagnoseCode2(int diagnoseCode2) {
        DiagnoseCode2 = diagnoseCode2;
    }

    public int getDiagnoseCodeN() {
        return DiagnoseCodeN;
    }

    public void setDiagnoseCodeN(int diagnoseCodeN) {
        DiagnoseCodeN = diagnoseCodeN;
    }

    @XmlElement(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @XmlElement(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}