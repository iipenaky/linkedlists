import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * The EnhancedPlayListMain class provides a menu-driven interface for managing a playlist.
 * Users can add songs, display the playlist, remove songs,shuffle songs, play next and previous songs, and display the total duration of the playlist.
 */
public class EnhancedPlayListMain {

    /**
     * The main method to run the application.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);
        // Create an EnhancedPlaylist object
        EnhancedPlaylist playlist = new EnhancedPlaylist();

        System.out.println("Welcome to your Playlist!!!.");
        while (true) {
            try {
                // Display menu options to the user
                System.out.println("What do you want to do?");
                System.out.println("1. Add a song");
                System.out.println("2. Add a song at a specific position");
                System.out.println("3. Display playlist");
                System.out.println("4. Display total duration");
                System.out.println("5. Remove a song by title");
                System.out.println("6. Remove a song by position");
                System.out.println("7. Shuffle playlist");
                System.out.println("8. Play next song");
                System.out.println("9. Play previous song");
                System.out.println("10. Exit");
                System.out.print("Option:");

                // Get the user's choice
                int choice = scanner.nextInt();
                // Consume newline left-over
                scanner.nextLine();

                // Perform actions based on user's choice
                switch (choice) {
                    case 1:
                        // Get song details from user and add the song to the playlist
                        System.out.print("Enter song title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter artist: ");
                        String artist = scanner.nextLine();
                        System.out.print("Please be noted that the program will wait for the number of duration of the song when playing the song. Enter duration (in seconds): ");
                        int duration = scanner.nextInt();
                        // Consume newline left-over
                        scanner.nextLine();
                        playlist.addSong(title, artist, duration);
                        break;
                    case 2:
                        // Get song details and position from user and add the song at the specified position
                        System.out.print("Enter song title: ");
                        title = scanner.nextLine();
                        System.out.print("Enter artist: ");
                        artist = scanner.nextLine();
                        System.out.print("Please be noted that the program will wait for the number of duration of the song when playing the song. 1Enter duration (in seconds): ");
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
                        // Display all songs in the playlist
                        playlist.displayPlaylist();
                        break;
                    case 4:
                        // Display the total duration of all songs in the playlist
                        System.out.println("Total Duration: " + playlist.getTotalDuration() + " seconds");
                        break;
                    case 5:
                        // Get the title of the song to remove from the user and remove it from the playlist
                        System.out.print("Enter the title of the song to remove: ");
                        title = scanner.nextLine();
                        playlist.remmoveSongByTitL(title);
                        break;
                    case 6:
                        // Get the position of the song to remove from the user and remove it from the playlist
                        System.out.print("Enter the position of the song to remove: ");
                        position = scanner.nextInt();
                        // Consume newline left-over
                        scanner.nextLine();
                        playlist.removeSongByPosition(position);
                        break;
                    case 7:
                        // Shuffle the songs in the playlist
                        playlist.shuffle();
                        break;
                    case 8:
                        // Play the next song in the playlist
                        System.out.println("Playing next song:");
                        playlist.playNext();
                        break;
                    case 9:
                        // Play the previous song in the playlist
                        System.out.println("Playing previous song:");
                        playlist.playPrevious();
                        break;
                    case 10:
                        // Exit the application
                        System.out.println("So sad to see you go. Hope to see you again");
                        scanner.close();
                        System.exit(0);
                    default:
                        // Handle invalid choice
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                // Handle invalid numeric input
                System.out.println("Invalid input. Please enter a valid number.");
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
