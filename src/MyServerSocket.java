
import java.io.*;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyServerSocket {

    private ServerSocket server;

    // Sockets
    Socket clientSocket = null;
    ExecutorService pool = null;
    int clientCount = 0;
    ArrayList<Socket> socketsClient = new ArrayList<>();
    HashMap<BigInteger, BigInteger> cleClient = new HashMap<>();
    ArrayList<String> nomUtilisateur= new ArrayList<>();


    public MyServerSocket(String ipAddress) throws Exception {
        if (ipAddress != null && !ipAddress.isEmpty())
            this.server = new ServerSocket(6652, 1, InetAddress.getByName(ipAddress));
        else
            this.server = new ServerSocket(6652, 1, InetAddress.getLocalHost());
        pool = Executors.newFixedThreadPool(5);
    }
    private void listen() throws Exception {
        String data = null;
        while (true){
            clientSocket = this.server.accept();
            String clientAddress = clientSocket.getInetAddress().getHostAddress();
            System.out.println("\r\nNew connection from " + clientAddress);
            clientCount++;
            socketsClient.add(clientSocket);
            this.broadcast("Hehe",clientSocket);
            //ServerThread runnable= new ServerThread(clientSocket , this);
            //pool.execute(runnable);
        }

    }


    public InetAddress getSocketAddress() {
        return this.server.getInetAddress();
    }

    public int getPort() {
        return this.server.getLocalPort();
    }

    public static void main(String[] args) throws Exception {
        MyServerSocket app = new MyServerSocket(args[0]);
        System.out.println("\r\nRunning Server: " +
                "Host=" + app.getSocketAddress().getHostAddress() +
                " Port=" + app.getPort());

        app.listen();
    }

    /***
     *
     * @param data
     * @throws IOException
     */
    synchronized public void broadcast(String data, Socket socketExcepted) throws IOException {
        PrintWriter cout ;
        Socket client;
        System.out.println("BROADCASTING MESSAGE");
        for (int i = 0; i < socketsClient.size(); i++) {
            if(socketsClient.get(i).equals(socketExcepted))
                continue;
            client = socketsClient.get(i);
            cout = new PrintWriter(client.getOutputStream(), true);
            cout.println(data);
            cout.flush();
        }
    }
}