import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * The FullyFunctionalPlayListMain class provides a menu-driven interface for managing a playlist.
 * Users can add songs, display the playlist, remove songs,shuffle songs, play next and previous songs, toggle the continuous play, and display the total duration of the playlist.
 */
public class FullyFunctionalPlayListMain {

     /**
     * The main method to run the application.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);
        // Create an FullyFunctionalPlayList object
        FullyFunctionalPlaylist playlist = new FullyFunctionalPlaylist();

        // Welcome message
        System.out.println("Welcome to your playlist!!!");
        while (true) {
            try {
                // Display menu options
                System.out.println("What do you want to do?");
                System.out.println("1. Add a song");
                System.out.println("2. Add a song at a specific position");
                System.out.println("3. Display playlist");
                System.out.println("4. Display total duration");
                System.out.println("5. Remove a song by title");
                System.out.println("6. Remove a song by position");
                System.out.println("7. Shuffle playlist");
                System.out.println("8. Toggle continuous play");
                System.out.println("9. Play next song");
                System.out.println("10. Play previous song");
                System.out.println("11. Play your playlist");
                System.out.println("12. Exit");
                System.out.print("Option:");

                // Read user choice
                int choice = scanner.nextInt();
                // Consume newline character
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        // Read song title
                        System.out.print("Enter song title: ");
                        String title = scanner.nextLine();
                        // Read artist
                        System.out.print("Enter artist: ");
                        String artist = scanner.nextLine();
                        // Read duration
                        System.out.print("Please be noted that the program will wait for the number of duration of the song when playing the song. Enter duration (in seconds): ");
                        int duration = scanner.nextInt();
                        // Consume newline character
                        scanner.nextLine();
                        // Add song to playlist
                        playlist.addSong(title, artist, duration);
                        break;
                    case 2:
                        // Read song title
                        System.out.print("Enter song title: ");
                        title = scanner.nextLine();
                        // Read artist
                        System.out.print("Enter artist: ");
                        artist = scanner.nextLine();
                        // Read duration
                        System.out.print("Please be noted that the program will wait for the number of duration of the song when playing the song. Enter duration (in seconds): ");
                        duration = scanner.nextInt();
                        // Consume newline character
                        scanner.nextLine();
                        // Read position
                        System.out.print("Enter position: ");
                        int position = scanner.nextInt();
                        // Consume newline character
                        scanner.nextLine();
                        // Add song at specific position
                        playlist.addSongAtPosition(title, artist, duration, position);
                        break;
                    case 3:
                        // Display all songs in playlist
                        playlist.displayPlaylist();
                        break;
                    case 4:
                        // Display total duration of playlist
                        System.out.println("Total Duration: " + playlist.getTotalDuration() + " seconds");
                        break;
                    case 5:
                        // Read song title to remove
                        System.out.print("Enter the title of the song to remove: ");
                        title = scanner.nextLine();
                        // Remove song by title
                        playlist.remmoveSongByTitle(title);
                        break;
                    case 6:
                        // Read position to remove
                        System.out.print("Enter the position of the song to remove: ");
                        position = scanner.nextInt();
                        // Consume newline character
                        scanner.nextLine();
                        // Remove song by position
                        playlist.removeSongByPosition(position);
                        break;
                    case 7:
                        // Shuffle the playlist
                        playlist.shuffle();
                        break;
                    case 8:
                        // Toggle continuous play mode
                        System.out.println("Toggling continuous play...");
                        playlist.toggleContinuousPlay();
                        break;
                    case 9:
                        // Play the next song
                        playlist.playNext();
                        break;
                    case 10:
                        // Play the previous song
                        playlist.playPrevious();
                        break;
                    case 11:
                        // Play the entire playlist
                        playlist.playPlaylist();
                        break;
                    case 12:
                        // Exit message
                        System.out.println("So sad to see you go. Hope to see you again.");
                        // Close scanner
                        scanner.close();
                        // Exit the program
                        System.exit(0);
                    default:
                        // Invalid input message
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                // Handle invalid input
                System.out.println("Invalid input. Please enter a valid number.");
                // Consume newline character
                scanner.nextLine();
            } catch (Exception e) {
                // Handle general exceptions
                System.out.println("An error occurred: " + e.getMessage());
                e.printStackTrace();
                // Consume the invalid input
                scanner.nextLine();
            }
        }
    }
}
