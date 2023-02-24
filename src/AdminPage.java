import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AdminPage extends JDialog{
    private JButton SHOWALLDATAButton;
    private JButton PARTICULARDATAButton;
    private JButton HOMEButton;
    private JPanel apaan;
    private JButton TRANSACTIONHISTORYButton;

    public AdminPage(JFrame parent){
        super(parent);
        setTitle("Admin Page");
        setContentPane(apaan);
        setMinimumSize(new Dimension(500, 404));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        SHOWALLDATAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Printing(null);

            }
        });
        PARTICULARDATAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ParticularSearch(null);

            }
        });

        HOMEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Home(null);
                setVisible(false);
            }
        });
        TRANSACTIONHISTORYButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Transaction(null);
                    setVisible(false);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        AdminPage a=new AdminPage(null);
    }
}
