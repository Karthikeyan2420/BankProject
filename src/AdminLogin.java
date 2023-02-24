import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminLogin extends JDialog {
    private JPanel apan;
    private JTextField textField1;
    private JButton LOGINButton;
    private JButton CANCELButton;
    private JPasswordField passwordField1;
    public AdminLogin(JFrame parent){
        super(parent);
        setTitle("Bank Admin Login");
        setContentPane(apan);
        setMinimumSize(new Dimension(400, 304));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        LOGINButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    alogin();
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
    private void alogin() throws SQLException {
        String i= textField1.getText();
        int ii=0;
        String te=String.valueOf(passwordField1.getPassword());
        Connection con= ServerConnection.getConnection();
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery("select * from admin where aname='"+i+"' and pass='"+te+"'");
        while(rs.next()){
            ii++;
        }
        if(ii==0){
            JOptionPane.showMessageDialog(this,"Wrong name or password","Try again",JOptionPane.ERROR_MESSAGE);

        } else if (ii>0) {
            new AdminPage(null);
           setVisible(false);

        }
    }

    public static void main(String[] args) {
        AdminLogin a=new AdminLogin(null);
    }
}
