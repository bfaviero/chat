package client;

import java.net.Socket;
import java.util.Calendar;
import java.util.concurrent.BlockingQueue;

import server.Server;
import main.User;

import main.Connection;
import main.Message;



public class ClientConnection extends Connection {
    private MainApp gui;
    private BlockingQueue<Message> messageQueue;
    public ClientConnection(Socket socket, MainApp gui) {
        super(socket);
        this.gui = gui;   
    }
    public void join(String room) {
        Message m = new Message(Command.JOIN, room, Calendar.getInstance(), "");
        processMessage(m);
    }
    public void message(String message, String room) {
        Message m = new Message(Command.MESSAGE, room, Calendar.getInstance(), message);
        processMessage(m);
    }
    


}
