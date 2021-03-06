package context.concretes.panel;

import context.abstracts.Component;
import context.concretes.frame.MainFrame;
import factory.abstracts.AbstractComponentFactory;
import factory.abstracts.FactoryManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TopPanel extends JPanel implements Component {

    private MainFrame mainFrame;

    public TopPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    private final AbstractComponentFactory componentFactory;
    {
        componentFactory = (AbstractComponentFactory) FactoryManager.COMPONENT.get();
    }

    public void init() throws IOException {
        FlowLayout flowLayoutx = new FlowLayout(FlowLayout.LEFT,5,5);
        this.setLayout(flowLayoutx);

        Image iconNewProject = ImageIO.read(new File("src/main/java/icon/annotate_dark.png"));
        Image iconOpenP = ImageIO.read(new File("src/main/java/icon/install_dark.png"));
        Image iconReload = ImageIO.read(new File("src/main/java/icon/buildLoadChanges_dark.png"));
        Image iconGenerate = ImageIO.read(new File("src/main/java/icon/compile_dark.png"));
        Image iconRefactor = ImageIO.read(new File("src/main/java/icon/forceRefresh_dark.png"));
        Image iconExecute = ImageIO.read(new File("src/main/java/icon/execute_dark.png"));
        Image iconCloseTab = ImageIO.read(new File("src/main/java/icon/cancel_dark.png"));

        JButton newProject = componentFactory.factoryButton("New Tab");
        newProject.setIcon(new ImageIcon(iconNewProject));
        newProject.addActionListener((e)->{
            try {
                mainFrame.addRock();
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this,"Unknown Exception.Please contact with RockSupport");
            }
        });

        JButton openProject = componentFactory.factoryButton("Open Tab");
        openProject.setIcon(new ImageIcon(iconOpenP));

        JButton execute = componentFactory.factoryButton("Execute");
        execute.setIcon(new ImageIcon(iconExecute));

        JButton editProject = componentFactory.factoryButton("Reload");
        editProject.setIcon(new ImageIcon(iconReload));

        JButton generate = componentFactory.factoryButton("Generate");
        generate.setIcon(new ImageIcon(iconGenerate));

        JButton refactor = componentFactory.factoryButton("Force Refresh");
        refactor.setIcon(new ImageIcon(iconRefactor));

        JButton closeTab = componentFactory.factoryButton("Close Tab");
        closeTab.setIcon(new ImageIcon(iconCloseTab));
        closeTab.addActionListener((e -> {

        }));

        this.add(newProject);
        this.add(closeTab);
        this.add(openProject);
        this.add(execute);
        this.add(generate);
        this.add(editProject);
        this.add(refactor);
    }
}
