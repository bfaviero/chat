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
    public void addMessage(Packet m)
    {
        this.messages.add(m);
    }
    public void addUser(User user)
    {
        //if !this.users.has(user) { users.add(user) }
    }
    public void removeUser(User user)
    {
        //if this.users.has(user) { users.remove(user) }
        //if this.users.size() == 0 { //delete Conversation }
    }
    public void hasUser(User user)
    {
        //if this.users.has(user)
    }

}
