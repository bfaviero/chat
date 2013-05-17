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
    
    public TypeListener(JList chatList, JTextField type, JTable roomTable, JLabel roomLabel, ClientConnection conn) {
        this.chatList = chatList;
        this.type = type;
        this.roomLabel = roomLabel;
        this.conn = conn;
        this.roomTable = roomTable;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER) { 
            synchronized(chatList) {
                String room = roomLabel.getText();
                if (room.equals("Room Name")) {
                    JOptionPane.showMessageDialog(null, "Please join a room first!");
                }
                else {
                    String text = type.getText();
                    type.setText("");
                    DefaultListModel model = (DefaultListModel) chatList.getModel();
                    model.addElement(conn.getUsername()+": "+ text);
                    String user = conn.getUsername();
                    
                    List<String> messages = conn.getMessages(room);
                    Packet message = new Packet(Command.MESSAGE, room, text, user);
                    if (messages.size() > 1) {
                        String lastMessage = messages.get(messages.size()-1);
                        if (lastMessage.substring(0, lastMessage.indexOf(":")).equals(user)) {
                            text = user+": "+text;
                        }
                    }
                    else text = user+": "+text;
                    
                    messages.add(":"+text);
                    conn.sendMessage(message);
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub
        
    }
    
}
