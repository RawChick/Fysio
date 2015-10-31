package domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.time.LocalTime;

public class Treatment {

	private int treatmentCode;
	private String treatmentName;
	private int amountSessions;
	private double sessionCost;
	private double sessionDuration;

	public Treatment (int treatmentCode, String treatmentName, int amountSessions, double sessionCost, double sessionDuration) {
		setTreatmentCode(treatmentCode);
		setTreatmentName(treatmentName);
		setAmountSessions(amountSessions);
		setSessionCost(sessionCost);
		setSessionDuration(sessionDuration);
	}

	public int getTreatmentCode() {
		return treatmentCode;
	}

	private void setTreatmentCode(int treatmentCode) {
		this.treatmentCode = treatmentCode;
	}

	public String getTreatmentName() {
		return treatmentName;
	}

	private void setTreatmentName(String treatmentName) {
		this.treatmentName = treatmentName;
	}

	public int getAmountSessions() {
		return amountSessions;
	}

	private void setAmountSessions(int amountSessions) {
		this.amountSessions = amountSessions;
	}

	public double getSessionDuration() {
		return sessionDuration;
	}

	private void setSessionDuration(double sessionDuration) {
		this.sessionDuration = sessionDuration;
	}

	public double getSessionCost() {
		return sessionCost;
	}

	private void setSessionCost(double sessionCost) {
		this.sessionCost = sessionCost;
	}
}