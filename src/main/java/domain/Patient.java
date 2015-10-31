package domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "client")
public class Patient {

	private int bsn;
	private String name;
	private String adress;
	private String city;
	private LocalDate dateOfBirth;
    private String zipCode;
	private String phone;
	private String email;
    private List<Treatment> treatments;

    public Patient(int bsn, String name, String adress, String city, LocalDate dateOfBirth, String zipCode, String phone, String email) {
        setBsn(bsn);
        setName(name);
        setAdress(adress);
        setCity(city);
        setDateOfBirth(dateOfBirth);
        setZipCode(zipCode);
        setPhone(phone);
        setEmail(email);
    }

    public int getBsn() {
        return bsn;
    }

    private void setBsn(int bsn) {
        this.bsn = bsn;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    private void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCity() {
        return city;
    }

    private void setCity(String city) {
        this.city = city;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    private void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getZipCode() {
        return zipCode;
    }

    private void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhone() {
        return phone;
    }

    private void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    public List<Treatment> getTreatments() {
        return treatments;
    }

    private void setTreatments(List<Treatment> treatments) {
        this.treatments = treatments;
    }
}