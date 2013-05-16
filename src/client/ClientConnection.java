package client;

import java.net.Socket;
import java.util.Calendar;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import main.Connection;
import main.Packet;


public class ClientConnection extends Connection {
    Client client;
    private MainApp gui;
    
    public ClientConnection(Socket socket, Client client) {
        super(socket);
        this.client = client;
    }
    
    public void setGUI(MainApp gui){
    	this.gui = gui;
    }
    
    public List<String> getMessages(String room) {
        return client.getMessages(room);
    }
    
    public boolean roomExists(String room) {
        return client.currentRooms.contains(room);
    }
    
    public void join(String room) {
    	System.out.println("Sent join request, room: " + room);
        Packet m = new Packet(Command.JOIN, room, Calendar.getInstance(), "", "");
        sendMessage(m);
    }
    
    public void login(String userName) {
        System.out.println("Connection's got this shit");
        Packet m = new Packet(Command.LOGIN, "", Calendar.getInstance(), userName, "");
        sendMessage(m);
    }
    
    public void message(String message, String room) {
        Packet m = new Packet(Command.MESSAGE, room, Calendar.getInstance(), message,  "");
        sendMessage(m);
    }
    
    public void listUsers() {
        Packet m = new Packet(Command.LIST_USERS, "", Calendar.getInstance(), "",  "");
        sendMessage(m);
    }

    public String getUsername() {
        return client.getUser();
    }
    
    public List<String> getRoomMessages(String roomName) {
        return client.roomMessages.get(roomName);
    }
    
    public void processMessage(Packet message){
        System.out.println("Message received and processing in progress");
        System.out.println(message.getCommand().name() + ": " + message.getChannelName() + " " + message.getMessageText());
        Packet response;
        switch(message.getCommand()){
        case REPLY_LIST_CHANNELS:
            
            break;
        case REPLY_SUCCESS:
            System.out.print("Reply success");
            break;
        case LOGIN:
            System.out.print("Logged in?");
            break;
        case JOIN:
            response = new Packet(Command.LOGIN, "", Calendar.getInstance(), client.getUser(), "");
            sendMessage(response);
            break;
        case REPLY_FAILURE:
            break;
        case REPLY_LIST_USERS:
            DefaultListModel model1 = (DefaultListModel) gui.userList.getModel();
            model1.clear();

            JTree tree = gui.tree;
            DefaultMutableTreeNode root = new DefaultMutableTreeNode("All Users");
            DefaultTreeModel model = new DefaultTreeModel(root);

            String[] text = message.getMessageText().split(" ");
            for (String name : text) {
                root.add(new DefaultMutableTreeNode(name));
            }
            tree.setModel(model);
            break;
        case MESSAGE:
            String mess = message.getAuthor()+": "+ message.getMessageText();
            if (gui.roomLabel.getText().equals(message.getChannelName())) {
                synchronized(gui.chatList) {
                    JList chatList = gui.chatList;

                    DefaultListModel model2 = (DefaultListModel) chatList.getModel();
                    model2.addElement(mess);
                    chatList.repaint();
                }
            }
            client.getMessages(message.getChannelName()).add(mess);
            break;
        case QUIT:
            break;
        default:
            System.out.println("Fell through");
            break;  
        }
    }

	public void processUserDisconnect(){
		// Do nothing
	}
    
    


}
