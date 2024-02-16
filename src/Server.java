package src;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8080);
        System.out.println("Started, waiting for clients...");
        while (true){
            try(Socket clientSocket = server.accept();){
                System.out.println("Client connected");
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                String msg;
                while (true){
                    msg = reader.readLine();
                    System.out.println(msg);

                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


        }


    }
}
