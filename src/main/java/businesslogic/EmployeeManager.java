package businesslogic;

import com.sun.xml.internal.ws.api.pipe.FiberContextSwitchInterceptor;
import domain.Employee;
import domain.Employees;
import domain.Workday;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;

import static javax.xml.bind.JAXBContext.newInstance;

/**
 * Created by Barrie on 22-10-2015.
 */
public class EmployeeManager {
    //region Attributes and properties
    private final ObservableList<Employee> data;
    Employees employees = new Employees();
    File file = new File("C:\\Users\\rvroe\\workspace\\fysio-2015-10-26\\fysio\\src\\main\\java\\datastorage\\xml\\employee.xml");

    //endregion

    //region Methods
    public EmployeeManager() {
        data = FXCollections.observableArrayList(
                (new Employee
                        ("1", "Renee", "Fysio", "123", "Breda", "Nederland", "46", "Op de hoek", "6-11-1992", "1212 CC", "061234765" ,"renee@avans.nl")),
                new Employee
                        ("2", "Mark", "Fysio", "1234", "Tilburg", "Nederland", "17", "Zwartvenseweg", "5-8-1992", "5044 PA", "061234567" ,"mlajturn@avans.nl")
        );

        Workday tempWorkdayRenee = new Workday(LocalDate.now(), LocalTime.now(), (LocalTime.now()));
        Workday tempWorkdayMark = new Workday(LocalDate.now(), LocalTime.now(), (LocalTime.now()));

        Boolean testWorkdayRenee = addWorkday("1", tempWorkdayRenee);
        Boolean testWorkdayMark = addWorkday("2", tempWorkdayMark);
       Save(data);
    }

    public boolean Save(ObservableList<Employee> observableList)
    {
        boolean tempBool = true;


        try {
            for (Employee e : observableList) {
                employees.add(e);
            }


            JAXBContext jaxbContext = newInstance(Employees.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(employees, file);



        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return tempBool;
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
            Save(data);
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
