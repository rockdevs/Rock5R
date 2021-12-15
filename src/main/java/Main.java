import context.abstracts.Rock5RApplication;
import context.concretes.MainApplication;

import javax.swing.*;
import java.io.IOException;

public class Main {
    static Rock5RApplication application = new MainApplication();
    public static void main(String[] args) {
        try {
            application.run();
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException | IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(new JFrame("Error"),"Unknown exception");
            System.exit(1);
        }
    }
}
