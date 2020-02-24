package Multi;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server extends Thread {
    Socket client;
    BufferedReader inClient;
    DataOutputStream outClient;
    String input = null;
    public Server (Socket s){
        this.client = s;
    }
    @Override
    public void run(){
        comunica();
    }
    public void comunica(){
        try {
            inClient = new BufferedReader (new InputStreamReader(client.getInputStream()));
            outClient = new DataOutputStream(client.getOutputStream());
            while(true){
                input = inClient.readLine();
                System.out.println(input);
                if(input != null || !"FINE".equals(input)){
                    System.out.println("Hai un nuovo messaggio!: \n"+input);
                }
                else{
                    System.out.println("Mi sto chiudendo...");
                    break;
                }
            }
        } catch (IOException ex) {
            System.out.println("Errore I/O");
        }
        finally{
            try {
                inClient.close();
                outClient.close();
                client.close();
            } catch (IOException ex) {
                System.out.println("Errore durante la chiusura del Socket");
            }
        }
    }
}
