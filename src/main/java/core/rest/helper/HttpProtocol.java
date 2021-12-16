package core.rest.helper;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public enum HttpProtocol {
    HTTP("http://"),
    HTTPS("https://");

    private final String value;

    HttpProtocol(String val) {
        value=val;
    }

    public String getValue() {
        return value;
    }
}
