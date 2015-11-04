package datastorage;

import domain.*;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@XmlRootElement(name = "datastore")
public class datastore {



    private List<Patient> patientList;


    public datastore() {

        patientList = new ArrayList<>();
        List<Treatment> treatmentList = new ArrayList<>();
    }


    @XmlElementWrapper(name = "patients")
    @XmlElementRef
    public List<Patient> getPatients() {
        return patientList;
    }

    public void setPatients(List<Patient> patients) {
        this.patientList = patients;
    }


    public boolean addPatient(Patient patient) {
        return patientList.add(patient);
    }


    public void replacePatient(Patient patient, int index) {
        patientList.set(index, patient);
    }


    public boolean removePatient(int index) {
        patientList.remove(index);
        return true;
    }

}
