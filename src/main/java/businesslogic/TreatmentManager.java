package businesslogic;

import domain.Treatment;
import domain.Treatments;
import javafx.collections.ObservableList;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import static javax.xml.bind.JAXBContext.*;


public class TreatmentManager {

    Treatment treatment = new Treatment(1, "enkelmassage", 4, 1000, 40);
    Treatment treatment2 = new Treatment(2, "voetkraken", 2, 2000, 20);

    private final Treatments treatments = new Treatments();

    private final File file = new File("C:\\Users\\ids1\\Desktop\\Fysio\\src\\main\\java\\datastorage\\xml\\treatment.xml");

    public TreatmentManager() {

}

    public boolean Save(ObservableList<Treatment> observableList)
    {
        boolean tempBool = true;


            try {
                observableList.forEach(treatments::add);


                JAXBContext jaxbContext = newInstance(Treatments.class);
                Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

                jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

                jaxbMarshaller.marshal(treatments, file);



            } catch (JAXBException e) {
            e.printStackTrace();
        }
        return tempBool;
    }
}