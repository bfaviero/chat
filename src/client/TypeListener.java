package client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextField;

public class TypeListener implements KeyListener {
    private JList chatList;
    private JTextField type;
    
    public TypeListener(JList chatList, JTextField type) {
        this.chatList = chatList;
        this.type = type;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {   
            DefaultListModel model = (DefaultListModel) chatList.getModel();
            model.addElement(type.getText());
            type.setText("");
            
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
