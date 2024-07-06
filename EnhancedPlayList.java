import java.util.Random;

/**
 * This class represents an enhanced playlist which uses the doubly linked list and allows
 * adding, removing, and shuffling songs. 
 */
class EnhancedPlaylist {
    // The first node in the playlist
    private SongNode head;
    // The last node in the playlist
    private SongNode tail;
    // The current node being played
    SongNode current = null;

    /**
     * Constructor to initialize an empty playlist.
     */
    public EnhancedPlaylist() {
        // Initialize head and tail as null indicating an empty list
        this.head = null;
        this.tail = null;
    }

    /**
     * Adds a new song to the end of the playlist.
     * 
     * @param title    The title of the song
     * @param artist   The artist of the song
     * @param duration The duration of the song in seconds
     */
    public void addSong(String title, String artist, int duration) {
        // Create a new song node
        SongNode newNode = new SongNode(title, artist, duration);
        // If the list is empty, set head and tail to the new node
        if (head == null) {
            head = newNode;
            tail = newNode;
        } 
        // Otherwise, add the new node to the end of the list
        else {
            tail.next = newNode;
            newNode.prev = tail; 
            tail = newNode;
        }
        System.out.println("Song has been added successfully.\n");
    }

    /**
     * Adds a new song at a specific position in the playlist.
     * 
     * @param title    The title of the song
     * @param artist   The artist of the song
     * @param duration The duration of the song in seconds
     * @param position The position to insert the new song at (1-based index)
     */
    public void addSongAtPosition(String title, String artist, int duration, int position) {
        // Create a new song node
        SongNode newNode = new SongNode(title, artist, duration);
        // Get the current size of the list
        int size = size(); 
    
        // Check if the position is valid
        if (position < 1 || position > size + 1) {
            System.out.println("Position out of bounds\n");
            return;
        }
    
        // If adding at the start of the list
        if (position == 1) {
            if(head == null){
                head = newNode;
                tail = newNode;
            }
            else{
                newNode.next = head;
                head.prev = newNode; 
                head = newNode;
            }
        } 
        // If adding at the end of the list
        else if (position == (size + 1)) {
            if (tail != null) {
                tail.next = newNode; 
                newNode.prev = tail; 
                tail = newNode; 
            } else {
                head = newNode;
                tail = newNode;
            }
        } 
        // If adding in the middle of the list
        else {
            SongNode current = head;
            int count = 1;
            while (count < (position - 1)) {
                current = current.next;
                count++;
            }
            newNode.next = current.next; 
            newNode.prev = current; 
            if (current.next != null) {
                current.next.prev = newNode; 
            }
            current.next = newNode; 
        }
        System.out.println("Song has been added successfully.\n");
    }

    /**
     * Removes a song from a specific position in the playlist.
     * 
     * @param position The position of the song to be removed (1-based index)
     */
    public void removeSongByPosition(int position) {
        // Check if the list is empty
        if (head == null) {
            System.out.println("The playlist is empty\n");
            return;
        }
        
        // Get the current size of the list
        int size = size(); 
        // Check if the position is valid
        if (position < 1 || position > size) {
            System.out.println("Position out of bounds\n");
            return;
        }

        // If removing the first song
        if (position == 1) {
            head = head.next; 
            if (head != null) {
                head.prev = null; 
            } else {
                // List is now empty
                tail = null;
            }
        }
        // If removing the last song
        else if (position == size){
            tail = tail.prev;
            tail.next = null; 
        }
        // If removing a song from the middle
        else {
            SongNode current = head;
            int count = 1;
            while (count < (position - 1)) {
                current = current.next;
                count++;
            }
            current.next = current.next.next;
            if (current.next != null) {
                current.next.prev = current;
            }
        }
        System.out.println("Song has been removed successfully.\n");
    }

    /**
     * Displays all songs in the playlist.
     */
    public void displayPlaylist() {
        // Check if the list is empty
        if (head == null) {
            System.out.println("The playlist is empty\n");
            return;
        }
        // Print each song in the list
        SongNode current = head;
        System.out.println("Playlist:");
        while (current != null) {
            System.out.println(current);
            current = current.next;
        }
        if (current == null){
            System.out.println("That is the end of the playlist.\n");
        }
    }

