import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerPage extends JDialog {
    private JTextField textField1;
    private JTextField textField2;
    private JButton UPDATEButton;
    private JButton SAVEButton;
    private JButton CANCELButton1;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;
    private JTextField textField12;
    private JPanel cuspan;
    private JButton CREDITDEBITEButton;
    private JButton TRANSACTIONHISTORYButton;

    public CustomerPage(JFrame parent, int a) throws SQLException {
        super(parent);
        setTitle("Customer Page");
        setContentPane(cuspan);
        setMinimumSize(new Dimension(600, 504));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        int i= a;
        int accno = 0;
        Connection con= ServerConnection.getConnection();
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery("select * from customer,permanentAddress,currentaddress,account where customer.customerid="+a+" and customer.customerid=permanentAddress.customerid and account.customerid=customer.customerid");
        //DefaultTableModel model = new DefaultTableModel(new String[]{"Name", "id"}, 0);
        while(rs.next()){
            textField1.setText(rs.getString("customer.customerid")); textField1.enable(false);
            textField2.setText(rs.getString("customername"));
            textField3.setText(rs.getString("phone"));
            textField4.setText(rs.getString("aadhar"));
            textField5.setText(rs.getString("email"));
            textField6.setText(rs.getString("dob"));
            textField7.setText(rs.getString("permanentaddress.city"));
            textField8.setText(rs.getString("currentaddress.city"));
            textField9.setText(rs.getString("accountno"));
            textField10.setText(rs.getString("opendate"));
            textField11.setText(rs.getString("accounttype"));
            textField12.setText(rs.getString("balance"));
            int b= Integer.parseInt(rs.getString("accountno"));
            accno=b;
        }

        UPDATEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cupdate(a);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        SAVEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] a={"Customer Id : "+textField1.getText(),"Name : "+textField2.getText(),"Phone No : "+textField3.getText(),"Email : "+textField4.getText(),"Aadhar Card : "+textField5.getText(),
                        "Date Of Birth : "+textField6.getText(),"Permanent city : "+textField7.getText(),"Current city : "+textField8.getText(),
                        "Account No : "+textField9.getText(),"Open Date : "+textField10.getText(),"Account Type : "+textField11.getText(),
                        "Balance : "+textField12.getText()};
                writeToFile(a,textField2.getText());
            }
        });

        CANCELButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomerLogin a=new CustomerLogin(null);
                setVisible(false);
            }
        });

        int finalAccno = accno;
        CREDITDEBITEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new CreditandDebit(null,finalAccno);
                    //setVisible(false);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        TRANSACTIONHISTORYButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println(finalAccno);
                    new Transaction(null, finalAccno);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        setVisible(true);
    }

    public void writeToFile(String[] a,String b) {
        String filePath = "C:\\Users\\Karthikeyan K A\\OneDrive\\Desktop\\sql\\"+b+".txt";
        try {

            //String[] lines = new String[]{"This is first line","This is second line"};
            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(String line:a){
                bufferedWriter.write(line+"\n");
            }
            bufferedWriter.close();
            JOptionPane.showMessageDialog(this,"Your Data is Saved a File "+filePath,"File Saved",JOptionPane.PLAIN_MESSAGE);

        } catch (IOException e) {

            System.out.println("Couldn't write the data to file " + filePath);

        }
    }

    private void cupdate(int a) throws SQLException {
        Connection con= ServerConnection.getConnection();
        Statement st=con.createStatement();
        st.executeUpdate("update customer set cname='"+textField2.getText()+"', phone='"+textField3.getText()+"', email='"+textField4.getText()+"', aadhar='"+textField5.getText()+"', dob='"+textField6.getText()+"' where cid="+a);
        //st.executeUpdate("update address set paddress='"+textField7.getText()+"', raddress='"+textField8.getText()+"' where cid="+a);
        st.executeUpdate("update account set accno='"+textField9.getText()+"', opendate='"+textField10.getText()+"', acctype='"+textField11.getText()+"', balance='"+textField12.getText()+"' where cid="+a);

        JOptionPane.showMessageDialog(this,"Your Data is Updated Successfully Saved","Updated Successfully",JOptionPane.PLAIN_MESSAGE);

    }

    public static void main(String[] args) throws SQLException {
        CustomerPage c=new CustomerPage(null,1001);
    }

}
