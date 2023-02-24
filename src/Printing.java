import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Printing extends JDialog{
    private JButton bcus;
    private JButton badd;
    private JButton bacc;
    private JButton ball;
    private JTable table1;
    private JPanel printpanel;

    public Printing(JFrame parent){
        super(parent);
        setTitle("Search");
        setContentPane(printpanel);
        setMinimumSize(new Dimension(900, 604));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        bcus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    printcustomer();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        badd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    printaddress();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        bacc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    printaccount();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        ball.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    printall();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        setVisible(true);
    }

    private void printall() throws SQLException {
        Connection con= ServerConnection.getConnection();
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery("select * from Account,Customer,PermanentAddress,currentAddress where Account.customerid=Customer.customerid and Account.customerid=PermanentAddress.customerid");
        DefaultTableModel model = new DefaultTableModel(new String[]{"Id","Name", "Phone No","Email Id","Aadhar No","Date of Birth","Permanent Address", "Current Address", "Account No","Open Date","Account Type","Balance"}, 0);
        while(rs.next()){
            String id=rs.getString("customerid");
            String name=rs.getString("customername");
            String phno=rs.getString("phone");
            String email=rs.getString("email");
            String aadhar=rs.getString("aadhar");
            String dob=rs.getString("dob");
            String padd=rs.getString("permanentaddress.city");
            String aadd=rs.getString("currentaddress.city");
            String accn=rs.getString("accountno");
            String open=rs.getString("opendate");
            String acctype=rs.getString("accounttype");
            String balance=rs.getString("balance");
            model.addRow(new Object[]{id,name,phno,email,aadhar,dob,padd,aadd,accn,open,acctype,balance});
        }
        table1.setModel(model);
    }

    private void printaccount() throws SQLException {
        Connection con= ServerConnection.getConnection();
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery("select * from Account,Customer where Account.customerid=Customer.customerid");
        DefaultTableModel model = new DefaultTableModel(new String[]{"Id","Name", "Account No","Open Date","Account Type","Balance"}, 0);
        while(rs.next()){
            String id=rs.getString("customerid");
            String name=rs.getString("customername");
            String accn=rs.getString("accountno");
            String open=rs.getString("opendate");
            String acctype=rs.getString("accounttype");
            String balance=rs.getString("balance");
            model.addRow(new Object[]{id,name,accn,open,acctype,balance});
        }
        table1.setModel(model);
    }

    private void printaddress() throws SQLException {
        Connection con= ServerConnection.getConnection();
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery("select * from Customer,permanentAddress,currentaddress where Customer.customerid=permanentAddress.customerid");
        DefaultTableModel model = new DefaultTableModel(new String[]{"Id","Name","Permanent Address", "Current Address"}, 0);
        while(rs.next()){
            String id=rs.getString("customerid");
            String name=rs.getString("customername");
            String padd=rs.getString("permanentAddress.city");
            String cadd=rs.getString("currentaddress.city");

            model.addRow(new Object[]{id,name,padd,cadd});
        }
        table1.setModel(model);
    }


    private void printcustomer() throws SQLException {
        Connection con= ServerConnection.getConnection();
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery("select * from Customer");
        DefaultTableModel model = new DefaultTableModel(new String[]{"Id","Name", "Phone No","Email Id","Aadhar No","Date of Birth"}, 0);
        while(rs.next()){
            String id=rs.getString("customerid");
            String name=rs.getString("customername");
            String phno=rs.getString("phone");
            String email=rs.getString("email");
            String aadhar=rs.getString("aadhar");
            String dob=rs.getString("dob");
            model.addRow(new Object[]{id,name,phno,email,aadhar,dob});
        }
        table1.setModel(model);
    }

    public static void main(String[] args) {
        new Printing(null);

    }
}
