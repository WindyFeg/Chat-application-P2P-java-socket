package chat;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import chat.*;

/**
 * menuUI
 */
public class chatUI extends JFrame
        implements ActionListener {
    private JLabel Username;
    private Container container;
    private JButton btnExit;
    private JButton btnSend;
    private JButton btnFile;
    private JTextField iMessage;
    private static JTextArea aMessage;
    private chat chatHandler;
    private static String oldMsg;

    chatUI(Socket socketChat, String username, String peername, ObjectInputStream in, ObjectOutputStream out)
            throws IOException {
        chatHandler = new chat(socketChat, username, peername, in, out);
        initComponents(peername);
    }

    private void initComponents(String peerName) {
        setTitle("Friend Request!");
        setBounds(300, 90, 460, 575);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        container = getContentPane();
        container.setLayout(null);
        container.setBackground(new java.awt.Color(204, 204, 204));

        // Name request
        Username = new JLabel("Chat with " + peerName);
        Username.setFont(new Font("Arial", Font.PLAIN, 18));
        Username.setSize(200, 25);
        Username.setLocation(125, 25);
        container.add(Username);

        btnSend = new JButton("Send");
        btnSend.setFont(new Font("Arial", Font.PLAIN, 18));
        btnSend.setSize(50, 50);
        btnSend.setLocation(307, 450);
        btnSend.addActionListener(this);
        container.add(btnSend);

        btnFile = new JButton("file");
        btnFile.setFont(new Font("Arial", Font.PLAIN, 15));
        btnFile.setSize(50, 50);
        btnFile.setLocation(367, 450);
        btnFile.addActionListener(this);
        container.add(btnFile);

        btnExit = new JButton("Exit");
        btnExit.setFont(new Font("Arial", Font.PLAIN, 15));
        btnExit.setSize(50, 25);
        btnExit.setLocation(325, 25);
        btnExit.addActionListener(this);
        container.add(btnExit);

        // input
        iMessage = new JTextField();
        iMessage.setFont(new Font("Arial", Font.PLAIN, 15));
        iMessage.setSize(300, 50);
        iMessage.setLocation(0, 450);
        container.add(iMessage);

        // screen
        aMessage = new JTextArea();
        aMessage.setFont(new Font("Arial", Font.PLAIN, 15));
        aMessage.setSize(460, 350);
        aMessage.setLocation(0, 75);
        aMessage.setEditable(false);
        aMessage.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(aMessage);
        container.add(aMessage);
        container.add(scroll);

        setVisible(true);
    }

    public static void updateMsg(String msg) {
        oldMsg = aMessage.getText();
        aMessage.setText(oldMsg + msg);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSend) {
            String msg = iMessage.getText();
            iMessage.setText("");
            try {
                chatHandler.SendMessage(msg);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        if (e.getSource() == btnFile) {

        }
        if (e.getSource() == btnExit) {

        }
    }

}