package MedalKeeper;

import java.io.IOException;

import javax.jms.JMSException;
import javax.naming.NamingException;

import org.xml.sax.SAXException;

import ConversionClasses.Countrycolection;

public class ReceiverHandler {
	private static Countrycolection countryC;
	public static void main(String[] args) throws NamingException, SAXException, IOException, JMSException {
		//Create 2 Threads
		//Syncronized Object
		/*
		while(true){
			Receiver r = new Receiver(countryC);
			r.start();
			ReceiverQueue rQ = new ReceiverQueue(countryC);
			rQ.start();
		}
		*/
		//Receiver r = new Receiver(countryC);
		//r.start();
		System.out.println("Waiting for requests...");
		ReceiverQueue rQ = new ReceiverQueue(countryC);
		rQ.start();
		
		//System.out.println("Message: " + msg);
	

	}
}
