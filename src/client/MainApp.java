package client;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import java.awt.Component;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.ScrollPane;
import java.awt.SystemColor;
import java.awt.Toolkit;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Font;
import javax.swing.ListSelectionModel;
import javax.swing.BoxLayout;
import javax.swing.border.EtchedBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;

public class MainApp {

    private JFrame frame;
    //Where users type in the room they want to join
    private JTextField roomText;
    //Where users type in messages
    private JTextField type;
    //Where users type in their username
    private JTextField SigninText;
    private ClientConnection conn;
    public JTable roomTable;
    public JList chatList;
    public JLabel roomLabel;
    public JList userList;
    public JTree tree;
    protected int x;
    protected int y;
    /**
     * Launch the application.
     * @wbp.parser.entryPoint
     */
    public void init() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    initialize(x,y);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MainApp(ClientConnection conn, int x, int y) {
        this.conn = conn;
        this.x = x;
        this.y = y;
    }

    /**
     * Initialize the contents of the frame, using the top corner (passed in via x, y)
     */
    private void initialize(int x, int y) {
        frame = new JFrame();
        frame.setBounds(x, y, 500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new CardLayout(0, 0));

        /**
         * Go to the main app when the button is clicked
         */
        
        JPanel main_panel = new JPanel();
        frame.getContentPane().add(main_panel, "name_1367989148345403000");
        main_panel.setLayout(new BorderLayout(10, 0));
        
        JLabel title_label = new JLabel("");
        main_panel.add(title_label, BorderLayout.NORTH);
        title_label.setHorizontalAlignment(SwingConstants.CENTER);
        
        
        JPanel panel = new JPanel();
        panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        panel.setBackground(new Color(220, 220, 220));
        main_panel.add(panel, BorderLayout.WEST);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel lblNewLabel = new JLabel("Rooms");
        lblNewLabel.setFont(new Font("Noteworthy", Font.PLAIN, 15));
        lblNewLabel.setBorder(new EmptyBorder(0, 0, 0, 0));
        lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblNewLabel);
        
        Box verticalBox_1 = Box.createVerticalBox();
        verticalBox_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        verticalBox_1.setBackground(new Color(253, 245, 230));
        main_panel.add(verticalBox_1, BorderLayout.EAST);
        
        
        
        tree = new JTree();
        tree.setFont(new Font("Modern No. 20", Font.PLAIN, 12));
        tree.setBackground(new Color(255, 255, 255));
        tree.setModel(new DefaultTreeModel(
                new DefaultMutableTreeNode("All Users") {
                }
            ));
        tree.getSelectionModel().setSelectionMode
            (TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.setAlignmentX(Component.LEFT_ALIGNMENT);
        tree.setVisibleRowCount(40);
        tree.setShowsRootHandles(true);

        
        
        verticalBox_1.add(tree);
        
        
        JLabel lblPeople = new JLabel("Users in room");
        lblPeople.setFont(new Font("Noteworthy", Font.PLAIN, 16));
        lblPeople.setBorder(new EmptyBorder(0, 0, 0, 0));
        verticalBox_1.add(lblPeople);
        
        DefaultListModel userListModel = new DefaultListModel();
        
        userList = new JList(userListModel);
        userList.setBackground(new Color(253, 245, 230));
        verticalBox_1.add(userList);
        
        JPanel chatPanel = new JPanel();
        chatPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
        main_panel.add(chatPanel, BorderLayout.CENTER);
        chatPanel.setLayout(new BorderLayout(0, 0));
        
        DefaultListModel chatListModel = new DefaultListModel();
        chatList = new JList(chatListModel);
        chatList.setBackground(new Color(253, 245, 230));

        JScrollPane scrollPane = new JScrollPane(chatList);
        chatPanel.add(scrollPane, BorderLayout.CENTER);
        
        Box horizontalBox = Box.createHorizontalBox();
        chatPanel.add(horizontalBox, BorderLayout.SOUTH);
        
        JList list_3 = new JList();
        horizontalBox.add(list_3);
        
        JLabel lblNewLabel_1 = new JLabel(" Type: ");
        lblNewLabel_1.setBackground(new Color(240, 255, 240));
        lblNewLabel_1.setFont(new Font("Noteworthy", Font.PLAIN, 15));
        lblNewLabel_1.setBorder(new EmptyBorder(0, 0, 0, 0));
        horizontalBox.add(lblNewLabel_1);
        
        type = new JTextField();
        type.setPreferredSize(new Dimension(14, 56));
        type.setBorder(UIManager.getBorder("InsetBorder.aquaVariant"));
        type.setBackground(new Color(240, 255, 240));
        type.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        type.setColumns(10);

        roomLabel = new JLabel("Room Name");
        roomLabel.setFont(new Font("Noteworthy", Font.PLAIN, 15));
        roomLabel.setForeground(new Color(0, 0, 0));
        roomLabel.setBorder(new CompoundBorder());
        chatPanel.add(roomLabel, BorderLayout.NORTH);
        
        roomText = new JTextField();
        roomText.setBorder(UIManager.getBorder("InsetBorder.aquaVariant"));
        roomText.setBackground(new Color(240, 255, 240));
        roomText.setToolTipText("Enter a room name");
        
        roomText.setColumns(10);
  
        roomText.setMaximumSize(roomText.getPreferredSize() );
        
        
        DefaultTableModel roomModel = new DefaultTableModel();
        roomModel.setDataVector(new Object[][] {}, new Object[] { "leave", "join", "name", ""});
        roomTable = new JTable(roomModel);
        roomTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        roomTable.setGridColor(new Color(224, 255, 255));
        roomTable.setBackground(new Color(253, 245, 230));
        roomTable.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        roomTable.getColumnModel().getColumn(0).setPreferredWidth(20);
        roomTable.getColumnModel().getColumn(1).setPreferredWidth(20);
        roomTable.getColumnModel().getColumn(2).setPreferredWidth(40);

        roomTable.getColumnModel().getColumn(3).setPreferredWidth(20);

        roomTable.getColumn("leave").setCellRenderer(new ButtonRenderer());
        roomTable.getColumn("leave").setCellEditor(
            new ButtonEditor(new JCheckBox(), roomLabel, conn, chatList, userList));
        roomTable.getColumn("join").setCellRenderer(new ButtonRenderer());
        roomTable.getColumn("join").setCellEditor(
            new JoinButtonEditor(new JCheckBox(), roomLabel, conn, chatList, roomTable));
        //JScrollPane roomPane = new JScrollPane();
        roomText.addKeyListener(new RoomTextListener(roomTable, roomText, roomLabel, conn));
        panel.add(roomText);
        
        ScrollPane scrollPane_1 = new ScrollPane();
        scrollPane_1.setBackground(new Color(253, 245, 230));
        scrollPane_1.add(roomTable);
        panel.add(scrollPane_1);
        
        //verticalBox.add(roomPane);
        
        
        TypeListener typeListener = new TypeListener(chatList, type, roomTable, roomLabel, conn);
        type.addKeyListener(typeListener);
        horizontalBox.add(type);
        
        
       


        
    }

}
