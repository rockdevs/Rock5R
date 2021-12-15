package context.abstracts;

import javax.swing.*;
import java.io.IOException;

public interface FrameInitializer {

    void start();

    void init() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException;

    void postConstruction() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException;

    void preDestroy();

}
