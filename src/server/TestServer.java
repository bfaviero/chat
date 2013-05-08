package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Date;

import main.Connection.Command;
import main.Message;

public class TestServer {

	public static void main(String[] args) throws UnknownHostException, IOException{
		Socket s = new Socket("localhost", 1234);
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		Message m = new Message(1, Command.LIST_USERS, "testChannel", new Date(2012, 10, 04), "Hello There");
		try {
			oos.writeObject(m);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(true){}
	}
	
}
