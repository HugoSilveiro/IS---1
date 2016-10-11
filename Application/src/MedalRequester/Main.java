package MedalRequester;

import java.util.Scanner;

import javax.jms.JMSException;
import javax.naming.NamingException;

public class Main {
	public static void main(String[] args){
		while(true){
	    	display();
	    	Scanner sc = new Scanner(System.in);
		    int userOption = sc.nextInt();
		    	
	    	switch(userOption){
		    	case 1: //Get data from the country selected
		    			String country = sc.nextLine();  
		    		    //String result = getCountryMedals(country);
		    			
			case 2: //Get data from the selected sport"
		    			String sport = sc.nextLine();
		    			//getSportMedals(sport);
		    			
			case 3: //Get data from the selected sport"
	    				String athlete = sc.nextLine();
	    				//getSportMedals(athlete);
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
	
	public static void getCountryMedals() throws JMSException{
		Scanner sc = new Scanner(System.in);
		String country = sc.nextLine();
		Requester r = null;
		
		try {
			r = new Requester();
			String request = "country/"+country;
			r.sendRequest(request);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void getSportMedals() throws JMSException{
		Scanner sc = new Scanner(System.in);
		String sport = sc.nextLine();
		Requester r = null;
		
		try {
			r = new Requester();
			String request = "sport/"+sport;
			r.sendRequest(request);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void getAthleteMedals() throws JMSException{
		Scanner sc = new Scanner(System.in);
		String athlete = sc.nextLine();
		Requester r = null;
		
		try {
			r = new Requester();
			String request = "athlete/"+athlete;
			r.sendRequest(request);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
