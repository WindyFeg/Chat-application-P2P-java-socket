package protocols;

import java.util.*;

import peer.peer;

public class encode {

    public static String PeerInfo(peer peer) {
        return tag.PEER_NAME_HEAD + peer.getName() + tag.PEER_IP_TAIL + tag.PEER_IP_HEAD + peer.getHost()
                + tag.PEER_IP_TAIL + tag.PEER_PORT_HEAD + peer.getPort() + tag.PEER_PORT_TAIL;
    }

    public static String FriendRequest(peer peer) {
        return tag.FRIEND_REQUEST_HEAD + peer.getName() + tag.FRIEND_REQUEST_TAIL;
    }

    public static String OnlineList(ArrayList<peer> peers) {
        String res = "" + tag.ROOT_BEGIN;
        for (peer peer : peers) {
            res += tag.PEER_HEAD;
            // username
            res += tag.PEER_NAME_HEAD;
            res += peer.getName();
            res += tag.PEER_NAME_TAIL;
            // ipAddress
            res += tag.PEER_IP_HEAD;
            res += peer.getHost();
            res += tag.PEER_IP_TAIL;
            // port
            res += tag.PEER_PORT_HEAD;
            res += peer.getPort();
            res += tag.PEER_PORT_TAIL;
            res += tag.PEER_TAIL;

        }
        return res + tag.ROOT_END;
    }
}
