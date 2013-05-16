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

    /**
     * Create a new instance of Channel.
     * @param title - the name of the Channel. Cannot contain whitespace.
     * This is enforced by the GUI/Client.  
     * @param owner - the User who created this Channel. 
     */
    public Channel(String title, User owner)
    {
        this.title = title;
        this.users.add(owner);
    }
    
    /**
     * Adds a message to this Channel's list of messages then 
     * sends alert to all Users in this Channel.
     * @param Packet m, a message.  
     * @param user, the Packet's author.  
     */
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
    
    /**
     * Adds the User to this Channel if it was not already a member.  
     * If the User was already a member, do not add it again.
     * @param user - the User to be added to this Channel.
     */
    public void addUser(User user)
    {
        if(!this.users.contains(user))
        	users.add(user);
    }
    
    /**
     * Removes the User from this Channel's list of users,
     * if it was in the list at all.  
     * @param user - the User to be removed.  
     */
    public void removeUser(User user)
    {
        if(this.hasUser(user))      
        	this.users.remove(user);
    }

    // Rep invariant: number of users in the current room > 0;
    public boolean getRepInvariant(){
    	return this.users.size() > 0;
    }
    
    /**
     * Check if a User is in this Channel.  
     * @param user - a User
     * @return true if user is in this Channel's list of users; false otherwise.
     */
    public boolean hasUser(User user){
        return this.users.contains(user);
    }

    /**
     * Get the number of Users in this Channel.
     * @return the number of Users in this Channel's list of users.  
     */
    public int getUserCount(){
    	return this.users.size();
    }
    
    /**
     * Get a String representation of all the messages in this Channel,
     * each message has form "AUTHOR:MESSAGETEXT", and are delineated by "\n" character.  
     * @return String containing all messages of this Channel
     */
    public String getMessages() {
        //TODO: Implement Date/User filter?
        StringBuilder channelMessages = new StringBuilder("");
        for(Packet m : this.messages){
            channelMessages.append(m.getAuthor() + m.getMessageText() + "\n");
        }
        return channelMessages.toString().trim();
    }
    
    /**
     * Get a String representation of all Users that are members of this Channel. 
     * @return String of the nicknames of the Users of this Channel, separated by a space.
     */
    public String getUserNames(){
        StringBuilder channelUsers = new StringBuilder("");
        for(User u : this.users){
            channelUsers.append(u.nickname + " ");
        }
        return channelUsers.toString().trim();
    }
}
