package chat;

import java.net.Socket;
import java.util.Random;
import java.awt.event.*;

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

        public chatUI(Socket server, String serverIp, int serverPort, String username) {
                chatHandle = new chat(server, serverIp, serverPort, username);

                chatHandle.GetFriendFromServer();
                // Send to server peer information
                initComponents(username);
        }

        public void listenOnPeer() {

        }

        @SuppressWarnings("unchecked")

        private void initComponents(String username) {

                background = new javax.swing.JPanel();
                messagePanel = new javax.swing.JPanel();
                jScrollPane3 = new javax.swing.JScrollPane();
                jTextArea1 = new javax.swing.JTextArea();
                jPanel2 = new javax.swing.JPanel();
                jTextField1 = new javax.swing.JTextField();
                btnSend = new javax.swing.JButton();
                btnAdd = new javax.swing.JButton();
                jPanel1 = new javax.swing.JPanel();
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

                jTextArea1.setColumns(20);
                jTextArea1.setRows(5);
                jScrollPane3.setViewportView(jTextArea1);

                jPanel2.setBackground(new java.awt.Color(204, 255, 255));

                jTextField1.setText("jTextField1");

                btnSend.setText("Send");
                btnSend.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnSendActionPerformed(evt);
                        }
                });

                btnAdd.setText("Add");

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jTextField1)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(btnAdd,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                54,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(btnSend,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                49,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap()));
                jPanel2Layout.setVerticalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout
                                                                .createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jTextField1,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                43,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(btnSend)
                                                                                .addComponent(btnAdd))
                                                                .addContainerGap()));

                jPanel1.setBackground(new java.awt.Color(204, 255, 255));

                tUsernname.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                tUsernname.setText(username);

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(tUsernname,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                215,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(207, Short.MAX_VALUE)));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(tUsernname,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                29, Short.MAX_VALUE)));

                javax.swing.GroupLayout messagePanelLayout = new javax.swing.GroupLayout(messagePanel);
                messagePanel.setLayout(messagePanelLayout);
                messagePanelLayout.setHorizontalGroup(
                                messagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
                messagePanelLayout.setVerticalGroup(
                                messagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(messagePanelLayout.createSequentialGroup()
                                                                .addComponent(jPanel1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(jScrollPane3,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                394,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jPanel2,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap()));

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
                                getValueChangedOnline(evt);
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
                /* Create and display the form */
                java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                                new chatUI(new Socket(), "192.168.1.1", 7777, "WindyFeng").setVisible(true);
                        }
                });
        }
        
        public void UpdateOnlineListUI(String[] friendList) {
                lOnline.setModel(itemInl);
                itemInl.clear();
                // for (String string : friendList) {
                //         itemInl.addElement(string);
                // }
                for(int i = 0; i< friendList.length;i++)
                {
                        itemInl.addElement(friendList[i]);
                }
        }

        private void btnlRefreshActionPerformed(ActionEvent evt) {
                System.out.println("Refresh");
                chatHandle.refreshOnlineList();
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

        public void getValueChangedOnline(ListSelectionEvent evt) {
                System.out.println((String) lOnline.getSelectedValue());
        }
        // myVariable
        public chat chatHandle;

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btnAdd;
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
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JScrollPane scrollFriend;
        private javax.swing.JScrollPane scrollOnline;
        private javax.swing.JScrollPane jScrollPane3;
        private javax.swing.JTextArea jTextArea1;
        private javax.swing.JTextField jTextField1;
        private javax.swing.JPanel messagePanel;
        private javax.swing.JPanel onlinePanel;
        private javax.swing.JButton btnSend;
        private DefaultListModel itemInl;

        // End of variables declaration//GEN-END:variables
        @Override
        public void actionPerformed(ActionEvent e) {
                if (e.getSource() == lFriends) {
                        System.out.println("click");
                }
        }
}