import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public void run() throws UnknownHostException, IOException {

        int port = 8010;
        // the above port defines the port hwich the client gonna give req
        // the port of the client will be automatticaly assigned by os with the available port
        InetAddress address = InetAddress.getByName("localhost");

        Socket socket = new Socket(address, port);
        System.out.println("Connected to server at " + socket.getRemoteSocketAddress());

        // Send data to the server
        PrintWriter toSocket = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader fromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // Receive the initial message from the server
        String serverMessage = fromSocket.readLine();
        System.out.println("Server: " + serverMessage);

        // Send a message to the server
        toSocket.println("From Client");
        
        // Receive and print the response from the server
        String line = fromSocket.readLine();
        System.out.println("Response from the server: " + line);

        // Close the streams and socket
        toSocket.close();
        fromSocket.close();
        socket.close();
    }

    public static void main(String[] args) {
        try {
            Client client = new Client();
            client.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
