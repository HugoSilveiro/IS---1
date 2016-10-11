package MedalKeeper;

import java.io.IOException;

import javax.naming.NamingException;

import org.xml.sax.SAXException;

import ConversionClasses.Countrycolection;

public class ReceiverHandler {
	private static Countrycolection countryC;
	public static void main(String[] args) throws NamingException, SAXException, IOException {
		//Creete 2 Threads
		//Syncronized Object
		
		//Receiver r = new Receiver(countryC);
		//r.start();
		
		ReceiverQueue rQ = new ReceiverQueue(countryC);
		rQ.start();
		//System.out.println("Message: " + msg);
	}
}
