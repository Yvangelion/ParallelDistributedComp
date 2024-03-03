import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) throws IOException {

        // Variables for setting up connection and communication
        Socket socket = null; // socket to connect with ServerRouter
        PrintWriter out = null; // for writing to ServerRouter
        BufferedReader in = null; // for reading from ServerRouter
        InetAddress addr = InetAddress.getLocalHost();
        String host = addr.getHostAddress(); // Client machine's IP
        String routerName = "127.0.0.2";
        int sockNum = 5555; // port number

        // Tries to connect to the ServerRouter
        try {
            long routingTableLookupStartTime = System.currentTimeMillis();
            socket = new Socket(routerName, sockNum);
            long routingTableLookupEndTime = System.currentTimeMillis();
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Routing-Table Lookup Time: " + (routingTableLookupEndTime - routingTableLookupStartTime) + " ms");
        } catch (UnknownHostException e) {
            System.err.println("Don't know about router: " + routerName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: " + routerName);
            System.exit(1);
        }

        // Variables for message passing
        Reader reader = new FileReader("file.txt");
        BufferedReader fromFile = new BufferedReader(reader); // reader for the string file
        String fromServer; // messages received from ServerRouter
        String fromUser; // messages sent to ServerRouter
        String address = "127.0.0.1"; // destination IP (Server)
        long t0, t1, t;
        int totalBytesSent = 0; // Variable to track total bytes sent

        // Communication process (initial sends/receives
        out.println(address);// initial send (IP of the destination Server)
        fromServer = in.readLine();// initial receive from router (verification of connection)
        System.out.println("ServerRouter: " + fromServer);
        out.println(host); // Client sends the IP of its machine as an initial send
        t0 = System.currentTimeMillis();

        // Start sending messages from the file
        int numberOfMessages = 0;
        long totalTransmissionTime = 0;
        while ((fromUser = fromFile.readLine()) != null) {
            System.out.println("Client: " + fromUser);
            out.println(fromUser); // Send the message to the server via ServerRouter
            totalBytesSent += fromUser.getBytes().length; // Track total bytes sent
            numberOfMessages++;

            if ((fromServer = in.readLine()) != null) { // Wait for a response
                System.out.println("Server: " + fromServer);
                t1 = System.currentTimeMillis();
                t = t1 - t0;
                System.out.println("Transmission Time: " + t + " ms");
                totalTransmissionTime += t;

                if (fromServer.equals("Bye.")) // Check for exit condition
                    break;

                t0 = System.currentTimeMillis();
            }
        }

        // Calculate and print the average message size
        double avgMessageSize = (double) totalBytesSent / numberOfMessages;
        System.out.println("Average Message Size: " + avgMessageSize + " bytes");

        // Calculate and print the average transmission time
        double avgTransmissionTime = (double) totalTransmissionTime / numberOfMessages;
        System.out.println("Average Transmission Time: " + avgTransmissionTime + " ms");

        // Closing connections
        out.close();
        in.close();
        socket.close();
    }
}
