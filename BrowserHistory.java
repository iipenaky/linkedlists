import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The BrowserHistory class manages a browsing history using a doubly linked list.
 * It allows adding, removing, displaying, saving, and loading history records.
 */
public class BrowserHistory {
    // The head or first history of the doubly linked list
    private HistoryNode head;
    
    // The tail or last hitory of the doubly linked list
    private HistoryNode tail;

    /**
     * Constructs an empty BrowserHistory.
     */
    public BrowserHistory() {
        // Initialize head to null indicating that the list is empty when created
        this.head = null; 
        // Initialize tail to null indicating that the list is empty when created
        this.tail = null; 
    }

    /**
     * Adds a new page to the browsing history.
     * 
     * @param url The URL of the webpage
     * @param timestamp The timestamp of when the webpage was visited
     */
    public void addPage(String url, String timestamp) {
        // Create a new history node with the given URL and timestamp
        HistoryNode newNode = new HistoryNode(url, timestamp);
        // If the list is empty, set both head and tail to the new node
        if (head == null) { 
            head = newNode;
            tail = newNode;
        } 
        // Otherwise, link the current tail to the new node, link the new node back to the current tail, and update the tail to the new node
        else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode; 
        }
        System.out.println("History has been added successfully.");
    }

    /**
     * Removes pages from the browsing history based on the timestamp.
     * 
     * @param timestamp The timestamp of the pages to remove
     */
    public void removePage(String timestamp) {
        // Condition to check if the list is empty
        if (head == null) {
            System.out.println("Browsing history is empty. No pages to remove.");
            return;
        }
        
        boolean found = false;
        
        // Remove nodes from the beginning if they match
        while (head != null && head.timestamp.equalsIgnoreCase(timestamp)) {
            found = true;
            head = head.next;
            if (head != null) {
                head.prev = null;
            } else {
                // If head becomes null, update tail as well
                tail = null;
            }
        }
        
        // Remove nodes from the middle and end
        HistoryNode current = head;
        while (current != null) {
            if (current.timestamp.equalsIgnoreCase(timestamp)) {
                found = true;
                
                // Remove current node
                if (current.prev != null) {
                    current.prev.next = current.next;
                }
                if (current.next != null) {
                    current.next.prev = current.prev;
                }
                
                // Update tail if current is the tail
                if (current == tail) {
                    tail = current.prev;
                }
            }
            current = current.next;
        }
        
        if (found == false) {
            System.out.println("No pages found with the timestamp: " + timestamp);
        } else {
            System.out.println("All pages with the timestamp " + timestamp + " have been removed.");
        }
    }
    
    

    /**
     * Displays the browsing history from the beginning to the end.
     */
    public void displayHistoryForward() {
        // Condition to check if the list is empty
        if (head == null) {
            System.out.println("Browsing history is empty.");
            return;
        } 
        // Set current to head to start printing from the head
        HistoryNode current = head;
        System.out.println("Browser History Forwards:");
        while (current != null) {
            // Print the timestamp and URL of the current node
            System.out.println(current.timestamp + ": " + current.url);
            current = current.next;
        }
        if (current == null){
            System.out.println("There are no more histories to show.");
        }
    }

    /**
     * Displays the browsing history from the end to the beginning.
     */
    public void displayHistoryBackward() {
        // Condition to check if the list is empty
        if (tail == null) { 
            System.out.println("Browsing history is empty.");
            return;
        }
        // Set current to tail to start printing from the tail
        HistoryNode current = tail; 
        System.out.println("Browser History Backwards:");
        while (current != null) {
            // Print the timestamp and URL of the current node
            System.out.println(current.timestamp + ": " + current.url); 
            current = current.prev; 
        }
        if (current == null){
            System.out.println("There are no more histories to show.");
        }
    }

    /**
     * Saves the browsing history to a file named "History.txt".
     * 
     * @throws IOException If an I/O error occurs
     */
    public void saveHistoryToFile() throws IOException {
        // Condition to check if the list is empty
        if (head == null) {
            System.out.println("Browsing history is empty. Nothing to save.");
            return;
        }
        // Create a FileWriter to write to the file
        FileWriter writer = new FileWriter("History.txt"); 
        // Set current to head to start writing from the head
        HistoryNode current = head;
        while (current != null) {
            // Write the URL and timestamp of the current node to the file
            writer.write(current.url + "," + current.timestamp + "\n");
            current = current.next; 
        }
        // Close the writer
        writer.close(); 
        System.out.println("Browsing history saved to file.");
    }

    /**
     * Loads the browsing history from a file named "History.txt".
     * 
     * @throws IOException If an I/O error occurs
     */
    @SuppressWarnings("resource")
    public void loadHistoryFromFile() throws IOException {
        // Open the file
        File myObj = new File("History.txt"); 
        // Create a scanner to read the file
        Scanner reader = new Scanner(myObj); 
        // Condition to check if the file is empty
        if (reader.hasNext() == false) {
            System.out.println("The file is empty.");
            return;
        }
        // Close the scanner so we can open it again to read if the file is not empty
        reader.close();
        // Reopen the scanner
        reader = new Scanner(myObj);
        while (reader.hasNextLine()) {
            // Read a line from the file
            String line = reader.nextLine(); 
            // Split the line into URL and timestamp
            String[] parts = line.split(","); 
            // Add the page to the history
            addPage(parts[0], parts[1]); 
        }
        System.out.println("History loaded successfully.");
        // Close the scanner
        reader.close(); 
    }

}
