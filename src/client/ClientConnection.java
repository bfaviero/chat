package client;

import java.net.Socket;
import java.util.Enumeration;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import main.Connection;
import main.Packet;


public class ClientConnection extends Connection {
    Client client;
    private MainApp gui;
    
    public ClientConnection(Socket socket, Client client) {
        super(socket);
        this.client = client;
    }
    
    /**
     * Set GUI corresponding to this ClientConnection.
     * @param gui - the MainApp GUI.  
     */
    public void setGUI(MainApp gui){
    	this.gui = gui;
    }
    
    /**
     * Get a list of a room Messages.  
     * @param room - the room being queried
     * @return a List of the room Message contents
     */
    public List<String> getMessages(String room) {
        return client.getMessages(room);
    }
    
    /**
     * See if this client is in this room.
     * @param room The room being queried.
     * @return boolean Returns true if the client is in the room
     */
    public boolean roomExists(String room) {
        return client.roomMessages.containsKey(room);
    }
    
    /**
     * Request this client join a channel on the server.
     * @param room String name of the channel being queried.  
     */
    public void join(String room) {
    	System.out.println("Sent join request, room: " + room);
        Packet m = new Packet(Command.JOIN, room, "", "");
        sendMessage(m);
    }
    
    /**
     * Send a request to log into the server 
     * @param userName String username of this user
     */
    public void login(String userName) {
        System.out.println("Connection's got this shit");
        Packet m = new Packet(Command.LOGIN, "", userName, "");
        sendMessage(m);
    }
    
    /**
     * Send a request to send a message to a channel.
     * @param message - the message being sent
     * @param room - name of the channel being sent to
     */
    public void message(String message, String room) {
        Packet m = new Packet(Command.MESSAGE, room, message, "");
        sendMessage(m);
    }
    
    /**
     * Send a request for the client to leave a channel.
     * @param room - the name of the channel we want to leave
     */
    public void quit(String room) {
        client.roomMessages.remove(room);
        Packet response = new Packet(Command.QUIT, room, client.getUser(), "");
        sendMessage(response);
    }
    
    /**
     * Send a request for the currently logged-in users of the server.
     */
    public void listUsers() {
        Packet m = new Packet(Command.LIST_USERS, "", "", "");
        sendMessage(m);
    }
    
    /**
     * Send a request for the usernames of the members of a channel
     * @param room - the channel whose users we want to know
     */
    public void listChannelUsers(String room) {
        Packet m = new Packet(Command.LIST_USERS, room, "", "");
        sendMessage(m);
    }

    /**
     * Get this connection's client's username
     * @return the username of the client
     */
    public String getUsername() {
        return client.getUser();
    }
    
    /**
     * Return the list of the specified room's messages
     * @param roomName
     * @return List of the room's Packets' contents.  
     */
    public List<String> getRoomMessages(String roomName) {
        return client.roomMessages.get(roomName);
    }
    
    /**
     * @param Packet message - the message to be processed
     * Process messages returned from the ServerConnection by updating 
     * GUI objects.  
     */
    public synchronized void processMessage(Packet message){
        System.out.println("Message received and processing in progress");
        System.out.println(message.getCommand().name() + ": " + message.getChannelName() + " " + message.getMessageText());
        Packet response;
        switch(message.getCommand()){
        case REPLY_LIST_CHANNEL_USERS:
            if (message.getChannelName().equals(gui.roomLabel.getText())) {
                DefaultListModel userListModel = (DefaultListModel) gui.userList.getModel();
                userListModel.setSize(0);
                String[] users = message.getMessageText().split(" ");
                for (String user : users) {
                    userListModel.addElement(user);
                }
            }
            break;
        case REPLY_SUCCESS:
            System.out.print("Reply success");
            break;
        case REPLY_FAILURE:
            break;
        case JOIN:
            if (message.getChannelName().equals(gui.roomLabel.getText())) {
                DefaultListModel userListModel = (DefaultListModel) gui.userList.getModel();
                userListModel.addElement(message.getAuthor());
            }
            break;
        case REPLY_LIST_USERS:
            JTree tree = gui.tree;
            DefaultMutableTreeNode root = new DefaultMutableTreeNode("All Users");
            DefaultTreeModel model = new DefaultTreeModel(root);

            String[] text = message.getMessageText().split(" ");
            for (String name : text) {
                root.add(new DefaultMutableTreeNode(name));
            }
            tree.setModel(model);
            tree.repaint();
            break;
        case MESSAGE:
            String mess = message.getAuthor()+": "+ message.getMessageText();
            if (gui.roomLabel.getText().equals(message.getChannelName())) {
                synchronized(gui.chatList) {
                    JList chatList = gui.chatList;
                    DefaultListModel model2 = (DefaultListModel) chatList.getModel();
                    model2.add(0, mess);
                    chatList.repaint();
                }
            }
            else {                
                DefaultTableModel roomModel = (DefaultTableModel) gui.roomTable.getModel(); 
                synchronized(roomModel){
                    for (int i=0;i<roomModel.getRowCount();i++) {
                        if (message.getChannelName()!=gui.roomLabel.getText() && message.getChannelName().equals(roomModel.getValueAt(i, 2))) {
                            String missed = (String) roomModel.getValueAt(i, 3);
                            try {
                                Integer numMissed = Integer.parseInt(missed);
                                numMissed +=1;
                                roomModel.setValueAt(numMissed.toString(), i, 3);
                            }
                            catch(Exception e) {
                                roomModel.setValueAt("1", i, 3);
                            }
                            
                            break;
                        }
                    }
                }
            }
            client.getMessages(message.getChannelName()).add(0, mess);
            break;
        case QUIT:
            JTree treeCopy = gui.tree;
            DefaultTreeModel treeModel = (DefaultTreeModel) treeCopy.getModel();
            DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) treeModel.getRoot();
            for (int i=0;i<rootNode.getChildCount();i++) {
                if (rootNode.getChildAt(i).equals(message.getAuthor())) {
                    rootNode.remove(i);
                    break;
                }
            }
            
            break;
        default:
            System.out.println("Fell through");
            break;  
        }
    }

    /**
     * Dummy method; use in debugging.  
     */
	public void processUserDisconnect(){
		// Do nothing
	}
    
    


}
