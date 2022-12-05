package chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import protocols.files;

public class chat {
    private Socket socketChat;
    private String username;
    private String peername;
    public ObjectInputStream peerIn;
    public ObjectOutputStream peerOut;
    public DataOutputStream peerFileOut;
    public DataInputStream peerFileIn;

    chat(Socket socketChat, String username, String peername, ObjectInputStream in, ObjectOutputStream out)
            throws IOException {
        this.socketChat = socketChat;
        this.username = username;
        this.peername = peername;
        peerOut = out;
        peerIn = in;
        // ------------------------------------------------------------------------
        // Port server assigned for chat
        Thread listenThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    listenOnMessage(socketChat);
                } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                }

            }
        });
        // listenThread.start();
        // ------------------------------------------------------------------------
        Thread fileListenThread = new Thread(new Runnable() {
            @Override
            public void run() {
                listenOnFile(socketChat);
            }
        });
        fileListenThread.start();
    }

    // listen on file
    public void listenOnFile(Socket serverSocket) {
        while (true) {
            try {
                peerFileIn = new DataInputStream(serverSocket.getInputStream());
                // reading
                int fileNamelength = peerFileIn.readInt();

                // check the file name
                if (fileNamelength > 0) {
                    // create a variable for name data
                    byte[] fileNameByte = new byte[fileNamelength];
                    // read the name
                    peerFileIn.readFully(fileNameByte, 0, fileNameByte.length);
                    String filename = new String(fileNameByte);

                    int fileContentLength = peerFileIn.readInt();

                    if (fileContentLength > 0) {
                        // create a variable for data
                        byte[] fileContentByte = new byte[fileContentLength];
                        // read data
                        peerFileIn.readFully(fileContentByte, 0, fileContentByte.length);

                        // add new row to file UI
                        chatUI.addFile(filename, fileContentByte);
                    }
                }

            } catch (Exception e) {
            }
        }
    }

    // send out file
    public void sendfile(byte[] fileName_byte, byte[] fileContent_byte) throws IOException {
        peerFileOut = new DataOutputStream(socketChat.getOutputStream());
        // (length of file + file)
        // send name file
        peerFileOut.writeInt(fileName_byte.length);
        peerFileOut.write(fileName_byte);

        // send file content
        peerFileOut.writeInt(fileContent_byte.length);
        peerFileOut.write(fileContent_byte);
    }

    // listen on message
    public void listenOnMessage(Socket otherPeerSocket) throws ClassNotFoundException, IOException {
        while (true) {
            String text = (String) peerIn.readObject();
            chatUI.updateMsg(peername + ": " + text + "\n");

            if (text == null) {
                peerOut.close();
                socketChat.close();
                System.out.println("Peer disconnect");
            }
        }
    }

    // send out message
    public void SendMessage(String msg) throws IOException {
        if (msg == "") {
            return;
        }
        peerOut.writeObject(msg);
        chatUI.updateMsg(username + ": " + msg + "\n");
        peerOut.flush();
    }

    public void DownloadFile(String fileName, byte[] fileData) throws IOException {
        File fileDownload = new File(fileName);
        FileOutputStream peerFileOut2 = new FileOutputStream(fileDownload);

        peerFileOut2.write(fileData);
        peerFileOut2.close();
    }
}
