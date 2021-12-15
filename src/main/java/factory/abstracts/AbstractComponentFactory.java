package factory.abstracts;

import javax.swing.*;

public interface AbstractComponentFactory {

    JButton factoryButton(String nameOfButton);

    JLabel factoryLabel(String labelText);

    JList<?> factoryList();

    JOptionPane factoryOptionPane();

    JScrollPane factoryScrollPane();

    JScrollBar factoryScrollBar();

    JComboBox<?> factoryComboBox();

    JTextField factoryTextField(String text);

    JTextArea factoryTextPane();

    JPasswordField factoryPasswordField();

    JTable factoryTable();

    JProgressBar factoryProgressBar();

    JRadioButton factoryRadioButton();

    JCheckBox factoryCheckBox();


}
