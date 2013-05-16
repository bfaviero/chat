package main;

import java.util.Calendar;

import main.Connection.Command;

public class Packet implements java.io.Serializable{
	private static final long serialVersionUID = -5296132268968878736L;
	private Command command;	
	private String channelName;
	private Calendar calendar;
	private String messageText;
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