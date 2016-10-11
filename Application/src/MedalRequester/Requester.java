package MedalRequester;

import java.io.IOException;
import java.util.Scanner;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.JMSRuntimeException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.xml.sax.SAXException;

import MedalKeeper.Receiver;
import MedalKeeper.Validation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



public class Requester {
	private static ConnectionFactory cf;
	private static Destination d;
	public Requester() throws NamingException{
		this.cf = InitialContext.doLookup("jms/RemoteConnectionFactory");
		this.d = InitialContext.doLookup("jms/queue/PlayQueue");
	}
	
	public static void main(String[] args)  throws SAXException, IOException {
		//Send the request to the queue
		String msg = null;
		try (JMSContext jcontext = cf.createContext("teste", "teste");) {
			JMSProducer mp = jcontext.createProducer();
			String userOption = getUserOption();
			mp.send(d, userOption);
		} catch (JMSRuntimeException re) {
			re.printStackTrace();
		}
		
	}
	public static String getUserOption(){
		while(true){
	    	display();
	    	Scanner sc = new Scanner(System.in);
		    int userOption = sc.nextInt();
		    	
	    	switch(userOption){
		    	case 1: //Get data from the country selected
		    			String country = sc.nextLine();  
		    		    //String result = getCountryMedals(country);
		    			return country;
			case 2: //Get data from the selected sport"
		    			String sport = sc.nextLine();
		    			//getSportMedals(sport);
		    			return sport;
			case 3: //Get data from the selected sport"
	    				String athlete = sc.nextLine();
	    				//getSportMedals(athlete);
	    				return athlete;
			case 4: System.exit(0);
		    		break;
		    	default: System.out.println("Invalid Option");
		    		break;
		    }
		}
	}
	
	public static void display(){
		System.out.println("Rio 2016");
		System.out.println("1. Country");
		System.out.println("2. Sport");
		System.out.println("3. Athlete");
		System.out.println("4. Exit");
		
	}
	
	public static void createQueue(){
		
	}
	
	public static void insertQueue(){
		
	}
}
