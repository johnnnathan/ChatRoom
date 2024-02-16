package src;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Socket{
    Socket clientSocket;
    String name;
    BufferedReader reader;
    PrintWriter writer;
    static boolean active = true;
    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("127.0.0.1", 8080);
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(),true);
        Thread sendMessage = new Thread( ()-> {
            String msg;
            Scanner scanner = new Scanner(System.in);

            while (active) {

                msg = scanner.nextLine();
                writer.println(msg);


            }

        });
        sendMessage.start();

        Thread receiveMessage = new Thread(() ->{
            String msg;


            while (active) {
                try {
                    msg = reader.readLine();
                    System.out.println(msg);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            try {
                reader.close();
                clientSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        });
        receiveMessage.start();
    }
}
