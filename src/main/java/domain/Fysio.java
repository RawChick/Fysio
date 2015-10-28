package domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Fysio")
public class Fysio {

	private int fysioNr;
	private String name;
	private String fysioAdres;
	private String fysioPhoneNR;
	private String fysioEmail;

	@XmlElement(name = "fysioMail")
	public String getFysioEmail() {
		return fysioEmail;
	}

	public void setFysioEmail(String fysioEmail) {
		this.fysioEmail = fysioEmail;
	}

	@XmlElement(name = "companyId")
	public int getFysioNr() {
		return fysioNr;
	}

	public void setFysioNr(int fysioNr) {
		this.fysioNr = fysioNr;
	}

	@XmlElement(name = "companyName")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name = "fysioAddress")
	public String getFysioAdres() {
		return fysioAdres;
	}

	public void setFysioAdres(String fysioAdres) {
		this.fysioAdres = fysioAdres;
	}

	@XmlElement(name = "fysioPhone")
	public String getFysioPhoneNR() {
		return fysioPhoneNR;
	}

	public void setFysioPhoneNR(String fysioPhoneNR) {
		this.fysioPhoneNR = fysioPhoneNR;
	}
}