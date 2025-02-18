package threadPool;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final ExecutorService threadPool;
    public Server(int poolSize){
        this.threadPool=Executors.newFixedThreadPool(poolSize);
    }

    public void handleClient(Socket clientSocket){
            try(PrintWriter toSocket=new PrintWriter(clientSocket.getOutputStream(),true)){
                toSocket.println("HELLO this is from server "+clientSocket.getInetAddress());
            }
            catch(Exception e){
                e.printStackTrace();
            }
    }

    public static void main(String[] args) {
        int port=8010;
        int poolSize=10;
        Server server=new Server(poolSize);
        try{
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                System.out.println("Server is listinoing in "+port);
                while(true){
                    Socket clientSocket=serverSocket.accept();
                    // handleClient(clientSocket) is the command (task) that gets passed to the execute() method of ExecutorService.
                    // How It Works
                    // execute(Runnable command) is a method in ExecutorService, where Runnable is the command to execute.
                    // Here, ()-> server.handleClient(clientSocket) creates a Runnable task using a lambda expression.
                    // This Runnable task is then submitted to the thread pool for execution.
                    server.threadPool.execute(()->server.handleClient(clientSocket));
                }
            }
        }
//Analogy
// Think of execute() as a task manager that gets a command (Runnable) and assigns it to a worker.

// Without a thread pool:
// You manually hire a new worker every time (new Thread().start()).
// With a thread pool:
// You submit a task, and the task manager assigns it to an available worker (existing thread).
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
// public interface Executor {

//     /**
//      * Executes the given command at some time in the future.  The command
//      * may execute in a new thread, in a pooled thread, or in the calling
//      * thread, at the discretion of the {@code Executor} implementation.
//      *
//      * @param command the runnable task
//      * @throws RejectedExecutionException if this task cannot be
//      * accepted for execution
//      * @throws NullPointerException if command is null
//      */
//     void execute(Runnable command);
// }