/**
 * Represents a node in a singly linked list for storing information about a song.
 */
public class SongNodeSingly {
    String title;              // The title of the song
    String artist;             // The artist who performed the song
    int duration;              // Duration of the song in seconds
    SongNodeSingly next;       // Reference to the next song node

    /**
     * Constructs a new SongNodeSingly with given title, artist, and duration.
     * @param title The title of the song
     * @param artist The artist who performed the song
     * @param duration The duration of the song in seconds
     */
    public SongNodeSingly(String title, String artist, int duration) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.next = null;    // Initially no next node
    }

    /**
     * Returns a string representation of the SongNodeSingly.
     * @return A string containing the title, artist, and duration of the song
     */
    @Override
    public String toString() {
        return title + " by " + artist + " (" + duration + " seconds)";
    }
}
