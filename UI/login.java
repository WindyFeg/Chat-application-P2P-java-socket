import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.*;  


public class login implements ActionListener {

    private static JLabel userLabel;
    private static JTextField userText;
    private static JLabel passwordLabel;
    private static JPasswordField passwordText;
    private static JButton button;
    private static JLabel success;

    public static void main(String[] args){
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.add(panel);

        panel.setLayout(null);
        
        // Tag name account
        userLabel = new JLabel("Account name");
        userLabel.setBounds(10,20,80, 25);
        panel.add(userLabel);
        
        // Create the box of input account name 
        userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);
        
        // Tag name password
        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 50, 80, 35);
        panel.add(passwordLabel);
        
        // Box for password
        passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);

        // Login button
        button = new JButton("Login");
        button.setBounds (10, 80, 80, 25);
        button.addActionListener(new login());
        panel.add(button);


        success = new JLabel("");
        success.setBounds(10, 110, 300, 25);
        panel.add(success);
        
        frame.setVisible(true);

        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        String user = userText.getText();  
        String password = passwordText.getText();
        System.out.println(user + ", " + password);

        if (user.equals("thisismessup") && password.equals("lol")){
            success.setText("Login successful!");
        }
        else{
            success.setText("");
        }
    }
}
