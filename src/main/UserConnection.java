package main;

import java.net.Socket;

/**
 * Start a new connection for the given user and 
 *
 */

public class UserConnection implements Runnable{
		public String username;
		public Socket socket;
		
		public UserConnection(String user, Socket s){
			this.username = user;
			this.socket = s;
		}

		@Override
		public void run() {
			while(true){
				
			}
		}
}
