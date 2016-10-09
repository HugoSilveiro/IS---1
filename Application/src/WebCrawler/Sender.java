package WebCrawler;


import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.JMSRuntimeException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.google.common.io.Files;

public class Sender {
	private ConnectionFactory cf;
	private Destination d;

	public Sender() throws NamingException {
		this.cf = InitialContext.doLookup("jms/RemoteConnectionFactory");
		this.d = InitialContext.doLookup("jms/topic/PC");
	}

	public void send(String text) throws IOException {
		try (JMSContext jcontext = cf.createContext("teste", "teste");) {
			JMSProducer mp = jcontext.createProducer();
			String xmlContent = readFile("yolo.xml");
			
			mp.send(d, xmlContent);
		} catch (JMSRuntimeException re) {
			re.printStackTrace();
		}
	}
	
	private String readFile(String pathname) throws IOException {

	    File file = new File(pathname);
	    StringBuilder fileContents = new StringBuilder((int)file.length());
	    Scanner scanner = new Scanner(file);
	    String lineSeparator = System.getProperty("line.separator");

	    try {
	        while(scanner.hasNextLine()) {
	            fileContents.append(scanner.nextLine() + lineSeparator);
	        }
	        return fileContents.toString();
	    } finally {
	        scanner.close();
	    }
	}
	
	

}

