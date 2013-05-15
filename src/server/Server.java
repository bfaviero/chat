package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import main.Connection.Command;
import main.Packet;
import main.User;

/**
 * Chat server runner.
 */

public class Server{
	private boolean debug; // For testing only
	private int nextId;
	private ServerSocket server;
    private HashMap<Integer, User> userMap; // Maps usernames to users.
    private HashMap<String, Channel> channelMap; // Maps room names to channels
    private Thread channelMonitor;
    
    public class ChannelMonitor implements Runnable{
		@Override
		public void run() {
			while(!Thread.currentThread().isInterrupted()){
				for(Iterator<Map.Entry<String, Channel>> it = channelMap.entrySet().iterator(); it.hasNext();){
					Map.Entry<String, Channel> entry = it.next();
					synchronized(channelMap){
						if(!entry.getValue().getRepInvariant())
							channelMap.remove(entry.getKey());
					}
				}
			}
		}
    }
    
    /**
     * Instantiate a server on the specified port.
     * @param port The port to use for our server
     */
    public Server(int port, boolean debug) {
    	try{
    		this.debug = debug;
    		server = new ServerSocket(port);
    		userMap = new HashMap<Integer, User>();
    		channelMap = new HashMap<String, Channel>();
    		nextId = 0;
    		channelMonitor = new Thread(new ChannelMonitor());
    		channelMonitor.start();
    	}
    	catch(Exception e){
    		e.printStackTrace();   		
    	}
    }
   
    
    /**
     *  Listen for connections on the port specified in the Server constructor.
     *  Create new ServerConnections and corresponding Users when a connection is made.  
     */
    public void listen(){
    	try{    		
    		while(true){
	    		Socket socket = server.accept();
	    		makeUserFromSocket(socket);
    		}
    	}
    	catch(IOException e){
    		e.printStackTrace();
    	}
    }
    
    /**
     * Creates an instance of User with unique to represent the client on the socket
     * and adds it to the Server's userMap.  The User is given a default name.  
     * @param socket, the Socket the user is communicating from
     */
    public void makeUserFromSocket(Socket socket){
    	String nickname = new String("Guest_" + String.valueOf(nextId));
		
		// Create a server connection and connect it to the appropriate user.
		ServerConnection userConnection = new ServerConnection(nextId, socket, this);
		User user = new User(nextId, nickname, userConnection);
		userConnection.setUser(user);
		
		synchronized(userMap){
			userMap.put(nextId, user);
		}
		// Send a response that connection was successful;
		
		//I'd argue to put nextID++ in the synchronized block.  
		nextId++;
    }
    
    /**
     * Create a Channel as per request from a User, with a given channel Name.  
     * TODO: Handle behavior when channel Name is already taken.  
     * @param channelName - the name the User wants to call this new Channel.
     * @param firstUserId - the User requesting to creating this channel.
     * @return the newly created Channel.  
     */
    public Channel createChannel(String channelName, int firstUserId){
    	Channel newChannel;
    	synchronized(channelMap){
	    	newChannel = new Channel(channelName, userMap.get(firstUserId));
	    	channelMap.put(channelName, newChannel);
    	}
    	return newChannel;
    }
    
    /**
     * Have a User join a Channel with a specified name, if such a Channel exists.  
     * @param userId - the userID of the User making the join request.
     * @param channelName - the name of the Channel the User wants to join.
     */
    public void addUserToChannel(int userId, String channelName){
    	// Need to create a new channel if this one doesn't exist already
    	if(!channelMap.containsKey(channelName))
    		createChannel(channelName, userId);
    	channelMap.get(channelName).addUser(userMap.get(userId));
    		
    }
    
    //TODO: Synchronize this with addUserFromChannel - user vs. userID.  
    /**
     * Removes a User from a Channel with a specified name, if such a Channel exists.  
     * @param user - the User that will be removed.
     * @param channelName - the name of the Channel the User will be removed from.
     */
    public void removeUserFromChannel(User user, String channelName){
    	if(channelMap.containsKey(channelName)){
    		Channel channel = channelMap.get(channelName);
    		channel.removeUser(user);
    	}
    }
    
    /**
     * Returns a string representation of all active Users on the Server.
     * @return a string of active User nicknames separated by " ".  
     */
    // Returns a string formatted as "user1 user2 user3"
    public String getUserList(){
    	System.out.println(userMap.size());
    	StringBuilder userList = new StringBuilder("");
    	for(User u : userMap.values()){
    		userList.append(u.nickname+" ");
    	}
    	return userList.toString();
    }
    
    /**
     * Returns a string representation of all active Channels on the Server.
     * @return a string of active Channel names separated by " ".  
     */
    public String getChannelList(){
    	StringBuilder channelList = new StringBuilder("");
    	for(String channelName : channelMap.keySet()){
    		channelList.append(channelName+" ");
    	}
    	return channelList.toString().trim();
    }
    
    //TODO: Make sure User parameter matches (user vs. int userID)
    /**
     * Sends a Message to a requested Channel from a User, if the Channel exists.  
     * @param u - the User sending the message.
     * @param message - a Packet containing info as to which Channel the 
     * message is being sent to and the contents of the message.
     */
    public void sendMessageToChannel(User u, Packet message){
    	System.out.println(u.nickname);
    	System.out.println(this.channelMap.keySet().size());
    	System.out.println(message.getChannelName() + " " + message.getMessageText());
        System.out.println("Send message to channel");
    	if(channelMap.containsKey(message.getChannelName())){
    	    System.out.println("channelMap contains channelName "+message.getChannelName());
    		Channel channel = channelMap.get(message.getChannelName());
    		channel.addMessage(message, u);
    	}
    }
    
    /**
     * Removes a User from the Server upon its client disconnecting 
     * and erases its presence from the Server's Channels.
     * @param userId - the ID of the User leaving the Server.
     */
    public void notifyServerOfUserDisconnect(int userId){
    	User u = userMap.get(userId);
    	for(String channel : channelMap.keySet()){
    		Channel c = channelMap.get(channel);
    		if(c.hasUser(u)){
    			c.removeUser(u);
    		}
    	}
    }
    
    /**
     * Gets an instance of Channel.
     * @param channelName
     * @return requested instance of Channel; 
     * returns null if requested Channel does not exist.
     */
    public Channel getChannel(String channelName){
    	return channelMap.get(channelName);
    }
    
    /**
     * Closes the server.
     * @throws IOException
     */
    public void terminate() throws IOException{
    	this.server.close();
    	this.channelMonitor.interrupt();
    }
    
    /**
     * Start a chat server.
     */
    public static void main(String[] args) {
        Server server = new Server(1234, false);
        server.listen();
        
        // YOUR CODE HERE
        // It is not required (or recommended) to implement the server in
        // this runner class.
    }

}
