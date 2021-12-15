package context.abstracts;

import javax.swing.*;
import java.io.IOException;

@FunctionalInterface
public interface Rock5RApplication {
    void run() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException;
}
