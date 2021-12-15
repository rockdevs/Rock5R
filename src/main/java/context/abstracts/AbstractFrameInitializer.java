package context.abstracts;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public abstract class AbstractFrameInitializer extends JFrame implements FrameInitializer {

    public AbstractFrameInitializer(String title) throws HeadlessException {
        super(title);
    }

    @Override
    public void init() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        postConstruction();
        preDestroy();
        start();
    }

}
