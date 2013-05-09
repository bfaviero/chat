package main;

import java.util.Calendar;

import main.Connection.Command;

public class Message implements java.io.Serializable{
	private static final long serialVersionUID = -5296132268968878736L;
	private Command command;	
	private String channelName;
	private Calendar calendar;
	private String messageText;
	
	public Message(Command command, String channelName, Calendar date, String messageText){
		this.channelName = channelName;
		this.command = command;
		this.calendar = date;
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
	
}