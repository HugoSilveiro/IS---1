package MedalRequester;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import javax.jms.*;
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
	
	public void sendRequest(String request) throws JMSException{
		System.out.println("Request: "+request);
		String msg = null;
		try (JMSContext jcontext = cf.createContext("teste", "teste");) {
			JMSProducer mp = jcontext.createProducer();
			System.out.println("Creating temporary QUEUE");
			
			TextMessage requestMessage = jcontext.createTextMessage();
			requestMessage.setText(request);
			
			//Temporary QUEUE
			Destination tempQueue = jcontext.createTemporaryQueue();
			
			//replyConsumer.setMessageListener(this);
			
			
			String correlationId = this.createRandomString();
			//System.out.println(tempQueue);
			requestMessage.setJMSCorrelationID(correlationId);
			requestMessage.setJMSReplyTo(tempQueue);
			
            //System.out.println("d: "+d);
			mp.send(d, requestMessage);
			//System.out.println("mp.send: "+requestMessage);
			jcontext.stop();


			JMSConsumer mc = jcontext.createConsumer(tempQueue);
			//System.out.println("put listener");
			Message message = mc.receive();
			System.out.println("Received Information:");
			if(message.getStringProperty("answer").equals("") ){
				System.out.println("No matches for your search.... Try again!");
			}else{
				System.out.println(message.getStringProperty("answer"));
			}
			
			

		} catch (JMSRuntimeException re) {
			re.printStackTrace();
		} 
		
	}
	
	
	public void receiveAnswer(Destination queue) throws JMSException{
		
		System.out.println("Receiving Answer");
		try (JMSContext jcontext = cf.createContext("teste", "teste");) {


			
			
		} catch (JMSRuntimeException re) {
			re.printStackTrace();
		}
		
	}
	
	
	
	private String createRandomString() {
        Random random = new Random(System.currentTimeMillis());
        long randomLong = random.nextLong();
        return Long.toHexString(randomLong);
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
