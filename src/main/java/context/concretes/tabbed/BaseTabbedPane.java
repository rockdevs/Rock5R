package context.concretes.tabbed;

import context.concretes.tabbed.panel.RestPanel;
import context.concretes.tabbed.panel.SSEPanel;
import context.concretes.tabbed.panel.WebsocketPanel;

import javax.swing.*;
import java.io.IOException;

public class BaseTabbedPane extends JTabbedPane {
    private final RestPanel restPanel;
    private final WebsocketPanel websocketPanel;
    private final SSEPanel ssePanel;

    {
        restPanel  = new RestPanel();
        websocketPanel= new WebsocketPanel();
        ssePanel = new SSEPanel();
    }

    public void initPane(){
        this.add(restPanel,"REST");
        this.add(websocketPanel,"Websocket");
        this.add(ssePanel,"SSE");
        try {
            restPanel.init();
            websocketPanel.init();
            ssePanel.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
