package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.HashMap;

import main.Connection.Command;
import main.Packet;
import main.User;

/**
 * Chat server runner.
 */

public class Server{
	private int nextId;
	private ServerSocket server;
    private HashMap<Integer, User> userMap; // Maps usernames to users.
    private HashMap<String, Channel> channelMap; // Maps room names to channels
    
    /**
     * Instantiate a server on the specified port.
     * @param port The port to use for our server
     */
    public Server(int port) {
    	try{
    		server = new ServerSocket(port);
    		userMap = new HashMap<Integer, User>();
    		channelMap = new HashMap<String, Channel>();
    		nextId = 0;
    	}
    	catch(Exception e){
    		e.printStackTrace();   		
    	}
    }
    
    /**
     *  Listen for connections on the port specified in the Server constructor
     */
    public void listen(){
    	try{    		
    		while(true){
	    		Socket socket = server.accept();
	    		String nickname = new String("Guest_" + String.valueOf(nextId));
	    		
	    		// Create a server connection and connect it to the appropriate user.
	    		ServerConnection userConnection = new ServerConnection(nextId, socket, this);
	    		User user = new User(nextId, nickname, userConnection);
	    		userConnection.setUser(user);
	    		
	    		synchronized(userMap){
	    			userMap.put(nextId, user);
	    		}
	    		// Send a response that connection was successful;	    		
	    		nextId++;
    		}
    	}
    	catch(IOException e){
    		e.printStackTrace();
    	}
    }
    
    public void addUserToChannel(int userId, String channelName){
    	synchronized(userMap){
    		User user = userMap.get(userId);
    	}
    	// Need to create a new channel if this one doesn't exist already
	    synchronized(channelMap){
    		if(!channelMap.keySet().contains(channelName)){
	    		Channel newChannel = new Channel(channelName, userMap.get(userId));
	    		channelMap.put(channelName, newChannel);
	    	}
	    	else{
	    		channelMap.get(channelName).addUser(userMap.get(userId));
	    	}
	    }
    }
    
    // Returns a string formatted as "user1 user2 user3"
    public String getUserList(){
    	StringBuilder userList = new StringBuilder("");
    	for(User u : userMap.values()){
    		userList.append(u.nickname+" ");
    	}
    	return userList.toString();
    }
    
    public String getChannelList(){
    	StringBuilder channelList = new StringBuilder("");
    	for(String channelName : channelMap.keySet()){
    		channelList.append(channelName+" ");
    	}
    	return channelList.toString();
    }
    
    
    public void sendMessageToChannel(User u, Packet message){
    	if(channelMap.containsKey(message.getChannelName())){
    		Channel channel = channelMap.get(message.getChannelName());
    		channel.addMessage(message, u);
    	}
    }
    
    /**
     * Start a chat server.
     */
    public static void main(String[] args) {
        Server server = new Server(1234);
        server.listen();
        
        // YOUR CODE HERE
        // It is not required (or recommended) to implement the server in
        // this runner class.
    }

}
