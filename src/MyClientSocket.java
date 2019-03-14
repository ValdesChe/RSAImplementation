import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import security.RSA;

/**
 * Created by ValdoR on 2019-03-13.
 */
import java.util.Scanner;
public class MyClientSocket {
    private Socket socket;
    private Scanner scanner;
    private MyClientSocket(InetAddress serverAddress, int serverPort) throws Exception {
        this.socket = new Socket(serverAddress, serverPort);
        this.scanner = new Scanner(System.in);
    }
    private void start() throws IOException {
        String input;
        boolean quit = false;
        RSA rsa = new RSA(150);
        System.out.println(rsa.toString());

        input = "c"+rsa.getPublicKey();
        PrintWriter firstOut = new PrintWriter(this.socket.getOutputStream(), true);
        firstOut.println(input);
        firstOut.flush();

        while (true && !quit) {
            input = scanner.nextLine();
            PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
            out.println(input);
            out.flush();
        }
    }

    public static void main(String[] args) throws Exception {
        MyClientSocket client = new MyClientSocket(
                InetAddress.getByName(args[0]),
                Integer.parseInt(args[1]));


        System.out.println("\r\nConnected to Server: " + client.socket.getInetAddress());
        client.start();
    }
}