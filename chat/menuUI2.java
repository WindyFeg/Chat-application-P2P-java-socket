package chat;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;

/**
 * menuUI
 */
public class menuUI2 extends JFrame
        implements ActionListener {
    private JLabel WelcomeLabel;
    private JLabel Username;
    private JLabel OnlineLabel;
    private JLabel FriendLabel;
    private Container container;
    private JButton btnLogout;
    private JButton btnRefresh;
    private JList OnlineList;
    private JList FriendList;

    menuUI2() {

        setTitle("Friend Request!");
        setBounds(300, 90, 600, 575);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        container = getContentPane();
        container.setLayout(null);
        container.setBackground(new java.awt.Color(204, 204, 204));

        // big Label Friend Request
        WelcomeLabel = new JLabel("Welcome");
        WelcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        WelcomeLabel.setSize(300, 25);
        WelcomeLabel.setLocation(25, 25);
        container.add(WelcomeLabel);

        // Name request
        Username = new JLabel("WIndyFeng");
        Username.setFont(new Font("Arial", Font.PLAIN, 18));
        Username.setSize(300, 25);
        Username.setLocation(150, 25);
        container.add(Username);

        OnlineLabel = new JLabel("Online");
        OnlineLabel.setFont(new Font("Arial", Font.BOLD, 14));
        OnlineLabel.setSize(100, 25);
        OnlineLabel.setLocation(75, 75);
        container.add(OnlineLabel);

        FriendLabel = new JLabel("Friend");
        FriendLabel.setFont(new Font("Arial", Font.BOLD, 14));
        FriendLabel.setSize(100, 25);
        FriendLabel.setLocation(300, 75);
        container.add(FriendLabel);

        String week[] = { "Monday", "Tuesday", "Wednesday",
                "Thursday", "Friday", "Saturday", "Sunday" };
        OnlineList = new JList<>(week);
        OnlineList.setSize(200, 350);
        OnlineList.setLocation(25, 100);
        OnlineList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                JList source = (JList) e.getSource();
                if (!e.getValueIsAdjusting()) {
                    // do something
                    System.out.println((String) source.getSelectedValue());
                }
            }
        });
        container.add(OnlineList);

        String week2[] = { "Monday", "Tuesday", "Wednesday",
                "Thursday", "Friday", "Saturday", "Sunday" };
        FriendList = new JList<>(week2);
        FriendList.setSize(200, 350);
        FriendList.setLocation(250, 100);
        container.add(FriendList);

        btnRefresh = new JButton("Refresh");
        btnRefresh.setFont(new Font("Arial", Font.PLAIN, 15));
        btnRefresh.setSize(100, 25);
        btnRefresh.setLocation(25, 475);
        btnRefresh.addActionListener(this);
        container.add(btnRefresh);

        btnLogout = new JButton("Logout");
        btnLogout.setFont(new Font("Arial", Font.PLAIN, 15));
        btnLogout.setSize(100, 25);
        btnLogout.setLocation(450, 475);
        btnLogout.addActionListener(this);
        container.add(btnLogout);

        // set visible the damn thing =))
        setVisible(true);

    }

    public static void main(String[] args) {
        menuUI2 a = new menuUI2();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}