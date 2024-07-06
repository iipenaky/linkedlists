import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The BrowserHistoryMain class provides a menu-driven interface for managing browser history.
 * Users can add pages, remove pages, display history, save history to a file, and load history from a file.
 */
public class BrowserHistoryMain {
    /**
     * The main method to run the browser history application.
     * 
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        // Create a BrowserHistory object
        BrowserHistory history = new BrowserHistory(); 
        // Create a Scanner for user input
        Scanner scanner = new Scanner(System.in); 

        // Welcome message for the user
        System.out.println("Welcome to your browser history!!");
        while (true) {
            // Display menu options
            System.out.println("What do you want to do?");
            System.out.println("1. Add Page");
            System.out.println("2. Remove Page");
            System.out.println("3. Display History Forward");
            System.out.println("4. Display History Backward");
            System.out.println("5. Save History to File");
            System.out.println("6. Load History from File");
            System.out.println("7. Exit");
            System.out.print("Option: ");

            try {
                // Get user's choice
                int choice = scanner.nextInt(); 
                // Consume newline 
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                        // Add a page
                        System.out.print("Enter URL: ");
                        // Get URL from user
                        String url = scanner.nextLine(); 
                        System.out.print("Enter Time (e.g., 10:00 AM): ");
                         // Get time from user
                        String time = scanner.nextLine();
                        // Add the page to history
                        history.addPage(url, time); 
                        break;

                    case 2:
                        // Remove a page
                        System.out.print("Enter Time to remove (e.g., 10:05 AM): ");
                        // Get time to remove from user
                        String removeTime = scanner.nextLine(); 
                         // Remove the page from history
                        history.removePage(removeTime);
                        break;

                    case 3:
                        history.displayHistoryForward();
                        break;

                    case 4:
                        // Display history backward
                        history.displayHistoryBackward();
                        break;

                    case 5:
                        // Save history to file
                        try {
                            history.saveHistoryToFile(); 
                        } catch (IOException e) {
                            System.out.println("Error saving history to file: " + e.getMessage());
                        }
                        break;

                    case 6:
                        // Load history from file
                        try {
                            history.loadHistoryFromFile();
                        } catch (IOException e) {
                            System.out.println("Error loading history from file: " + e.getMessage());
                        }
                        break;

                    case 7:
                        // Exit the application by setting the running flag to false 
                        System.out.println("So sad to see you go. Hope to see you again");
                        scanner.close();
                        System.exit(0);

                    default:
                        // Handle invalid menu options
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            } catch (InputMismatchException e) {
                // Handle invalid numeric input
                System.out.println("Invalid input. Please enter a number.");
                // Consume the invalid input
                scanner.nextLine(); 
            }
        }
    }
}
