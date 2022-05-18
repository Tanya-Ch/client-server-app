package fileTransferTest;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;

public class TestClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1234);
        System.out.println("Connected to server.");

        JFrame jFrame = new JFrame("Client");
        jFrame.setSize(400,400);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon imageIcon = new ImageIcon("/home/tania/Desktop/flower.png");

        JLabel jLabelPic = new JLabel(imageIcon);
        JButton jButton = new JButton("Send image to server.");

        jFrame.add(jLabelPic);
        jFrame.add(jButton);
        jFrame.setVisible(true);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try{
                    OutputStream outputStream = socket.getOutputStream();
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
                    Image image = imageIcon.getImage();
                    BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
                    Graphics graphics = bufferedImage.createGraphics();
                    graphics.drawImage(image, 0, 0, null);
                    graphics.dispose();

                    ImageIO.write(bufferedImage, "png", bufferedOutputStream);
                    bufferedOutputStream.close();
                    socket.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });

    }
}
//
//import java.io.*;
//import java.net.Socket;
//import java.util.Scanner;
//
//public class Client {
//
//    private Socket socket;
//    private BufferedReader bufferedReader;
//    private BufferedWriter bufferedWriter;
//    private String username;
//
//    public Client(Socket socket, String username) {
//        try {
//            this.socket = socket;
//            this.username = username;
//            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            this.bufferedWriter= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//        } catch (IOException e) {
//            closeEverything(socket, bufferedReader, bufferedWriter);
//        }
//    }
//
//    public void sendMessage() {
//        try {
//            bufferedWriter.write(username);
//            bufferedWriter.newLine();
//            bufferedWriter.flush();
//            Scanner scanner = new Scanner(System.in);
//            while (socket.isConnected()) {
//                String messageToSend = scanner.nextLine();
//                bufferedWriter.write(username + ": " + messageToSend);
//                bufferedWriter.newLine();
//                bufferedWriter.flush();
//            }
//        } catch (IOException e) {
//            closeEverything(socket, bufferedReader, bufferedWriter);
//        }
//    }
//
//    public void listenForMessage() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                String msgFromGroupChat;
//                while (socket.isConnected()) {
//                    try {
//                        msgFromGroupChat = bufferedReader.readLine();
//                        System.out.println(msgFromGroupChat);
//                    } catch (IOException e) {
//                        closeEverything(socket, bufferedReader, bufferedWriter);
//                    }
//                }
//            }
//        }).start();
//    }
//
//    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
//        try {
//            if (bufferedReader != null) {
//                bufferedReader.close();
//            }
//            if (bufferedWriter != null) {
//                bufferedWriter.close();
//            }
//            if (socket != null) {
//                socket.close();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) throws IOException {
//
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter your username for the group chat: ");
//        String username = scanner.nextLine();
//        Socket socket = new Socket("localhost", 1234);
//
//        Client resources.client.client = new Client(socket, username);
//        resources.client.client.listenForMessage();
//        resources.client.client.sendMessage();
//    }
//}
