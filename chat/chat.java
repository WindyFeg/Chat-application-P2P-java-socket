package chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class chat {
    private Socket socketChat;
    private String username;
    private String peername;
    private ObjectInputStream peerIn;
    private ObjectOutputStream peerOut;

    chat(Socket socketChat, String username, String peername, ObjectInputStream in, ObjectOutputStream out)
            throws IOException {
        this.socketChat = socketChat;
        this.username = username;
        this.peername = peername;
        peerOut = out;
        peerIn = in;
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
        listenThread.start();
    }

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

    public void SendMessage(String msg) throws IOException {
        if (msg == "") {
            return;
        }
        peerOut.writeObject(msg);
        chatUI.updateMsg(username + ": " + msg + "\n");
        peerOut.flush();
    }
}
