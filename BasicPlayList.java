/**
 * A class representing a basic playlist of songs using a singly linked list.
 * It allows adding, removing and adding songs.
 */
public class BasicPlayList {
    // Head or the first song of the playlist
    private SongNodeSingly head; 

    /**
     * Constructor to initialize an empty playlist.
     */
    public BasicPlayList() {
        // The head set to null initially indicating that the playlist is empty when created.
        this.head = null; 
    }

    /**
     * Adds a new song to the end of the playlist.
     *
     * @param title    The title of the song.
     * @param artist   The artist of the song.
     * @param duration The duration of the song in seconds.
     */
    public void addSong(String title, String artist, int duration) {
        // Create a new song node with the given title, artist, and duration
        SongNodeSingly newNode = new SongNodeSingly(title, artist, duration); 
        // If the playlist is empty, set the new node as the head
        if (head == null) {
            head = newNode; 
        }
        // Otherwise, start at the head of the list and traverse to the end of the list to add the song at the end
        else {
            SongNodeSingly current = head;
            while (current.next != null) {
                current = current.next; 
            }
            current.next = newNode; 
        }
        System.out.println("Song has been added successfully.\n");
    }

    /**
     * Adds a new song at the specified position in the playlist.
     *
     * @param title    The title of the song.
     * @param artist   The artist of the song.
     * @param duration The duration of the song in seconds.
     * @param position The position at which the song should be added (1-based index).
     */
    public void addSongAtPosition(String title, String artist, int duration, int position) {
        // Create a new song node with the given title, artist, and duration
        SongNodeSingly newNode = new SongNodeSingly(title, artist, duration); 
        // Get the current size of the playlist
        int size = size(); 
        // Check if the position is out of bounds
        if (position < 1 || position > size + 1) {
            System.out.println("Position out of bounds\n");
            return;
        }
        // If inserting at the beginning, set the new node as the head
        if (position == 1) {  
            newNode.next = head; 
            head = newNode;
        } 
        // If inserting at the end, traverse to the end and add the new node
        else if (position == size + 1) {
            SongNodeSingly current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        // Otherwise, traverse to the specified position and insert the new node
        else {
            SongNodeSingly current = head;
            int count = 1;
            while (count < (position - 1)) {
                current = current.next;
                count++;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
        System.out.println("Song has been added successfully.\n");
    }

    /**
     * Removes the song at the specified position from the playlist.
     *
     * @param position The position of the song to be removed (1-based index).
     */
    public void removeSongByPosition(int position) {
        // Check if the playlist is empty
        if (head == null) {
            System.out.println("The playlist is empty.\n");
            return;
        }
        // Get the current size of the playlist
        int size = size();
        // Check if the position is out of bounds
        if (position < 1 || position > size) {
            System.out.println("Position out of bounds\n"); 
            return;
        } 
        // If removing the first song, set the head to the next song
        if (position == 1) {
            head = head.next; 
        } 
        // If removing the last song, traverse to the second last song and set its next to null
        else if (position == size) {
            SongNodeSingly current = head;
            int count = 1;
            while (count < (position - 1)) {
                current = current.next;
                count++;
            }
            current.next = null;
        } 
        // Otherwise, traverse to the song before the one to be removed and update its next pointer
        else {
            SongNodeSingly current = head;
            int count = 1;
            while (count < (position - 1)) {
                current = current.next;
                count++;
            }
            current.next = current.next.next;
        }
        System.out.println("Song has been removed successfully.\n");
    }

    /**
     * Removes all songs with the specified title from the playlist.
     *
     * @param title The title of the song(s) to be removed.
     */
    public void removeSongByTitle(String title) {
        // Check if the playlist is empty
        if (head == null) {
            System.out.println("The playlist is empty. There is nothing to remove.\n");
            return;
        }

        // Traverse the playlist and remove songs with the specified title based on the position of the song in the list
        int count = 1;
        int size = size();
        boolean found = false;
        SongNodeSingly current = head;
        while (current != null && count <= size) {
            if (current.title.equalsIgnoreCase(title)) {
                found = true;
                int pos = count;
                count--;
                removeSongByPosition(pos);
            }
            current = current.next;
            count++;
        }
        if (found == true) {
            System.out.println("All songs with the title " + title + " have been removed.\n");
        } else {
            System.out.println("There is no song with the title " + title+"\n");
        }
    }

    /**
     * Displays all songs in the playlist.
     */
    public void displayPlaylist() {
        // Check if the playlist is empty
        if (head == null) {
            System.out.println("There is nothing to display since the list is empty\n");
            return;
        }
        // Traverse the playlist and print each song
        System.out.println("Playlist:");
        SongNodeSingly current = head;
        while (current != null) {
            System.out.println(current); 
            current = current.next;
        }
        // Indicate the end of the playlist
        if (current == null) {
            System.out.println("There are no more songs to show, since that is the end of the playlist.\n");
        }
    }

    /**
     * Calculates and returns the total duration of all songs in the playlist.
     *
     * @return The total duration of all songs in seconds.
     */
    public int getTotalDuration() {
        // Initialize the total duration to 0
        int totalDuration = 0;
        // Traverse the playlist and accumulate the duration of each song
        SongNodeSingly current = head;
        while (current != null) {
            totalDuration += current.duration; 
            current = current.next;
        }
        return totalDuration;
    }

    /**
     * Returns the current size of the playlist.
     *
     * @return The number of songs in the playlist.
     */
    public int size() {
        // Initialize the count to 0
        int count = 0; 
        // Start from the head of the list
        SongNodeSingly current = head; 

        // Traverse the list and count the number of nodes
        while (current != null) {
            count++; 
            current = current.next; 
        }

        return count; 
    }

}
