package rmi;

import javax.swing.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {
    private static RMIInterface look_up;

    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {

        look_up = (RMIInterface) Naming.lookup("//localhost/MyServer");
        String txt = JOptionPane.showInputDialog("What is your name?");

        String response1 = look_up.helloTo(txt);
        String response2 = look_up.echo(txt);
        JOptionPane.showMessageDialog(null, response1 + " -> " + response2);
    }
}
