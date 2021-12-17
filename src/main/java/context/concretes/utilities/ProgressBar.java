package context.concretes.utilities;

import javax.swing.*;
import java.awt.*;

public class ProgressBar extends JProgressBar {

    public ProgressBar(int i, int i1) {
        super(i,i1);
    }

    public void build(){
        this.setBounds(40,40,250,50);
        this.setValue(0);
        this.setStringPainted(true);
        iterate();
    }

    private void iterate(){
        int i = 0;
        while(i<=2000){
            this.setValue(i);
            i+=20;
        }
    }
}

