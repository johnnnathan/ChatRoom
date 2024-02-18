package src;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable{
    private Socket socket;
    private String name;
    public static String currentName = null;
    public static boolean acceptUserFlagafterLogin = false;

    public ClientHandler(Socket clientSocket){
        this.socket = clientSocket;
    }
    @Override
    public void run() {
        try {

            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner scanner = new Scanner(System.in);
            Frame.LoginPage();
            while(!acceptUserFlagafterLogin){
                Thread.sleep(1000);
            }
            this.name = currentName;
            currentName = null;

            String msg;
            System.out.println("Your name is: " + this.name + "! Welcome to the server.");
            writer.println("Your name is: " + this.name + "! Welcome to the server.");
            while ((msg = reader.readLine())!= null){
                writer.println("You" + ": " + msg);
                System.out.println(this.name + ": " + msg);
                
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


}
