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
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.xml.sax.SAXException;

import Unmarshall.Unmarshall;

public class Receiver {
	private ConnectionFactory cf;
	private Destination d;

	public Receiver() throws NamingException {
		this.cf = InitialContext.doLookup("jms/RemoteConnectionFactory");
		this.d = InitialContext.doLookup("jms/topic/PC");
	}

	private String receive() throws SAXException, IOException {
		String msg = null;
		try (JMSContext jcontex = cf.createContext("teste", "teste");) {
			JMSConsumer mc = jcontex.createConsumer(d);
			msg = mc.receiveBody(String.class);
			sendToFile(msg);
			Validation validation = new Validation();
			validation.Validation("example.xsd", "yolo_after.xml");
		} catch (JMSRuntimeException re) {
			re.printStackTrace();
		}
		return msg;
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

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws NamingException, SAXException, IOException {
		Receiver r = new Receiver();

		String msg = r.receive();
		//System.out.println("Message: " + msg);
	}

}