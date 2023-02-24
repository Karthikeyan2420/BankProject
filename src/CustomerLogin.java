import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerLogin extends JDialog{
    private JTextField textField1;
    private JButton loginButton;
    private JButton cancelButton;
    private JPanel clogp;
    private JPasswordField passwordField1;

    public CustomerLogin(JFrame parent) {
        super(parent);
        setTitle("Customer Login");
        setContentPane(clogp);
        setMinimumSize(new Dimension(500, 404));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    chlogin();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);
            }
        });
        setVisible(true);
    }

    private void chlogin() throws SQLException {
        int i= Integer.parseInt(textField1.getText());
        int ii=0;
        String te=String.valueOf(passwordField1.getPassword());
        Connection con= ServerConnection.getConnection();
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery("select * from customer where customerid="+i+" and phone='"+te+"'");
        while(rs.next()){
            ii++;
        }
        if(ii==0){
            JOptionPane.showMessageDialog(this,"Wrong id or password","Try again",JOptionPane.ERROR_MESSAGE);

        } else if (ii>0) {
            int text= Integer.parseInt(textField1.getText());
            CustomerPage c=new CustomerPage(null,text);
            setVisible(false);

        }
    }

    public static void main(String[] args) {
        CustomerLogin c=new CustomerLogin(null);
    }
}
