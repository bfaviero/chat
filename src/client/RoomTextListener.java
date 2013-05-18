package client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import main.Packet;
import main.Connection.Command;


public class RoomTextListener implements KeyListener {
    private JTable roomTable;
    private JTextField roomText;
    private ClientConnection conn;
    private JLabel roomLabel;
    public RoomTextListener(JTable roomTable, JTextField roomText, JLabel roomLabel, ClientConnection conn) {
        this.roomTable = roomTable;
        this.roomText = roomText;
        this.conn = conn;
        this.roomLabel = roomLabel;
    }

    private boolean channelAlreadyInTable(DefaultTableModel model, String channel){
    	boolean channelExists = false;
        
        // Check if we've already instantiated room in left-hand table
        Vector<Vector<String>> v = ((Vector)model.getDataVector());
        for(int i = 0; i<v.size(); i++){
        	if(v.elementAt(i).elementAt(2).equals(channel)){
        		channelExists = true;
        		break;
        	}
        }
        return channelExists;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER) { 
            synchronized(roomTable) {
                synchronized(roomLabel) {
                    synchronized(roomText) {
                            String room = roomText.getText();
                            
                            
                            if (room.equals("")){
                            	JOptionPane.showMessageDialog(null, "Please enter a room name");
                            }
                            else if (room.contains(" ")) {
                                JOptionPane.showMessageDialog(null, "Please type in a name without spaces.");
                            }
                            else if (!conn.roomExists(room)) {
                                List<String> messages = new ArrayList<String>();
                                conn.client.roomMessages.put(roomText.getText(), messages);

                                DefaultTableModel model = (DefaultTableModel) roomTable.getModel();

                                model.addRow(new Object[]{"x", ">", room, ""});
                                conn.join(room);
                            }
                            else{
                                DefaultTableModel model = (DefaultTableModel) roomTable.getModel();

                                if(!channelAlreadyInTable(model, room)){
                                	model.addRow(new Object[]{"x", ">", room, ""});
                                	conn.join(room);
                            	}
                            }
                            roomText.setText("");
                        }}}
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
