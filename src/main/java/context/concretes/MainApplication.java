package context.concretes;

import context.abstracts.Rock5RApplication;
import context.concretes.frame.MainFrame;

import javax.swing.*;
import java.io.IOException;

public class MainApplication implements Rock5RApplication {
    @Override
    public void run() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        new MainFrame("Rock5R").init();
    }
}
