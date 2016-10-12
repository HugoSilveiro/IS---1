package MedalRequester;

import java.io.IOException;
import java.util.Scanner;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.JMSRuntimeException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
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



public class Requester implements MessageListener{
	private static ConnectionFactory cf;
	private static Destination d;
	public Requester() throws NamingException{
		//Opening connection with NORMAL QUEUE
		this.cf = InitialContext.doLookup("jms/RemoteConnectionFactory");
		this.d = InitialContext.doLookup("jms/queue/PlayQueue");
	}
	
	public void sendRequest(String request) throws JMSException, IOException {
		System.out.println("Request: "+request);
		String msg = null;
		try (JMSContext jcontext = cf.createContext("teste1", "teste1");) {
			JMSProducer mp = jcontext.createProducer();
			System.out.println("Creating temporary QUEUE");
			//Temporary QUEUE
			Destination tempQueue = jcontext.createTemporaryQueue();
			JMSConsumer replyConsumer = jcontext.createConsumer(tempQueue);
			
			replyConsumer.setMessageListener((MessageListener) this);
			
			TextMessage requestMessage = jcontext.createTextMessage();
			requestMessage.setText(request);
			
			requestMessage.setJMSReplyTo(tempQueue);
			System.out.println("mp.send");
			mp.send(d, requestMessage);
			System.in.read();
		} catch (JMSRuntimeException re) {
			re.printStackTrace();
		} 
		
	}

	@Override
	public void onMessage(Message textMsg) {
		//Check if keeper replied
		String message = null;
        System.out.println("KEEPER REPLIED!\n");
        try{
            if(textMsg instanceof TextMessage){
                TextMessage tmsg = (TextMessage) textMsg;
                message = tmsg.getText();
                System.out.println(message);
            }
        }catch(JMSException e){
            e.printStackTrace();
        }
		
	}
}
