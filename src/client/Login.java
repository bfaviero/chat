package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.Box;
import javax.swing.JButton;

public class Login {

    private JFrame frame;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login window = new Login();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Login() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel lblWelcomeToGui = new JLabel("Welcome to GUI Chat!");
        panel.add(lblWelcomeToGui);
        lblWelcomeToGui.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblWelcomeToGui.setAlignmentY(0.0f);
        lblWelcomeToGui.setHorizontalAlignment(SwingConstants.CENTER);
        
        Component verticalStrut = Box.createVerticalStrut(20);
        panel.add(verticalStrut);
        
        JLabel lblNewLabel = new JLabel("Type your username:");
        panel.add(lblNewLabel);
        lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        textField = new JTextField();
        panel.add(textField);
        textField.setColumns(10);
        textField.setMaximumSize( textField.getPreferredSize() );
        
        JButton btnNewButton = new JButton("Join server");
        panel.add(btnNewButton);
        btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

}
