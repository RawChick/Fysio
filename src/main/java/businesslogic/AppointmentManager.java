package businesslogic;

import domain.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDate;
import java.time.LocalTime;


/**
 * Created by Barrie on 31-10-2015.
 */
public class AppointmentManager {
    //region Attributes and propperties
    private ObservableList<Appointment> data;
    private EmployeeManager employeeManager;
    private PatientManager patientManager;
    //endregion

    //region Methods
    public AppointmentManager() {
        employeeManager = new EmployeeManager();
        patientManager = new PatientManager();

        data = FXCollections.observableArrayList(
                new Appointment(1, LocalDate.now(), LocalTime.now(), LocalTime.now(), employeeManager.searchEmployeeWithNumber("2"), patientManager.searchWithBSN(2)),
                new Appointment(1, LocalDate.of(2015, 11, 1), LocalTime.now(), LocalTime.now(), employeeManager.searchEmployeeWithNumber("2"), patientManager.searchWithBSN(2))
        );
    }

    /**
     *
     * @return List with all the data
     */
    public ObservableList<Appointment> getData() {
        return data;
    }

    /**
     *
     * @param appointment An appointment object
     * @return True if the appointment has been added
     */
    public boolean addApointment(Appointment appointment) {
        Appointment tempAppointment = searchWithAppointmentNumber(appointment.getAppointmentNumber());
        boolean tempBool = false;

        if (tempAppointment == null) {
            data.add(appointment);
            tempBool = true;
        }

        return tempBool;
    }

    /**
     *
     * @param appointment An appointment object
     * @return True if the appointment has been removed
     */
    public boolean deleteAppointment(Appointment appointment) {
        Appointment tempAppointment = searchWithAppointmentNumber(appointment.getAppointmentNumber());
        boolean tempBool = false;

        if (tempAppointment != null) {
            data.remove(appointment);
            tempBool = true;
        }

        return tempBool;
    }

    /**
     *
     * @param appointmentNr Number belonging to an appointment
     * @return The appointment the appointmentNr belongs to
     */
    public Appointment searchWithAppointmentNumber(int appointmentNr) {
        Appointment tempAppointment = null;

        for (Appointment a : data) {
            if (a.getAppointmentNumber() == appointmentNr) {
                tempAppointment = a;
            }
        }

        return tempAppointment;
    }

    /**
     *
     * @param workDate Date in dd/MM/yyyy
     * @return A list with all the appointments that day
     */
    public ObservableList<Appointment> searchWithWorkDate(LocalDate workDate) {
        ObservableList<Appointment> tempAppointments  = FXCollections.observableArrayList();
        for (Appointment a : data) {
            if (a.getAppointmentDate().equals(workDate)) {
                tempAppointments.add(a);
            }
        }
        return tempAppointments;
    }

    /**
     *
     * @param BSN BSN belonging to patient
     * @return A list with all the appointments for the patient belonging to the BSN
     */
    public ObservableList<Appointment> searchWithPatientBSN(int BSN) {
        ObservableList<Appointment> tempAppointments  = FXCollections.observableArrayList();
        for (Appointment a : data) {
            if (a.getAppointmentPatient().getPatientBSN() == BSN) {
                tempAppointments.add(a);
            }
        }
        return tempAppointments;
    }

    /**
     *
     * @param workDate Date in dd/MM/yyyy
     * @param employeeName Name belonging to an employee
     * @return A list with all the appointments that day with that employee
     */
    public ObservableList<Appointment> searchWithWorkDateAndEmployeeName(LocalDate workDate, String employeeName)
    {
        ObservableList<Appointment> tempAppointments = FXCollections.observableArrayList();
        for (Appointment a : data) {
            if (a.getAppointmentDate().equals(workDate) && a.getAppointmentFysio().getEmployeeName().equals(employeeName)) {
                tempAppointments.add(a);
            }
        }
        return tempAppointments;
    }
    //endregion
}
