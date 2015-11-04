package businesslogic;

import domain.Patient;
import domain.Patients;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.stream.Collectors;

import static javax.xml.bind.JAXBContext.newInstance;

public class PatientManager {
    //region Attributes and properties
    private final ObservableList<Patient> data;
    //endregion

    //region Methods
    public PatientManager() {
        data = FXCollections.observableArrayList();
        addPatient(new Patient(2, "Mark", "van Turnhout",  "Tilburg", "Nederland", "Zwartvenseweg", "17", LocalDate.of(1992, 8, 5), "5044 PA", "0614740368", "mlajturn@avans.nl"));
        Patient patient = new Patient(2, "Mark", "van Turnhout",  "Tilburg", "Nederland", "Zwartvenseweg", "17", LocalDate.of(1992, 8, 5), "5044 PA", "0614740368", "mlajturn@avans.nl");

        Patients patients = new Patients();
        URL url = getClass().getResource("/datastorage/xml/patient.xml");
        File file = new File(url.getPath());



        try {

            patients.add(patient);


            JAXBContext jaxbContext = newInstance(Patients.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(patients, file);



        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * @return List with all the names of patients
     */
    public ObservableList<String> getPatientNames() {
        ObservableList<String> names = FXCollections.observableArrayList();

        names.addAll(data.stream().map(Patient::getPatientFullName).collect(Collectors.toList()));

        return names;
    }

    /**
     *
     * @param patientName Name belonging to an patient
     * @return The patient the patientName belongs to
     */
    public Patient searchPatientWithName(String patientName) {
        Patient returnPatient = null;
        for (Patient e : data) {
            if (e.getPatientFullName().equals(patientName)) {
                returnPatient = e;
            }
        }
        return returnPatient;
    }

    /**
     *
     * @param patient A patient object
     * @return True if the patient has been added
     */
    public boolean addPatient(Patient patient) {
        Patient tempPatient = searchWithBSN(patient.getPatientBSN());
        boolean tempBool = false;

        if (tempPatient == null) {
            data.add(patient);
            tempBool = true;
        }

        return tempBool;
    }

    /**
     *
     * @param patient A patient object
     * @return Returns true if the patient has been removed
     */
    public boolean removePatient(Patient patient) {
        Patient tempPatient = searchWithBSN(patient.getPatientBSN());
        boolean tempBool = false;

        if (tempPatient == null) {
            data.add(patient);
            tempBool = true;
        }

        return tempBool;
    }

    /**
     *
     * @param BSN The BSN belonging to the patient
     * @return The patient the BSN belongs to
     */
    public Patient searchWithBSN(int BSN) {
        Patient tempPatient = null;

        for (Patient p : data) {
            if (p.getPatientBSN() == BSN) {
                tempPatient = p;
            }
        }

        return tempPatient;
    }
    //endregion
}