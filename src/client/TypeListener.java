package client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

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
            String room = roomLabel.getText();
            String text = type.getText();
            type.setText("");
            DefaultListModel model = (DefaultListModel) chatList.getModel();
            model.addElement(text);
            conn.message(text, room);
            
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
