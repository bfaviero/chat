package client;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class SigninListener implements ActionListener{
    private JTextField SigninText;
    private Client client;
    private JTextField serverText;
    private JTextField portText;
    private JFrame frame; // Save so that we can "swap" out windows seamlessly
    
    public SigninListener(JTextField SigninText, Client client, JTextField serverText, JTextField portText, JFrame frame) {
        this.client = client;
        this.SigninText = SigninText;
        this.serverText = serverText;
        this.portText = portText;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String text = SigninText.getText();
        String server = serverText.getText();
        String portString = portText.getText();
        int port = 0;
        if (text.equals("") || server.equals("") || portString.equals("")){
        	JOptionPane.showMessageDialog(null, "Please enter a server, port, and a username to proceed. ");
        }
        else if (text.contains(" ")) {
            JOptionPane.showMessageDialog(null, "Please type in a name without spaces.");
            SigninText.setText("");
        }
        else if (!portString.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Please type in a positive integer port.");
            portText.setText("");
        }
        else {
            port = Integer.parseInt(portText.getText());
            if(!client.login(text, server, port, frame.getX(), frame.getY())){
            	JOptionPane.showMessageDialog(null, "Could not establish a connection to port "+String.valueOf(port)
            										+ " on "+server);
            }
        }
    }
}
