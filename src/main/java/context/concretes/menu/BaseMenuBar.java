package context.concretes.menu;

import context.abstracts.Component;
import factory.abstracts.AbstractMenuBarFactory;
import factory.concretes.MenuBarFactory;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class BaseMenuBar extends JMenuBar implements Component{
    private final AbstractMenuBarFactory menuBarFactory;
    {
        menuBarFactory = new MenuBarFactory();
    }

    public void init(){


        JMenu file = menuBarFactory.factoryMenu("File");
        {
            JMenuItem fileNew = menuBarFactory.factoryMenuItem("New");
            JMenuItem fileOpen = menuBarFactory.factoryMenuItem("Open");
            JMenuItem fileOpenRecent = menuBarFactory.factoryMenuItem("Open Recent");
            JMenuItem fileCloseProject = menuBarFactory.factoryMenuItem("Close Project");
            JMenuItem fileSave = menuBarFactory.factoryMenuItem("Save");
            JMenuItem fileSaveAs = menuBarFactory.factoryMenuItem("Save As");
            JMenuItem fileReload = menuBarFactory.factoryMenuItem("Reload Project");
            JMenuItem fileExport = menuBarFactory.factoryMenuItem("Export");
            JMenuItem filePowerSafeMode = menuBarFactory.factoryMenuItem("Power Safe Mode");
            JMenuItem fileExit = menuBarFactory.factoryMenuItem("Exit");

            file.add(fileNew);
            file.add(fileOpen);
            file.add(fileOpenRecent);
            file.add(fileCloseProject);
            file.add(fileSave);
            file.add(fileSaveAs);
            file.add(fileReload);
            file.add(fileExport);
            file.add(filePowerSafeMode);
            file.add(fileExit);
        }

        JMenu edit = menuBarFactory.factoryMenu("Edit");
        {
            JMenuItem editUndo = menuBarFactory.factoryMenuItem("Undo");
            JMenuItem editRedo = menuBarFactory.factoryMenuItem("Undo");

            edit.add(editUndo);
            edit.add(editRedo);
        }

        JMenu windows = menuBarFactory.factoryMenu("Windows");
        windows.setMnemonic(KeyEvent.VK_O);
        {
            ButtonGroup directionGroup = new ButtonGroup();

            JMenuItem windowsSettings = menuBarFactory.factoryMenuItem("Settings");
            windows.add(windowsSettings);

            JMenu windowsTheme = menuBarFactory.factoryMenu("Themes");
            windowsTheme.setMnemonic(KeyEvent.VK_O);
            windows.add(windowsTheme);

            JRadioButtonMenuItem dark = new JRadioButtonMenuItem("Dark", true);
            dark.setMnemonic(KeyEvent.VK_F);
            windowsTheme.add(dark);
            directionGroup.add(dark);
            {
                dark.addActionListener((e)->{

                });
            }

            JRadioButtonMenuItem light = new JRadioButtonMenuItem("Light");
            light.setMnemonic(KeyEvent.VK_B);
            windowsTheme.add(light);
            directionGroup.add(light);
            {
                light.addActionListener((e)->{

                });
            }


        }

        JMenu help = menuBarFactory.factoryMenu("Help");
        {
            JMenuItem helpTutorial = menuBarFactory.factoryMenuItem("Tutorial");
            JMenuItem helpHelp = menuBarFactory.factoryMenuItem("HelpDesk");

            help.add(helpTutorial);
            help.add(helpHelp);
        }

        this.add(file);
        this.add(edit);
        this.add(windows);
        this.add(help);

    }
}
