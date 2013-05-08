package client;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
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
    private Client client;
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
        JPanel signin_panel = new JPanel();
        frame.getContentPane().add(signin_panel, "name_1367989163741677000");
        signin_panel.setLayout(new BoxLayout(signin_panel, BoxLayout.Y_AXIS));
        
        JLabel welcome_label = new JLabel("Welcome to GUI Chat!");
        welcome_label.setAlignmentY(0.0f);
        welcome_label.setAlignmentX(0.5f);
        welcome_label.setHorizontalAlignment(SwingConstants.CENTER);
        signin_panel.add(welcome_label);
        
        Component verticalStrut = Box.createVerticalStrut(20);
        signin_panel.add(verticalStrut);
        
        Box signin_box = Box.createHorizontalBox();
        signin_panel.add(signin_box);
        
        JLabel signin_label = new JLabel("Type your username:");
        signin_box.add(signin_label);
        
        SigninText = new JTextField();
        signin_box.add(SigninText);
        SigninText.setColumns(10);
        SigninText.setMaximumSize(SigninText.getPreferredSize() );
        
        JButton join_button = new JButton("Join Server");
        join_button.setAlignmentX(Component.CENTER_ALIGNMENT);
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
        JList chatList = new JList(chatListModel);
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

        JLabel roomLabel = new JLabel("New label");
        chatPanel.add(roomLabel, BorderLayout.NORTH);
        
        /*
         * 
         * 
         * Listeners
         * 
         * 
         */

        SigninListener signinListener = new SigninListener(SigninText, frame, title_label, client);
        join_button.addActionListener(signinListener);
        signin_panel.add(join_button);
                
                        
        roomText = new JTextField();
        verticalBox.add(roomText);
        roomText.setColumns(10);
        DefaultListModel model = new DefaultListModel();
        JList roomList = new JList(model);
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
