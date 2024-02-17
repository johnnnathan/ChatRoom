package src;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8080);
        System.out.println("Started, waiting for clients...");
        while (true){
            Socket clientSocket = server.accept();
            System.out.println("Client found");
            Thread clientHandler = new Thread(new ClientHandler(clientSocket));
            clientHandler.start();
        }


    }
}
