package rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements RMIInterface{

    protected Server() throws RemoteException {
        super();
    }

    @Override
    public String helloTo(String name) throws RemoteException{
        System.err.println(name + " is trying to contact!");
        return "Server says hello to " + name;
    }

    @Override
    public String echo(String aString) throws RemoteException{
        System.err.println(aString + " is trying to reverse! -> " + new StringBuilder(aString).reverse());
        return new StringBuilder(aString).reverse().toString();
    }

    public static void main(String[] args){
        try {
            Naming.rebind("//localhost/MyServer", new Server());
            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
