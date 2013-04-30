package main;

import java.net.Socket;

public class User {
		public String username;
		public Socket socket;
		
		public User(String user, Socket s){
			this.username = user;
			this.socket = s;
		}
}
