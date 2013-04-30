package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 * Chat server runner.
 */
public class Server {
	
	private ServerSocket server;
    private HashMap<String, User> userMap; // Maps usernames to users.
    private HashMap<String, Room> roomMap; // Maps room names to rooms
    
    public String getNickname(Socket s){
    	if(!s.isConnected())
    		throw new RuntimeException("Socket not connected");
    	
    	try {
	    	BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
	    	
			out.println("USER?");
			String line = in.readLine();
			String nickname;
			
			if(line.matches("USER (\\w+)")){
				nickname = line.split(" ")[1];
				return nickname;
			}
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	// Failed to get a nickname, generating a random one
		return new String("Guest_" + String.valueOf(userMap.size() + 1));
    }
    
    /**
     * Instantiate a server on the specified port.
     * @param port The port to use for our server
     */
    public Server(int port) {
    	try{
    		server = new ServerSocket(port);
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
	    		String nickname = getNickname(socket);
	    		
	    		userMap.put(nickname, new User(nickname, socket));
    		}
    	}
    	catch(IOException e){
    		e.printStackTrace();
    	}
    }
    /**
     * Start a chat server.
     */
    public static void main(String[] args) {
        Server server = new Server(1234);
        
        
        // YOUR CODE HERE
        // It is not required (or recommended) to implement the server in
        // this runner class.
    }
}
