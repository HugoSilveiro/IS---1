package MedalKeeper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSRuntimeException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.xml.sax.SAXException;

import ConversionClasses.Countrycolection;
import Unmarshall.Unmarshall;

public class Receiver extends Thread implements MessageListener{
	private ConnectionFactory cf;
	private Destination d;
	
	public Receiver() throws NamingException, SAXException, IOException{
		this.cf = InitialContext.doLookup("jms/RemoteConnectionFactory");
		this.d = InitialContext.doLookup("jms/topic/PC");
		String msg = null;
		try (JMSContext jcontex = cf.createContext("teste", "teste");) {
			JMSConsumer mc = jcontex.createConsumer(d);
			msg = mc.receiveBody(String.class);
			sendToFile(msg);
			Validation validation = new Validation();
			ReceiverHandler.countryC = validation.Validation("example.xsd", "yolo_after.xml");
			//System.out.println("total countries: " +  ReceiverHandler.countryC.getCountry().size());
		} catch (JMSRuntimeException re) {
			re.printStackTrace();
		}
	}

	private void sendToFile(String xmlContent){
		try {
			File file = new File("yolo_after.xml");

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(xmlContent);
			bw.close();

			//System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onMessage(Message arg0) {
		// TODO Auto-generated method stub
		System.out.println("onMessage");
		
	}

}