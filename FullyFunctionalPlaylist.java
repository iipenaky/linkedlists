import java.util.Random;
import java.util.Scanner;

/**
 * Class representing a fully functional playlist.
 * Allows adding, removing, displaying, shuffling, and playing songs.
 */
class FullyFunctionalPlaylist {
    private SongNode head; 
    private SongNode tail; 
    private SongNode current; 
    private boolean continuousPlay; 

    /**
     * Constructor to initialize the playlist.
     */
    public FullyFunctionalPlaylist() {
        this.head = null; 
        this.tail = null; 
        this.current = null; 
        this.continuousPlay = false; 
    }

    /**
     * Adds a song to the playlist.
     * 
     * @param title    Title of the song
     * @param artist   Artist of the song
     * @param duration Duration of the song in seconds
     */
    public void addSong(String title, String artist, int duration) {
        SongNode newNode = new SongNode(title, artist, duration);

        // If the playlist is empty, initialize it with the new song
        if (head == null) {
            head = newNode;
            tail = newNode;
            head.next = head;
            head.prev = head;
        } else { 
            // Otherwise, add the song to the end of the playlist
            tail.next = newNode;
            newNode.prev = tail;
            newNode.next = head;
            head.prev = newNode;
            tail = newNode;
        }
        System.out.println("Song has been added successfully."); 
    }

    /**
     * Adds a song to a specific position in the playlist.
     * 
     * @param title    Title of the song
     * @param artist   Artist of the song
     * @param duration Duration of the song in seconds
     * @param position Position to add the song (1-based index)
     */
    public void addSongAtPosition(String title, String artist, int duration, int position) {
        SongNode newNode = new SongNode(title, artist, duration); 
        int size = getSize(); 
        
        // Check if the position is out of bounds
        if (position < 1 || position > size + 1) {
            System.out.println("Position out of bounds");
            return;
        }
        
        // Add the song at the beginning
        if (position == 1) {
            if (head == null) {
                head = newNode;
                tail = newNode;
                head.next = head;
                head.prev = head;
            } else {
                newNode.next = head;
                newNode.prev = tail;
                head.prev = newNode;
                tail.next = newNode;
                head = newNode;
            }
        } 
        // Add the song at the end
        else if (position == size + 1) {
            newNode.next = head;
            newNode.prev = tail;
            tail.next = newNode;
            head.prev = newNode;
            tail = newNode;
        } 
        // Add the song at the specified position in the middlde
        else {
            SongNode current = head;
            int count = 1;
            while (count < position - 1 && current.next != head) {
                current = current.next;
                count++;
            }
            newNode.next = current.next;
            newNode.prev = current;
            current.next.prev = newNode;
            current.next = newNode;
        }
        System.out.println("Song has been added successfully."); 
    }

