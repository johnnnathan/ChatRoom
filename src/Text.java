package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Text {

    private final String sender, receiver,text,datetime;

    public final static SimpleDateFormat timeformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");


    public Text(String sender, String receiver, String text){
        this.sender = sender;
        this.receiver = receiver;
        this.text = text;
        this.datetime = timeformat.format(new Date());

    }


    public Text(String sender, String text) throws FileNotFoundException {
        this.sender = sender;
        this.text = text;
        this.datetime = timeformat.format(new Date());
        this.receiver = DecodeReceiver(this.text);

    }

    public String DecodeReceiver(String text) throws FileNotFoundException {
        Pattern pattern = Pattern.compile("@\\w+");
        Matcher matcher = pattern.matcher(text);
        String comparedname;


        if (matcher.find()){
            String temp = matcher.group();
            temp = temp.substring(1);


            File names = new File("names");
            Scanner nameScanner = new Scanner(names);

            while (nameScanner.hasNextLine()){
                comparedname = nameScanner.nextLine();
                if (comparedname.equals(temp)){
                    nameScanner.close();
                    return temp;
                }
            }
            nameScanner.close();
        }
        return null;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getText() {
        return text;
    }

    public String getSender() {
        return sender;
    }
}
