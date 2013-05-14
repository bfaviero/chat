package client;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.Component;
import java.awt.CardLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;

public class MainApp {

    private JFrame frame;
    private JTextField roomText;
    private JTextField type;
    private JTextField SigninText;
    private ClientConnection conn;
    JList roomList;
    JList chatList;
    JLabel roomLabel;
    /**
     * Launch the application.
     * @wbp.parser.entryPoint
     */
    public void init() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    initialize();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    /**
     * Create the application.
     */
    public MainApp(ClientConnection conn) {
        this.conn = conn;
        
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        /*
         * 
         * 
         * Frame
         * 
         * 
         */
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new CardLayout(0, 0));
        /*
         * 
         * 
         * signin screen
         * 
         * 
         */

        /**
         * Go to the main app when the button is clicked
         */
        
        /*
         * 
         * 
         * 
         * Main screen
         * 
         * 
         * 
         */
        JPanel main_panel = new JPanel();
        frame.getContentPane().add(main_panel, "name_1367989148345403000");
        main_panel.setLayout(new BorderLayout(0, 0));
        
        JLabel title_label = new JLabel("");
        main_panel.add(title_label, BorderLayout.NORTH);
        title_label.setHorizontalAlignment(SwingConstants.CENTER);
        
        Box verticalBox = Box.createVerticalBox();
        main_panel.add(verticalBox, BorderLayout.WEST);
        
        JLabel lblNewLabel = new JLabel("Rooms");
        verticalBox.add(lblNewLabel);
        
        Box verticalBox_1 = Box.createVerticalBox();
        main_panel.add(verticalBox_1, BorderLayout.EAST);
        
        JTree tree = new JTree();
        tree.setAlignmentX(Component.LEFT_ALIGNMENT);
        tree.setVisibleRowCount(40);
        tree.setShowsRootHandles(true);
        tree.setModel(new DefaultTreeModel(
            new DefaultMutableTreeNode("All Users") {
                {
                    add(new DefaultMutableTreeNode("All Users"));
                }
            }
        ));
        verticalBox_1.add(tree);
        
        JLabel lblPeople = new JLabel("Users in this room");
        verticalBox_1.add(lblPeople);
        
        JList list_2 = new JList();
        verticalBox_1.add(list_2);
        
        JPanel chatPanel = new JPanel();
        main_panel.add(chatPanel, BorderLayout.CENTER);
        chatPanel.setLayout(new BorderLayout(0, 0));
        
        DefaultListModel chatListModel = new DefaultListModel();
        chatList = new JList(chatListModel);

        JScrollPane scrollPane = new JScrollPane(chatList);
        chatPanel.add(scrollPane, BorderLayout.CENTER);
        

        
        Box horizontalBox = Box.createHorizontalBox();
        chatPanel.add(horizontalBox, BorderLayout.SOUTH);
        
        JList list_3 = new JList();
        horizontalBox.add(list_3);
        
        JLabel lblNewLabel_1 = new JLabel("Type:");
        horizontalBox.add(lblNewLabel_1);
        
        type = new JTextField();
        type.setColumns(10);

        roomLabel = new JLabel("New label");
        chatPanel.add(roomLabel, BorderLayout.NORTH);
        
        /*
         * 
         * 
         * Listeners
         * 
         * 
         */

        roomText = new JTextField();
        verticalBox.add(roomText);
        roomText.setColumns(10);
        DefaultListModel model = new DefaultListModel();
        roomList = new JList(model);
        //ListSelectionModel listSelectionModel = roomList.getSelectionModel();
        //listSelectionModel.addListSelectionListener(new RoomListSelectionHandler(chatList, client));
        roomList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                synchronized(chatList) {
                        JList list = (JList)evt.getSource();
                        int index = list.locationToIndex(evt.getPoint());
                        DefaultListModel model = (DefaultListModel) roomList.getModel();
                        String roomName = (String) model.get(index);
                        DefaultListModel chatModel = (DefaultListModel) chatList.getModel();
                        chatModel.setSize(0);
                        List<String> messages = conn.getRoomMessages(roomName);
                        roomLabel.setText(roomName);
                        String[] messagesArr = messages.toArray(new String[messages.size()]); 
                        for (String message : messagesArr) {
                            chatModel.addElement(message);
                        }
                    }
                }
        });
        roomList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        verticalBox.add(roomList);
        RoomTextListener roomTextListener = new RoomTextListener(roomList, roomText, roomLabel, conn);

        roomText.addKeyListener(roomTextListener);
        roomText.setMaximumSize(roomText.getPreferredSize() );
        
        TypeListener typeListener = new TypeListener(chatList, type, roomLabel,  conn);
        type.addKeyListener(typeListener);
        horizontalBox.add(type);
        
        
       


        
    }

}
