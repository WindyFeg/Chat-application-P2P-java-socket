package chat;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
import java.util.ArrayList;

import chat.*;
import protocols.files;

/**
 * menuUI
 */
public class chatUI extends JFrame
        implements ActionListener {
    private JLabel Username;
    private static JPanel aFile;
    private static Container container;
    private JButton btnExit;
    private JButton btnSend;
    private JButton btnSendFile;
    private JButton btnFile;
    private JTextField iMessage;
    private static JTextArea aMessage;
    private static JScrollPane sMessage;
    private static JTextArea fileLink;
    private static chat chatHandler;
    private static String oldMsg;
    private static String fileName;
    private static String pathSend;
    private static File fileSend;
    private boolean ischangeFileToBinary = false;
    public static int fieldId = 0;
    public static ArrayList<files> myFiles = new ArrayList<>();

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

        // File panel
        aFile = new JPanel();
        aFile.setLayout(new BoxLayout(aFile, BoxLayout.Y_AXIS));
        JScrollPane aFileScroll = new JScrollPane(aFile);
        aFileScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        aFileScroll.setSize(150, 300);
        aFileScroll.setLocation(425, 75);
        // aFile.setSize(150, 300);
        // aFile.setLocation(425, 75);
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
        // sMessage = new JScrollPane(aFile);
        // sMessage.setVerticalScrollBarPolicy(sMessage.VERTICAL_SCROLLBAR_ALWAYS);
        // sMessage.setFont(new Font("Arial", Font.PLAIN, 15));
        // sMessage.setSize(375, 350);
        // sMessage.setLocation(25, 75);
        aMessage.setFont(new Font("Arial", Font.PLAIN, 15));
        aMessage.setSize(375, 350);
        aMessage.setLocation(25, 75);
        // container.add(sMessage);
        container.add(aMessage);

        // show the thing
        setVisible(true);
    }

    public static JFrame createFame(String fileName, byte[] fileData, String fileExtension) {
        JFrame frame = new JFrame("File Downloader");
        frame.setSize(400, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("File Downloader");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Arial", Font.PLAIN, 25));
        title.setBorder(new EmptyBorder(20, 0, 10, 0));

        JLabel prompt = new JLabel("Do you want to download " + fileName + " ?");
        prompt.setAlignmentX(Component.CENTER_ALIGNMENT);
        prompt.setFont(new Font("Arial", Font.PLAIN, 20));
        prompt.setBorder(new EmptyBorder(20, 0, 10, 0));

        JButton btnYes = new JButton("Yes");
        btnYes.setPreferredSize(new Dimension(150, 75));
        btnYes.setFont(new Font("Arial", Font.PLAIN, 20));

        JButton btnNo = new JButton("No");
        btnNo.setPreferredSize(new Dimension(150, 75));
        btnNo.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel fileContent = new JLabel();
        fileContent.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel button = new JPanel();
        button.setBorder(new EmptyBorder(20, 0, 10, 0));
        button.add(btnYes, btnNo);

        if (fileExtension.equalsIgnoreCase("txt")) {
            fileContent.setText("<html>" + new String(fileData) + "</html>");
        } else {
            fileContent.setIcon(new ImageIcon(fileData));
        }

        // When you gotdamn download the file :))
        btnYes.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    chatHandler.DownloadFile(fileName, fileData);
                    frame.dispose();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

        });

        // close frame
        btnNo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }

        });

        panel.add(title);
        panel.add(prompt);
        panel.add(fileContent);
        panel.add(button);
        frame.add(panel);

        return frame;
    }

    public static void updateMsg(String msg) {
        oldMsg = aMessage.getText();
        aMessage.setText(oldMsg + msg);
    }

    public static MouseListener getMyMouseListener() {
        return new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                JPanel panel = (JPanel) e.getSource();
                int fieldid = Integer.parseInt(panel.getName());

                for (files file : myFiles) {
                    if (file.getId() == fieldid) {
                        JFrame previewFile = createFame(file.getName(), file.getData(), file.getFileEx());
                        previewFile.setVisible(true);
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

        };
    }

    // return type of file
    public static String getFileExtension(String filename) {
        int i = filename.lastIndexOf('.');
        if (i > 0) {
            return filename.substring(i + 1);
        } else {
            return "No extension found";
        }
    }

    // add new file to file UI
    public static void addFile(String namefile, byte[] fileContentByte) {
        // the row
        JPanel fileRow = new JPanel();
        fileRow.setLayout(new BoxLayout(fileRow, BoxLayout.Y_AXIS));
        // the of row
        JLabel fileNameRow = new JLabel();
        fileNameRow.setFont(new Font("Arial", Font.BOLD, 25));
        fileNameRow.setBorder(new EmptyBorder(10, 0, 10, 0));
        fileNameRow.setText(namefile);

        // the of row
        // check file tail
        if (getFileExtension(namefile).equalsIgnoreCase("txt")) {
            // set name as ID for mouse listener
            fileRow.setName(String.valueOf(fieldId));
            fileRow.addMouseListener(getMyMouseListener());
            // add damn thing to File listUI
            fileRow.add(fileNameRow);
            aFile.add(fileRow);
        } else {
            fileRow.setName(String.valueOf(fieldId));
            fileRow.addMouseListener(getMyMouseListener());

            fileRow.add(fileNameRow);
            aFile.add(fileRow);
        }
        myFiles.add(new files(fieldId, namefile, getFileExtension(namefile), fileContentByte));
        updateMsg("Your friend just send you a file!");
    }

    // send the damn name file
    public static void changeFileToBinary() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(pathSend);

        // file name now in binary
        byte[] fileName_byte = fileName.getBytes();
        // create a variable to store the file
        byte[] fileContent_byte = new byte[(int) fileSend.length()];

        fileInputStream.read(fileContent_byte);

        chatHandler.sendfile(fileName_byte, fileContent_byte);

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

        // set link of file
        if (e.getSource() == btnFile) {
            // File chooser
            JFileChooser fileChooser = new JFileChooser();

            // only file
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            // Show UI to pick file Yes - 0/ No - 1
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                ischangeFileToBinary = true;

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
            // click on send file
            try {
                changeFileToBinary();
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