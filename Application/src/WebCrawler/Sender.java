package WebCrawler;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.JMSRuntimeException;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class Sender {
	 private ConnectionFactory cf;
	 private Destination d;
	
	 public Sender() throws NamingException {
	  this.cf = InitialContext.doLookup("jms/RemoteConnectionFactory");
	  this.d = InitialContext.doLookup("jms/topic/PC");
	 }
	
	 private void send(String text) {
	  try (JMSContext jcontext = cf.createContext("joao", "br1o+sa*");) {
	   JMSProducer mp = jcontext.createProducer();
	   mp.send(d, text);
	  } catch (JMSRuntimeException re) {
	   re.printStackTrace();
	  }
	 }
	
	 public void sendToTopic() throws NamingException {
	  Sender s = new Sender();
	  s.send("Hello Receiver!");
	 }
	}
