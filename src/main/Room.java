package main;

import java.util.*;

public class Room implements Runnable{
	
    private String title;
    private List<UserConnection> users = new ArrayList<UserConnection>();
    private List<Message> messages = new ArrayList<Message>();

    public Room(String title, UserConnection owner)
    {
        this.title = title;
        this.users.add(owner);
    }
    public void addMessage(Message m)
    {
        this.messages.add(m);
    }
    public void addUser(Client user)
    {
        //if !this.users.has(user) { users.add(user) }
    }
    public void removeUser(Client user)
    {
        //if this.users.has(user) { users.remove(user) }
        //if this.users.size() == 0 { //delete Conversation }
    }
    public void hasUser(Client user)
    {
        //if this.users.has(user)
    }
	@Override
	public void run() {
		
	}

}
