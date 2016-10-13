package WebCrawler;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.xnio.IoUtils;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import ConversionClasses.ObjectFactory;
import ConversionClasses.Countrycolection;
import ConversionClasses.Countrycolection.Country;
import ConversionClasses.Countrycolection.Country.Medalcolection;
import ConversionClasses.Countrycolection.Country.Medalcolection.Medal;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.JMSRuntimeException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class WebCrawlerJava {
	public static void main(String[] args) throws NamingException, JMSException, IOException, InterruptedException {
		
			org.jsoup.nodes.Document doc;
			Elements newRef;
			
			boolean printing=false;
			int timeOut=5000;

			
			ObjectFactory objects= new ObjectFactory();
			
			Countrycolection colection;
			
			while(true){
				colection=  objects.createCountrycolection();
				System.out.println("Working");
			try {

				doc = Jsoup.connect("https://www.rio2016.com/en/medal-count-country").get();
				
				Elements content = doc.body().getElementsByClass("table-medal-countries__link-table");
				Elements expanded = doc.body().getElementsByClass("table-expand");
                int i=0;
				for(Element e: content){
					Country country = objects.createCountrycolectionCountry();
					//Get the overall stats of each nation
					if(printing){
					System.out.print(e.select("td.col-1").text() + "  ");
					System.out.print(e.select("td.col-2").text() + "  ");
					System.out.print(e.select("td.col-3").text() + "  ");
					System.out.print(e.select("td.col-4").text() + "  ");
					System.out.print(e.select("td.col-5").text() + "  ");
					System.out.print(e.select("td.col-6").text() + "  ");
					
					System.out.println(e.select("td.col-7").text());
					}
					country.setShortname(e.select("td.col-2").text());
					country.setNation(e.select("td.col-3").text());
					country.setGolds(Integer.parseInt(e.select("td.col-4").text()));
					country.setSilvers(Integer.parseInt(e.select("td.col-5").text()));
					country.setBronzes(Integer.parseInt(e.select("td.col-6").text()));
					country.setTotal(Integer.parseInt(e.select("td.col-7").text()));

					Elements winnersDetails = expanded.get(i).getElementsByTag("tr");
					i++;
					
					int golds=Integer.parseInt(e.select("td.col-4").text());
					int silvers=Integer.parseInt(e.select("td.col-5").text());
					
					Medalcolection medalcolection=objects.createCountrycolectionCountryMedalcolection();

					country.setMedalcolection(medalcolection);
					
					
					int max=Integer.parseInt((e.select("td.col-7").text()));
					for(int j=1;j<=max;j++){
						Medal medal = objects.createCountrycolectionCountryMedalcolectionMedal();
						
						Elements medalDetails = winnersDetails.get(j).getElementsByTag("td");
						if(printing){
						System.out.print((j)+": ");
						System.out.print(medalDetails.select("td.col-1").text() + "   ");
						System.out.print(medalDetails.select("td.col-2").text() + "   ");
						System.out.print(medalDetails.select("td.col-3").text() + "   ");
						System.out.println(medalDetails.select("td.col-4").text() + "   ");
						}
						if(golds>0){
							medal.setMedal("Gold");
							golds--;
						}
						else if(silvers>0){
							medal.setMedal("Silver");
							silvers--;
						}
						else{
							medal.setMedal("Bronze");
							
						}
						medal.setSport(medalDetails.select("td.col-2").text());
						medal.setCategorie(medalDetails.select("td.col-3").text());
						medal.setWinner(medalDetails.select("td.col-4").text());

						country.getMedalcolection().getMedal().add(medal);	
					}
					colection.getCountry().add(country);
				}

				timeOut=10000;
			} catch (Exception e) {
				timeOut=timeOut*2;
				System.out.println("Can't connect to the website, waiting "+timeOut/1000+" seconds");
				//e.printStackTrace();
			}
			
			
			jaxbObjectToXML(colection);
			

			Sender sender;
			String xmlDoc = Files.toString( new File("yolo.xml"), Charsets.UTF_8 );
			try {
				sender=new Sender();
				sender.send();
			} catch (NamingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Waiting "+timeOut/1000+" seconds");
			Thread.sleep(timeOut);
			}
	}
	

    private static void jaxbObjectToXML(Countrycolection emp) {

        try {
            JAXBContext context = JAXBContext.newInstance(Countrycolection.class);
            Marshaller m = context.createMarshaller();
            //for pretty-print XML in JAXB

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            // Write to System.out for debugging
            //m.marshal(emp, System.out);

            // Write to File
            m.marshal(emp, new File("yolo.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    

	
    

}