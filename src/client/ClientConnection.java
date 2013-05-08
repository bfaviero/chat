package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.net.Socket;

import server.Server;

import main.Connection;

public class ClientConnection extends Connection implements Runnable {
    public String username;
    public Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private Server server;
    private Thread writer;
    private Thread reader;
    
    public ClientConnection(String user, Socket sock, Server server) {
        super(user, sock);
        this.server = server;
        reader = new Thread(new ConnectionReader());
        reader.start();
        writer = new Thread(new ConnectionWriter());
        writer.start();
    }


    
    public class ConnectionReader implements Runnable {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            
        }
        
    }
    
    public class ConnectionWriter implements Runnable {

        @Override
        public void run() {
            
        }
        
        
    }
    
    
    public void closeSockets(){
        try{
            this.server.processUserDisconnect(this.nickname);
            in.close();
            out.close();
            this.socket.close();
        }
        catch(Exception e){
            System.err.println("Error attempting to process disconnect for user" + this.nickname);
            e.printStackTrace();
        }
    }
    
    public void run(){

    }
    
    
}
