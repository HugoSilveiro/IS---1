package Creator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringReader;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSRuntimeException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;


public class HTMLCreator {
 private ConnectionFactory cf;
 private Destination d;

 public HTMLCreator() throws NamingException {
  this.cf = InitialContext.doLookup("jms/RemoteConnectionFactory");
  this.d = InitialContext.doLookup("jms/topic/PC");
 }
 
 private String receive() {
  String msg = null;
  try (JMSContext jcontex = cf.createContext("teste", "teste");) {
   JMSConsumer mc = jcontex.createConsumer(d);
   msg = mc.receiveBody(String.class);
  } catch (JMSRuntimeException re) {
   re.printStackTrace();
  }
  return msg;
 }

 public static void main(String[] args) throws NamingException {
  HTMLCreator r = new HTMLCreator();

  while(true){
  String msg = r.receive();
  System.out.println("Received");
  try {
      TransformerFactory tFactory=TransformerFactory.newInstance();

      Source xslDoc=new StreamSource("HTMLTemplate.xsl");


      OutputStream htmlFile=new FileOutputStream("Country Medal Specefication.html");
      Transformer trasform=tFactory.newTransformer(xslDoc);
      trasform.transform((new StreamSource(new StringReader(msg))), new StreamResult(htmlFile));
      System.out.println("Deploied the HTML file!");
  } 
  catch (FileNotFoundException e) 
  {
      e.printStackTrace();
  }
  catch (TransformerConfigurationException e) 
  {
      e.printStackTrace();
  }
  catch (TransformerFactoryConfigurationError e) 
  {
      e.printStackTrace();
  }
  catch (TransformerException e) 
  {
      e.printStackTrace();
  }
}
 }
  
 }



