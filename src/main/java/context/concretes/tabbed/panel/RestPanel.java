package context.concretes.tabbed.panel;

import com.google.gson.*;
import context.abstracts.Component;
import core.response.RockResponse;
import core.rest.abstracts.RequestQuery;
import core.rest.concretes.RequestQueryManager;
import core.rest.helper.HttpProtocol;
import core.rest.helper.RequestMethod;
import exception.InvalidUrlAddressException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.HashMap;
import org.json.*;

public class RestPanel extends JPanel implements Component {




    private final FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
    private final JTabbedPane topTabbedPane;
    private final JTabbedPane responseTabbedPane;


    private RequestQuery requestQuery;
    private JComboBox<?> protocolJComboBox;
    private JTextField urlField;
    private JComboBox<?> requestMethod;

    private String protocol;
    private String request;
    private RockResponse response;
    private JSONObject jsonObject;
    private JEditorPane jEditorPane;

    private JTextArea responseArea;
    private JTextArea headersArea;
    private JTextArea bodyArea;

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

        String[] protocols = {HttpProtocol.HTTP.getValue(),HttpProtocol.HTTPS.getValue()};
        protocolJComboBox = new JComboBox<>(protocols);
        protocolJComboBox.setSelectedIndex(0);


        urlField = new JTextField();
        urlField.setColumns(70);

        JButton historyButton = new JButton();
        Image historyIcon = ImageIO.read(new File("src/main/java/icon/annotate_dark.png"));
        historyButton.setIcon(new ImageIcon(historyIcon));
        historyButton.addActionListener((e)->{

        });

        JButton saveButton = new JButton();
        Image saveIcon = ImageIO.read(new File("src/main/java/icon/save.png"));
        saveButton.setIcon(new ImageIcon(saveIcon));
        saveButton.addActionListener((e)->{

        });

        String[] items = {"GET","POST","DELETE","HEAD","PUT"};
        requestMethod = new JComboBox<>(items);
        requestMethod.setSelectedIndex(0);

        JButton requestButton = new JButton("Request");
        Image requestIcon = ImageIO.read(new File("src/main/java/icon/commit_dark.png"));
        requestButton.setIcon(new ImageIcon(requestIcon));
        requestButton.addActionListener((e)->{
            try {
                protocol = (String) protocolJComboBox.getSelectedItem();
                request =  (String)  requestMethod.getSelectedItem();
                requestQuery = new RequestQueryManager(protocol,urlField.getText(),request,new HashMap<>());
                response = requestQuery.request();
                if (response != null) {
                    Document doc = Jsoup.parse(response.getResponseMessage());
                    jEditorPane.setText(doc.toString());
                    responseArea.setText(doc.toString());
                    headersArea.setText(response.getHeadersFields().toString().replace(", ","\n"));
                }
            }catch (URISyntaxException exception){
                exception.printStackTrace();
                String message  = "URI must not be null";
                JOptionPane.showMessageDialog(this,message);
            }catch (InterruptedException exception){
                exception.printStackTrace();
                String message  = "Interrupted Exception";
                JOptionPane.showMessageDialog(this,message);
            }catch (InvalidUrlAddressException exception){
                exception.printStackTrace();
                String message  = "URI must not be null";
                JOptionPane.showMessageDialog(this,message);
            }catch (UnknownHostException exception){
                exception.printStackTrace();
                String message  = "Unknown URL address.Please try check address";
                JOptionPane.showMessageDialog(this,message);
            }catch (IOException exception){
                exception.printStackTrace();
                String message  = "Unknown Exception :( Please contact Support";
                JOptionPane.showMessageDialog(this,message);
            }
            //java.net.UnknownHostException
        });

        panel.add(protocolJComboBox);
        panel.add(urlField);
        panel.add(historyButton);
        panel.add(saveButton);
        panel.add(requestMethod);
        panel.add(requestButton);

        basePanel.add(panel);
        this.add(basePanel);
    }

    private void initExecutePanel(){
        JPanel basePanel = new JPanel(new BorderLayout());
        initQueryParamsPanel();
        initAuthorization();
        initHeader();
        initBody();
        initSettings();
        basePanel.add(topTabbedPane);
        this.add(basePanel);
    }

    private void initQueryParamsPanel(){
        JPanel  basePanel = new JPanel(new BorderLayout());
        String[][] rec = {
                { "", "",""},
                { "", "",""},
                { "", "",""},
                { "", "",""},
                { "", "",""},
                { "", "",""},
                { "", "",""},
                { "", "",""},

        };
        String[] header = { "Key", "Value","Description"};
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
        JPanel  panelBody = new JPanel();
        bodyArea =  new JTextArea(10,105);
        bodyArea.setTabSize(1);
        bodyArea.setText("{\n   }");
        bodyArea.setFont(new Font("Arial",Font.BOLD,14));
        JScrollPane scrollableTextArea = new JScrollPane(bodyArea);
        scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        panelBody.add(scrollableTextArea);
        JButton prettyButton = new JButton("Pretty");
        prettyButton.addActionListener((e)->{
            String prettyJson = prettyJson(bodyArea.getText());
            bodyArea.setText(prettyJson);
        });
        panelBody.add(prettyButton,BorderLayout.SOUTH);
        this.topTabbedPane.add(panelBody,"Body");
        this.add(topTabbedPane,BorderLayout.CENTER);
    }

    private void initAuthorization(){
        JPanel  panel = new JPanel();

        this.topTabbedPane.add(panel,"Authorization");
    }

    private void initSettings(){
        JPanel  panel = new JPanel();

        this.topTabbedPane.add(panel,"Settings");
    }

    private void initResponsePanel(){
        JPanel  panelResponse = new JPanel(new BorderLayout());


        responseArea = new JTextArea(40,100);
        JScrollPane scrollableTextArea = new JScrollPane(responseArea);
        scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        panelResponse.add(scrollableTextArea);
        this.responseTabbedPane.add(panelResponse,"Response");
        this.add(responseTabbedPane,BorderLayout.PAGE_END);
        initHeaders();
        initPreview();
    }

    private void initHeaders(){
        JPanel  panelHeaders = new JPanel(new BorderLayout());

        headersArea =  new JTextArea(40,100);
        JScrollPane scrollableTextArea = new JScrollPane(headersArea);
        scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        panelHeaders.add(scrollableTextArea);


        this.responseTabbedPane.add(panelHeaders,"Headers");
        this.add(responseTabbedPane,BorderLayout.PAGE_END);
    }

    private void initPreview(){
        JPanel  panel = new JPanel(new BorderLayout());

        jEditorPane = new JEditorPane();
        jEditorPane.setContentType("text/html");
        JScrollPane scrollableTextArea = new JScrollPane(jEditorPane);
        scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollableTextArea);


        this.responseTabbedPane.add(panel,"Preview");
        this.add(responseTabbedPane,BorderLayout.PAGE_END);
    }

    private String prettyJson(String json){
        if(json==null||json.equals("")||json.equals(" ")){
            return "";
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = null;
        try {
            je = jp.parse(json);
        }catch (JsonSyntaxException exception){
            JOptionPane.showMessageDialog(this,"Oups! :( Text file not suitable for json syntax.\nThere must be a mistake somewhere");
            return json;
        }
        return gson.toJson(je);
    }

}
