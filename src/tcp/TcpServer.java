package tcp;

import java.net.*;
import java.io.*;

public class TcpServer {
    //initialize socket and input stream
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;

    public TcpServer(int port) {
        try {
            server = new ServerSocket(port);
            System.out.println("tcp.Server started");

            System.out.println("Waiting for a client ...");

            socket = server.accept();
            System.out.println("tcp.Client accepted");

            // takes input from the client socket
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            String line = "";

            while (!line.equals("Over")) {
                try {
                    line = in.readUTF();
                    System.out.println(line);
                } catch(IOException i) {
                    System.out.println(i);
                }
            }
            System.out.println("Closing connection");
            // close connection
            socket.close();
            in.close();
        } catch(IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String args[]) {
        TcpServer server = new TcpServer(5000);
    }
}