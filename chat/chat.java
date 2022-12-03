package chat;

import java.net.ServerSocket;
import java.net.Socket;

public class chat {
    private Socket socketChat;
    private String username;
    private String peername;

    chat(Socket socketChat, String username, String peername) {
        this.socketChat = socketChat;
        this.username = username;
        this.peername = peername;

        Thread listenThread = new Thread(new Runnable() {
            @Override
            public void run() {
                listenOnChating(socketChat);
            }
        });
        listenThread.start();
    }

    public void listenOnChating(Socket otherPeerSocket) {
        while (true) {

        }
    }
}
