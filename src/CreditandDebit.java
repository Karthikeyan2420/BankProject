import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CreditandDebit extends JDialog {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton BACKButton;
    private JPanel cdpan;
    private JButton CREDITButton;
    private JButton DEBITButton;

    public CreditandDebit(JFrame parent , int a ) throws SQLException {
        super(parent);
        setTitle("Customer Page");
        setContentPane(cdpan);
        setMinimumSize(new Dimension(500, 304));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        int i= a;
        Connection con= ServerConnection.getConnection();
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery("select * from account where AccountNo="+a);
        //DefaultTableModel model = new DefaultTableModel(new String[]{"Name", "id"}, 0);
        while(rs.next()){
            textField1.setText(rs.getString("balance")); textField1.enable(false);

        }



        CREDITButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float j= Float.parseFloat(textField2.getText());
                try {
                    CREDIT(j,a);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        DEBITButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float j= Float.parseFloat(textField3.getText());
                try {
                    Debit(j,a);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        BACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Home(null);
            }
        });
        setVisible(true);
    }

    private void Debit(float j, int a) throws SQLException {
        float k= Float.parseFloat(textField1.getText());
        float c=k-j;
        textField1.setText(String.valueOf(c));
        Connection con= ServerConnection.getConnection();
        Statement st=con.createStatement();
        st.executeUpdate("update account set balance='"+String.valueOf(c)+"' where customerid="+a);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        st.executeUpdate("insert into transactio(AccountNo,transactiondate,transaction1,transactionamount,balance) values("+a+",'"+dtf.format(now)+"','Debit','"+String.valueOf(j)+"','"+String.valueOf(c)+"')");

        JOptionPane.showMessageDialog(this,"Your Data is Updated Successfully Debit "+j+" Balance"+c,"Updated Successfully",JOptionPane.PLAIN_MESSAGE);

    }

    private void CREDIT(float a,int i) throws SQLException {
        float j= Float.parseFloat(textField1.getText());
        float c=j+a;
        textField1.setText(String.valueOf(c));
        Connection con= ServerConnection.getConnection();
        Statement st=con.createStatement();
        st.executeUpdate("update account set balance='"+String.valueOf(c)+"' where customerid="+i);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        st.executeUpdate("insert into transactio(AccountNo,transactiondate,transaction1,transactionamount,balance) values("+i+",'"+dtf.format(now)+"','Credit','"+String.valueOf(a)+"','"+String.valueOf(c)+"')");

        JOptionPane.showMessageDialog(this,"Your Data is Updated Successfully Credit "+a+" Balance"+c,"Updated Successfully",JOptionPane.PLAIN_MESSAGE);

    }

    public static void main(String[] args) throws SQLException {
        new CreditandDebit(null,84658945);
    }
}
