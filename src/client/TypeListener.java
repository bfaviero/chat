package client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import main.Packet;
import main.Connection.Command;

public class TypeListener implements KeyListener {
    private JList chatList;
    private JTextField type;
    private ClientConnection conn; 
    private JLabel roomLabel;
    private JTable roomTable;
    /**
     * Listens for a message being sent through the chat box
     * @param chatList The list of current messages
     * @param type The typing box for message
     * @param roomTable The table of the user's rooms
     * @param roomLabel The label representing the current room the user is in
     * @param conn The connection to the socket
     */
    public TypeListener(JList chatList, JTextField type, JTable roomTable, JLabel roomLabel, ClientConnection conn) {
        this.chatList = chatList;
        this.type = type;
        this.roomLabel = roomLabel;
        this.conn = conn;
        this.roomTable = roomTable;
    }

    @Override
    /**
     * When key is pressed in the typing box
     */
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER) { 
            //This is a gloval order of the locks
            synchronized(roomTable) {
                synchronized(roomLabel) {
                    synchronized(chatList) {
                        //Get the room name
                            String room = roomLabel.getText();
                            //Make sure you're in a room when you send a message
                            if (room.equals("Room Name")) {
                                JOptionPane.showMessageDialog(null, "Please join a room first!");
                            }
                            else {
                                //Get the message text
                                String text = type.getText();
                                //Clear the box
                                type.setText("");
                                //Grab the model of the chatList
                                DefaultListModel model = (DefaultListModel) chatList.getModel();
                                //get the user's username
                                String user = conn.getUsername();
                                //Add the message to the chat window                               
                                model.add(0, user+": "+ text);                                
                                //Add the message to the messages store
                                List<String> messages = conn.getMessages(room);
                                messages.add(0, user+": "+text);
                                //Send the message to the server
                                Packet message = new Packet(Command.MESSAGE, room, text, user);
                                conn.sendMessage(message);
                            }
            }}}
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        // here for interface's sake
        
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
     // here for interface's sake
        
    }
    
}
