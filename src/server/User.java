package server;

import main.Connection;


public class User {
	private int id;
	private String nickname;
	private Connection connection;
	
	public User(int id, String nickname, Connection connection){
		this.id = id;
		this.nickname = nickname;
		this.connection = connection;
	}
}
