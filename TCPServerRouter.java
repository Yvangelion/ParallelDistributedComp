import java.net.*;
import java.io.*;

public class TCPServerRouter {
    public static void main(String[] args) throws IOException {
        Socket clientSocket = null; // socket for the thread
        Object[][] RoutingTable = new Object[10][2]; // routing table
        int SockNum = 5555; // port number
        boolean Running = true;
        int ind = 0; // index in the routing table
      
        // Variables for data collection
        int totalBytesReceived = 0;
        int numberOfMessages = 0;
        long totalTransmissionTime = 0;

        // Accepting connections
        ServerSocket serverSocket = null; // server socket for accepting connections
        try {
            serverSocket = new ServerSocket(5555);
            System.out.println("ServerRouter is Listening on port: 5555.");
        } catch (IOException e) {
            System.err.println("Could not listen on port: 5555.");
            System.exit(1);
        }

        // Creating threads with accepted connections
        while (Running) {
            try {
                clientSocket = serverSocket.accept();
                SThread t = new SThread(RoutingTable, clientSocket, ind); // creates a thread with a random port

                // Data collection for routing-table lookup time
                long routingTableLookupStartTime = System.currentTimeMillis();

                t.start(); // starts the thread

                // Data collection for routing-table lookup time
                long routingTableLookupEndTime = System.currentTimeMillis();
                System.out.println("Routing-Table Lookup Time: " + (routingTableLookupEndTime - routingTableLookupStartTime) + " ms");

                ind++; // increments the index
                System.out.println("ServerRouter connected with Client/Server: " + clientSocket.getInetAddress().getHostAddress());
            } catch (IOException e) {
                System.err.println("Client/Server failed to connect.");
                System.exit(1);
            }
        }// end while

        // Closing connections
        // Note: In a real-world scenario, closing connections would be handled within each thread.

        // Data collection for average message size, average transmission time
        // Calculate and print the average message size
        double avgMessageSize = (double) totalBytesReceived / numberOfMessages;
        System.out.println("Average Message Size: " + avgMessageSize + " bytes");

        // Calculate and print the average transmission time
        double avgTransmissionTime = (double) totalTransmissionTime / numberOfMessages;
        System.out.println("Average Transmission Time: " + avgTransmissionTime + " ms");

        serverSocket.close();
    }
}
