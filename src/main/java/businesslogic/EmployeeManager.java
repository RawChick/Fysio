package businesslogic;

import com.sun.xml.internal.ws.api.pipe.FiberContextSwitchInterceptor;
import domain.Employee;
import domain.Workday;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by Barrie on 22-10-2015.
 */
public class EmployeeManager {
    //region Attributes and properties
    private final ObservableList<Employee> data;
    //endregion

    //region Methods
    public EmployeeManager() {
        data = FXCollections.observableArrayList(
                new Employee("1", "Noureddine Azzagari", "Baas", "22775566", "Goes", "Tak van Poortvlietstraat 19", "11-10-1995", "4463 TA", "0618559116", "nazzagar@avans.nl"),
                new Employee("2", "Mark van Turnhout", "Baas", "11664455", "Tilburg", "Zwartvenseweg 17", "05-08-1992", "5044 PA", "0614740368", "mlajturn@avans.nl")
        );

        Workday tempWorkday = new Workday(LocalDate.now(), LocalTime.now(), (LocalTime.now()));
        Boolean testWorkday = addWorkday("1", tempWorkday);

    }

    /**
     *
     * @return List with all the data
     */
    public ObservableList<Employee> getData() {
        return data;
    }

    /**
     *
     * @return List with all the names of employees
     */
    public ObservableList<String> getEmployeeNames() {
        ObservableList<String> names = FXCollections.observableArrayList();

        for (Employee e : data) {
            names.add(e.getEmployeeName());
        }

        return names;
    }

    /**
     *
     * @param employee An employee object
     * @return True if employee has been added
     */
    public boolean addEmployee(Employee employee) {
        Employee oldEmployee = searchEmployeeWithNumber(employee.getEmployeeNr());
        boolean returnBoolean = false;

        if (oldEmployee == null) {
            data.add(employee);
            returnBoolean = true;
        }
        return returnBoolean;
    }

    /**
     *
     * @param employee An employee object
     * @return True if employee has been removed
     */
    public boolean deleteEmployee(Employee employee) {
        Employee returnEmployee = searchEmployeeWithNumber(employee.getEmployeeNr());
        boolean returnBoolean = false;

        if (returnEmployee != null) {
            data.remove(employee);
            returnBoolean = true;
        }
        return returnBoolean;
    }

    /**
     *
     * @param employeeNr Number belonging to an employee
     * @return The employee the employeeNr belongs to
     */
    public Employee searchEmployeeWithNumber(String employeeNr) {
        Employee returnEmployee = null;
        for (Employee e : data) {
            if (e.getEmployeeNr().equals(employeeNr)) {
                returnEmployee = e;
            }
        }
        return returnEmployee;
    }

    /**
     *
     * @param employeeName Name belonging to an employee
     * @return The employee the employeeName belongs to
     */
    public Employee searchEmployeeWithName(String employeeName) {
        Employee returnEmployee = null;
        for (Employee e : data) {
            if (e.getEmployeeName().equals(employeeName)) {
                returnEmployee = e;
            }
        }
        return returnEmployee;
    }

    /**
     *
     * @param employeeNr Number belonging to an employee
     * @param workday An workday object
     * @return True if the workday has been added to the employee
     */
    public boolean addWorkday(String employeeNr, Workday workday) {
        boolean tempBool = false;
        Employee tempEmployee = searchEmployeeWithNumber(employeeNr);

        if (tempEmployee != null) {
            tempEmployee.addWorkday(workday);
            tempBool = true;
        }

        return tempBool;
    }

    /**
     *
     * @param employeeNr Number belonging to an employee
     * @param workday An workday object
     * @return True if the workday has been removed from the employee
     */
    public boolean removeWorkday(String employeeNr, Workday workday) {
        boolean tempBool = false;
        Employee tempEmployee = searchEmployeeWithNumber(employeeNr);

        if (tempEmployee != null) {
            tempEmployee.removeWorkday(workday);
            tempBool = true;
        }

        return tempBool;
    }

    /**
     *
     * @param employeeNr
     * @param workDate
     * @return
     */
    public Workday searchWorkdayWithDate(String employeeNr, LocalDate workDate) {
        Workday tempWorkday = null;
        Employee tempEmployee = searchEmployeeWithNumber(employeeNr);

        if (tempEmployee != null) {
            tempWorkday = tempEmployee.searchWorkdayByDate(workDate);
        }

        return tempWorkday;
    }
    //endregion
}
