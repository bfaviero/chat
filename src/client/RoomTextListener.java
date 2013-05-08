package client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class RoomTextListener implements KeyListener {
    private JList roomList;
    private JTextField roomText;
    public RoomTextListener(JList roomList, JTextField roomText) {
        this.roomList = roomList;
        this.roomText = roomText;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {   
            DefaultListModel model = (DefaultListModel) roomList.getModel();
            model.addElement(roomText.getText());
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
