package udp;

import java.io.*;
import java.net.*;

public class UdpServer {

        private DatagramSocket datagramSocket;
        private byte[] buffer = new byte[256];


        public UdpServer(DatagramSocket datagramSocket) throws SocketException {
            this.datagramSocket = datagramSocket;
        }
        public void receiveThenSend(){
            while(true){
                try{
                    DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
                    datagramSocket.receive(datagramPacket);
                    InetAddress inetAddress = datagramPacket.getAddress();
                    int port = datagramPacket.getPort();
                    String messageFromClient = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                    System.out.println("Message from client: " + messageFromClient);
                    datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, port);
                    datagramSocket.send(datagramPacket);
                } catch (IOException e){
                    e.printStackTrace();
                    break;
                }
            }
        }

        public static void main(String[] args) throws SocketException {
            DatagramSocket datagramSocket = new DatagramSocket(1234);
            UdpServer udpServer = new UdpServer(datagramSocket);
            udpServer.receiveThenSend();
        }

    }
