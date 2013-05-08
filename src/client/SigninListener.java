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
    private JTextField text;
    private JFrame frame;
    private JLabel titleLabel;
    private Client client;
    public SigninListener(JTextField text, JFrame frame, JLabel titleLabel, Client client) {
        this.text = text;
        this.frame = frame;
        this.titleLabel = titleLabel;
        this.client = client;
        
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
        titleLabel.setText("<html>Welcome to GUI Chat! Your nickname is <b>"+text.getText()+"</b></html>");
        cardLayout.next(frame.getContentPane()); 
        try {
            client.createConnection(text.getText());
        } catch (UnknownHostException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
    }
}
