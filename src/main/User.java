package main;


public class User {
	public int id;
	//The nickname of a User cannot contain whitespace.  
	//This is enforced by the GUI/Client.  
	public String nickname;
	public Connection connection;
	
	//This dummy User constructor is solely used for Server Tests.  
	public User(String s)
	{
	    this.nickname = s;
	}
	public User(int id, String nickname, Connection connection){
		this.id = id;
		this.nickname = nickname;
		this.connection = connection;
	}
	
	public void setNickname(String newNickname){
		this.nickname = newNickname;
	}
}
