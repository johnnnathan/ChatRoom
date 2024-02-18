package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Frame extends JFrame {
    public static JFrame MainFrame = new JFrame();
    public static JTextField passwordField = new JTextField(20);
    public static JTextField nameField = new JTextField(20);
    public static JPanel MainPanel = new JPanel();

    public static JButton createAccountButton = new JButton("Create Account");

    public static JLabel nameLabel = new JLabel("Name: ");
    public static JLabel passLabel = new JLabel("Password: ");




    public static void LoginPage() throws IOException {
        MainPanel.setLayout(null);
        MainFrame.setSize(400,400);
        myActionListener actionListener = new myActionListener();
        nameField.addActionListener(actionListener);
        passwordField.addActionListener(actionListener);

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    createAccount(nameField.getText(),passwordField.getText());

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        MainFrame.setResizable(false);
        MainPanel.add(passwordField, BorderLayout.NORTH);
        MainPanel.add(nameField, BorderLayout.CENTER);
        MainPanel.add(createAccountButton);
        MainPanel.add(nameLabel);
        MainPanel.add(passLabel);
        passLabel.setBounds(10,100,100,30);
        nameLabel.setBounds(10,40,60,30);
        nameField.setBounds(80,40,240,30);
        passwordField.setBounds(80,100,240,30);
        createAccountButton.setBounds(140,160,120,30);
        MainFrame.add(MainPanel);
        MainFrame.setVisible(true);


    }

    public static void createAccount(String name, String password) throws IOException {
        FileWriter fileWriter = null;

        FileWriter fileWriterName = new FileWriter("names", true);
        FileWriter fileWriterPass = new FileWriter("passwords", true);

        fileWriterName.write(name);
        fileWriterName.write("\n");
        fileWriterPass.write("\n");
        fileWriterPass.write(password);
        fileWriterName.flush();
        fileWriterPass.flush();
        fileWriterPass.close();
        fileWriterName.close();
    }
    public static boolean checkLogins(String name, String password) throws IOException {
        File names = new File("names");
        File passes = new File("passwords");
        Scanner nameScanner = new Scanner(names);
        Scanner passScanner = new Scanner(passes);
        int index = -1;
        int counter = 0;
        String comparedname;
        boolean foundFlag = false;
        while (nameScanner.hasNextLine()){
            comparedname = nameScanner.nextLine();
            if (comparedname.equals(name)){index = counter; foundFlag = true;break;}
            counter++;

        }

        if (foundFlag){
            String comparedPass = Files.readAllLines(Paths.get("passwords")).get(index);
            if (comparedPass.equals(password)){
                System.out.println("Accepted");
                ClientHandler.currentName = name;
                ClientHandler.acceptUserFlagafterLogin = true;
                MainFrame.setVisible(false);
                MainFrame.dispose();
                return true;}

        }
        return false;


    }
}