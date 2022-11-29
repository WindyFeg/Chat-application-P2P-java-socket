package protocols;

import java.util.ArrayList;
import java.util.regex.Pattern;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import peer.peer;
import protocols.*;

public class decode {

    public static peer getPeerInfo(String code) {
        Document document = convertStringToXML(code);

        String name = document.getElementsByTagName("PEER_NAME").item(0).getTextContent();
        String host = document.getElementsByTagName("PEER_IP").item(0).getTextContent();
        int port = Integer.parseInt(document.getElementsByTagName("PEER_PORT").item(0).getTextContent());
        peer peer = new peer(name, host, port);
        return peer;
    }

    public static ArrayList<peer> getOnlineList(String code) {
        ArrayList<peer> onlinelist = new ArrayList<peer>();

        Document document = convertStringToXML(code);

        NodeList node_list = document.getElementsByTagName("PEER");

        for(int i = 0; i < node_list.getLength(); i = i + 1) {
            Node node = node_list.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String name = element
                        .getElementsByTagName("PEER_NAME")
                        .item(0).getTextContent();
                    String host = element
                        .getElementsByTagName("PEER_IP")
                        .item(0).getTextContent();
                    Integer port = Integer.parseInt(element
                        .getElementsByTagName("PEER_PORT")
                        .item(0).getTextContent());
                    
                    peer new_peer = new peer(name, host,port);
                    onlinelist.add(new_peer);
            }	
        }

        return onlinelist;
    }

    private static Document convertStringToXML(String xmlString) {
        // Parser that produces DOM object trees from XML content
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        // API to obtain DOM Document instance
        DocumentBuilder builder = null;
        try {
            // Create DocumentBuilder with default configuration
            builder = factory.newDocumentBuilder();

            // Parse the content to Document object
            Document document = builder.parse(
                    new InputSource(new StringReader(xmlString)));

            // Normalize xml structure
            document.normalize();

            return document;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
