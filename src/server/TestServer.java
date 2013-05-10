package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Date;
import java.util.Calendar;

import main.Connection.Command;
import main.Packet;

public class TestServer implements Runnable{

	public void run(){
		try {
			Socket s = new Socket("localhost", 1234);
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Packet m = new Packet(Command.LOGIN, "", Calendar.getInstance(), "strelok", "");
			Packet n = new Packet(Command.JOIN, "chess", Calendar.getInstance(), "", "");
			Packet l = new Packet(Command.LIST_CHANNELS, "", Calendar.getInstance(), "", "");
			Packet o = new Packet(Command.LIST_USERS, "", Calendar.getInstance(), "", "");
			
			Packet[] messages = {m, n, l, o};
			try {
				for(int i = 0; i<4; i++){
					oos.writeObject(messages[i]);
					Thread.sleep(500);
				}
				for(int i = 0; i<4; i++){
					Packet response = (Packet) ois.readObject();
					System.out.println(Thread.currentThread().getId() + "  " + response.getCommand().name() + " " + response.getChannelName() + " " + response.getMessageText());
				}
			} catch (IOException e) {
				// TODLOGIN_SUCCESSO Auto-generated catch block
				e.printStackTrace();
			}
			while(true){}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		Thread t = new Thread(new TestServer());
		Thread q = new Thread(new TestServer());
		Thread s = new Thread(new TestServer());
		
		
		t.start();
		q.start();
		s.start();
	}
	
}
