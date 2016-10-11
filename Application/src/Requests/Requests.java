package Requests;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ConversionClasses.Countrycolection;
import ConversionClasses.Countrycolection.Country;
import ConversionClasses.Countrycolection.Country.Medalcolection.Medal;

public class Requests {
	/*public static void getMedalsPerCountry(String country){
		//Receive the object of the Countrycollection
		//Get the medals for the specific country
		List<Countrycolection.Country> list = Countrycolection.getCountry();
		for (Country p : list) {
			if (country.equals(p.getNation())){
				System.out.println("Country " + p.getNation());
				if(p.getTotal() > 0){
					System.out.println(p.getGolds() + p.getSilvers() + p.getBronzes());
				}
			}		
		}
	}
	
	public static void getMedalsPerSport(String sport){
		//Get the medals for the specific sport
		List<Country> list = q.getResultList();
		for (Country p : list) {
			for(Medal m: p.getMedalcolection().getMedal())
			if (sport.equals(p.getNation())){
				System.out.println("Country " + p.getNation());
				if(p.getTotal() > 0){
					System.out.println(p.getGolds() + p.getSilvers() + p.getBronzes());
				}
			}		
		}
	}
	
	public static void getMedalsPerAthlete(String athlete){
		//Get the medals for the specific athlete
		List<Country> list = q.getResultList();
		for (Country p : list) {
			p.getMedalcolection().getMedal().
			if (athlete.equals(p.getNation())){
				System.out.println("Country " + p.getNation());
				if(p.getTotal() > 0){
					System.out.println(p.getGolds() + p.getSilvers() + p.getBronzes());
				}
			}		
		}
	}*/
}
