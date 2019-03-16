import utils.MessageParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.math.BigInteger;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by ValdoR on 2019-03-14.
 */
public class ServerThread implements Runnable {

    MyServerSocket server=null;
    Socket client=null;
    BufferedReader cin;
    PrintStream cout;
    Scanner sc=new Scanner(System.in);
    int id;
    String data = null ;

    ServerThread(Socket client, MyServerSocket server ) throws IOException {

        this.client=client;
        this.server = server;

        cin=new BufferedReader(new InputStreamReader(client.getInputStream()));
        cout=new PrintStream(client.getOutputStream());

    }

    @Override
    public void run() {
        int x=1;

        MessageParser parser;
        try{
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(client.getInputStream()));
            while ( (data = in.readLine()) != null ) {
                System.out.println("\r\n Message from " + client.getInetAddress() + ": " + data);
                parser = new MessageParser(data);
                switch (parser.giveTypeOfMessage()){
                    case 'M' :

                        break;
                    case 'C':
                        String parts[] = data.split("-");

                        server.cleClient.put( new BigInteger(parts[0].substring(1,parts[0].length())), new BigInteger(parts[1]));
                        server.nomUtilisateur.add(parts[2]);
                        server.broadcast(data, client);
                        break;

                }
            }

            cin.close();
            client.close();
            cout.close();
            if(x==0) {
                System.out.println( "Server cleaning up." );
                System.exit(0);
            }
        }
        catch(IOException ex){
            System.out.println("Error : "+ex);
        }


    }
}