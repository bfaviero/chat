package client;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *Button Renderer represents the class of the table of room names. 
 */
class ButtonRenderer extends JButton implements TableCellRenderer {

  public ButtonRenderer() {
    setOpaque(true);
  }

  public Component getTableCellRendererComponent(JTable table, Object value,
      boolean isSelected, boolean hasFocus, int row, int column) {
      setForeground(table.getForeground());
      setBackground(UIManager.getColor("Button.background"));
    setText((value == null) ? "" : value.toString());
    return this;
  }
}

/**
 * 
 * The button editor is what fires when the button is clicked.
 * This button editor represents the "leave room" button.
 */
class ButtonEditor extends DefaultCellEditor {
    /**
     * The button
     */
  protected JButton button;
  /**
   * 
   */
  private String label;
  /**
   * Label containing the name of the current room
   */
  private JLabel roomLabel;
  /**
   * The connection that communicates with the socket
   */
  private ClientConnection conn;
  /**
   * Whether or not the button has been pushed
   */
  private boolean isPushed;
  private JList chatList;
private JList userList;
  /**
   * Instantiates the button editor, which fires when the "leave" button is pressed.
   * @param checkBox A placeholder component
   * @param roomLabel The name of the current room
   * @param conn The connection that communicates with the socket
 * @param chatList 
   */
  public ButtonEditor(JCheckBox checkBox, JLabel roomLabel, ClientConnection conn, JList chatList, JList userList) {
    super(checkBox);
    this.userList = userList;
    this.conn = conn;
    this.roomLabel = roomLabel;
    button = new JButton();
    button.setOpaque(true);
    this.chatList = chatList;
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        fireEditingStopped();
      }
    });
  }
  /**
   * This method controls what happens when the "leave" button is pressed.
   * The user is removed from the room, the roomLabel is reset, and a 'quit' message is sent to the server
   */
  public Component getTableCellEditorComponent(JTable table, Object value,
      boolean isSelected, int row, int column) {
    System.out.println("good");
    synchronized(table) {
        synchronized(roomLabel) {
            synchronized(chatList) {
                synchronized(userList) {
                isPushed = true;
                String roomName = roomLabel.getText();
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                roomLabel.setText("Room Name");
                DefaultListModel chatModel = (DefaultListModel) chatList.getModel();
                chatModel.setSize(0);
                if (roomName.equals(model.getValueAt(row, 2))) {
                    DefaultListModel userListModel = (DefaultListModel) userList.getModel();
                    userListModel.setSize(0);
                }
                conn.quit((String) model.getValueAt(row, 2));
                model.removeRow(row);
                }
            }
        }
    }
    return null;
  }

  public Object getCellEditorValue() {
    isPushed = true;
    return new String(label);
  }

  public boolean stopCellEditing() {
    isPushed = true;
    return super.stopCellEditing();
  }

  protected void fireEditingStopped() {
  }
}
class JoinButtonEditor extends DefaultCellEditor {
    protected JButton button;

    private String label;
    private JLabel roomLabel;
    private boolean isPushed;
    private JList chatList;
    private JTable roomTable;
    private ClientConnection conn;
    public JoinButtonEditor(JCheckBox checkBox, JLabel roomLabel, ClientConnection conn, JList chatList, JTable roomTable) {
      super(checkBox);
      this.roomTable = roomTable;
      this.conn = conn;
      this.chatList = chatList;
      this.roomLabel = roomLabel;
      button = new JButton();
      button.setOpaque(true);
      button.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          fireEditingStopped();
        }
      });
    }

    public synchronized Component getTableCellEditorComponent(JTable table, Object value,
        boolean isSelected, int row, int column) {
      if (isSelected) {
        button.setForeground(table.getSelectionForeground());
        button.setBackground(table.getSelectionBackground());
      } else {
        button.setForeground(table.getForeground());
        button.setBackground(table.getBackground());
      }
      label = (value == null) ? "" : value.toString();
      button.setText(label);
      isPushed = true;
      
      DefaultTableModel model = (DefaultTableModel) table.getModel();
      String text = (String) model.getValueAt(row, column+1);
      roomLabel.setText(text);
      synchronized(roomTable) {
          synchronized(roomLabel) {
              synchronized(chatList) {
              String roomName = roomLabel.getText();
              DefaultListModel chatModel = (DefaultListModel) chatList.getModel();
              chatModel.setSize(0);
              List<String> messages = conn.getRoomMessages(roomName);
              String[] messagesArr = messages.toArray(new String[messages.size()]); 
              for (String message : messagesArr) {
                  chatModel.addElement(message);
              }
              conn.listChannelUsers(roomName);
              DefaultTableModel roomModel = (DefaultTableModel) roomTable.getModel(); 
              roomModel.setValueAt("", row, 3);
      }}}
      
      
      return button;
    }

    public Object getCellEditorValue() {
      isPushed = false;
      return new String(label);
    }

    public boolean stopCellEditing() {
      isPushed = false;
      return super.stopCellEditing();
    }

    protected void fireEditingStopped() {
      super.fireEditingStopped();
    }
  }