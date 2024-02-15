package src;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    static Socket clientSocket;
    static BufferedReader reader;
    static PrintWriter writer;
    static boolean active = true;

    public static void main(String[] args) throws IOException {
        clientSocket = new Socket("127.0.0.1", 8080);//create clientSocket connected to same address as server
        reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        writer = new PrintWriter(clientSocket.getOutputStream(), true);

        Thread sendMessage = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (active) {
                String msg = scanner.nextLine();
                writer.println(msg);
            }
        });
        sendMessage.start();

        Thread receiveMessage = new Thread(() -> {
            try {
                String msg;
                while ((msg = reader.readLine()) != null) {
                    System.out.println("Server: " + msg);
                }
                System.out.println("End of Conversation");
                reader.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        receiveMessage.start();
    }
}
