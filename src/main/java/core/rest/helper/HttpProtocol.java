package core.rest.helper;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public enum HttpProtocol {
    HTTP("http://"){
        @Override
        public URLConnection openConnection(URL url,RequestMethod method) throws IOException {
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(method.getValue());
            return httpURLConnection;
        }
    },
    HTTPS("https://"){
        @Override
        public URLConnection openConnection(URL url,RequestMethod method) throws IOException {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setRequestMethod(method.getValue());
            return httpsURLConnection;
        }
    };

    private final String value;

    HttpProtocol(String val) {
        value=val;
    }

    public abstract URLConnection openConnection(URL url,RequestMethod method) throws IOException;

    public String getValue() {
        return value;
    }
}
