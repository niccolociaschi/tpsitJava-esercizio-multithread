import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerStr extends Thread {
    ServerSocket server = null;
    Socket socket;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;

    public ServerStr() { //costruttore
        
        this.client = socket;
    }

    public void run() {
        try {
            comunica();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public void comunica() throws Exception {
        inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        outVersoClient = new DataOutputStream(client.getOutputStream());
        for (;;) {
            stringaRicevuta = inDalClient.readLine();
            if (stringaRicevuta == null || stringaRicevuta.equals("FINE")) {
                outVersoClient.writeBytes(stringaRicevuta + "(=> server in chiusura...)" + '\n'); // traduce l'output//
                                                                                                  // del client in bytes
                                                                                                  // in modo da poterlo
                                                                                                  // mandare al server
                                                                                                  // in linguaggio
                                                                                                  // macchina
                System.out.println("Echo sul server in chiusura:" + stringaRicevuta);
            } // if
            else {
                outVersoClient.writeBytes(stringaRicevuta + "(ricevuta e ritrasmessa)" + '\n');
                System.out.println("6...echo sul server" + stringaRicevuta);
            } // else
            if (stringaRicevuta == null || stringaRicevuta.equals("STOPPA")) {
                outVersoClient.writeBytes(stringaRicevuta + "(=> server in chiusura...)" + '\n');
                System.out.println("9.1 chiusura server" + server); 
                client.close();
                server.close();
                break;
            } // if2
        } // for
        outVersoClient.close();
        inDalClient.close();
        System.out.println("9 chiusura socket" + client + '\n');
        client.close();
    } // comunica

    public class MultiServer {
public void start(){
    try{
ServerSocket serverSocket= new ServerSocket(1234);
for(;;){
System.out.println("1 server in attesa ..");
Socket socket= serverSocket.accept();
System.out.println("3 Server socket "+ socket);
ServerThread serverThread = new ServerThread(server);  //prof ho provato ad cambiare un pò di cose anche seguendo il libro ma continua a dirmi che il ServerThread non può essere "risolto" in un tipo, e non so cosa vuoldire
serverThread.start();
} //for
    }//tryy
catch(

        Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("errore durante l'istanza del server!");
            System.exit(1);
        } //catch
    } //void start
    } // class MUltiserver

    public static void main(String args[]){
ServerStr Google = new ServerStr();
Google.start();
} // MAIN

} // class
