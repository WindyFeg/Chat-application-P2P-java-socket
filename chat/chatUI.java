package chat;

import java.net.Socket;
import java.util.Random;
import java.awt.event.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author USER
 */
public class chatUI extends javax.swing.JFrame implements ActionListener {

        // private javax.swing.JButton btnAdd;
        private javax.swing.JPanel background;
        private javax.swing.JPanel friendPanel;
        private javax.swing.JPanel functionBox;
        private javax.swing.JButton btnlogout;
        private javax.swing.JButton btnRefresh;
        private javax.swing.JLabel tOnline;
        private javax.swing.JLabel tFriends;
        private javax.swing.JLabel tUsernname;
        private javax.swing.JList<String> lFriends;
        private javax.swing.JList<String> lOnline;
        // private javax.swing.JPanel jPanel1;
        // private javax.swing.JPanel jPanel2;
        private javax.swing.JScrollPane scrollFriend;
        private javax.swing.JScrollPane scrollOnline;
        // private javax.swing.JScrollPane jScrollPane3;
        // private javax.swing.JTextArea jTextArea1;
        // private javax.swing.JTextField jTextField1;
        private javax.swing.JPanel messagePanel;
        private javax.swing.JPanel onlinePanel;
        // private javax.swing.JButton btnSend;
        private DefaultListModel itemInl;
        // myVariable
        public chat chatHandle;
        public static friend_requestUI requestUI;

        public chatUI(Socket server, ObjectInputStream serverIn, ObjectOutputStream serverOut, String username)
                        throws ClassNotFoundException {
                chatHandle = new chat(server, username, serverOut, serverIn);

                chatHandle.GetFriendFromServer();
                // Send to server peer information
                initComponents(username);
        }

        @SuppressWarnings("unchecked")

