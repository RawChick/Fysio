package businesslogic;

import domain.Treatments;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.net.URL;


/**
 * Created by rvroe on 2-11-2015.
 */
public class unmarshall {



    public unmarshall()

    {

        try {
            URL url = getClass().getResource("/datastorage/xml/treatment.xml");
            File file = new File(url.getPath());

            JAXBContext jaxbContext = JAXBContext.newInstance(Treatments.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Treatments treatmentlist = (Treatments)jaxbUnmarshaller.unmarshal(file);
            System.out.println( treatmentlist );

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