    /**
     * Removes a song from a specific position in the playlist.
     * 
     * @param position Position to remove the song (1-based index)
     */
    public void removeSongByPosition(int position) {
        // Check if the playlist is empty
        if (head == null && getSize() != 0) {
            System.out.println("The playlist is empty");
            return;
        }
    
        int size = getSize(); 
        
        // Check if the position is out of bounds
        if (position < 1 || position > size) {
            System.out.println("Position out of bounds");
            return;
        }
    
        // Remove the song at the beginning
        if (position == 1) {
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                head.prev = tail;
                tail.next = head;
            }
        } 
        // Remove the song at the end
        else if (position == size) {
            if (tail == head) {
                head = null;
                tail = null;
            } else {
                tail = tail.prev;
                tail.next = head;
                head.prev = tail;
            }
        } 
        // Remove the song at the specified position
        else {
            SongNode current = head;
            int count = 1;
            while (count < position - 1) {
                current = current.next;
                count++;
            }
            current.next = current.next.next;
            current.next.prev = current;
        }
        System.out.println("Song has been removed successfully."); 
    }

    /**
     * Removes all songs with a specific title from the playlist.
     * 
     * @param title Title of the songs to remove
     */
    public void remmoveSongByTitle(String title){
        // Check if the playlist is empty
        if (head == null) {
            System.out.println("The playlist is empty. There is nothing to remove");
            return;
        }

        int count = 1; 
        boolean found = false;
        SongNode current = head;

        // Iterate through the playlist to find and remove songs with the specified title
        while (count <= getSize()) {
            do {
                if (current.title.equalsIgnoreCase(title)) {
                    found = true;
                    int pos = count;
                    count--;
                    removeSongByPosition(pos);
                }
                current = current.next;
                count++;
            } while (current != head && getSize() != 0);
        }

        if (found == true) {
            System.out.println("All songs with the title " + title + " have been removed.");
        } else {
            System.out.println("There is no song with the title " + title);
        }
    }

    /**
     * Displays all the songs in the playlist.
     */
    public void displayPlaylist() {
        // Check if the playlist is empty
        if (head == null) {
            System.out.println("The playlist is empty");
            return;
        }
        SongNode current = head;
        System.out.println("Playlist:");
        
        // Traverse through the playlist to display each song
        do {
            System.out.println(current);
            current = current.next;
        } while (current != head);
    }

    /**
     * Calculates the total duration of all songs in the playlist.
     * 
     * @return Total duration in seconds
     */
    public int getTotalDuration() {
        int totalDuration = 0;
        
        // If the playlist is empty, return 0
        if (head == null) return totalDuration;

        SongNode current = head;
        
        // Traverse through the playlist to sum up the duration of each song
        do {
            totalDuration += current.duration;
            current = current.next;
        } while (current != head);

        return totalDuration;
    }

    /**
     * Plays the next song in the playlist.
     */
    public void playNext() throws InterruptedException {
        // Check if the playlist is empty
        if (head == null) {
            System.out.println("The playlist is empty");
            return;
        }
        System.out.println("Playing next song:");
        
        // If no song is currently playing, start with the first song
        if (current == null) {
            current = head;
        } 
        else if (current == tail){
            if (continuousPlay == true){
                current = current.next;
            }
            else{
                current = null;
            }
        }
        else {
            current = current.next;
        }


        
        // Play the next song
        if (current != null) {
            System.out.println("Playing: " + current);
            Thread.sleep(current.duration * 1000);} // duration is assumed to be in seconds
        else{
            System.out.println("That's the end.");
        }
    }

    /**
     * Plays the previous song in the playlist.
     * @throws InterruptedException 
     */
    public void playPrevious() throws InterruptedException {
        // Check if the playlist is empty
        if (head == null) {
            System.out.println("The list is empty");
            return;
        }
        System.out.println("Playing previous song:");
        
        // If no song is currently playing, start with the last song
        if (current == null) {
            current = tail;
        } 
        else if (current == head){
            if (continuousPlay == true){
                current = current.prev;
            }
            else{
                current = null;
            }
        }
        else {
            current = current.prev;
        }
        
        // Play the previous song
        if (current != null) {
            System.out.println("Playing: " + current);
            Thread.sleep(current.duration * 1000); // duration is assumed to be in seconds
        }
        else{
            System.out.println("That is all");
        }
    }

    /**
     * Shuffles the playlist.
     */
    public void shuffle() {
        // Check if the playlist is empty
        if (head == null) {
            System.out.println("The list is empty");
            return;
        }

        int size = getSize();
        System.out.println("Shuffling playlist...");

        // If the playlist has fewer than 2 songs, no need to shuffle
        if (size < 2) return;

        SongNode[] songs = new SongNode[size];
        SongNode current = head;

        // Store all songs in an array
        for (int i = 0; i < size; i++) {
            songs[i] = current;
            current = current.next;
        }

        Random rand = new Random();

        // Shuffle the songs in the array
        for (int i = 0; i < songs.length; i++) {
            int index = rand.nextInt(songs.length);
            SongNode temp = songs[index];
            songs[index] = songs[i];
            songs[i] = temp;
        }

        // Reconstruct the circular doubly linked list with the shuffled songs
        head = songs[0];
        tail = songs[size - 1];
        for (int i = 0; i < size; i++) {
            songs[i].next = songs[(i + 1) % size];
            songs[i].prev = songs[(i - 1 + size) % size];
        }
        tail.next = head;
        head.prev = tail;

        System.out.println("The playlist has been shuffled successfully.");
    }

    /**
     * Gets the size of the playlist.
     * 
     * @return Number of songs in the playlist
     */
    private int getSize() {
        // If the playlist is empty, return 0
        if (head == null) return 0;

        int size = 0;
        SongNode current = head;

        // Iterate through the playlist to count the number of songs
        do {
            size++;
            current = current.next;
        } while (current != head);

        return size;
    }

    /**
     * Toggles continuous play mode.
     */
    public void toggleContinuousPlay() {
        // If the playlist is empty
        if (head == null) {
            // Print message
            System.out.println("The list is empty");
            return;
        }
        // Toggle continuous play flag
        continuousPlay = !continuousPlay;
        // If continuous play is enabled
        if (continuousPlay== true) {
            // Print message
            System.out.println("Continuous play mode enabled.");
        } else {
            // If continuous play is disabled
            // Print message
            System.out.println("Continuous play mode disabled.");
        }
    }

    /**
     * Plays the playlist.
     * @throws InterruptedException 
     */
    public void playPlaylist() throws InterruptedException {
        // If the playlist is empty
        if (head == null) {
            // Print message
            System.out.println("The list is empty");
            return;
        }

        // Create a scanner for user input
        Scanner scanner = new Scanner(System.in);
        // Start from the head
        SongNode current = head;

        // If continuous play is disabled
        if (continuousPlay == false) {
            // Play the playlist once
            do {
                // Print the current song
                System.out.println("Playing: " + current.title + " by " + current.artist);
                Thread.sleep(current.duration * 1000); // duration is assumed to be in seconds
                // Move to the next song
                current = current.next;
            } while (current != head); // Continue until back to the head
        } else {
            // If continuous play is enabled
            // Loop while continuous play is enabled
            while (continuousPlay== true) {
                // Play the playlist 5 times
                for (int i = 0; i < 3; i++) {
                    // Start from the head
                    SongNode temp = head;
                    do {
                        // Print the current song
                        System.out.println("Playing: " + temp.title + " by " + temp.artist);
                        Thread.sleep(current.duration * 1000);
                        // Move to the next song
                        temp = temp.next;
                    } while (temp != head); // Continue until back to the head
                }
                // Ask user if they want to continue
                System.out.print("Do you want to continue playing the playlist for another 3 times? (yes/no):");
                // Get user response
                String response = scanner.nextLine().trim();
                // Update continuous play flag based on response
                continuousPlay = response.equalsIgnoreCase("yes");
            }
        }
    }
    
}
