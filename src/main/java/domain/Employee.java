package domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "employee")
public class Employee {

	private int employeeNr;
	private String name;
	private int bsn;
	private String function;


	@XmlElement(name = "employeeId")
	public int getEmployeeNr() {
		return employeeNr;
	}

	public void setEmployeeNr(int employeeNr) {
		this.employeeNr = employeeNr;
	}

	@XmlElement(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name = "ssn")
	public int getBsn() {
		return bsn;
	}

	public void setBsn(int bsn) {
		this.bsn = bsn;
	}

	@XmlElement(name = "function")
	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}
}