package multiThreaded;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;


public class Server {
    public Consumer<Socket>getConsumer(){
        return (clientSocket)->{
            try{
                PrintWriter toClient=new PrintWriter(clientSocket.getOutputStream());
                toClient.println("Hello this server");
                toClient.close();
                clientSocket.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        };
    }
    public static void main(String[] args) {
        int port=8010;
        try{
            try (ServerSocket socket = new ServerSocket(port)) {
                // socket.setSoTimeout(10000);
                Server server=new Server();
                System.out.println("Hey I am server listening to the port "+port);
                while(true){
                    Socket acceptedSocket=socket.accept();
                    System.out.println("Connection accepted from client " + acceptedSocket.getRemoteSocketAddress());
                    Thread thread=new Thread(()->server.getConsumer().accept(acceptedSocket));
                    thread.start();

                }
            }            
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
}
