import javax.swing.AbstractListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
// ww w  . ja  va 2s . co m
public class test {
  JList jList1 = new JList();
  JPanel jPanel1 = new JPanel();
  JTextField jTextField2 = new JTextField();
  JFrame frame = new JFrame();

  public test() {
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    jList1.setModel(new AbstractListModel() {
      String[] strings = { "Computer", "Mouse", "HDD" };
      public int getSize() {
        return strings.length;
      }
      public Object getElementAt(int i) {
        return strings[i];
      }
    });
    jList1.addListSelectionListener(new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent evt) {
        jList1ValueChanged(evt);
      }
    });
    jTextField2.setText("jTextField1");
    jPanel1.add(new JScrollPane(jList1));
    jPanel1.add(jTextField2);
    frame.add(jPanel1);
    frame.pack();
    frame.setVisible(true);
  }
  private void jList1ValueChanged(ListSelectionEvent evt) {
    if (!jList1.getValueIsAdjusting()) {
      jTextField2.setText((String) jList1.getSelectedValue());
    }
  }

  public static void main(String args[]) {
    new test();
  }
}