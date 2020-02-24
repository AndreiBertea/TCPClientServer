package Multi;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Client {
    Socket server;
    BufferedReader tastiera;
    String tast;
    DataOutputStream out;
    BufferedReader in;
    String adress = "localhost";
    int port = 2002;
    
    public Socket connetti(){
        try {
            server = new Socket(adress,port);
            tastiera = new BufferedReader(new InputStreamReader(server.getInputStream()));
            out = new DataOutputStream(server.getOutputStream());
            in = new BufferedReader(new InputStreamReader (server.getInputStream()));
            
        } catch (IOException ex) {
            System.out.println("Errore I/O");
        }
        return server;
    }
    public void comunica(){
        while(true)
            try {
                System.out.println("Scrivi il messaggio: ");
                tast = tastiera.readLine();
                out.writeBytes(tast);
                if(tast.equals("FINE")){
                    System.out.println("Mi sto spegnendo...");
                    break;
                }
            } catch (IOException ex) {
                System.out.println("Non so cosa stia succedendo");
            }
            finally{
            try {
                server.close();
            } catch (IOException ex) {
                System.out.println("errore durante la chiusura");
            }
            }
        
    }
}
