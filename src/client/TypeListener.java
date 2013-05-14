package client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Calendar;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

import main.Packet;
import main.Connection.Command;

public class TypeListener implements KeyListener {
    private JList chatList;
    private JTextField type;
    private ClientConnection conn; 
    private JLabel roomLabel;
    public TypeListener(JList chatList, JTextField type, JLabel roomLabel, ClientConnection conn) {
        this.chatList = chatList;
        this.type = type;
        this.roomLabel = roomLabel;
        this.conn = conn;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER) { 
            synchronized(chatList) {
                String room = roomLabel.getText();
                String text = type.getText();
                type.setText("");
                DefaultListModel model = (DefaultListModel) chatList.getModel();
                model.addElement(conn.getUsername()+": "+ text);
                String user = conn.getUsername();
                List<String> messages = conn.client.roomMessages.get(roomLabel.getText());
                messages.add(user+": "+ text);
                Packet message = new Packet(Command.MESSAGE, room, Calendar.getInstance(), text, user);
                conn.sendMessage(message);
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
