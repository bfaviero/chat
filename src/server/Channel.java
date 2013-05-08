package server;

import java.util.*;

import main.Connection;
import main.Message;


import client.Client;

public class Channel{
	
    private String title;
    private List<Connection> users = new ArrayList<Connection>();
    private List<Message> messages = new ArrayList<Message>();

    public Channel(String title, Connection owner)
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

}
