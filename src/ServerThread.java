import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
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

    ServerThread(Socket client, int count , MyServerSocket server ) throws IOException {

        this.client=client;
        this.server = server;
        this.id=count;

        cin=new BufferedReader(new InputStreamReader(client.getInputStream()));
        cout=new PrintStream(client.getOutputStream());

    }

    @Override
    public void run() {
        int x=1;
        try{
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(client.getInputStream()));
            while ( (data = in.readLine()) != null ) {
                System.out.println("\r\nMessage from " + client.getInetAddress() + ": " + data);
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