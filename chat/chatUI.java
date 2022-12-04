package chat;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
    private JPanel aFile;
    private Container container;
    private JButton btnExit;
    private JButton btnSend;
    private JButton btnSendFile;
    private JButton btnFile;
    private JTextField iMessage;
    private static JTextArea aMessage;
    private static JTextArea fileLink;
    private chat chatHandler;
    private static String oldMsg;
    private static String fileName;
    private static String pathSend;
    private static File fileSend;
    private boolean isSendFile = false;

    private JScrollPane scroll;

    chatUI(Socket socketChat, String username, String peername, ObjectInputStream in, ObjectOutputStream out)
            throws IOException {
        chatHandler = new chat(socketChat, username, peername, in, out);
        initComponents(peername);
    }

    private void initComponents(String peerName) {
        setTitle("Friend Request!");
        setBounds(300, 90, 600, 575);
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
        btnSend.setSize(75, 50);
        btnSend.setLocation(325, 450);
        btnSend.addActionListener(this);
        container.add(btnSend);

        btnFile = new JButton("file");
        btnFile.setFont(new Font("Arial", Font.PLAIN, 15));
        btnFile.setSize(75, 50);
        btnFile.setLocation(425, 450);
        btnFile.addActionListener(this);
        container.add(btnFile);

        btnSendFile = new JButton("Send file");
        btnSendFile.setFont(new Font("Arial", Font.PLAIN, 12));
        btnSendFile.setSize(75, 50);
        btnSendFile.setLocation(500, 450);
        btnSendFile.addActionListener(this);
        container.add(btnSendFile);

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
        iMessage.setLocation(25, 450);
        container.add(iMessage);

        aFile = new JPanel();
        aFile.setLayout(new BoxLayout(aFile, BoxLayout.Y_AXIS));
        JScrollPane aFileScroll = new JScrollPane(aFile);
        aFileScroll.setVerticalScrollBarPolicy(aFileScroll.VERTICAL_SCROLLBAR_ALWAYS);
        aFileScroll.setSize(150, 300);
        aFileScroll.setLocation(425, 75);
        container.add(aFileScroll);

        // link
        fileLink = new JTextArea();
        fileLink.setFont(new Font("Arial", Font.PLAIN, 12));
        fileLink.setSize(150, 30);
        fileLink.setLocation(425, 395);
        fileLink.setEditable(false);
        container.add(fileLink);
        // screen

        aMessage = new JTextArea();
        aMessage.setEditable(false);
        JScrollPane sMessage = new JScrollPane(aFile);
        sMessage.setVerticalScrollBarPolicy(sMessage.VERTICAL_SCROLLBAR_ALWAYS);
        sMessage.setFont(new Font("Arial", Font.PLAIN, 15));
        sMessage.setSize(375, 350);
        sMessage.setLocation(25, 75);
        container.add(sMessage);

        // show the thing
        setVisible(true);
    }

    public static void updateMsg(String msg) {
        oldMsg = aMessage.getText();
        aMessage.setText(oldMsg + msg);
    }

    public static void sendFile() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(pathSend);

        // file name now in binary
        byte[] fileName_byte = fileName.getBytes();
        // create a variable to store the file
        byte[] fileContent_byte = new byte[(int) fileSend.length()];

        fileInputStream.read(fileContent_byte);

    };

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

            // File chooser
            JFileChooser fileChooser = new JFileChooser();

            // only file
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            // Show UI to pick file Yes - 0/ No - 1
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                isSendFile = true;

                // the path
                pathSend = (fileChooser.getSelectedFile().getAbsolutePath());
                fileSend = new File(pathSend);

                // file name
                fileName = fileChooser.getSelectedFile().getName();
                // textPath.setText(pathSend);
                fileLink.setText(pathSend);
                updateMsg("File Name:  " + fileName + "\nPath:  " + pathSend);
            }

        }
        if (e.getSource() == btnSendFile) {
            try {
                sendFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        if (e.getSource() == btnExit) {

        }
    }

    public static void main(String[] args) throws IOException {
        chatUI a = new chatUI(null, oldMsg, oldMsg, null, null);
    }

}