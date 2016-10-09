package Creator;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSRuntimeException;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class HTMLCreator {
 private ConnectionFactory cf;
 private Destination d;

 public HTMLCreator() throws NamingException {
  this.cf = InitialContext.doLookup("jms/RemoteConnectionFactory");
  this.d = InitialContext.doLookup("jms/queue/PlayQueue");
 }
 
 private String receive() {
  String msg = null;
  try (JMSContext jcontex = cf.createContext("hugo", "hugo1995*");) {
   JMSConsumer mc = jcontex.createConsumer(d);
   msg = mc.receiveBody(String.class);
  } catch (JMSRuntimeException re) {
   re.printStackTrace();
  }
  return msg;
 }

 public static void main(String[] args) throws NamingException {
	 HTMLCreator r = new HTMLCreator();

  String msg = r.receive();
  System.out.println("Message: " + msg);
 }


}