    /**
     * Calculates the total duration of all songs in the playlist.
     * 
     * @return The total duration in seconds
     */
    public int getTotalDuration() {
        int totalDuration = 0;
        // Sum the duration of each song in the list
        SongNode current = head;
        while (current != null) {
            totalDuration += current.duration; 
            current = current.next; 
        }
        return totalDuration;
    }

    /**
     * Plays the next song in the playlist.
     */
    public void playNext() {
        // Check if the list is empty
        if (head == null) {
            System.out.println("The playlist is empty\n");
            return;
        }
        // If no song is currently being played, start from the head
        if (current == null) {
            current = head;
        } else {
            // Otherwise, move to the next song
            current = current.next;
        }
        // If there is a next song, play it
        if (current != null) {
            System.out.println("Playing: " + current);
            try {
                Thread.sleep(current.duration * 1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Song playback interrupted");
            }
        } else {
            System.out.println("End of playlist. You can play previous song to play the last song\n");
        }
    }

    /**
     * Plays the previous song in the playlist.
     */
    public void playPrevious() {
        // Check if the list is empty
        if (head == null) {
            System.out.println("The playlist is empty\n");
            return;
        }
        // If no song is currently being played, start from the tail
        if (current == null) {
            current = tail;
        } else {
            // Otherwise, move to the previous song
            current = current.prev;
        }
        // If there is a previous song, play it
        if (current != null) {
            System.out.println("Playing: " + current);
            try {
                Thread.sleep(current.duration * 1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Song playback interrupted");
            }
        } else {
            System.out.println("Start of playlist. You can play next to play the first song.\n");
        }
    }

    /**
     * Removes all songs with a specific title from the playlist.
     * 
     * @param title The title of the songs to remove
     */
    public void remmoveSongByTitL(String title){
        // Check if the list is empty
        if (head == null){
            System.out.println("The playlist is empty. There is nothing to remove\n");
            return;
        }

        int count = 1;
        int size = size();
        boolean found = false;
        SongNode current = head;
        // Traverse the list to find and remove songs with the given title
        while (current != null && count <= size){
            if (current.title.equalsIgnoreCase(title)){
                found = true;
                int pos = count;
                count--;
                removeSongByPosition(pos);
            }
            current = current.next;
            count++;
        }
        if(found){
            System.out.println("All songs with the title "+ title + " have been removed.\n");
        } else{
            System.out.println("There is no title with the title "+ title+"\n");
        }
    }

    /**
     * Gets the number of songs in the playlist.
     * 
     * @return The number of songs in the playlist
     */
    public int size() {
        int length = 0;
        // Count the number of songs in the list
        SongNode current = head;
        while (current != null) {
            length++;
            current = current.next;
        }
        return length;
    }

    /**
     * Shuffles the songs in the playlist.
     */
    public void shuffle() {
        // Check if the list is empty
        if (head == null) {
            System.out.println("The playlist is empty\n");
            return;
        }
        System.out.println("Shuffling playlist...");
        int length = size();
        if (length < 2)
            return;
        // Put the songs in an array
        SongNode[] songs = new SongNode[length];
        SongNode current = head;
        for (int i = 0; i < length; i++) {
            songs[i] = current;
            current = current.next; 
        }

        // Shuffle the array
        Random rand = new Random();
        for (int i = 0; i < songs.length; i++) {
            int index = rand.nextInt(songs.length); 
            SongNode temp = songs[index]; 
            songs[index] = songs[i];
            songs[i] = temp;
        }
    
        // Put the array back to the list
        head = songs[0];
        head.prev = null;
        current = head;
        for (int i = 1; i < length; i++) {
            current.next = songs[i]; 
            songs[i].prev = current; 
            current = current.next;
        }
        current.next = null; 
        tail = current;      
        System.out.println("The playlist has been shuffled successfully.\n");
    }
}
