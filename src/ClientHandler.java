package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable{
    private Socket socket;
    private String name;

    public ClientHandler(Socket clientSocket){
        this.socket = clientSocket;
    }
    @Override
    public void run() {
        try {

            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner scanner = new Scanner(System.in);
            writer.println("What's your name");
            this.name = reader.readLine();

            String msg;
            System.out.println("Your name is: " + this.name + "! Welcome to the server.");
            writer.println("Your name is: " + this.name + "! Welcome to the server.");
            while ((msg = reader.readLine())!= null){
                writer.println("You" + ": " + msg);
                System.out.println(this.name + ": " + msg);
                
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
