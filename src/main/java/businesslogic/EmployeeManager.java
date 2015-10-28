package businesslogic;

import domain.Employee2;
import domain.EmployeeTable;
import java.time.LocalDate;
import java.util.ArrayList;

public class EmployeeManager {
	private ArrayList<EmployeeTable> employees;
	private ArrayList<Employee2>employee2s;


	public ArrayList<EmployeeTable> getEmployees(LocalDate date, String name, String function){
		return employees;
	}
	public ArrayList<Employee2>getEmployee2(LocalDate date, String name, String function){
		return employee2s;
	}
}