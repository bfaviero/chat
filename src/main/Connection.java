package main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;



public class Connection {
		protected String nickname;
		protected Socket socket;
		protected User user;
		
		protected Thread readerThread;
		protected Thread writerThread;
		
		// Messages to be sent across the socket
		protected BlockingQueue<Message> messageQueue;
		
		public enum Command{
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
					while(!Thread.currentThread().isInterrupted()){
						Message nextMessage = (Message)ois.readObject();
						processMessage(nextMessage);
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
				while(!Thread.currentThread().isInterrupted()){
					if(!messageQueue.isEmpty()){
						Message m = messageQueue.poll();
						try {
							oos.writeObject(m);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		
		}
		
		public Connection(String nickname, Socket sock){
			if(!sock.isConnected())
	    		throw new RuntimeException("Socket not connected");
			
			this.messageQueue = new PriorityBlockingQueue<Message>();
			this.nickname = nickname;
			this.socket = sock;
			ConnectionReader reader = new ConnectionReader();
			ConnectionWriter writer = new ConnectionWriter();
			readerThread = new Thread(reader);
			readerThread.start();
			writerThread = new Thread(writer);
			writerThread.start();
		}
		
		public void processMessage(Message message){
			System.out.println("Dummy processMessage message in Connection");
		}
		
		public void sendMessage(Message output){
			messageQueue.offer(output);
		}
		
		public User getUser(){
			return this.user;
		}
		
		public void setUser(User u){
			this.user = u;
		}
}