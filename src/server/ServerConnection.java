package server;

import java.net.Socket;
import java.util.concurrent.BlockingQueue;

import servervisitors.JoinServerVisitor;

import main.Connection;
import main.Message;
import main.User;



public class ServerConnection extends Connection {
	// Commented out fields inherited from Connection:
	
	// public String username;
	// public Socket socket;
	private Server server;
	private User user;
	
	// Queue of messages to send across the socket
	private BlockingQueue<Message> messageQueue;
	
	public ServerConnection(int userId, Socket sock, Server server) {
		super(userId, sock);
		this.server = server;
	}

	public void processMessage(Message message){
		System.out.println("Message received and processing in progress");
		System.out.println(message.getDate() + ": " + message.getUserId() + " " + message.getChannelName() + " " + message.getMessageText());
		
		switch(message.getCommand()){
		case JOIN:
			// JoinServerVisitor join = new JoinServerVisitor(message.getMessageText())
			// join.visitServer(this.server);
			break;
		case LIST_CHANNELS:
			break;
		case LIST_USERS:
			break;
		case LOGIN:
			break;
		case LOGOUT:
			break;
		case MESSAGE:
			break;
		case QUIT:
			break;
		default:
			break;
			
		}
	}
	
	public void closeSockets(){
		try{
			// Stop handling IO
			this.readerThread.interrupt();
			this.writerThread.interrupt();
			
			// Process disconnect and clean up sockets
			this.socket.close();
		}
		catch(Exception e){
			System.err.println("Error attempting to process disconnect for user");
			e.printStackTrace();
		}
	}
}
