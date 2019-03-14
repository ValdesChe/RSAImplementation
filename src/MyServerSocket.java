
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
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
    HashMap<Integer, Integer> cleClient = new HashMap<>();


    public MyServerSocket(String ipAddress) throws Exception {
        if (ipAddress != null && !ipAddress.isEmpty())
            this.server = new ServerSocket(6652, 1, InetAddress.getByName(ipAddress));
        else
            this.server = new ServerSocket(6652, 1, InetAddress.getLocalHost());
        pool = Executors.newFixedThreadPool(5);
    }
    private void listen() throws Exception {
        String data = null;
        clientSocket = this.server.accept();
        String clientAddress = clientSocket.getInetAddress().getHostAddress();
        System.out.println("\r\nNew connection from " + clientAddress);
        clientCount++;
        ServerThread runnable= new ServerThread(clientSocket , clientCount , this);
        pool.execute(runnable);
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



}