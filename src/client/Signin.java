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

import javax.swing.BoxLayout;
import javax.swing.JButton;

public class Signin {

    protected JFrame frame;
    private JTextField SigninText;
    private Client client;
    public JList chatList;
    public JLabel roomLabel;
    public JTextField ServerText;
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
        
        JLabel welcome_label = new JLabel("Welcome to GUI Chat!");
        welcome_label.setAlignmentY(0.0f);
        welcome_label.setAlignmentX(0.5f);
        welcome_label.setHorizontalAlignment(SwingConstants.CENTER);
        signin_panel.add(welcome_label);
        
        Component verticalStrut = Box.createVerticalStrut(20);
        signin_panel.add(verticalStrut);
        
        Box horizontalBox = Box.createHorizontalBox();
        signin_panel.add(horizontalBox);
        
        JLabel ServerLabeL = new JLabel("Server:");
        horizontalBox.add(ServerLabeL);
        
        ServerText = new JTextField("localhost");
        horizontalBox.add(ServerText);
        ServerText.setColumns(10);
        
        Box PortBox = Box.createHorizontalBox();
        signin_panel.add(PortBox);
        
        JLabel PortLabel = new JLabel("Port:");
        PortBox.add(PortLabel);
        
        PortText = new JTextField("1234");
        PortBox.add(PortText);
        PortText.setColumns(10);
        
        Box signin_box = Box.createHorizontalBox();
        signin_panel.add(signin_box);
        
        JLabel signin_label = new JLabel("Type your username:");
        signin_box.add(signin_label);
        
        SigninText = new JTextField();
        
        JButton join_button = new JButton("Join Server");
        join_button.setAlignmentX(Component.CENTER_ALIGNMENT);
        SigninListener signinListener = new SigninListener(SigninText, client, ServerText, PortText);
        join_button.addActionListener(signinListener);

        // Make enter key work the same as pressing the Join Server button
        frame.getRootPane().setDefaultButton(join_button);

        signin_box.add(SigninText);
        SigninText.setColumns(10);
        SigninText.setMaximumSize(SigninText.getPreferredSize() );
        ServerText.setMaximumSize(ServerText.getPreferredSize() );
        PortText.setMaximumSize(PortText.getPreferredSize() );
        signin_panel.add(join_button);
   
    }

}
