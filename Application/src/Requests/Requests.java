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

	public String getInfo(String searchType, String keyword, Countrycolection countryC) {
		if(searchType.equals("country")){
			//String countryMedals = getMedalsPerCountry(keyword, countryC);
			String countryMedals = "PORTUGAL";
			return countryMedals;
		}
		else if(searchType.equals("sport")){
			String sportMedals = getMedalsPerSport(keyword, countryC);
			return sportMedals;
		}
		else if(searchType.equals("athlete")){
			String athleteMedals = getMedalsPerAthlete(keyword, countryC);
			return athleteMedals;
		}
		else{
			return null;
		}
		
	}
	public static String getMedalsPerCountry(String country, Countrycolection countryC){
		//Receive the object of the Countrycollection
		//Get the medals for the specific country
		
		return null;
	}
	
	public static String getMedalsPerSport(String sport, Countrycolection countryC){
		//Receive the object of the Countrycollection
		//Get the medals for the specific Sport
		
		return null;
	
	}
	
	public static String getMedalsPerAthlete(String athlete, Countrycolection countryC){
		//Receive the object of the Countrycollection
		//Get the medals for the specific athlete
		
		return null;
	}
}
