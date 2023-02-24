import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Transaction extends JDialog{
    private JPanel tpan;
    private JTable table1;
    private JButton homeButton;

    public Transaction(JFrame parent, int a) throws SQLException {
        super(parent);
        setTitle("Customer Page");
        setContentPane(tpan);
        setMinimumSize(new Dimension(600, 404));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Connection con= ServerConnection.getConnection();
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery("select * from transactio where AccountNo="+a);
        DefaultTableModel model = new DefaultTableModel(new String[]{"AccountNo","Date and Time", "Transaction","Transaction amount","Balance"}, 0);
        while(rs.next()){
            String id=rs.getString("accountno");
            String name=rs.getString("transactiondate");
            String accn=rs.getString("transaction1");
            String open=rs.getString("transactionamount");

            String balance=rs.getString("balance");
            model.addRow(new Object[]{id,name,accn,open,balance});
        }
        table1.setModel(model);
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new CustomerPage(null,a);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        setVisible(true);

    }

    public Transaction(JFrame parent) throws SQLException {
        super(parent);
        setTitle("Customer Page");
        setContentPane(tpan);
        setMinimumSize(new Dimension(600, 404));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Connection con= ServerConnection.getConnection();
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery("select * from transactio ");
        DefaultTableModel model = new DefaultTableModel(new String[]{"Id","Date and Time", "Transaction","Transaction amount","Balance"}, 0);
        while(rs.next()){
            String id=rs.getString("accountno");
            String name=rs.getString("transactiondate");
            String accn=rs.getString("transaction1");
            String open=rs.getString("transactionamount");

            String balance=rs.getString("balance");
            model.addRow(new Object[]{id,name,accn,open,balance});
        }
        table1.setModel(model);
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        setVisible(true);
    }

    public static void main(String[] args) throws SQLException {
        //new Transaction(null,84658945);
       //new Transaction(null);
    }
}
