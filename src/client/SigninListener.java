package client;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SigninListener implements ActionListener{
    private JTextField text;
    private JFrame frame;
    private JLabel titleLabel;
    public SigninListener(JTextField text, JFrame frame, JLabel titleLabel) {
        this.text = text;
        this.frame = frame;
        this.titleLabel = titleLabel;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
        titleLabel.setText("<html>Welcome to GUI Chat! Your nickname is <b>"+text.getText()+"</b></html>");
        cardLayout.next(frame.getContentPane()); 
        
    }
}
