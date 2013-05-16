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
    public SigninListener(JTextField SigninText, Client client) {
        this.client = client;
        this.SigninText = SigninText;
        
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String text = SigninText.getText();
        if (text.contains(" ")) {
            JOptionPane.showMessageDialog(null, "Please type in a name without spaces.");
            SigninText.setText(" ");
        }
        else {
            client.login(text);        
        }
    }
}
