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

public class ReceiverQueue {
	private ConnectionFactory cf;
	private Destination d;

	public ReceiverQueue() throws NamingException {
		this.cf = InitialContext.doLookup("jms/RemoteConnectionFactory");
		this.d = InitialContext.doLookup("jms/queue/PlayQueue");
	}

	String receive() throws SAXException, IOException {
		String msg = null;
		try (JMSContext jcontex = cf.createContext("teste", "teste");) {
			JMSConsumer mc = jcontex.createConsumer(d);
			msg = mc.receiveBody(String.class);
			//This message will contain the request
			
			//Send the request to Requester Class and Send to the MedalRequester via the temporary Queue
			
		} catch (JMSRuntimeException re) {
			re.printStackTrace();
		}
		return msg;
	}

}