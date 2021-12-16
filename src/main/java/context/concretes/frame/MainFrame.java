package context.concretes.frame;

import com.formdev.flatlaf.*;
import com.formdev.flatlaf.ui.FlatTabbedPaneUI;
import context.abstracts.AbstractFrameInitializer;
import context.concretes.menu.BaseMenuBar;
import context.concretes.panel.TopPanel;
import context.concretes.tab.RockTab;
import context.concretes.tabbed.BaseTabbedPane;
import factory.abstracts.*;
import helper.ThemeChanger;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainFrame extends AbstractFrameInitializer {

    private final AbstractComponentFactory componentFactory;
    private final AbstractLayoutFactory layerFactory;
    private final AbstractWindowsFactory windowsFactory;
    private final RockTab rockTab = new RockTab();



    private FlatLaf flatLightLaf;

    {
        try {
            this.setIconImage(ImageIO.read(new File("src/main/java/icon/5R.png")));
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(new JFrame("Error"),"Init Application Icon exception");
        }
        componentFactory = (AbstractComponentFactory) FactoryManager.COMPONENT.get();
        layerFactory = (AbstractLayoutFactory) FactoryManager.LAYOUT.get();
        windowsFactory = (AbstractWindowsFactory) FactoryManager.WINDOWS.get();
    }

    public MainFrame(String title) {
        super(title);
    }

    @Override
    public void start() {
        this.setVisible(true);
    }

    @Override
    public void postConstruction() throws UnsupportedLookAndFeelException, IOException {
        initFrame();
        initMenuBar();
        initTopBar();
        initCenter();
        initSouth();
    }

    @Override
    public void preDestroy() {
        this.add(rockTab, BorderLayout.CENTER);
        this.setVisible(false);
    }

    public void addRock() throws IOException {
        rockTab.addTab(new BaseTabbedPane());
    }


    private void initFrame() throws UnsupportedLookAndFeelException{
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200,700);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.DARK_GRAY);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        flatLightLaf = ThemeChanger.DRACULA.get();
        UIManager.setLookAndFeel (flatLightLaf);
        this.setLayout(layerFactory.factoryBorderLayout());
    }

    private void initMenuBar(){
        BaseMenuBar menuBar = new BaseMenuBar();
        menuBar.init();
        this.setJMenuBar(menuBar);
    }

    private void initTopBar() throws IOException {
        TopPanel topPanel= new TopPanel(this);
        topPanel.init();
        this.add(topPanel, BorderLayout.NORTH);
    }

    private void initCenter() throws IOException {
        rockTab.addTab(new BaseTabbedPane());
    }

    private void initWest(){

    }

    private void initSouth(){
        JPanel panel = new JPanel();

        this.add(panel,BorderLayout.SOUTH);
    }

    private void initEast(){

    }

}
