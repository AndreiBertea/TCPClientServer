package Multi;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MultiThread {
    public void start(){
        try {
            ServerSocket sSocket = new ServerSocket(2002);
            while(true){
                System.out.println("Server in attesa...");
                Socket s = sSocket.accept();
                System.out.println("Connesso!");
                Server serverThread = new Server(s);
                serverThread.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(MultiThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
