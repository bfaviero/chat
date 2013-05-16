package main;

import java.util.Calendar;

import main.Connection.Command;

/**
 * Represents a general instance of a message object, sent between ServerConnection 
 * and ClientConnection.  
 *
 */
public class Packet implements java.io.Serializable{
	private static final long serialVersionUID = -5296132268968878736L;
	//Tells what type of processing action the Connection must do.  
	//In addition, Packets with Command MESSAGE are stored in Channels.  
	private Command command;
	//The name of the Channel the Packet may be stored in, if one exists (else is "").  
	private String channelName;
	//The date this Packet was sent.
	private Calendar calendar;
	//The body text of this Packet.  
	private String messageText;
	//The sender of this Packet.  If sent by the server-side, this field is "".  
	private String author;
	
	//Dummy constructor used in Server Testing.  
	public Packet()
	{
	    
	}
	
	public Packet(Command command, String channelName, Calendar date, String messageText, String author){
		this.channelName = channelName;
		this.command = command;
		this.calendar = date;
		this.messageText = messageText;
		this.author = author;
	}


	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	
	public Calendar getDate(){
		return this.calendar;
	}
	
	public void setDate(Calendar newDate){
		this.calendar = Calendar.getInstance();
	}
	
	public String getMessageText(){
		return this.messageText;
	}
	
	public void setMessageText(String newMessage){
		this.messageText = newMessage;
	}


    public String getAuthor() {
        return author;
    }


    public void setAuthor(String author) {
        this.author = author;
    }
	
}