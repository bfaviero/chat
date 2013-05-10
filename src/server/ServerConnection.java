package server;

import java.net.Socket;
import java.util.Calendar;
import java.util.concurrent.BlockingQueue;

import main.Connection;
import main.Message;
import main.User;



public class ServerConnection extends Connection {
	// Commented out fields inherited from Connection:
	
	// public String username;
	// public Socket socket;
	private int userId;
	private Server server;
		
	public ServerConnection(int userId, Socket sock, Server server) {
		super(sock);
		this.userId = userId;
		this.server = server;
	}

	public void processMessage(Message message){
		System.out.println("Message received and processing in progress");
		System.out.println(message.getCommand().name() + ": " + message.getChannelName() + " " + message.getMessageText());
		Message response;
		
		switch(message.getCommand()){
		case JOIN:
			// This user joins appropriate channel
			this.server.addUserToChannel(this.userId, message.getChannelName());
			response = new Message(Command.REPLY_SUCCESS, "", Calendar.getInstance(), "");
			sendMessage(response);
			break;
		case LIST_CHANNELS:
			String channelList = this.server.getChannelList();
			response = new Message(Command.REPLY_LIST_CHANNELS, "", Calendar.getInstance(), channelList);
			sendMessage(response);
			break;
		case LIST_USERS:
			String userList = this.server.getUserList();
			response = new Message(Command.REPLY_LIST_USERS, "", Calendar.getInstance(), userList);			
			sendMessage(response);
			break;
		case LOGIN:
			String nickname = message.getMessageText();
			this.user.setNickname(nickname);
			response = new Message(Command.REPLY_SUCCESS, "", Calendar.getInstance(), "");
			sendMessage(response);
			break;
		case LOGOUT:
			// Terminate everything
			break;
		case MESSAGE:
			
			break;
		case QUIT:
			
			break;
		default:
			System.out.println("Fell through");
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
