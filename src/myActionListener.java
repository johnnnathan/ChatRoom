package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class myActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String name = Frame.nameField.getText();
        String password = Frame.passwordField.getText();
        try {
            Frame.checkLogins(name,password);

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
