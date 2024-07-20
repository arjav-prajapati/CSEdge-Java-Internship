package com.quizapp;

import com.quizapp.utils.DatabaseManager;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        DatabaseManager.createTables();
        SwingUtilities.invokeLater(() -> {
            QuizApp quizApp = new QuizApp();
            quizApp.showHomePage();
        });
    }
}


//for compiling and running the program
// javac -cp "lib/mysql-connector-j-9.0.0.jar;lib/gson-2.9.0.jar" -d bin src/main/java/com/quizapp/*.java src/main/java/com/quizapp/utils/*.java src/main/java/com/quizapp/ui/*.java src/main/java/com/quizapp/models/*.java

//for running the program
// java -cp "bin;lib/mysql-connector-j-9.0.0.jar;lib/gson-2.9.0.jar" com.quizapp.Main 