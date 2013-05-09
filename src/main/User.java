package main;


public class User {
	public int id;
	public String nickname;
	public Connection connection;
	
	public User(int id, String nickname, Connection connection){
		this.id = id;
		this.nickname = nickname;
		this.connection = connection;
	}
	
	public void setNickname(String newNickname){
		this.nickname = newNickname;
	}
}
