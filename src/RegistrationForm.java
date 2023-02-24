import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class RegistrationForm extends JDialog {
    private JPanel regipanel;
    private JTextField tid;
    private JTextField tname;
    private JTextField tphno;
    private JTextField temail;
    private JTextField tdob;
    private JTextField tacc;
    private JTextField topda;
    private JTextField tbal;

    private JButton REGISTERButton;
    private JButton CANCELButton;
    private JComboBox taccty;

    private JTextField taadh;
    private JTextField tpdoorNo;
    private JTextField tArea;
    private JTextField tCity;
    private JTextField tPincode;
    private JTextField tcdoorNo;
    private JTextField tcArea;
    private JTextField tcCity;
    private JTextField tcPincode;


    public RegistrationForm(JFrame parent) {
        super(parent);
        setTitle("Registration form");
        setContentPane(regipanel);
        setMinimumSize(new Dimension(900, 704));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        REGISTERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Register();
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        CANCELButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        setVisible(true);
    }

    private void Register() throws ClassNotFoundException, SQLException {

        int id= Integer.parseInt(tid.getText()); String name=tname.getText(); String phoneno=tphno.getText(); String email= temail.getText();
        String aadhar= taadh.getText(); String dob= tdob.getText(); String door=tpdoorNo.getText(); String Area= tArea.getText();
        String City=tCity.getText(); String Pincode=tPincode.getText(); String Door1=tcdoorNo.getText(); String Area1=tcArea.getText();
        String City1=tcCity.getText();String Pincode1=tcPincode.getText();
        String acno=tacc.getText(); String opda=topda.getText(); String acc=taccty.getSelectedItem().toString(); String bal= tbal.getText();

        if(name.isEmpty()||phoneno.isEmpty()||email.isEmpty()||aadhar.isEmpty()||dob.isEmpty()||door.isEmpty()||acno.isEmpty()||opda.isEmpty()||bal.isEmpty()){
            JOptionPane.showMessageDialog(this,"Please enter all field","Try again",JOptionPane.ERROR_MESSAGE);
            //JOptionPane.showMessageDialog(this,"wrong");
           return;
        }
       BankCustomers cus=new BankCustomers(id,name,phoneno,email,aadhar,dob);
        /*if(cadd.isEmpty()){
            Bank_Customers add=new Bank_Customers(id,padd);
        }else {
            Bank_Customers add=new Bank_Customers(id,padd,cadd);
        }*/
        BankCustomers add=new BankCustomers(id,door,Area,City,Pincode,Door1,Area1,City1,Pincode1);

        BankCustomers accd=new BankCustomers(id,acno,opda,acc,bal);

        ServerConnection.getsql();
        ServerConnection.getConnection();
        ServerConnection.createcustomer(cus);
        ServerConnection.createPermanentAddress(add);
        ServerConnection.createCurrentAddress(add);
        ServerConnection.createAcc(accd);
        JOptionPane.showMessageDialog(this,"Your Data is Successfully Saved","Saved Successfully",JOptionPane.PLAIN_MESSAGE);
        tid.setText("");tname.setText("");tphno.setText("");temail.setText("");taadh.setText("");tdob.setText("");tpdoorNo.setText("");tcdoorNo.setText("");
        tacc.setText("");topda.setText("");tbal.setText("");taccty.setSelectedIndex(0);

    }

    public static void main(String[] args) {
        RegistrationForm a=new RegistrationForm(null);
    }
}
