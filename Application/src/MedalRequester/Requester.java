package MedalRequester;

import java.io.IOException;
import java.util.Scanner;

import javax.naming.NamingException;

import org.xml.sax.SAXException;

import MedalKeeper.Receiver;

public class Requester {
	public static void main(String[] args) {
		
		
	    while(true){
	    	display();
	    	Scanner sc = new Scanner(System.in);
		    int userOption = sc.nextInt();
		    
	    	switch(userOption){
		    	case 1: //Get data from the country selected
		    			String country = sc.nextLine();  
		    		    //getCountryMedals(country);
		    		break;
		    	case 2: //Get data from the selected sport"
		    			String sport = sc.nextLine();
		    			//getSportMedals(sport);
		    		break;
		    	case 3: //Get data from the selected sport"
	    				String athelete = sc.nextLine();
	    				//getSportMedals(athelete);
		    		break;
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
}
