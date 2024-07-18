package panels;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.*;

public class UserAccount extends JPanel implements ActionListener {
    private static final Color BACKGROUND_COLOR = Color.WHITE;
    private static final Color TEXT_COLOR = Color.BLACK;
    private static final Color BUTTON_COLOR = new Color(243, 156, 18); // Orange shade
    private static final Color BORDER_COLOR = new Color(230, 126, 34); // Darker orange shade

    Font f = new Font("Arial", Font.BOLD, 15);
    JTextField t2 = new JTextField();
    JTextField t3 = new JTextField();
    JTextField t4 = new JTextField();
    JTextField t5 = new JTextField();
    JLabel l1 = new JLabel("ACCOUNT OPENING", SwingConstants.CENTER);
    JLabel l2 = new JLabel("Customer Name", SwingConstants.RIGHT);
    JLabel l3 = new JLabel("Account No", SwingConstants.RIGHT);
    JLabel l4 = new JLabel("PanCard No", SwingConstants.RIGHT);
    JLabel l5 = new JLabel("Gender:", SwingConstants.RIGHT);
    JLabel l6 = new JLabel("Branch City", SwingConstants.RIGHT);
    JLabel l7 = new JLabel("Password", SwingConstants.RIGHT);
    JButton x1 = new JButton("Create");
    JButton x2 = new JButton("Refresh");
    JButton x3 = new JButton("Close");
    JComboBox<String> c1 = new JComboBox<>();

    JRadioButton b1 = new JRadioButton("Male");
    JRadioButton b2 = new JRadioButton("Female");
    ButtonGroup group = new ButtonGroup();

    public UserAccount() {
        setLayout(null);
        setBackground(BACKGROUND_COLOR);
        setBounds(0, 140, 600, 550);

        l1.setFont(new Font("Arial", Font.BOLD, 20));
        l1.setForeground(TEXT_COLOR);
        l2.setFont(f);
        l2.setForeground(TEXT_COLOR);
        l3.setFont(f);
        l3.setForeground(TEXT_COLOR);
        l4.setFont(f);
        l4.setForeground(TEXT_COLOR);
        l5.setFont(f);
        l5.setForeground(TEXT_COLOR);
        l6.setFont(f);
        l6.setForeground(TEXT_COLOR);
        l7.setFont(f);
        l7.setForeground(TEXT_COLOR);

        t2.setFont(f);
        t3.setFont(f);
        t4.setFont(f);
        t5.setFont(f);

        b1.setFont(f);
        b1.setBackground(BACKGROUND_COLOR);
        b1.setForeground(TEXT_COLOR);
        b2.setFont(f);
        b2.setBackground(BACKGROUND_COLOR);
        b2.setForeground(TEXT_COLOR);

        x1.setFont(f);
        x1.setBackground(BUTTON_COLOR);
        x1.setForeground(TEXT_COLOR);
        x2.setFont(f);
        x2.setBackground(BUTTON_COLOR);
        x2.setForeground(TEXT_COLOR);
        x3.setFont(f);
        x3.setBackground(new Color(192, 57, 43)); // Darker shade of red
        x3.setForeground(TEXT_COLOR);

        c1.setFont(f);
        c1.setBackground(BACKGROUND_COLOR);
        c1.setForeground(TEXT_COLOR);

        l1.setBounds(0, 30, 600, 30);
        l2.setBounds(90, 80, 150, 30);
        t2.setBounds(250, 80, 250, 30);
        l3.setBounds(90, 130, 150, 30);
        t3.setBounds(250, 130, 250, 30);
        l4.setBounds(90, 180, 150, 30);
        t4.setBounds(250, 180, 250, 30);
        l5.setBounds(90, 230, 150, 30);
        b1.setBounds(250, 230, 70, 30);
        b2.setBounds(330, 230, 80, 30);
        l6.setBounds(90, 280, 150, 30);
        c1.setBounds(250, 280, 250, 30);
        l7.setBounds(90, 330, 150, 30);
        t5.setBounds(250, 330, 250, 30);
        x1.setBounds(520, 105, 150, 30);
        x2.setBounds(520, 155, 150, 30);
        x3.setBounds(520, 205, 150, 30);

        group.add(b1);
        group.add(b2);

        c1.addItem("Ahmedabad");
        c1.addItem("Baroda");
        c1.addItem("Rajkot");
        c1.addItem("Surat");
        c1.addItem("Vapi");

        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(t2);
        add(t3);
        add(t4);
        add(l5);
        add(b1);
        add(b2);
        add(l6);
        add(c1);
        add(l7);
        add(t5);
        add(x1);
        add(x2);
        add(x3);

        x1.addActionListener(this);
        x2.addActionListener(this);
        x3.addActionListener(this);

        // Centering components
        centerComponents();
    }

