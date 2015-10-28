package domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.time.LocalTime;

@XmlRootElement(name = "treatment")
public class Treatment {

	private int treatmentNR;
	private LocalTime treatmentDuration;
	private int treatmentCode1;
	private int treatmentCode2;
	private LocalDate treatmentStartDate;
	private LocalDate treatmentEndDate;
	private String treatmentName;
	private int amountSessions;
	private double sessionDuration;
	private double cost;


	@XmlElement(name = "code")
	public int getTreatmentNR() {
		return treatmentNR;
	}

	public void setTreatmentNR(int treatmentNR) {
		this.treatmentNR = treatmentNR;
	}

	@XmlElement(name = "treatmentDuration")
	public LocalTime getTreatmentDuration() {
		return treatmentDuration;
	}

	public void setTreatmentDuration(LocalTime treatmentDuration) {
		this.treatmentDuration = treatmentDuration;
	}


	@XmlElement(name = "code1")
	public int getTreatmentCode1() {
		return treatmentCode1;
	}

	public void setTreatmentCode1(int treatmentCode1) {
		this.treatmentCode1 = treatmentCode1;
	}

	@XmlElement(name = "code2")
	public int getTreatmentCode2() {
		return treatmentCode2;
	}

	public void setTreatmentCode2(int treatmentCode2) {
		this.treatmentCode2 = treatmentCode2;
	}

	@XmlElement(name = "endDate")
	public LocalDate getTreatmentStartDate() {
		return treatmentStartDate;
	}

	public void setTreatmentStartDate(LocalDate treatmentStartDate) {
		this.treatmentStartDate = treatmentStartDate;
	}

	@XmlElement(name = "endDate")
	public LocalDate getTreatmentEndDate() {
		return treatmentEndDate;
	}

	public void setTreatmentEndDate(LocalDate treatmentEndDate) {
		this.treatmentEndDate = treatmentEndDate;
	}


	@XmlElement(name = "name")
	public String getTreatmentName() {
		return treatmentName;
	}

	public void setTreatmentName(String treatmentName) {
		this.treatmentName = treatmentName;
	}

	@XmlElement(name= "numberOfSessions")
	public int getAmountSessions() {
		return amountSessions;
	}

	public void setAmountSessions(int amountSessions) {
		this.amountSessions = amountSessions;
	}

	@XmlElement(name= "sessionDuration")
	public double getSessionDuration() {
		return sessionDuration;
	}

	public void setSessionDuration(double sessionDuration) {
		this.sessionDuration = sessionDuration;
	}

	@XmlElement(name= "priceTreatment")
	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
}