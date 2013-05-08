package main;

import java.util.Date;

import main.Connection.Command;

public class Message implements java.io.Serializable{
	private static final long serialVersionUID = -5296132268968878736L;
	private Command command;	
	private String channelName;
	private Date date;
	private String messageText;
	
	public Message(Command command, String channelName, Date date, String messageText){
		this.channelName = channelName;
		this.command = command;
		this.date = date;
		this.messageText = messageText;
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
	
	public Date getDate(){
		return this.date;
	}
	
	public void setDate(Date newDate){
		this.date = newDate;
	}
	
	public String getMessageText(){
		return this.messageText;
	}
	
	public void setMessageText(String newMessage){
		this.messageText = newMessage;
	}
	
}