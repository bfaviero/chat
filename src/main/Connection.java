package main;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;


import main.User;


public class Connection {
		protected Socket socket;
		protected User user;
		protected Thread readerThread;
		protected Thread writerThread;
		protected boolean userDisconnected = false;
		
		// Messages to be sent across the socket
		protected BlockingQueue<Packet> messageQueue;
		
		public enum Command{ // Quit exits a channel, logout exits connection
			LIST_USERS, LIST_CHANNELS, LOGOUT, LOGIN, MESSAGE, JOIN, QUIT,
			REPLY_SUCCESS, REPLY_FAILURE, REPLY_ERROR, REPLY_LIST_CHANNELS, REPLY_LIST_USERS 
		};
		
		public class ConnectionReader implements Runnable {
			@Override
			public void run() {
				ObjectInputStream ois = null;
				try {
					ois = new ObjectInputStream(socket.getInputStream());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				try{
					while(!userDisconnected && !Thread.currentThread().isInterrupted()){
						// Make sure that we're dealing with a valid socket;
						
						Packet nextMessage = (Packet)ois.readObject();
						processMessage(nextMessage);
					}
					if(userDisconnected){
						processUserDisconnect();
					}
				}
				catch(Exception e){						
					e.printStackTrace();
				}
			}
			
		}
		
		public class ConnectionWriter implements Runnable {
			@Override
			public void run() {
				ObjectOutputStream oos = null;
				try {
					oos = new ObjectOutputStream(socket.getOutputStream());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				// Run until we are interrupted - read messages from the queue and write them onto our socket.
				while(!userDisconnected && !Thread.currentThread().isInterrupted()){
					if(!messageQueue.isEmpty()){
						Packet m = messageQueue.poll();
						try {
							oos.writeObject(m);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		
		}
		
		public Connection(Socket sock){
			if(!sock.isConnected())
	    		throw new RuntimeException("Socket not connected");
			
			this.messageQueue = new LinkedBlockingQueue<Packet>();
			this.socket = sock;
			ConnectionReader reader = new ConnectionReader();
			ConnectionWriter writer = new ConnectionWriter();
			readerThread = new Thread(reader);
			readerThread.start();
			writerThread = new Thread(writer);
			writerThread.start();
		}
		
		public void processMessage(Packet message){
			System.out.println("Dummy processMessage message in Connection");
		}
		
		public void sendMessage(Packet output){
			messageQueue.offer(output);
		}
		
		public void closeConnection(){
			writerThread.interrupt();
		}
		
		public User getUser(){
			return this.user;
		}
		
		public void setUser(User user){
			this.user = user;
		}
		
		public void processUserDisconnect(){
			// Overwritten in child class
		}
}