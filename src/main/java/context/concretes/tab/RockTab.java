package context.concretes.tab;



import context.concretes.tabbed.BaseTabbedPane;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class RockTab extends JTabbedPane {
    private final ArrayList<Component> components = new ArrayList<>();

    private final Font fontTitle = new Font("Arial", Font.BOLD, 12);

    public RockTab() {}

    public void init(){
        this.setFont(fontTitle);
    }

    public void addTab(Component component) throws IOException {
        init();
        if(components.size()<6){
            components.add(component);
            Image iconExecute = ImageIO.read(new File("src/main/java/icon/cancel_dark.png"));
            this.addTab("Unknown Request",new ImageIcon(iconExecute),component);
            if(component instanceof BaseTabbedPane pane)
                pane.initPane();
        }else {
            JOptionPane.showMessageDialog(this,"Tabs count must be low from 5");
        }
    }

    @Override
    public void addAncestorListener(AncestorListener listener) {
        super.addAncestorListener(listener);
    }

    public void deleteTab(Component component){
        this.remove(component);
        components.remove(component);
    }



}
