package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.JPanel;

public class MainApp {

    private JFrame frame;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainApp window = new MainApp();
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
    public MainApp() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel lblGuiChat = new JLabel("Gui Chat!");
        lblGuiChat.setHorizontalAlignment(SwingConstants.CENTER);
        frame.getContentPane().add(lblGuiChat, BorderLayout.NORTH);
        
        Box verticalBox = Box.createVerticalBox();
        frame.getContentPane().add(verticalBox, BorderLayout.WEST);
        
        JLabel lblNewLabel = new JLabel("Rooms");
        verticalBox.add(lblNewLabel);
        
        JPanel panel = new JPanel();
        verticalBox.add(panel);

        
        textField = new JTextField();
        panel.add(textField);
        textField.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("New label");
        verticalBox.add(lblNewLabel_1);
        
        JList list = new JList();
        verticalBox.add(list);
        
        JList list_1 = new JList();
        frame.getContentPane().add(list_1, BorderLayout.CENTER);
        
        Box verticalBox_1 = Box.createVerticalBox();
        frame.getContentPane().add(verticalBox_1, BorderLayout.EAST);
        
        JLabel lblPeople = new JLabel("People");
        verticalBox_1.add(lblPeople);
        
        JList list_2 = new JList();
        verticalBox_1.add(list_2);
    }

}
