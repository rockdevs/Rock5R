package context.concretes.tabbed.panel;

import context.abstracts.Component;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class RestPanel extends JPanel implements Component {

    private final FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
    private JTabbedPane topTabbedPane;
    private JTabbedPane responseTabbedPane;


    {
        topTabbedPane = new JTabbedPane();
        responseTabbedPane = new JTabbedPane();
    }


    @Override
    public void init() throws IOException {
        this.setBorder(new EmptyBorder(5,10,5,10));
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        initTop();
        initTFURL();
        initExecutePanel();
        initResponsePanel();
    }

    private void initTop(){
        JPanel basePanel = new JPanel(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(flowLayout);

        JLabel  jLabel  = new JLabel("API",JLabel.LEFT);
        jLabel.setVerticalAlignment(JLabel.TOP);
        jLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
        jLabel.setForeground(new Color(255, 255, 255));
        panel.add(jLabel);

        basePanel.add(panel);
        this.add(basePanel);
    }

    private void initTFURL() throws IOException {
        JPanel basePanel = new JPanel(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(flowLayout);

        String[] protocols = {"http://","https://"};
        JComboBox<?> protocol = new JComboBox<>(protocols);
        protocol.setSelectedIndex(0);

        JTextField urlField = new JTextField();
        urlField.setColumns(70);

        JButton historyButton = new JButton();
        Image historyIcon = ImageIO.read(new File("src/main/java/icon/annotate_dark.png"));
        historyButton.setIcon(new ImageIcon(historyIcon));
        historyButton.addActionListener((e)->{

        });

        String[] items = {"GET","POST","DELETE","HEAD","PUT"};
        JComboBox<?> requests = new JComboBox<>(items);
        requests.setSelectedIndex(0);

        JButton requestButton = new JButton("Request");
        Image requestIcon = ImageIO.read(new File("src/main/java/icon/commit_dark.png"));
        requestButton.setIcon(new ImageIcon(requestIcon));
        requestButton.addActionListener((e)->{

        });
        panel.add(protocol);
        panel.add(urlField);
        panel.add(historyButton);
        panel.add(requests);
        panel.add(requestButton);

        basePanel.add(panel);
        this.add(basePanel);
    }

    private void initExecutePanel(){
        JPanel basePanel = new JPanel(new BorderLayout());
        initQueryParamsPanel();
        initHeader();
        initBody();
        basePanel.add(topTabbedPane);
        this.add(basePanel);
    }

    private void initQueryParamsPanel(){
        JPanel  basePanel = new JPanel(new BorderLayout());
        String[][] rec = {
                { "", ""},
                { "", "" },
                { "", "" },
                { "", ""},
                { "", ""},
                { "", ""},
                { "", ""},
                { "", ""},
                { "", ""},
                { "", ""},
        };
        String[] header = { "Key", "Value"};
        JTable table = new JTable(rec, header);
        table.getColumnModel().setColumnMargin(20);
        basePanel.add(new JScrollPane(table));
        this.topTabbedPane.add(basePanel,"Query Params");
    }

    private void initHeader() {
        JPanel  panel = new JPanel();

        this.topTabbedPane.add(panel,"Header");
    }

    private void initBody() {
        JPanel  panel = new JPanel();

        this.topTabbedPane.add(panel,"Body");
    }


    private void initResponsePanel(){
        JPanel  panel = new JPanel(new BorderLayout());
        JTextArea responseArea = new JTextArea();
        responseArea.setRows(40);
        panel.add(responseArea);
        this.responseTabbedPane.add(panel,"Response");
        this.add(responseTabbedPane,BorderLayout.PAGE_END);
    }



}
