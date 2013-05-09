package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Date;
import java.util.Calendar;

import main.Connection.Command;
import main.Message;

public class TestServer {

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException, ClassNotFoundException{
		Socket s = new Socket("localhost", 1234);
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		Message m = new Message(Command.LIST_USERS, "testChannel", Calendar.getInstance(), "Hello There");
		try {
			oos.writeObject(m);
			Thread.sleep(500);
			Message message = (Message) ois.readObject();
			System.out.println(message.getDate() + ": " + message.getCommand().name() + " " + message.getChannelName() + " " + message.getMessageText());
		} catch (IOException e) {
			// TODLOGIN_SUCCESSO Auto-generated catch block
			e.printStackTrace();
		}
		while(true){}
	}
	
}
