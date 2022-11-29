package protocols;

import peer.peer;
import protocols.tag;

public class encode {
    public String PeerInfo(peer peer){
        return tag.PEER_NAME_HEAD + peer.getName() + tag.PEER_IP_TAIL + tag.PEER_IP_HEAD + peer.getHost() + tag.PEER_IP_TAIL + tag.PEER_PORT_HEAD + peer.getPort() + tag.PEER_PORT_TAIL;
    }
}
