import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) throws IOException {

        // Variables for setting up connection and communication
        Socket socket = null; // socket to connect with ServerRouter
        PrintWriter out = null; // for writing to ServerRouter
        BufferedReader in = null; // for reading form ServerRouter
        InetAddress addr = InetAddress.getLocalHost();
        String host = addr.getHostAddress(); // Server machine's IP
        String routerName = "127.0.0.1"; // ServerRouter host name
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
        String fromServer; // messages sent to ServerRouter
        String fromClient; // messages received from ServerRouter
        String address = "127.0.0.2"; // destination IP (Client)
        long t0, t1, t;
        int totalBytesReceived = 0; // Variable to track total bytes received
        int numberOfMessages = 0; // Variable to track the number of messages received

        // Communication process (initial sends/receives)
        out.println(address);// initial send (IP of the destination Client)
        fromClient = in.readLine();// initial receive from router (verification of connection)
        System.out.println("ServerRouter: " + fromClient);

        // Communication while loop
        t0 = System.currentTimeMillis();
        while ((fromClient = in.readLine()) != null) {
            numberOfMessages++; // Increment the number of messages received
            System.out.println("Client said: " + fromClient);
            if (fromClient.equals("Bye.")) // exit statement
                break;
            totalBytesReceived += fromClient.getBytes().length; // Track total bytes received
            fromServer = fromClient.toUpperCase(); // converting received message to upper case
            System.out.println("Server said: " + fromServer);
            out.println(fromServer); // sending the converted message back to the Client via ServerRouter
            t1 = System.currentTimeMillis();
            t = t1 - t0;
            System.out.println("Transmission Time: " + t + " ms");
            t0 = System.currentTimeMillis();
        }

        // Calculate and print the average message size
        double avgMessageSize = (double) totalBytesReceived / numberOfMessages;
        System.out.println("Average Message Size: " + avgMessageSize + " bytes");

        // Closing connections
        out.close();
        in.close();
        socket.close();
    }
}
