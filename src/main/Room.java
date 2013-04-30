package main;

import java.util.*;

public class Room implements Runnable{
	
    //The unique ID for this Conversation.
    private int id;
    private String title;
    private List<User> users = new ArrayList<User>();
    private List<Message> messages = new ArrayList<Message>();

    public Room(int id, String title, Client starter)
    {
        this.id = id;
        this.title = title;
        this.users.add(starter);
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
    public int getID()
    {
        //Return this Conversation's ID
        return this.id;
    }

	@Override
	public void run() {
		
	}

}