        private void initComponents(String username) {

                background = new javax.swing.JPanel();
                messagePanel = new javax.swing.JPanel();
                // jScrollPane3 = new javax.swing.JScrollPane();
                // jTextArea1 = new javax.swing.JTextArea();
                // jPanel2 = new javax.swing.JPanel();
                // jTextField1 = new javax.swing.JTextField();
                // btnSend = new javax.swing.JButton();
                // btnAdd = new javax.swing.JButton();
                // jPanel1 = new javax.swing.JPanel();
                tUsernname = new javax.swing.JLabel();
                onlinePanel = new javax.swing.JPanel();
                tOnline = new javax.swing.JLabel();
                scrollOnline = new javax.swing.JScrollPane();
                lOnline = new javax.swing.JList<>();
                functionBox = new javax.swing.JPanel();
                btnlogout = new javax.swing.JButton();
                btnRefresh = new javax.swing.JButton();
                friendPanel = new javax.swing.JPanel();
                tFriends = new javax.swing.JLabel();
                scrollFriend = new javax.swing.JScrollPane();
                lFriends = new JList();
                itemInl = new DefaultListModel<>();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                background.setBackground(new java.awt.Color(204, 204, 204));

                // jTextArea1.setColumns(20);
                // jTextArea1.setRows(5);
                // jScrollPane3.setViewportView(jTextArea1);

                // JPanel2

                // Online
                onlinePanel.setBackground(new java.awt.Color(204, 255, 255));

                tOnline.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                tOnline.setText("ONLINE");

                lOnline.setModel(new javax.swing.AbstractListModel<String>() {
                        String[] strings = {};

                        public int getSize() {
                                return strings.length;
                        }

                        public String getElementAt(int i) {
                                return strings[i];
                        }
                });
                scrollOnline.setViewportView(lOnline);
                lOnline.addListSelectionListener(new ListSelectionListener() {
                        public void valueChanged(ListSelectionEvent evt) {
                                getValuedOnline(evt);
                                return;
                        }
                });

                javax.swing.GroupLayout onlinePanelLayout = new javax.swing.GroupLayout(onlinePanel);
                onlinePanel.setLayout(onlinePanelLayout);
                onlinePanelLayout.setHorizontalGroup(
                                onlinePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(onlinePanelLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(
                                                                                onlinePanelLayout.createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                .addGroup(onlinePanelLayout
                                                                                                                .createSequentialGroup()
                                                                                                                .addComponent(tOnline,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                95,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGap(0, 94, Short.MAX_VALUE))
                                                                                                .addComponent(scrollOnline,
                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING))
                                                                .addContainerGap()));
                onlinePanelLayout.setVerticalGroup(
                                onlinePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(onlinePanelLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(tOnline)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(scrollOnline)
                                                                .addContainerGap()));

                // FuctionBox
                functionBox.setBackground(new java.awt.Color(204, 255, 255));

                btnlogout.setText("Logout");
                btnlogout.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnlogoutActionPerformed(evt);
                        }
                });

                btnRefresh.setText("Refresh");
                btnRefresh.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnlRefreshActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout functionBoxLayout = new javax.swing.GroupLayout(functionBox);
                functionBox.setLayout(functionBoxLayout);
                functionBoxLayout.setHorizontalGroup(
                                functionBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                functionBoxLayout.createSequentialGroup()
                                                                                .addGap(16, 16, 16)
                                                                                .addComponent(btnRefresh)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(btnlogout)
                                                                                .addContainerGap(16, Short.MAX_VALUE)));
                functionBoxLayout.setVerticalGroup(
                                functionBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(functionBoxLayout.createSequentialGroup()
                                                                .addGap(17, 17, 17)
                                                                .addGroup(functionBoxLayout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(btnlogout)
                                                                                .addComponent(btnRefresh))
                                                                .addContainerGap(20, Short.MAX_VALUE)));

                // Friend Panel
                friendPanel.setBackground(new java.awt.Color(204, 255, 255));

                tFriends.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                tFriends.setText("FRIENDS");

                lFriends.setModel(new AbstractListModel() {
                        String[] strings = {};

                        public int getSize() {
                                return strings.length;
                        }

                        public String getElementAt(int i) {
                                return strings[i];
                        }
                });
                scrollFriend.setViewportView(lFriends);
                lFriends.addListSelectionListener(new ListSelectionListener() {
                        public void valueChanged(ListSelectionEvent evt) {
                                getValueChangedFriend(evt);
                        }
                });

                javax.swing.GroupLayout friendPanelLayout = new javax.swing.GroupLayout(friendPanel);
                friendPanel.setLayout(friendPanelLayout);
                friendPanelLayout.setHorizontalGroup(
                                friendPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(friendPanelLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(
                                                                                friendPanelLayout.createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                .addComponent(scrollFriend)
                                                                                                .addGroup(friendPanelLayout
                                                                                                                .createSequentialGroup()
                                                                                                                .addComponent(tFriends)
                                                                                                                .addGap(0, 0, Short.MAX_VALUE)))
                                                                .addContainerGap()));
                friendPanelLayout.setVerticalGroup(
                                friendPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(friendPanelLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(tFriends)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(scrollFriend,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                393,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));

                javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
                background.setLayout(backgroundLayout);
                backgroundLayout.setHorizontalGroup(
                                backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout
                                                                .createSequentialGroup()
                                                                .addComponent(onlinePanel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(messagePanel,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(backgroundLayout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                false)
                                                                                .addComponent(friendPanel,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(functionBox,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE))));
                backgroundLayout.setVerticalGroup(
                                backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(messagePanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(onlinePanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout
                                                                .createSequentialGroup()
                                                                .addComponent(friendPanel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(functionBox,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

                pack();
        }

        /**
         * @param args the command line arguments
         */
        public static void main(String args[]) {
                java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                                // new chatUI(new Socket(), "192.168.1.1", 7777, "WindyFeng").setVisible(true);
                        }
                });
        }

        public static void FriendRequest(String requestName) {
                requestUI = new friend_requestUI(requestName);
        }

        public void UpdateOnlineListUI(String[] friendList) {
                lOnline.setModel(itemInl);
                itemInl.clear();
                for (int i = 0; i < friendList.length; i++) {
                        itemInl.addElement(friendList[i]);
                }
        }

        private void btnlRefreshActionPerformed(ActionEvent evt) {
                System.out.println("Refresh");

                // call server to get latest online list
                chatHandle.refreshOnlineList();

                // get friendList from chatHandle
                String[] friendList = chatHandle.getOnlineList();
                if (friendList != null) {
                        UpdateOnlineListUI(friendList);
                }
        }

        private void btnSendActionPerformed(ActionEvent evt) {
        }

        private void btnlogoutActionPerformed(ActionEvent evt) {
                chatHandle.logout();
                System.out.println("logged out!");
        }

        public void getValueChangedFriend(ListSelectionEvent evt) {
                System.out.println((String) lFriends.getSelectedValue());
        }

        public void getValuedOnline(ListSelectionEvent evt) {
                String peerSelect = (String) lOnline.getSelectedValue();
                System.out.println(peerSelect);
                // Check select
                if (chatHandle.isMypeer(peerSelect)) {
                        System.out.println("Error: You can not chat with yourself!");
                }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
                if (e.getSource() == lFriends) {
                        System.out.println("click");
                }
        }
}
