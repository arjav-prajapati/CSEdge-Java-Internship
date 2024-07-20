package com.quizapp;

import com.quizapp.ui.HomePage;
import com.quizapp.ui.QuizPage;
import com.quizapp.ui.ResultPage;

import javax.swing.JFrame;

public class QuizApp {
    private JFrame frame;

    public QuizApp() {
        frame = new JFrame("Quiz Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
    }

    public void showHomePage() {
        HomePage homePage = new HomePage(this);
        frame.setContentPane(homePage.getPanel());
        frame.setVisible(true);
    }

    public void showQuizPage(String subject, int userId) {
        QuizPage quizPage = new QuizPage(this, subject, userId);
        frame.setContentPane(quizPage.getPanel());
        frame.setVisible(true);
    }

    public void showResultPage(int score) {
        ResultPage resultPage = new ResultPage(this, score);
        frame.setContentPane(resultPage.getPanel());
        frame.setVisible(true);
    }
}
