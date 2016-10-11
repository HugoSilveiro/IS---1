package MedalKeeper;

import java.io.IOException;

import javax.naming.NamingException;

import org.xml.sax.SAXException;

public class ReceiverHandler {
	public static void main(String[] args) throws NamingException, SAXException, IOException {
		//Creete 2 Threads
		//Syncronized Object
		Receiver r = new Receiver();

		String msg = r.receive();
		//System.out.println("Message: " + msg);
	}
}
