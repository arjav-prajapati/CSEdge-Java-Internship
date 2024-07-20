package com.quizapp.ui;

import com.quizapp.QuizApp;
import com.quizapp.models.Question;
import com.quizapp.utils.DatabaseManager;
import com.quizapp.utils.QuestionLoader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;

public class QuizPage {
    private JPanel panel;
    private JLabel questionLabel;
    private JRadioButton[] options;
    private ButtonGroup optionsGroup;
    private JButton nextButton;
    private List<Question> questions;
    private int currentQuestionIndex;
    private int score;
    private int userId;
    private String subject;

    public QuizPage(QuizApp quizApp, String subject, int userId) {
        this.userId = userId;
        this.subject = subject;
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(245, 245, 245)); // Light gray background

        questions = QuestionLoader.loadQuestions(subject);
        currentQuestionIndex = 0;
        score = 0;

        // Question Label
        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        questionLabel.setForeground(new Color(33, 150, 243)); // Blue color
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        questionLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        panel.add(questionLabel);

        // Options Panel
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1));
        optionsPanel.setBackground(panel.getBackground());
        options = new JRadioButton[4];
        optionsGroup = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            options[i].setFont(new Font("Arial", Font.PLAIN, 16));
            options[i].setBackground(new Color(255, 255, 255)); // White background for radio buttons
            options[i].setForeground(new Color(0, 0, 0)); // Black text
            optionsGroup.add(options[i]);
            optionsPanel.add(options[i]);
        }
        panel.add(optionsPanel);

        // Next Button
        nextButton = new JButton("Next");
        nextButton.setPreferredSize(new Dimension(150, 40));
        nextButton.setFont(new Font("Arial", Font.BOLD, 16));
        nextButton.setBackground(new Color(33, 150, 243)); // Blue color
        nextButton.setForeground(Color.WHITE); // White text
        nextButton.setBorderPainted(false); // Remove border
        nextButton.setFocusPainted(false); // Remove focus border
        nextButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.size()) {
                    loadQuestion();
                } else {
                    DatabaseManager.addScore(userId, subject, score);
                    quizApp.showResultPage(score);
                }
            }
        });
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Padding
        panel.add(nextButton);

        loadQuestion();
    }

    private void loadQuestion() {
        Question question = questions.get(currentQuestionIndex);
        System.out.println(question.getQuestionText());
        questionLabel.setText(question.getQuestionText());
        optionsGroup.clearSelection();
        for (int i = 0; i < options.length; i++) {
            options[i].setText(question.getOptions().get(i));
        }
    }

    private void checkAnswer() {
        Question question = questions.get(currentQuestionIndex);
        for (JRadioButton option : options) {
            if (option.isSelected() && option.getText().equals(question.getCorrectAnswer())) {
                score++;
            }
        }
    }

    public JPanel getPanel() {
        return panel;
    }
}
