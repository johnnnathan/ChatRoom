package src;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client{
    Socket clientSocket;

    BufferedReader reader;
    PrintWriter writer;
    static boolean active = true;
    private String name;
    private Socket socket;

//    public Client(Socket socket){
//        this.socket = socket;
//
//    }
    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("127.0.0.1", 8080);
//        Client client = new Client(clientSocket);
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // read from server
        PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(),true); //write to server
        Thread sendMessage = new Thread( ()-> {
            String msg;
            Scanner scanner = new Scanner(System.in);//read from user

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
                    System.out.println(msg);//write to user terminal
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

//    public Socket getSocket(){return this.socket;}
//    public String getName(){
//        if (this.name != null){return this.name;}
//        JOptionPane.showMessageDialog(null, "User has no name");
//        return null;
//    }
}
