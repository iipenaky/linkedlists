/**
 * Represents a node in a doubly linked list for storing information about a song.
 */
public class SongNode {
    String title;        // The title of the song
    String artist;       // The artist who performed the song
    int duration;        // Duration of the song in seconds
    SongNode prev;       // Reference to the previous song node
    SongNode next;       // Reference to the next song node

    /**
     * Constructs a new SongNode with given title, artist, and duration.
     * @param title The title of the song
     * @param artist The artist who performed the song
     * @param duration The duration of the song in seconds
     */
    public SongNode(String title, String artist, int duration) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.prev = null;   // Initially no previous node
        this.next = null;   // Initially no next node
    }

    /**
     * Returns a string representation of the SongNode.
     * @return A string containing the title, artist, and duration of the song
     */
    @Override
    public String toString() {
        return title + " by " + artist + " (" + duration + " seconds)";
    }
}
