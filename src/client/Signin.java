package client;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import java.awt.Label;
import java.awt.Color;

public class Signin {

    protected JFrame frame;
    private JTextField SigninText;
    private Client client;
    public JList chatList;
    public JLabel roomLabel;
    public JTextField serverText;
    public JTextField PortText;
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
                    SigninText.requestFocusInWindow();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
 
    /**
     * Create the application.
     */
    public Signin(Client client) {
        this.client = client;
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new CardLayout(0, 0));

        JPanel signin_panel = new JPanel();
        frame.getContentPane().add(signin_panel, "name_1367989163741677000");
        signin_panel.setLayout(new BoxLayout(signin_panel, BoxLayout.Y_AXIS));
        
        Component verticalStrut0 = Box.createVerticalStrut(10);
        signin_panel.add(verticalStrut0);
        
        JLabel welcome_label = new JLabel("Welcome to GUI Chat!");
        welcome_label.setBackground(new Color(138, 43, 226));
        welcome_label.setAlignmentY(0.0f);
        welcome_label.setAlignmentX(0.5f);
        welcome_label.setHorizontalAlignment(SwingConstants.CENTER);
        signin_panel.add(welcome_label);
        
        Component verticalStrut = Box.createVerticalStrut(20);
        signin_panel.add(verticalStrut);
        
        // Box horizontalBox = Box.createHorizontalBox();
        // signin_panel.add(horizontalBox);
        Box outerGrid = Box.createHorizontalBox();
        Box labelBox = Box.createVerticalBox();
        Box fieldsBox = Box.createVerticalBox();
        outerGrid.add(labelBox);
        outerGrid.add(fieldsBox);
        signin_panel.add(outerGrid);

        
        JLabel serverLabel = new JLabel("Server:", SwingConstants.TRAILING);
        labelBox.add(serverLabel);
        labelBox.add(Box.createRigidArea(new Dimension(5,5)));
        //horizontalBox.add(ServerLabeL);
        
        serverText = new JTextField("localhost");
        fieldsBox.add(serverText);
        serverText.setColumns(10);
        
        
        //Box PortBox = Box.createHorizontalBox();
        //signin_panel.add(PortBox);
        
        JLabel PortLabel = new JLabel("Port:");
        //PortBox.add(PortLabel);
        labelBox.add(PortLabel);
        labelBox.add(Box.createRigidArea(new Dimension(5,5)));
        
        PortText = new JTextField("1234");
        // PortBox.add(PortText);
        PortText.setColumns(10);
        fieldsBox.add(PortText);
        
        Box horizontalBox = Box.createHorizontalBox();
        labelBox.add(horizontalBox);
        
        //Box signin_box = Box.createHorizontalBox();
        //signin_panel.add(signin_box);
        
        JLabel signin_label = new JLabel("Username: ");
        signin_label.setVerticalAlignment(SwingConstants.TOP);
        signin_label.setHorizontalAlignment(SwingConstants.TRAILING);
        signin_label.setLabelFor(serverText);
        labelBox.add(signin_label);
        labelBox.add(Box.createRigidArea(new Dimension(0,3)));

        //signin_box.add(signin_label);
        
        SigninText = new JTextField();
        fieldsBox.add(SigninText);
        
        JButton join_button = new JButton("Join Server");
        join_button.setAlignmentX(Component.CENTER_ALIGNMENT);
        SigninListener signinListener = new SigninListener(SigninText, client, serverText, PortText);
        join_button.addActionListener(signinListener);

        // Make enter key work the same as pressing the Join Server button
        frame.getRootPane().setDefaultButton(join_button);

        SigninText.setColumns(10);
        
        SigninText.setMaximumSize(SigninText.getPreferredSize() );
        serverText.setMaximumSize(serverText.getPreferredSize() );
        PortText.setMaximumSize(PortText.getPreferredSize() );
        Component verticalStrut2 = Box.createVerticalStrut(10);
        signin_panel.add(verticalStrut2);
        
        signin_panel.add(join_button);
   
    }

}
