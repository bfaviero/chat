package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/**
 * Chat server runner.
 */
public class Server {
	
	private ServerSocket server;
    private ArrayList<Conversation> channels;
    private Map<String, Socket> usernameConnectionMap; // Maps usernames to Sockets
    
    public String getNickname(Socket s){
    	try {
	    	BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			
			String line = in.readLine();
			String nickname;
			
			if(line.matches("NICK (\\w+)")){
				nickname = line.split(" ")[1];
				return nickname;
			}
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	// Failed to get a nickname, generating a random one
		return new String("Guest_" + String.valueOf(usernameConnectionMap.size() + 1));
    }
    
    public Server() {
    	try{
    		server = new ServerSocket(1234);
    		
    		while(true){
	    		Socket socket = server.accept();
	    		String nickname = getNickname(socket);
	    		
	    		usernameConnectionMap.put(nickname, socket);
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
        Server server = new Server();
        
        
        // YOUR CODE HERE
        // It is not required (or recommended) to implement the server in
        // this runner class.
    }
}
