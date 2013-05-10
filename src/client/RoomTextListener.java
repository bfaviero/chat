package client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class RoomTextListener implements KeyListener {
    private JList roomList;
    private JTextField roomText;
    private ClientConnection conn;
    private JLabel roomLabel;
    public RoomTextListener(JList roomList, JTextField roomText, JLabel roomLabel, ClientConnection conn) {
        this.roomList = roomList;
        this.roomText = roomText;
        this.conn = conn;
        this.roomLabel = roomLabel;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {   
            List<String> messages = new ArrayList<String>();
            conn.client.roomMessages.put(roomText.getText(), messages);
            String text = roomText.getText();
            DefaultListModel model = (DefaultListModel) roomList.getModel();
            model.addElement(text);
            conn.join(text);
            roomText.setText("");
            
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
