package server;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

import main.Connection;

public class ServerConnection extends Connection implements Runnable {
	// Commented out fields inherited from Connection:
	
	// public String username;
	// public Socket socket;
	// private BufferedReader in;
	// private PrintWriter out;
	private Server server;
	private Thread writer;
	private Thread reader;
	
	public class ConnectionReader implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public class ConnectionWriter implements Runnable {

		@Override
		public void run() {
			
		}
		
		
	}
	
	public ServerConnection(String user, Socket sock, Server server) {
		super(user, sock);
		this.server = server;
		reader = new Thread(ConnectionReader);
		reader.start();
		writer = new Thread(ConnectionWriter);
		writer.start();
	}

	public void processCommand(String command){
		if(command.matches("USER (\\w+)")){
			String newUsername = command.split(" ")[1];
			System.out.println(newUsername);
			String response = this.server.changeUsername(this.nickname, newUsername);
			
			if(response.contains("successful")){
				this.nickname = newUsername;
			}
			
			out.write(response + "\n");
			out.flush();
		}
		else if(command.matches("QUIT")){
			closeSockets();
			return;
		}		
	}
	
	public void closeSockets(){
		try{
			this.server.processUserDisconnect(this.nickname);
			in.close();
			out.close();
			this.socket.close();
		}
		catch(Exception e){
			System.err.println("Error attempting to process disconnect for user" + this.nickname);
			e.printStackTrace();
		}
	}
	
	public void run(){
		try{
			for (String command =in.readLine(); command!=null; command=in.readLine()) {
				
			}
		catch(Exception e){
			System.err.println("Error reading commands from socket.");
		}
			e.printStackTrace();
		}
	}
	
	
}
