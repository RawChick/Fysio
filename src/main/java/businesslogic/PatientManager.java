package businesslogic;

import domain.Appointment;
import domain.Patient;
import domain.CustomerTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalTime;

public class PatientManager {
    private ObservableList<Patient> data;

    public PatientManager() {
        data = FXCollections.observableArrayList();
        addPatient(new Patient(2, "Mark van Turnhout",  "Zwartvenseweg 17", "Tilburg", LocalDate.of(1992, 8, 5), "5044 PA", "0614740368", "mlajturn@avans.nl"));
    }

    public boolean addPatient(Patient patient) {
        Patient tempPatient = searchWithBSN(patient.getBsn());
        boolean tempBool = false;

        if (tempPatient == null) {
            data.add(patient);
            tempBool = true;
        }

        return tempBool;
    }

    public boolean removePatient(Patient patient) {
        Patient tempPatient = searchWithBSN(patient.getBsn());
        boolean tempBool = false;

        if (tempPatient == null) {
            data.add(patient);
            tempBool = true;
        }

        return tempBool;
    }

    public Patient searchWithBSN(int BSN) {
        Patient tempPatient = null;

        for (Patient p : data) {
            if (p.getBsn() == BSN) {
                tempPatient = p;
            }
        }

        return tempPatient;
    }

}