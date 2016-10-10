package Unmarshall;


import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import ConversionClasses.Countrycolection;

public class Unmarshall {


    public static void toObject(String xmlFile) {
    	System.out.println(xmlFile);
        try {

            File file = new File(xmlFile);
            JAXBContext jaxbContext = JAXBContext.newInstance(Countrycolection.class);


            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            Countrycolection customer = (Countrycolection) jaxbUnmarshaller.unmarshal(file);
            System.out.println(customer);

            //If needeed, return to xml -> Marshall
            /*
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(customer, System.out);
            */
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}