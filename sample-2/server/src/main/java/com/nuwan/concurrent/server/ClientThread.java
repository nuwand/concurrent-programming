package com.nuwan.concurrent.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClientThread extends Thread{

    Socket serverClient;
    int clientNo;
    int square;

    ClientThread(Socket inSocket, int counter) {

        serverClient = inSocket;
        clientNo = counter;
    }

    public void run() {

        try {
            DataInputStream inStream = new DataInputStream(serverClient.getInputStream());
            DataOutputStream outStream = new DataOutputStream(serverClient.getOutputStream());
            String clientMessage = "", serverMessage = "";
            while (!clientMessage.equals("bye")) {
                clientMessage = inStream.readUTF();
                System.out.println("From Client-" + clientNo + ": Number is :" + clientMessage);
                square = Integer.parseInt(clientMessage) * Integer.parseInt(clientMessage);
                serverMessage = "From Server to Client-" + clientNo + " Square of " + clientMessage + " is " + square;
                outStream.writeUTF(serverMessage);
                outStream.flush();
            }
            inStream.close();
            outStream.close();
            serverClient.close();
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            System.out.println("Client -" + clientNo + " exit!! ");
        }
    }

}
