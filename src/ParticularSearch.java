import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ParticularSearch extends JDialog {
    public JTextField textField1;
    public JTextField textField2;
    private JButton searchButton;
    private JTable table1;
    private JPanel parpanel;

    public ParticularSearch(JFrame parent) {
        super(parent);
        setTitle("Search");
        setContentPane(parpanel);
        setMinimumSize(new Dimension(900, 604));
        setModal(true);
        setLocationRelativeTo(parent);
        textField1.setText("0");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    psearch();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        setVisible(true);
    }

    public void msg(String a){
        textField2.setText(a);
    }
    private void psearch() throws SQLException {
        int i= Integer.parseInt(textField1.getText());
        String te=textField2.getText();
        Connection con= ServerConnection.getConnection();
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery("select * from customer where customerid="+i+" or customername='"+te+"'");
        DefaultTableModel model = new DefaultTableModel(new String[]{"Name", "id"}, 0);
        while(rs.next()){
            String d=rs.getString("customername");
            String g=rs.getString("customerid");
            model.addRow(new Object[]{d,g});
        }
        table1.setModel(model);
    }

    public static void main(String[] args) {
        new ParticularSearch(null);
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
