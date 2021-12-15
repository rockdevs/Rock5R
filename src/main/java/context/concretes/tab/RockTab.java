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
        this.setBackground(new Color(25, 25, 25));
        this.setForeground(Color.white);
    }

    public void addTab(Component component) throws IOException {
        init();
        if(components.size()<6){
            components.add(component);
            Image iconExecute = ImageIO.read(new File("src/main/java/icon/cancel_dark.png"));
            this.addTab("Request Page "+components.size(),new ImageIcon(iconExecute),component);
            if(component instanceof BaseTabbedPane pane)
                pane.initPane();
        }else {
            JOptionPane.showMessageDialog(this,"Oupst!! Tabs count must be low from 7");
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
