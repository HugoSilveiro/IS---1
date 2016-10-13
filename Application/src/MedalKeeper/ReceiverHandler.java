package MedalKeeper;

import java.io.IOException;

import javax.jms.JMSException;
import javax.naming.NamingException;

import org.xml.sax.SAXException;

import ConversionClasses.Countrycolection;

public class ReceiverHandler {
	public static Countrycolection countryC=null;
	public static void main(String[] args) throws NamingException, SAXException, IOException, JMSException, InterruptedException {
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
		Receiver r = new Receiver();
		r.start();
		System.out.println("Waiting for requests...");
		
		while(countryC==null){
			Thread.sleep(1000);
		}
		System.out.println("Initiating receiver");
		ReceiverQueue rQ = new ReceiverQueue();
		rQ.start();
		
		//System.out.println("Message: " + msg);
		
		

	}
	
}
