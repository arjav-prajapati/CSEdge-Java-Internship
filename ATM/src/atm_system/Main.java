package atm_system;

import database.DatabaseHandler;
import panels.ATM;

public class Main {
    public static void main(String[] args) {
        // Initialize the database handler
        DatabaseHandler handler = new DatabaseHandler();
        
        // Create and display the ATM management system GUI
        ATM atm = new ATM(handler);
        atm.setVisible(true);
        
        // Ensure proper closing of the database connection when the application exits  
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            handler.closeConnection();
        }));
    }
}


//for running program go to ATM/src then first compile the program by typing
//javac atm_system/Main.java database/DatabaseHandler.java panels/*.java

//then run the program by typing
//java atm_system.Main