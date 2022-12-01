package chat;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class friend_requestUI extends JFrame
        implements ActionListener {
    private JLabel requestLabel;
    private JLabel requestname;
    private Container c;
    private JButton btnDecline;
    private JButton btnAccept;

    friend_requestUI(String requestName) {

        setTitle("Friend Request!");
        setBounds(300, 90, 400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        // big Label Friend Request
        requestLabel = new JLabel("Friend Request!");
        requestLabel.setFont(new Font("Arial", Font.BOLD, 24));
        requestLabel.setSize(400, 20);
        requestLabel.setLocation(100, 20);
        c.add(requestLabel);

        // Name request
        requestname = new JLabel(requestName);
        requestname.setFont(new Font("Arial", Font.PLAIN, 18));
        requestname.setSize(400, 20);
        requestname.setLocation(120, 50);
        c.add(requestname);

        btnAccept = new JButton("Accept");
        btnAccept.setFont(new Font("Arial", Font.PLAIN, 15));
        btnAccept.setSize(100, 40);
        btnAccept.setLocation(50, 100);
        btnAccept.addActionListener(this);
        c.add(btnAccept);

        btnDecline = new JButton("Decline");
        btnDecline.setFont(new Font("Arial", Font.PLAIN, 15));
        btnDecline.setSize(100, 40);
        btnDecline.setLocation(200, 100);
        btnDecline.addActionListener(this);
        c.add(btnDecline);

        // set visible the damn thing =))
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
