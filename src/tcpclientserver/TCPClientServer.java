/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* prova Git */
/* prova Git2 */
package tcpclientserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.ConnectException;

public class TCPClientServer {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        //oggetto da usare per realizzare la connessione TCP
        Socket connection = null;
        //nome o IP del server
        String serverAddress = "localhost";
        //porta del server in ascolto
        int port = 2001;
        String tast =  null;

        //apertura della connessione al server sulla porta specificata
        try{
            connection = new Socket(serverAddress, port);
            System.out.println("Connessione aperta");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            BufferedReader tastiera = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Scrivi un messaggio: ");
            tast = tastiera.readLine();
            out.writeBytes(tast);
                    
        }
        catch(ConnectException e){
            System.err.println("Server non disponibile!");
        }
        catch(UnknownHostException e1){
            System.err.println("Errore DNS!");
        }

        catch(IOException e2){//
            System.err.println(e2);
            e2.printStackTrace();
        }

        //chiusura della connnessione
        finally{
                try {
            if (connection!=null)
                {
                    connection.close();
                    System.out.println("Connessione chiusa!");
                }
            }
            catch(IOException e){
                System.err.println("Errore nella chiusura della connessione!");
            }
        }
    }
}