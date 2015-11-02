package businesslogic;

import domain.Treatment;
import domain.Treatments;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElementWrapper;

import static javax.xml.bind.JAXBContext.*;


public class TreatmentManager {


    Treatment treatment = new Treatment(200, "enkelmassage", 4, 1000, 40);
    Treatment treatment2 = new Treatment(300, "voetkraken", 2, 2000, 20);

    Treatments treatments = new Treatments();
    treatments.add(treatment);
    treatments.add(treatment2);

    File file = new File("C:\\Users\\rvroe\\workspace\\fysio-2015-10-26\\fysio\\src\\main\\java\\businesslogic\\treatment.xml");

    public TreatmentManager() {

        try {
            JAXBContext jaxbContext = newInstance(Treatments.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(treatments, file);
            jaxbMarshaller.marshal(treatments, System.out);
//            jaxbMarshaller.marshal(treatment2, file);
//            jaxbMarshaller.marshal(treatment2, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}