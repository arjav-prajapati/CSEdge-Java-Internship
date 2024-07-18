package panels;

import database.DatabaseHandler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATM extends JFrame implements ActionListener {
    private DatabaseHandler handler;

    private static final Color BACKGROUND_COLOR = new Color(243, 156, 18);
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Color BUTTON_COLOR = new Color(211, 84, 0);
    private static final Color BORDER_COLOR = new Color(230, 126, 34);

    JLabel l1 = new JLabel("ATM Mgmt System", SwingConstants.CENTER);
    JButton b1 = new JButton("Authenticate");
    JButton b2 = new JButton("Add User");
    JButton b3 = new JButton("Withdraw");
    JButton b4 = new JButton("Deposit");
    JButton b5 = new JButton("Reset Password");
    JButton b6 = new JButton("Check Balance");
    Font f = new Font("arial", Font.BOLD, 13);
    Font f1 = new Font("arial", Font.BOLD, 18);
    JPanel nav = new JPanel();
    Component frame2;
    JPanel contentPanel = new JPanel();
    Component currentPanel;

    public ATM(DatabaseHandler handler) {
        this.handler = handler;
        setLayout(null);
        setSize(800, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("ATM MANAGEMENT SYSTEM");
        setVisible(true);
        setResizable(false);
        setBackground(BACKGROUND_COLOR);

        nav.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        nav.setBackground(BUTTON_COLOR);
        nav.setBounds(0, 0, 800, 140);
        nav.setVisible(true);

        styleButton(b1);
        styleButton(b2);
        styleButton(b3);
        styleButton(b4);
        styleButton(b5);
        styleButton(b6);

        nav.add(b1);
        nav.add(b2);
        nav.add(b3);
        nav.add(b4);
        nav.add(b5);
        nav.add(b6);
        add(nav);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b6.addActionListener(this);
        b5.addActionListener(this);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(BUTTON_COLOR);
        button.setForeground(TEXT_COLOR);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 2));
        button.setPreferredSize(new Dimension(140, 40));
        button.addActionListener(this);
    }

    public void actionPerformed(ActionEvent k1) {
        if (frame2 != null) {
            frame2.setVisible(false);
            repaint();
        }

        if (k1.getSource() == b2) {
            UserAccount u1 = new UserAccount();
            addAndRepaint(u1);
        }
        if (k1.getSource() == b5) {
            ResetPassword r = new ResetPassword();
            addAndRepaint(r);
        }
        if (k1.getSource() == b6) {
            CheckBalance cb = new CheckBalance();
            addAndRepaint(cb);
        }
        if (k1.getSource() == b3) {
            Withdrawal w1 = new Withdrawal();
            addAndRepaint(w1);
        }
        if (k1.getSource() == b4) {
            Deposit d1 = new Deposit();
            addAndRepaint(d1);
        }
        if (k1.getSource() == b1) {
            Authenticate a1 = new Authenticate();
            addAndRepaint(a1);
        }
    }

    private void addAndRepaint(Component component) {
        component.setBounds(0, 140, 800, 410);
        add(component);
        repaint();
        frame2 = component;
    }
}
