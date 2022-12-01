import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JLabel;

import chat.chatUI;
import protocols.tag;

/**
 *
 * @author USER
 */
public class loginUI extends javax.swing.JFrame {

        public loginUI() {
                initComponents();
        }

        @SuppressWarnings("unchecked")

        private void initComponents() {

                background = new javax.swing.JPanel();
                jPanel1 = new javax.swing.JPanel();
                tTitle = new JLabel();
                tError = new JLabel();
                jPanel2 = new javax.swing.JPanel();
                tUsername = new javax.swing.JLabel();
                iUsername = new javax.swing.JTextField();
                tPort = new javax.swing.JLabel();
                iPort = new javax.swing.JTextField();
                tAddress = new javax.swing.JLabel();
                iAddress = new javax.swing.JTextField();
                btnLogin = new javax.swing.JButton();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                setPreferredSize(new java.awt.Dimension(400, 420));

                background.setBackground(new java.awt.Color(204, 255, 255));

                jPanel1.setBackground(new java.awt.Color(204, 255, 255));

                tTitle.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
                tTitle.setText("WASSAP");

                tError.setFont(new java.awt.Font("Segoe UI", 0, 20));
                tError.setText("Please chose a different name");

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(30, 30, 30)
                                                                .addComponent(tTitle,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                203,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                jPanel1Layout.createSequentialGroup()
                                                                                .addContainerGap(16, Short.MAX_VALUE)
                                                                                .addComponent(tTitle)
                                                                                .addGap(15, 15, 15)));

                jPanel2.setBackground(new java.awt.Color(204, 255, 255));

                tUsername.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                tUsername.setText("USERNAME");

                int randomUserNumber = (int) Math.floor(Math.random() * (99 - 0 + 1) + 0);
                iUsername.setText("User" + randomUserNumber);

                tPort.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                tPort.setText("Port");

                iPort.setText("7777");

                tAddress.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                tAddress.setText("IP Address");

                iAddress.setText("127.0.0.1");

                btnLogin.setText("Login");
                btnLogin.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                try {
                                        ConnectButtonActionPerformed(evt);
                                } catch (UnknownHostException e) {
                                        e.printStackTrace();
                                } catch (IOException e) {
                                        e.printStackTrace();
                                } catch (ClassNotFoundException e) {
                                        e.printStackTrace();
                                }
                        }
                });

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGap(58, 58, 58)
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(btnLogin)
                                                                                .addGroup(jPanel2Layout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                false)
                                                                                                .addGroup(jPanel2Layout
                                                                                                                .createSequentialGroup()
                                                                                                                .addComponent(tUsername,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                92,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addPreferredGap(
                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                .addComponent(iUsername,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                200,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGroup(jPanel2Layout
                                                                                                                .createSequentialGroup()
                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                .createParallelGroup(
                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                                false)
                                                                                                                                .addComponent(tPort,
                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                .addComponent(tAddress,
                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                77,
                                                                                                                                                Short.MAX_VALUE))
                                                                                                                .addGap(18, 18, 18)
                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                .createParallelGroup(
                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                .addComponent(iPort)
                                                                                                                                .addComponent(iAddress)))))
                                                                .addContainerGap(44, Short.MAX_VALUE)));
                jPanel2Layout.setVerticalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGap(82, 82, 82)
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(tUsername,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                32,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(iUsername,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(tPort)
                                                                                .addComponent(iPort,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(tAddress)
                                                                                .addComponent(iAddress,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(38, 38, 38)
                                                                .addComponent(btnLogin)
                                                                .addContainerGap(28, Short.MAX_VALUE)));

                javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
                background.setLayout(backgroundLayout);
                backgroundLayout.setHorizontalGroup(
                                backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
                backgroundLayout.setVerticalGroup(
                                backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(backgroundLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jPanel1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jPanel2,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(background, javax.swing.GroupLayout.Alignment.TRAILING,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                Short.MAX_VALUE));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE));

                pack();
        }

        /**
         * @param args the command line arguments
         */
        public static void main(String args[]) {
                try {
                        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
                                        .getInstalledLookAndFeels()) {
                                if ("Nimbus".equals(info.getName())) {
                                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                                        break;
                                }
                        }
                } catch (ClassNotFoundException ex) {
                        java.util.logging.Logger.getLogger(loginUI.class.getName()).log(java.util.logging.Level.SEVERE,
                                        null, ex);
                } catch (InstantiationException ex) {
                        java.util.logging.Logger.getLogger(loginUI.class.getName()).log(java.util.logging.Level.SEVERE,
                                        null, ex);
                } catch (IllegalAccessException ex) {
                        java.util.logging.Logger.getLogger(loginUI.class.getName()).log(java.util.logging.Level.SEVERE,
                                        null, ex);
                } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                        java.util.logging.Logger.getLogger(loginUI.class.getName()).log(java.util.logging.Level.SEVERE,
                                        null, ex);
                }

                /* Create and display the form */
                java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                                new loginUI().setVisible(true);
                        }
                });

        }

        private void ConnectButtonActionPerformed(java.awt.event.ActionEvent evt)
                        throws UnknownHostException, IOException, ClassNotFoundException {
                // Get value from UI
                String serverIp = iAddress.getText();
                int serverPort = Integer.parseInt(iPort.getText());
                String username = iUsername.getText();

                // Connect to the server Create server a socket
                Socket server = new Socket(serverIp, serverPort);
                ObjectOutputStream serverOut = new ObjectOutputStream(server.getOutputStream());
                serverOut.writeObject(username);

                ObjectInputStream serverIn = new ObjectInputStream(server.getInputStream());
                String checkName = (String) serverIn.readObject();

                // CheckName
                if (!checkName.equals(tag.NAME_VALID)) {
                        System.out.println("try a different name!");
                }

                // Show Chat UI
                ChatUI = new chatUI(server, serverIn, serverOut, username);
                ChatUI.setVisible(true);

                // Close login tab
                this.dispose();
                this.setVisible(false);
        }

        // Variable
        private chatUI ChatUI;

        // UI Variable
        private javax.swing.JPanel background;
        private javax.swing.JButton btnLogin;
        private javax.swing.JLabel tTitle;
        private javax.swing.JLabel tError;
        private javax.swing.JLabel tUsername;
        private javax.swing.JLabel tPort;
        private javax.swing.JLabel tAddress;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JTextField iUsername;
        private javax.swing.JTextField iPort;
        private javax.swing.JTextField iAddress;
        // End of variables declaration//GEN-END:variables
}
