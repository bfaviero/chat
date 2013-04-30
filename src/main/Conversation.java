package main;

import java.util.*;

public class Conversation {
    
    //The unique ID for this Conversation.
    private int id;
    //The List of all current participants in the Conversation.  
    //Invariant: There must be at least one Client in a Conversation's users List.  
    private List<Client> users = new ArrayList<Client>();
    private List<Message> messages = new ArrayList<Message>();
    
    public Conversation(int id, Client starter)
    {
        this.id = id;
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
    public void addMessage(Message m)
    {
        //check if message's author is in this conversation; if so, add to this.messages
    }
    public void getMessagesFrom(Date d)
    {
        //get all Messages starting from this Date
    }
    public int getID()
    {
        //Return this Conversation's ID
        return this.id;
    }

}
