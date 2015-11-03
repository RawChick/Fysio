package businesslogic;

import domain.Appointment;
import domain.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sun.tools.jar.resources.jar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;

import static javax.xml.bind.JAXBContext.newInstance;


/**
 * Created by Barrie on 31-10-2015.
 */
public class AppointmentManager {
    //region Attributes and propperties
    private ObservableList<Appointment> data;
    private EmployeeManager employeeManager;
    private PatientManager patientManager;
    //endregion

    Appointments appointments = new Appointments();


    File file = new File("C:\\Users\\rvroe\\workspace\\fysio-2015-10-26\\fysio\\src\\main\\java\\datastorage\\xml\\appointment.xml");
    //region Methods
    public AppointmentManager() {
        employeeManager = new EmployeeManager();
        patientManager = new PatientManager();

        data = FXCollections.observableArrayList(
                new Appointment(1, LocalDate.now(), LocalTime.now(), LocalTime.now(), employeeManager.searchEmployeeWithNumber("2"), patientManager.searchWithBSN(2))
        );

        Save(data);
    }

    public boolean Save(ObservableList<Appointment> observableList)
    {
        boolean tempBool = true;


        try {
            for (Appointment a : observableList) {
                appointments.add(a);
            }


            JAXBContext jaxbContext = newInstance(Appointments.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(appointments, file);



        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return tempBool;
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
            Save(data);
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
