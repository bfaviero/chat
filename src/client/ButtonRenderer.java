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
 * @version 1.0 11/09/98
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
 * @version 1.0 11/09/98
 */

class ButtonEditor extends DefaultCellEditor {
  protected JButton button;

  private String label;
  private JLabel roomLabel;
  private boolean isPushed;

  public ButtonEditor(JCheckBox checkBox, JLabel roomLabel) {
    super(checkBox);
    this.roomLabel = roomLabel;
    button = new JButton();
    button.setOpaque(true);
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        fireEditingStopped();
      }
    });
  }

  public Component getTableCellEditorComponent(JTable table, Object value,
      boolean isSelected, int row, int column) {
    button.setForeground(table.getForeground());
    button.setBackground(table.getBackground());
    label = (value == null) ? "" : value.toString();
    button.setText(label);
    isPushed = true;
    DefaultTableModel model = (DefaultTableModel) table.getModel();
    roomLabel.setText("");
    model.removeRow(row);
    return button;
  }

  public Object getCellEditorValue() {
    if (isPushed) {
      // 
      // 
      
      // System.out.println(label + ": Ouch!");
    }
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
class JoinButtonEditor extends DefaultCellEditor {
    protected JButton button;

    private String label;
    private JLabel roomLabel;
    private boolean isPushed;
    private JList chatList;
    private ClientConnection conn;
    public JoinButtonEditor(JCheckBox checkBox, JLabel roomLabel, ClientConnection conn, JList chatList) {
      super(checkBox);
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

    public Component getTableCellEditorComponent(JTable table, Object value,
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
      synchronized(chatList) {
          String roomName = roomLabel.getText();
          DefaultListModel chatModel = (DefaultListModel) chatList.getModel();
          chatModel.setSize(0);
          List<String> messages = conn.getRoomMessages(roomName);
          String[] messagesArr = messages.toArray(new String[messages.size()]); 
          for (String message : messagesArr) {
              chatModel.addElement(message);
          }
      }
      
      
      return button;
    }

    public Object getCellEditorValue() {
      if (isPushed) {
        // 
        // 
        
        // System.out.println(label + ": Ouch!");
      }
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