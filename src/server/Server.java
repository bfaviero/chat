package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import main.User;

/**
 * Chat server runner.
 */
public class Server {
	private int nextId;
	private ServerSocket server;
    private HashMap<String, User> userMap; // Maps usernames to users.
    private HashMap<String, Channel> roomMap; // Maps room names to rooms
    
    /**
     * Instantiate a server on the specified port.
     * @param port The port to use for our server
     */
    public Server(int port) {
    	try{
    		server = new ServerSocket(port);
    		userMap = new HashMap<String, User>();
    		roomMap = new HashMap<String, Channel>();
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
	    		String nickname = new String("Guest_" + String.valueOf(userMap.size() + 1));
	    		
	    		// Create a server connection and connect it to the appropriate user.
	    		ServerConnection userConnection = new ServerConnection(nickname, socket, this);
	    		User user = new User(nextId, nickname, userConnection);
	    		userConnection.setUser(user);
	    		nextId++;
	    		
	    		userMap.put(nickname, user);
    		}
    	}
    	catch(IOException e){
    		e.printStackTrace();
    	}
    }
    
    public String changeUsername(String oldUsername, String newUsername){
    	synchronized(userMap){
	    	if(userMap.containsKey(newUsername)){
	    		return "Error: Username is already taken";
	    	}
	    	User user = userMap.get(oldUsername);
	    	userMap.remove(oldUsername);
	    	userMap.put(newUsername, user);
	    	return "Username change successful";
    	}
    }
    
    public void processUserDisconnect(String username){
    	synchronized(userMap){
    		if(userMap.containsKey(username)){
    			userMap.remove(username);
    		}
    		else{
    			System.err.println("Trying to remove non-existent user");
    		}
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
