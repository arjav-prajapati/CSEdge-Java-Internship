package com.quizapp.ui;

import com.quizapp.QuizApp;
import com.quizapp.utils.DatabaseManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage {
    private JPanel panel;
    private JTextField nameField;
    private JButton startQuizButton;
    private JComboBox<String> subjectComboBox;
    private int userId;

    public HomePage(QuizApp quizApp) {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(245, 245, 245)); // Light gray background

        // Title Label
        JLabel titleLabel = new JLabel("QUIZ APP", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setPreferredSize(new Dimension(300, 50));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setForeground(new Color(33, 150, 243)); // Blue color
        panel.add(titleLabel);

        // Padding
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Input Panel for Name
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
        inputPanel.setBackground(panel.getBackground());
        JLabel nameLabel = new JLabel("Enter your name:");
        nameLabel.setForeground(new Color(0, 0, 0)); // Black color
        inputPanel.add(nameLabel);
        nameField = new JTextField(10); // Adjusted size
        nameField.setPreferredSize(new Dimension(150, 30)); // Adjusted size
        inputPanel.add(nameField);
        inputPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(inputPanel);

        // Padding
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Subject Selection
        JPanel subjectPanel = new JPanel();
        subjectPanel.setLayout(new BoxLayout(subjectPanel, BoxLayout.X_AXIS));
        subjectPanel.setBackground(panel.getBackground());
        JLabel subjectLabel = new JLabel("Select subject:");
        subjectLabel.setForeground(new Color(0, 0, 0)); // Black color
        subjectPanel.add(subjectLabel);
        String[] subjects = {"Java", "PHP", "JavaScript", "DSA"};
        subjectComboBox = new JComboBox<>(subjects);
        subjectComboBox.setPreferredSize(new Dimension(150, 30));
        subjectPanel.add(subjectComboBox);
        subjectPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(subjectPanel);

        // Padding
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Start Button
        startQuizButton = new JButton("Start Quiz");
        startQuizButton.setPreferredSize(new Dimension(200, 40));
        startQuizButton.setBackground(new Color(33, 150, 243)); // Blue color
        startQuizButton.setForeground(Color.WHITE); // White text
        startQuizButton.setBorderPainted(false); // Remove border
        startQuizButton.setFocusPainted(false); // Remove focus border
        startQuizButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startQuizButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Please enter your name.");
                    return;
                }
                userId = DatabaseManager.addUser(name);
                String subject = (String) subjectComboBox.getSelectedItem();
                quizApp.showQuizPage(subject, userId);
            }
        });
        panel.add(startQuizButton);
    }

    public JPanel getPanel() {
        return panel;
    }
}
