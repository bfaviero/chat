package client;

import java.net.Socket;
import java.util.concurrent.BlockingQueue;

import server.Server;
import server.User;

import main.Connection;
import main.Message;



public class ClientConnection extends Connection {
    
    private BlockingQueue<Message> messageQueue;
    public ClientConnection(String user, Socket socket, Client client) {
        super(user, socket);
        this.client = client;
        
    }


}
