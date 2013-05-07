package server;

import java.util.*;

import client.Client;

public class Message {

    private final Client author;
    private final Channel channel;
    private final Date date;
    private final String text;
    
    /** Initialize a message with the following fields.
     * All fields should be initialized upon creation of a Message.  
     * 
     * @param author the writer/sender of this message
     * @param conver the Conversation which this Message is a part of
     * @param date the time at which this message was sent; used in filtering.
     * @param text the contents of this message
     */
    public Message(Client author, Channel channel, Date date, String text)
    {
        this.author = author;
        this.channel = channel;
        this.date = date;
        this.text = text;
    }
    
    /**
     * Returns text contents of this Message; used to display Message in chatroom view.  
     */
    public String toString()
    {
        return this.text;
    }
}
