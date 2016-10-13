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

	public static String getInfo(String searchType, String keyword, Countrycolection countryC) {
		if(countryC==null){
			return "Nada a apresentar por não ter dados";
		}
		if(searchType.equals("country")){
			String countryMedals = getMedalsPerCountry(keyword, countryC);
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
			return "Nada a apresentar";
		}
		
	}
	public static String getMedalsPerCountry(String country, Countrycolection countryC){
		String output="";
		for(Country c: countryC.getCountry()){
			if(c.getNation().equals(country)){
				for(Medal m: c.getMedalcolection().getMedal()){
					output=output+m.getMedal()+"    "+m.getSport()+"   "+m.getCategorie()+"    "+m.getWinner()+"\n";
				}
			}
		}
		
		return output;
	}
	
	public static String getMedalsPerSport(String sport, Countrycolection countryC){
		//Receive the object of the Countrycollection
		//Get the medals for the specific Sport
		
		String output="";
		for(Country c: countryC.getCountry()){
				for(Medal m: c.getMedalcolection().getMedal()){

					if(m.getSport().equals(sport)){
					output=output+m.getMedal()+"    "+m.getSport()+"   "+m.getCategorie()+"    "+m.getWinner()+"\n";
					}
				}
			
		}
		
		return output;
		
	
	}
	
	public static String getMedalsPerAthlete(String athlete, Countrycolection countryC){
		String output="";
		for(Country c: countryC.getCountry()){
				for(Medal m: c.getMedalcolection().getMedal()){

					if(m.getWinner().equals(athlete)){
						output=output+m.getMedal()+"    "+m.getSport()+"   "+m.getCategorie()+"    "+m.getWinner()+"\n";
					}
				}
			
		}
		
		return output;
		
	
	}
}
