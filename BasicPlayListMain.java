import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * The BasicPlayListMain class provides a menu-driven interface for managing a playlist.
 * Users can add songs, display the playlist, remove songs, and display the total duration of the playlist.
 */
public class BasicPlayListMain {

    /**
     * The main method to run the application.
     * 
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);
        // Create an BasicPlayList object
        BasicPlayList playlist = new BasicPlayList();

        // A welcome message for the user
        System.out.println("Welcome to your playlist!");
        while (true) {
            try {
                // Display menu options
                System.out.println("What do you want to do?:");
                System.out.println("1. Add a song");
                System.out.println("2. Add a song at a specific position");
                System.out.println("3. Display playlist");
                System.out.println("4. Display total duration");
                System.out.println("5. Remove a song by position");
                System.out.println("6. Remove a song by title");
                System.out.println("7. Exit");
                System.out.print("Option: ");

                // Read user choice
                int choice = scanner.nextInt();
                // Consume newline left-over
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                        // Add a song
                        System.out.print("Enter song title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter artist: ");
                        String artist = scanner.nextLine();
                        System.out.print("Enter duration (in seconds): ");
                        int duration = scanner.nextInt();
                         // Consume newline left-over
                        scanner.nextLine();
                        playlist.addSong(title, artist, duration);
                        break;
                    case 2:
                        // Add a song at a specific position
                        System.out.print("Enter song title: ");
                        title = scanner.nextLine();
                        System.out.print("Enter artist: ");
                        artist = scanner.nextLine();
                        System.out.print("Enter duration (in seconds): ");
                        duration = scanner.nextInt();
                        // Consume newline left-over
                        scanner.nextLine();
                        System.out.print("Enter position: ");
                        int position = scanner.nextInt();
                        // Consume newline left-over
                        scanner.nextLine();
                        playlist.addSongAtPosition(title, artist, duration, position);
                        break;
                    case 3:
                        // Display the playlist
                        playlist.displayPlaylist();
                        break;
                    case 4:
                        // Display the total duration of the playlist
                        System.out.println("Total Duration: " + playlist.getTotalDuration() + " seconds");
                        break;
                    case 5:
                        // Remove a song by position
                        System.out.print("Enter the position of the song to remove: ");
                        position = scanner.nextInt();
                        // Consume newline left-over
                        scanner.nextLine(); 
                        playlist.removeSongByPosition(position);
                        break;
                    case 6:
                        // Remove a song by title
                        System.out.print("Enter the title of the song to remove: ");
                        title = scanner.nextLine();
                        playlist.removeSongByTitle(title);;
                        break;
                    case 7:
                        // Exit the application
                        System.out.println("So sad to see you go. Hope to see you again");
                        scanner.close();
                        System.exit(0);
                    default:
                        // Handle invalid menu options
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                // Handle invalid numeric input
                System.out.println("Invalid input. Please enter a valid number.");
                // Consume the invalid input
                scanner.nextLine(); 
            } catch (Exception e) {
                // Handle any other exceptions
                System.out.println("An error occurred: " + e.getMessage());
                e.printStackTrace();
                // Consume the invalid input
                scanner.nextLine(); 
            }
        }
    }
}
