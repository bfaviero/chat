package server;

import java.util.*;

import main.Connection;
import main.Message;
import main.User;


import client.Client;

public class Channel{
	
    private String title;
    private List<User> users = new ArrayList<User>();
    private List<Message> messages = new ArrayList<Message>();

    public Channel(String title, User owner)
    {
        this.title = title;
        this.users.add(owner);
    }
    public void addMessage(Message m)
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
