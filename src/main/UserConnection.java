package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

/**
 * Start a new connection for the given user and 
 *
 */

public class UserConnection implements Runnable{
		private Server server;
		public String username;
		public Socket socket;
		
		public UserConnection(String user, Socket sock, Server serv){
			if(!sock.isConnected())
	    		throw new RuntimeException("Socket not connected");
			
			this.username = user;
			this.socket = sock;
			this.server = serv;
		}
		
		@Override
		public void run() {
			try{
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
				
				for (String command =in.readLine(); command!=null; command=in.readLine()) {
					if(command.matches("USER (\\w+)")){
						String newUsername = command.split(" ")[1];
						System.out.println(newUsername);
						String response = this.server.changeUsername(this.username, newUsername);
						
						if(response.contains("successful")){
							this.username = newUsername;
						}
						
						out.write(response + "\n");
						out.flush();
					}
					else if(command.matches("QUIT")){
						this.server.processUserDisconnect(this.username);
						in.close();
						out.close();
						this.socket.close();
						return;
					}
				}
			}
			catch(Exception e){
				System.err.println("Error handling input/output. User: " + this.username);
				e.printStackTrace();
			}
		}
}
