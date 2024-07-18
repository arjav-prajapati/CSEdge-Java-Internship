package panels;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.*;

public class ResetPassword extends JPanel implements ActionListener {
    private static final Color BACKGROUND_COLOR = Color.WHITE;
    private static final Color TEXT_COLOR = Color.BLACK;
    private static final Color BUTTON_COLOR = new Color(255, 87, 34); // Vibrant orange
    private static final Color BUTTON_HOVER_COLOR = new Color(255, 143, 0); // Lighter orange for hover

    Font f = new Font("Arial", Font.BOLD, 15);
    JLabel l1 = new JLabel("RESET PASSWORD", SwingConstants.CENTER);
    JLabel l2 = new JLabel("Account No", SwingConstants.RIGHT);
    JLabel l3 = new JLabel("New Password", SwingConstants.RIGHT);
    JLabel l4 = new JLabel("Confirm Password", SwingConstants.RIGHT);

    JTextField t1 = new JTextField();
    JPasswordField t2 = new JPasswordField();
    JPasswordField t3 = new JPasswordField();
    JButton b1 = new JButton("Reset");
    JButton b2 = new JButton("Close");

    public ResetPassword() {
        setLayout(new GridBagLayout());
        setBackground(BACKGROUND_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        l1.setFont(f);
        l1.setForeground(BUTTON_COLOR);
        l2.setFont(f);
        l2.setForeground(TEXT_COLOR);
        l3.setFont(f);
        l3.setForeground(TEXT_COLOR);
        l4.setFont(f);
        l4.setForeground(TEXT_COLOR);

        t1.setFont(f);
        t1.setPreferredSize(new Dimension(250, 30)); // Increased width
        t2.setFont(f);
        t2.setPreferredSize(new Dimension(250, 30)); // Increased width
        t3.setFont(f);
        t3.setPreferredSize(new Dimension(250, 30)); // Increased width

        b1.setFont(f);
        b1.setBackground(BUTTON_COLOR);
        b1.setForeground(Color.WHITE);
        b1.setFocusPainted(false);
        b1.setBorderPainted(false);
        b1.setOpaque(true);

        b2.setFont(f);
        b2.setBackground(new Color(229, 57, 53)); // Red for close
        b2.setForeground(Color.WHITE);
        b2.setFocusPainted(false);
        b2.setBorderPainted(false);
        b2.setOpaque(true);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(l1, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.gridx = 0;
        add(l2, gbc);
        gbc.gridx = 1;
        add(t1, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(l3, gbc);
        gbc.gridx = 1;
        add(t2, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(l4, gbc);
        gbc.gridx = 1;
        add(t3, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(b1, gbc);
        gbc.gridx = 1;
        add(b2, gbc);

        b1.addActionListener(this);
        b2.addActionListener(this);
    }

    public void actionPerformed(ActionEvent k) {
        if (k.getSource() == b1) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "arjav1234");
                try {
                    long accountNo = Long.parseLong(t1.getText());
                    String newPassword = new String(t2.getPassword());
                    String confirmPassword = new String(t3.getPassword());

                    if (!newPassword.equals(confirmPassword)) {
                        JOptionPane.showMessageDialog(null, "Passwords do not match", "WARNING", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    String sql = "UPDATE internship_bank SET password=? WHERE accountno=?";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setString(1, newPassword);
                    ps.setLong(2, accountNo);

                    int rowsUpdated = ps.executeUpdate();
                    if (rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(null, "Password reset successfully", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Account not found", "WARNING", JOptionPane.WARNING_MESSAGE);
                    }

                    ps.close();
                    con.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Invalid account number or database error", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (k.getSource() == b2) {
            this.setVisible(false);
        }
    }
}
