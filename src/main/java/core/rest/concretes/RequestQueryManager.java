package core.rest.concretes;

import core.rest.helper.HttpProtocol;
import core.rest.helper.RequestMethod;

import javax.net.ssl.HttpsURLConnection;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author Vugar Mammadli
 */
public class RequestQueryManager {

    private HttpProtocol protocol;

    private String requestAddress;

    private RequestMethod requestMethod;

    private Map<String,String> parameters;



    private URL urlPort;

    private HttpURLConnection connectionHttp;

    private HttpsURLConnection connectionHttps;

    private final QueryMemento memento = new QueryMemento();


    public RequestQueryManager(HttpProtocol protocol, String requestAddress,
                               RequestMethod requestMethod, Map<String, String> parameters) {
        this.protocol = protocol;
        this.requestAddress = requestAddress;
        this.requestMethod = requestMethod;
        this.parameters = parameters;
    }

    public  String getParamsAsString(Map<String, String> params) {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
            result.append("&");
        }
        String resultString = result.toString();

        return resultString.length() > 0
                ? resultString.substring(0, resultString.length() - 1)
                : resultString;
    }


    public void request(){

    }


}
