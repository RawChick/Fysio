package businesslogic;

import domain.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDate;


/**
 * Created by Barrie on 31-10-2015.
 */
public class AppointmentManager {
    private ObservableList<Appointment> data;
    private EmployeeManager employeeManager;
    private PatientManager patientManager;

    public AppointmentManager() {
        employeeManager = new EmployeeManager();
        patientManager = new PatientManager();

        data = FXCollections.observableArrayList(
                new Appointment(1, LocalDate.now(), employeeManager.searchEmployeeWithNumber("1"), patientManager.searchWithBSN(2))
        );


    }

    public ObservableList<Appointment> getData() {
        return data;
    }

    public boolean addApointment(Appointment appointment) {
        Appointment tempAppointment = searchWithAppointmentNumber(appointment.getAppointmentNumber());
        boolean tempBool = false;

        if (tempAppointment == null) {
            data.add(appointment);
            tempBool = true;
        }

        return tempBool;
    }

    public boolean deleteAppointment(Appointment appointment) {
        Appointment tempAppointment = searchWithAppointmentNumber(appointment.getAppointmentNumber());
        boolean tempBool = false;

        if (tempAppointment != null) {
            data.remove(appointment);
            tempBool = true;
        }

        return tempBool;
    }

    public Appointment searchWithAppointmentNumber(int appointmentNr) {
        Appointment tempAppointment = null;

        for (Appointment a : data) {
            if (a.getAppointmentNumber() == appointmentNr) {
                tempAppointment = a;
            }
        }

        return tempAppointment;
    }

    public ObservableList<Appointment> searchWithWorkDate(LocalDate workDate) {
        ObservableList<Appointment> tempAppointments = null;
        for (Appointment a : data) {
            if (a.getAppointmentDate().equals(workDate)) {
                tempAppointments.add(a);
            }
        }
        return tempAppointments;
    }

}
