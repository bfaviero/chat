package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;

import main.Connection;
import main.Message;



public class ServerConnection extends Connection {
	// Commented out fields inherited from Connection:
	
	// public String username;
	// public Socket socket;
	private Server server;
	private User user;
	
	// Queue of messages to send across the socket
	private BlockingQueue<Message> messageQueue;
	
	public ServerConnection(String user, Socket sock, Server server) {
		super(user, sock);
		this.server = server;
	}

	public void processMessage(Message message){
		System.out.println("Message received and processing in progress");
	}
	
	public void closeSockets(){
		try{
			// Stop handling IO
			this.readerThread.interrupt();
			this.writerThread.interrupt();
			
			// Process disconnect and clean up sockets
			this.server.processUserDisconnect(this.nickname);
			this.socket.close();
		}
		catch(Exception e){
			System.err.println("Error attempting to process disconnect for user" + this.nickname);
			e.printStackTrace();
		}
	}
}
