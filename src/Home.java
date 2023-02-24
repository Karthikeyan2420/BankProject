import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JDialog{
    private JButton clickHereToAdminButton;
    private JButton clickHereToCustomerButton;
    private JButton registrationNowButton;
    private JPanel hpan;
    public Home(JFrame parent) {
        super(parent);
        setTitle("Bank Home");
        setContentPane(hpan);
        setMinimumSize(new Dimension(1250, 504));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        clickHereToAdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminLogin(null);

            }
        });
        clickHereToCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CustomerLogin(null);

            }
        });
        registrationNowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegistrationForm(null);

            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        Home h=new Home(null);
    }
}
