package businesslogic;

import domain.Appointment1;
import domain.Appointment2;
import domain.Planning;
import domain.Treatment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TreatmentManager {

	/**
	 * 
	 * @param treatment
	 */
	public boolean addTreatment(Treatment treatment) {
		return true;
	}

	/**
	 * 
	 * @param treatment
	 */
	public String changeTreatment(Treatment treatment) {
		return "";
	}

	/**
	 * 
	 * @param treatment
	 */
	public boolean removeTreatment(Treatment treatment) {
		return true;
	}

	public void showStatusTreatment() {

	}

	/**
	 * 
	 * @param planning
	 */
	public boolean addAppointment(Planning planning) {
		boolean result = true;
		return result;
	}


	public String changeAppointment(String time, String name, String treatment, String therapist) {
		return "";
	}


	public boolean removeAppointment(String name, String time, String treatment, String therapist) {
		return true;
	}
    public ArrayList<Appointment2> getAppointmet2ByDate(LocalDate newValue){
		ArrayList<Appointment2> appointment2s = new ArrayList<>();
		appointment2s.add(new Appointment2("a",1,true,true));
		appointment2s.add(new Appointment2("b",2,true,true));
		appointment2s.add(new Appointment2("c",3,true,true));
		appointment2s.add(new Appointment2("d",4,true,true));
		appointment2s.add(new Appointment2("e",5,true,true));
		appointment2s.add(new Appointment2("f",6,true,true));
		return appointment2s;
    }
    public ArrayList<Appointment1> getAppointmet1ByDate(LocalDate newValue){
		ArrayList<Appointment1> appointment1s = new ArrayList<>();
		appointment1s.add(new Appointment1(LocalTime.now(), "jaap","hand","dfkjgh"));
		appointment1s.add(new Appointment1(LocalTime.now(), "hans","voet","pqwoe"));
		appointment1s.add(new Appointment1(LocalTime.now(), "kees","arm","turiwr"));
		appointment1s.add(new Appointment1(LocalTime.now(), "joop","been","cxnzv"));
		return appointment1s;
    }

	public ArrayList<Planning> showAppointment() {
		return null;
	}

	public ArrayList<Treatment> printListTreatment() {
		return null;
	}

	public ArrayList<Planning> showPlanning() {
		return null;
	}

}