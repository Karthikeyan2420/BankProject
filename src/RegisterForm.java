import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RegisterForm extends JDialog {
    private JPanel registerpanel;
    private JButton button1;
    public JTable table1;
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox comboBox1;

    public RegisterForm() {
    }


    public RegisterForm(JFrame parent) {
        super(parent);
        setTitle("Bank Customer Details");
        setContentPane(registerpanel);
        setMinimumSize(new Dimension(300, 304));
        setModal(true);
        setLocationRelativeTo(parent);


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ParticularSearch ps =  new ParticularSearch(null);
                ps.setVisible(false);
                ps.msg(textField1.getText());
               ps.setVisible(true);
               setVisible(false);

            }


        });

        setVisible(true);
    }

    public void method() throws SQLException {
        int i= Integer.parseInt(textField1.getText());
        String te=textField2.getText();
        /*String t=comboBox1.getSelectedItem().toString();
        textField1.setText("0");*/
        Connection con= ServerConnection.getConnection();
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery("select * from customer where cid="+i+" or cname='"+te+"'");
        DefaultTableModel model = new DefaultTableModel(new String[]{"Name", "id"}, 0);
        while(rs.next()){
            String d=rs.getString("cname");
            String g=rs.getString("cid");
            model.addRow(new Object[]{d,g});
        }
        table1.setModel(model);


}
    public static void main(String[] args) {

        RegisterForm my=new RegisterForm(null);

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
