package businesslogic;

import domain.ManageEmployee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by Barrie on 22-10-2015.
 */
public class ManageEmployeeManager {
    private final ObservableList<ManageEmployee> data =
            FXCollections.observableArrayList(
                    new ManageEmployee("1", "Noureddine Azzagari", "Baas", "22775566", "Goes", "Tak van Poortvlietstraat 19", "11-10-1995", "4463 TA", "0618559116", "nazzagar@avans.nl"),
                    new ManageEmployee("2", "Mark van Turnhout", "Baas", "11664455", "Tilburg", "Zwartvenseweg 17", "05-08-1992", "5044 PA", "0614740368", "mlajturn@avans.nl")
            );

    public ObservableList<ManageEmployee> getData() {
        return data;
    }

    public boolean addEmployee(ManageEmployee manageEmployee) {
        ManageEmployee oldManageEmployee = searchWithNumber(manageEmployee.getEmployeeNr());
        boolean returnBoolean = false;

        if (oldManageEmployee == null) {
            data.add(manageEmployee);
            returnBoolean = true;
        }
        return returnBoolean;
    }

    public boolean deleteEmployee(ManageEmployee manageEmployee) {
        ManageEmployee returnManageEmployee = searchWithNumber(manageEmployee.getEmployeeNr());
        boolean returnBoolean = false;

        if (returnManageEmployee != null) {
            data.remove(manageEmployee);
            returnBoolean = true;
        }
        return returnBoolean;
    }

    public ManageEmployee searchWithNumber(String employeeNr) {
        ManageEmployee returnManageEmployee = null;
        for (ManageEmployee e : data) {
            if (e.getEmployeeNr().equals(employeeNr)){
                returnManageEmployee = e;
            }
        }
        return returnManageEmployee;
    }

}
