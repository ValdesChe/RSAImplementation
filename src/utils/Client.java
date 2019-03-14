package utils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by ValdoR on 2019-03-12.
 */
public class Client {

    private static String SERVER_ADR = "127.0.0.1";
    private static int SERVER_PORT = 8562;

    ArrayList<Utilisateur> utilisateurs = new ArrayList<>();


    public static void main (String args []) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String username = "";
        do{
            System.out.println("Entrer votre pseudonyme :");
            scanner.nextLine();
        }while ( username.length() < 3 || username.length() > 13 );




        /* Socket socket = new Socket()

        while (true){
            Socket clientSocket =  serverSocket.accept();
            System.out.println("NEW CLIENT");

        }
        */

    }


}
