import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.crypto.spec.PBEKeySpec;
import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import peer.peer;

// ww w  . ja  va 2s . co m
public class test {
  JList jList1 = new JList();
  JPanel jPanel1 = new JPanel();
  JTextField jTextField2 = new JTextField();
  JFrame frame = new JFrame();

  // public test() {
  // frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  // // jList1.setModel(new AbstractListModel() {
  // // String[] strings = { "Computer", "Mouse", "HDD" };
  // // public int getSize() {
  // // return strings.length;
  // // }
  // // public Object getElementAt(int i) {
  // // return strings[i];
  // // }
  // // });
  // jList1.addListSelectionListener(new ListSelectionListener() {
  // public void valueChanged(ListSelectionEvent evt) {
  // jList1ValueChanged(evt);
  // }
  // });
  // jTextField2.setText("jTextField1");
  // jPanel1.add(new JScrollPane(jList1));
  // jPanel1.add(jTextField2);
  // frame.add(jPanel1);
  // frame.pack();
  // frame.setVisible(true);
  // }

  // private void jList1ValueChanged(ListSelectionEvent evt) {
  // if (!jList1.getValueIsAdjusting()) {
  // jTextField2.setText((String) jList1.getSelectedValue());
  // }
  // }

  public static JFrame createFame(String fileName, byte[] fileData, String fileExtension) {
    JFrame frame = new JFrame("File Downloader");
    frame.setSize(400, 400);

    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    JLabel title = new JLabel("File Downloader");
    title.setAlignmentX(Component.CENTER_ALIGNMENT);
    title.setFont(new Font("Arial", Font.PLAIN, 25));
    title.setBorder(new EmptyBorder(20, 0, 10, 0));

    JLabel prompt = new JLabel("Do you want to download " + fileName + " ?");
    prompt.setAlignmentX(Component.CENTER_ALIGNMENT);
    prompt.setFont(new Font("Arial", Font.PLAIN, 20));
    prompt.setBorder(new EmptyBorder(20, 0, 10, 0));

    JButton btnYes = new JButton("Yes");
    btnYes.setPreferredSize(new Dimension(150, 75));
    btnYes.setFont(new Font("Arial", Font.PLAIN, 20));

    JButton btnNo = new JButton("No");
    btnNo.setPreferredSize(new Dimension(150, 75));
    btnNo.setFont(new Font("Arial", Font.PLAIN, 20));

    JLabel fileContent = new JLabel();
    fileContent.setAlignmentX(Component.CENTER_ALIGNMENT);

    JPanel button = new JPanel();
    button.setBorder(new EmptyBorder(20, 0, 10, 0));
    button.add(btnYes, btnNo);

    if (fileExtension.equalsIgnoreCase("txt")) {
      fileContent.setText("<html>" + new String(fileData) + "</html>");
    } else {
      fileContent.setIcon(new ImageIcon(fileData));
    }

    // When you gotdamn download the file :))
    btnYes.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        System.out.println("yup");

      }

    });

    // close frame

    panel.add(title);
    panel.add(prompt);
    panel.add(fileContent);
    panel.add(button);
    frame.add(panel);

    return frame;
  }

  public static void main(String args[]) {
    showFile();
  }

  public static void showFile() {
    JFrame frame = new JFrame("Frame name");
    frame.setSize(400, 400);
    frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    JScrollPane scrollPane = new JScrollPane(panel);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    frame.add(scrollPane);

    // JLabel title = new JLabel("HELLO");
    // title.setFont(new Font("Arial", Font.PLAIN, 25));
    // title.setBorder(new EmptyBorder(20, 0, 10, 0));
    // title.setAlignmentX(Component.CENTER_ALIGNMENT);
    // frame.add(title);

    JPanel row = new JPanel();
    row.setLayout(new BoxLayout(row, BoxLayout.Y_AXIS));
    row.setName("name");

    JLabel namef = new JLabel();
    namef.setFont(new Font("Arial", Font.PLAIN, 25));
    namef.setBorder(new EmptyBorder(10, 0, 10, 0));
    namef.setText("fuanfnsjl");

    row.add(namef);
    panel.add(row);
    frame.validate();
  }

  // public static void Add(String name, JPanel panel, JFrame frame) {
  // JPanel row = new JPanel();
  // row.setLayout(new BoxLayout(row, BoxLayout.Y_AXIS));
  // row.setName(name);

  // JLabel namef = new JLabel();
  // namef.setFont(new Font("Arial", Font.PLAIN, 25));
  // namef.setBorder(new EmptyBorder(10, 0, 10, 0));

  // row.add(namef);
  // panel.add(row);
  // frame.validate();
  // }
}

// String res = "";
// // if(onlineList.length == 0)
// // {
// // return ;
// // }
// for (String name : onlineList) {
// res+= name + "/";
// }
// // cut last /
// res = res.substring(0, res.length() - 1);
// return res.split("/");