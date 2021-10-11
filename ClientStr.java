import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.net.Socket;
import java.rmi.UnknownHostException;

import java.io.*;

public class ClientStr {
    String nomeServer= "ServerGoogle10110"; //il nome del server
    int portaServer = 1234; //la posta del server
    Socket mioSocket; //socket
    BufferedReader tastiera; //input utente
    String stringaUtente; //sttringa inserita 
    String stringaRicevutaDalServer; //flusso entrata
    DataOutputStream outVersoServer;   //flusso di uscita
    BufferedReader inDalServer;   //fluso di entrata
    

public Socket connetti(){
  System.out.println("2 CLIENT partito in esecuzione..."); //cominciamo
try{
tastiera= new BufferedReader(new InputStreamReader(System.in)); // input da tastiera
mioSocket= new Socket(nomeServer,portaServer); //cerca di connettersi trovando gli attributi
outVersoServer= new DataOutputStream(mioSocket.getOutputStream()); //stream di invio
inDalServer = new BufferedReader(new InputStreamReader(mioSocket.getInputStream())); //stream di ricezione
} //try
catch(UnknownHostException e){ //se si presenta un errore riguardo la connessione a Host
System.err.println("Host sconosciuto"); 
}//catch1
catch(Exception e){ //se si presenta un errore 

System.out.println(e.getMessage()); //prende il messaggio
System.out.println("Errore durante la connessione");
System.exit(1); //esce
}//catch2
return mioSocket; //ritorna il socket
} //socket


 
public void comunica(){
  
  for(;;){
    try{
System.out.println("4... inserisci la stringa da trasmettere al server" + '\n');
stringaUtente = tastiera.readLine(); //mette in stringaUtente l'input ricevuto da tasitiera
System.out.println("5...invio la stringa al server e attendo");
outVersoServer.writeBytes(stringaUtente+'\n'); //traduce l'output in bytes in odo che il computer possa leggere il messaggio

stringaRicevutaDalServer=inDalServer.readLine(); //mette in strngaRicevutaDalServer l'input dato dal server
System.out.println("7... risposta dal server" + '\n' + stringaRicevutaDalServer);
if(stringaUtente.equals("FINE")){ //controlla se la stringa inviata è uguale(.equals) a "FINE"
System.out.println("8 CLIENT: termina elaborazione e chiude connessione");
mioSocket.close(); // chiude il flusso
break;
}//if
    }//try
catch(Exception e){
  System.out.println(e.getMessage());
  System.out.println("errore durante la comunicazione col server!");
System.exit(1);
} //catch3
}//for
}//comunica


public static void main(String[] args) { //main
  ClientStr cliente= new ClientStr();
  cliente.connetti();
  cliente.comunica();
}//MAIN
} //class