package context.concretes.tab;


import context.concretes.frame.MainFrame;
import context.concretes.tabbed.BaseTabbedPane;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class RockTab extends JTabbedPane {
    private final ArrayList<Component> components = new ArrayList<>();

    private final Font fontTitle = new Font("Arial", Font.BOLD, 12);

    public RockTab() {}

    public void init(){
        this.setFont(fontTitle);
        //this.getComponentAt(0).setBackground(Color.BLACK);
    }

    public void addTab(Component component)  {
        init();
        if(components.size()<6){
            components.add(component);
            this.addTab("Unknown Request",component);
            if(component instanceof BaseTabbedPane pane)
                pane.initPane();
        }else {
            JOptionPane.showMessageDialog(this,"Tabs count must be low from 5");
        }
    }



    public void deleteTab(Component component){
        this.remove(component);
        components.remove(component);
    }



}
