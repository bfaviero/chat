package client;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SigninListener implements ActionListener{
    private JTextField SigninText;
    private Client client;
    public SigninListener(JTextField SigninText, Client client) {
        this.client = client;
        this.SigninText = SigninText;
        
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        client.login(SigninText.getText());        
    }
}
