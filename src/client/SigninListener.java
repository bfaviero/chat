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
    public SigninListener(JTextField SigninText, Client client, JTextField serverText, JTextField portText) {
        this.client = client;
        this.SigninText = SigninText;
        this.serverText = serverText;
        this.portText = portText;
        
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String text = SigninText.getText();
        String server = serverText.getText();
        int port = 0;
        if (text.contains(" ")) {
            JOptionPane.showMessageDialog(null, "Please type in a name without spaces.");
            SigninText.setText(" ");
        }
        else if (!portText.getText().matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Please type in a positive integer port.");
            portText.setText("");
        }
        else {
            port = Integer.parseInt(portText.getText());
            client.login(text, server, port);        
        }
    }
}
