package com.quizapp.ui;

import com.quizapp.QuizApp;
import com.quizapp.utils.DatabaseManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ResultPage {
    private JPanel panel;
    private JLabel resultLabel;
    private JButton homeButton;
    private JTextArea scoreDetailsArea;

    public ResultPage(QuizApp quizApp, int score) {
        panel = new JPanel(new BorderLayout());

        resultLabel = new JLabel("Your score is: " + score);
        resultLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(resultLabel, BorderLayout.NORTH);

        scoreDetailsArea = new JTextArea(10, 30);
        scoreDetailsArea.setEditable(false);
        panel.add(new JScrollPane(scoreDetailsArea), BorderLayout.CENTER);

        homeButton = new JButton("Go to Home");
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quizApp.showHomePage();
            }
        });
        panel.add(homeButton, BorderLayout.SOUTH);

        // Load and display the scores from the database
        displayScores();
    }

    private void displayScores() {
        List<com.quizapp.models.Score> scores = DatabaseManager.getScores();
        StringBuilder sb = new StringBuilder();
        sb.append("User Name\tSubject\tScore\n");
        sb.append("-------------------------------\n");
        for (com.quizapp.models.Score score : scores) {
            sb.append(score.getUserName()).append("\t")
              .append(score.getSubject()).append("\t")
              .append(score.getScore()).append("\n");
        }
        scoreDetailsArea.setText(sb.toString());
    }

    public JPanel getPanel() {
        return panel;
    }
}