    private void centerComponents() {
        Dimension size = getPreferredSize();
        int centerX = size.width / 2;

        // l1.setBounds(0, 50, 600, 30);
        // l2.setBounds(centerX - 175, 100, 150, 30);
        // t2.setBounds(centerX - 125, 100, 250, 30);
        // l3.setBounds(centerX - 175, 150, 150, 30);
        // t3.setBounds(centerX - 125, 150, 250, 30);
        // l4.setBounds(centerX - 175, 200, 150, 30);
        // t4.setBounds(centerX - 125, 200, 250, 30);
        // l5.setBounds(centerX - 175, 250, 150, 30);
        // b1.setBounds(centerX - 125, 250, 70, 30);
        // b2.setBounds(centerX - 45, 250, 80, 30);
        // l6.setBounds(centerX - 175, 300, 150, 30);
        // c1.setBounds(centerX - 125, 300, 250, 30);
        // x1.setBounds(centerX - 200, 400, 150, 30);
        // x2.setBounds(centerX - 30, 400, 150, 30);
        // x3.setBounds(centerX + 140, 400, 150, 30);
    }

    public void actionPerformed(ActionEvent k) {
        if (k.getSource() == x1) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root",
                        "arjav1234");

                try {
                    String name = t2.getText();
                    long accno = Long.parseLong(t3.getText());
                    String panno = t4.getText();
                    int password = Integer.parseInt(t5.getText());
                    String gender = "";
                    if (b1.isSelected()) {
                        gender = "Male";
                    }
                    if (b2.isSelected()) {
                        gender = "Female";
                    }
                    String city = c1.getSelectedItem().toString();

                    String sqlinsert = "INSERT INTO internship_bank(customername,accountno,pancard_no,gender,city,balance,password) VALUES(?,?,?,?,?,?,?)";
                    PreparedStatement statement = con.prepareStatement(sqlinsert);
                    statement.setString(1, name);
                    statement.setLong(2, accno);
                    statement.setString(3, panno);
                    statement.setString(4, gender);
                    statement.setString(5, city);
                    statement.setLong(6, 0);
                    statement.setInt(7, password);
                    statement.executeUpdate();
                    statement.close();
                    con.close();
                    JOptionPane.showMessageDialog(null, "Account created successfully", "INFORMATION",
                            JOptionPane.INFORMATION_MESSAGE);
                    repaint();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "All details are required", "WARNING",
                            JOptionPane.WARNING_MESSAGE);
                    System.out.println(e.getMessage());
                    repaint();
                }
            } catch (Exception k1) {
                JOptionPane.showMessageDialog(null, k1, "WARNING", JOptionPane.WARNING_MESSAGE);
                repaint();
            }
        }
        if (k.getSource() == x2) {
            t2.setText(null);
            t3.setText(null);
            t4.setText(null);
            c1.setSelectedIndex(0);
            group.clearSelection();
            repaint();
            JOptionPane.showMessageDialog(null, "Fields are refreshed", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
        }
        if (k.getSource() == x3) {
            this.setVisible(false);
            repaint();
        }
    }
}
