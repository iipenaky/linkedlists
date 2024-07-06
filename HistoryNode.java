/**
 * The HistoryNode class represents a node in a doubly linked list
 * used to store browsing history with URLs and timestamps.
 */
public class HistoryNode {
    // The URL of the webpage 
    String url;
    
    // The timestamp of when the webpage was visited */
    String timestamp;
    
    // The previous node in the linked list 
    HistoryNode prev;
    
    // The next node in the linked list
    HistoryNode next;

    /**
     * Constructs a HistoryNode with the given URL and timestamp.
     * 
     * @param url The URL of the webpage
     * @param timestamp The timestamp of when the webpage was visited
     */
    public HistoryNode(String url, String timestamp) {
        this.url = url;           // Initialize the URL
        this.timestamp = timestamp; // Initialize the timestamp
        this.prev = null;         // The previous node is initially null
        this.next = null;         // The next node is initially null
    }
}
