package src;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Server started. Waiting for clients...");
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)) {
                    System.out.println("Client connected: " + clientSocket.getInetAddress());

                    String msg;
                    while ((msg = reader.readLine()) != null) {
                        System.out.println("Client: " + msg);
                        writer.println("Server received: " + msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
