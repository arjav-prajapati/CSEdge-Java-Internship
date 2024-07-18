package panels;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.*;

public class Deposit extends JPanel implements ActionListener {
    private static final Color BACKGROUND_COLOR = Color.WHITE;
    private static final Color TEXT_COLOR = Color.BLACK;
    private static final Color BUTTON_COLOR = new Color(255, 87, 34); // Vibrant orange
    private static final Color BUTTON_HOVER_COLOR = new Color(255, 143, 0); // Lighter orange for hover

    Font f = new Font("Arial", Font.BOLD, 15);
    JLabel l1 = new JLabel("DEPOSIT MONEY", SwingConstants.CENTER);
    JLabel l2 = new JLabel("Account No", SwingConstants.RIGHT);
    JLabel l3 = new JLabel("Amount", SwingConstants.RIGHT);

    JTextField t1 = new JTextField();
    JTextField t2 = new JTextField();
    JButton b1 = new JButton("Deposit");
    JButton b2 = new JButton("Close");

    public Deposit() {
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

        t1.setFont(f);
        t1.setPreferredSize(new Dimension(250, 30)); // Increased width
        t2.setFont(f);
        t2.setPreferredSize(new Dimension(250, 30)); // Increased width

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
                    double amount = Double.parseDouble(t2.getText());

                    String sql = "UPDATE internship_bank SET balance = balance + ? WHERE accountno=?";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setDouble(1, amount);
                    ps.setLong(2, accountNo);

                    int rowsUpdated = ps.executeUpdate();
                    if (rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(null, "Deposit successful", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Account not found", "WARNING", JOptionPane.WARNING_MESSAGE);
                    }

                    ps.close();
                    con.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Invalid input or database error", "ERROR", JOptionPane.ERROR_MESSAGE);
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
