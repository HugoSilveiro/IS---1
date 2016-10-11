package MedalKeeper;

import org.xml.sax.SAXException;

import ConversionClasses.Countrycolection;
import Unmarshall.Unmarshall;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.io.IOException;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSRuntimeException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class Validation {
	
	public Countrycolection Validation(String xsd, String xml) throws SAXException, IOException{
		Source schemaFile = new StreamSource(new File(xsd));
		Source xmlFile = new StreamSource(new File(xml));
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = schemaFactory.newSchema(schemaFile);
		Validator validator = schema.newValidator();try
		{
			validator.validate(xmlFile);
			System.out.println(xmlFile.getSystemId() + " is valid");
			Unmarshall unmarshall = new Unmarshall();
			System.out.println("Sent to Unmarshall: " +xml);
			return (unmarshall.toObject(xml));
			
		}catch(
		SAXException e)
	
		{
			System.out.println(xmlFile.getSystemId() + " is NOT valid");
			System.out.println("Reason: " + e.getLocalizedMessage());
			return null;
		}
	}
}
	
