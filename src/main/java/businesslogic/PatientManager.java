package businesslogic;

import domain.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class PatientManager {
    //region Attributes and properties
    private ObservableList<Patient> data;
    //endregion

    //region Methods
    public PatientManager() {
        data = FXCollections.observableArrayList();
        addPatient(new Patient(2, "Mark van Turnhout",  "Zwartvenseweg 17", "Tilburg", LocalDate.of(1992, 8, 5), "5044 PA", "0614740368", "mlajturn@avans.nl"));
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