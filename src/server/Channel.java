package server;

import java.util.*;

import main.Connection;
import main.Packet;
import main.User;


import client.Client;

public class Channel{
	
    private String title;
    private List<User> users = new ArrayList<User>();
    private List<Packet> messages = new ArrayList<Packet>();

    public Channel(String title, User owner)
    {
        this.title = title;
        this.users.add(owner);
    }
    public void addMessage(Packet m, User user)
    {
        this.messages.add(m);
        System.out.println("trying to send");
        for(User u : this.users){
        	if(u != user){
                System.out.println("Sent message to "+u.nickname);
        		u.connection.sendMessage(m);
        	}
        }
    }
    public void addUser(User user)
    {
        //if !this.users.has(user) { users.add(user) }
    }
    public void removeUser(User user)
    {
        if(this.hasUser(user))
        	this.users.remove(user);
    }

    // Rep invariant: number of users in the current room > 0;
    public boolean getRepInvariant(){
    	return this.users.size() > 0;
    }
    
    public boolean hasUser(User user){
        return this.users.contains(user);
    }

    public int getUserCount(){
    	return this.users.size();
    }
}
