package panels;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;

public class Authenticate extends JPanel implements ActionListener {
    private static final Color BACKGROUND_COLOR = Color.WHITE;
    private static final Color TEXT_COLOR = Color.BLACK;
    private static final Color BUTTON_COLOR = new Color(243, 156, 18); // Orange shade
    private static final Color BORDER_COLOR = new Color(230, 126, 34); // Orange shade

    Font f = new Font("Arial", Font.BOLD, 15);
    JTextField t2 = new JTextField();
    JTextField t3 = new JTextField();
    JTextField t4 = new JTextField();
    JLabel l1 = new JLabel("USER VERIFICATION", SwingConstants.CENTER);
    JLabel l2 = new JLabel("Customer Name", SwingConstants.RIGHT);
    JLabel l3 = new JLabel("Account No", SwingConstants.RIGHT);
    JLabel l4 = new JLabel("PanCard No", SwingConstants.RIGHT);
    JButton x1 = new JButton("Verify");
    JButton x2 = new JButton("Refresh");
    JButton x3 = new JButton("Close");

    public Authenticate() {
        setLayout(null);
        setBackground(Color.WHITE);
        setBounds(0, 140, 600, 550);

        l1.setFont(f);
        l1.setForeground(TEXT_COLOR);
        l2.setFont(f);
        l2.setForeground(TEXT_COLOR);
        l3.setFont(f);
        l3.setForeground(TEXT_COLOR);
        l4.setFont(f);
        l4.setForeground(TEXT_COLOR);

        t2.setFont(f);
        t3.setFont(f);
        t4.setFont(f);

        l1.setBounds(0, 50, 500, 30);
        l2.setBounds(0, 100, 235, 30);
        t2.setBounds(250, 100, 150, 30);
        l3.setBounds(0, 160, 235, 30);
        t3.setBounds(250, 160, 150, 30);
        l4.setBounds(0, 220, 235, 30);
        t4.setBounds(250, 220, 150, 30);
        x1.setBounds(70, 280, 150, 30);
        x2.setBounds(250, 280, 150, 30);
        x3.setBounds(160, 340, 150, 30);

        x1.setFont(f);
        x1.setBackground(BUTTON_COLOR);
        x1.setForeground(TEXT_COLOR);
        x2.setFont(f);
        x2.setBackground(BUTTON_COLOR);
        x2.setForeground(TEXT_COLOR);
        x3.setFont(f);
        x3.setBackground(new Color(192, 57, 43)); // Darker shade of red
        x3.setForeground(TEXT_COLOR);

        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(t2);
        add(t3);
        add(t4);
        add(x1);
        add(x2);
        add(x3);

        // Centering components
        centerComponents();

        x1.addActionListener(this);
        x2.addActionListener(this);
        x3.addActionListener(this);
    }

    private void centerComponents() {
        Dimension size = getPreferredSize();
        int centerX = size.width / 2;
        
        l1.setBounds(centerX - 250, 50, 500, 30);
        l2.setBounds(centerX - 235, 100, 235, 30);
        t2.setBounds(centerX + 15, 100, 150, 30);
        l3.setBounds(centerX - 235, 160, 235, 30);
        t3.setBounds(centerX + 15, 160, 150, 30);
        l4.setBounds(centerX - 235, 220, 235, 30);
        t4.setBounds(centerX + 15, 220, 150, 30);
        x1.setBounds(centerX - 80, 280, 150, 30);
        x2.setBounds(centerX + 100, 280, 150, 30);
        x3.setBounds(centerX - 10, 340, 150, 30);
    }

    public void actionPerformed(ActionEvent k1) {
        if (k1.getSource() == x1) {
            int flag = 0;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root",
                        "arjav1234");

                try {
                    long account = Long.parseLong(t3.getText());
                    String pan = t4.getText().toString();
                    String cname = t2.getText().toString();

                    String sqlretrieve = "SELECT * FROM internship_bank WHERE accountno=? AND pancard_no=?";
                    PreparedStatement ps = con.prepareStatement(sqlretrieve);
                    ps.setLong(1, account);
                    ps.setString(2, pan);

                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        if ((rs.getString(1).equals(cname) && rs.getLong(2) == account && rs.getString(3).equals(pan))) {
                            flag = 1;
                            break;
                        }
                    }
                    if (flag == 1) {
                        JOptionPane.showMessageDialog(null, "User is Authenticated", "INFORMATION",
                                JOptionPane.INFORMATION_MESSAGE);
                        repaint();
                    } else {
                        JOptionPane.showMessageDialog(null, "Create Account", "INFORMATION",
                                JOptionPane.INFORMATION_MESSAGE);
                        repaint();
                    }

                } catch (Exception k) {
                    JOptionPane.showMessageDialog(null, "All fields are required", "WARNING",
                            JOptionPane.WARNING_MESSAGE);
                    repaint();
                }
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, e1, "WARNING", JOptionPane.WARNING_MESSAGE);
                repaint();
            }

        }

        if (k1.getSource() == x2) {
            t2.setText(null);
            t3.setText(null);
            t4.setText(null);
            JOptionPane.showMessageDialog(null, "Fields refreshed", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);

            repaint();
        }
        if (k1.getSource() == x3) {
            this.setVisible(false);
            repaint();
        }
    }
}
