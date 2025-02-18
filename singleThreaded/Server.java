import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public void run() throws IOException {
        int port = 8010;
        // the above is the port where the server listens
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);
            String filePath="D:\\Projects\\web-server\\multiThreaded\\file.txt";
            while (true) {
                try {
                    Socket acceptedSocket = serverSocket.accept();
                    System.out.println("Connection accepted from client " + acceptedSocket.getRemoteSocketAddress());

                    // Output (server to client)
                    PrintWriter toClient = new PrintWriter(acceptedSocket.getOutputStream(), true);
                    // Input (client to server)
                    BufferedReader fromClient = new BufferedReader(new InputStreamReader(acceptedSocket.getInputStream()));

                    // Send a message to the client
                    try(BufferedReader fileReader=new BufferedReader(new FileReader(filePath))){
                        String line;
                        while((line=fileReader.readLine())!=null){
                            toClient.println(line);
                        }
                    }
                    
                    toClient.println("This is from server. Send a message:");

                    // Read the client's message
                    String clientMessage = fromClient.readLine();
                    System.out.println("Received from client: " + clientMessage);

                    // Close resources for this client
                    toClient.close();
                    fromClient.close();
                    acceptedSocket.close(); // Close only this client's socket
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
    }

    public static void main(String[] args) {
        try {
            Server server = new Server();
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
