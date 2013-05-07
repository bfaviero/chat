package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import main.Connection;

/**
 * Chat server runner.
 */
public class Server {
	
	private ServerSocket server;
    private HashMap<String, Connection> userMap; // Maps usernames to users.
    private HashMap<String, Channel> roomMap; // Maps room names to rooms
    
    /**
     * Instantiate a server on the specified port.
     * @param port The port to use for our server
     */
    public Server(int port) {
    	try{
    		server = new ServerSocket(port);
    		userMap = new HashMap<String, Connection>();
    		roomMap = new HashMap<String, Channel>();
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
	    		String username = new String("Guest_" + String.valueOf(userMap.size() + 1));
	    		
	    		Connection user = new Connection(username, socket, this);
	    		Thread t = new Thread(user);
	    		t.start();
	    		
	    		userMap.put(username, user);
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
	    	Connection user = userMap.get(oldUsername);
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
