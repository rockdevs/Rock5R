package core.rest.concretes;

import core.rest.abstracts.RockRequest;
import core.rest.helper.HttpProtocol;
import core.rest.helper.RequestMethod;
import exception.InvalidUrlAddressException;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author Vugar Mammadli
 */
public final class RequestQueryManager implements RockRequest {

    private HttpProtocol protocol;

    private String requestAddress;

    private RequestMethod requestMethod;

    private Map<String,String> parameters;

    private URLConnection connection;

    private URL urlPort;


    private final QueryMemento memento = new QueryMemento();


    public RequestQueryManager(HttpProtocol protocol, String requestAddress,
                               RequestMethod requestMethod, Map<String, String> parameters) {
        this.protocol = protocol;
        this.requestAddress = requestAddress.trim();
        this.requestMethod = requestMethod;
        this.parameters = parameters;
    }




    @Override
    public void request() throws InvalidUrlAddressException, IOException {
        refactorRequestAddress();
        initRequest();
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


    private void refactorRequestAddress() throws InvalidUrlAddressException {
        if(requestAddress.isEmpty())
            throw new InvalidUrlAddressException();
        if(requestAddress.startsWith("http://") || requestAddress.startsWith("https://"))
            requestAddress  = requestAddress.split("//")[1];
    }


    private void initRequest() throws IOException {
        urlPort = new URL(protocol.getValue() + requestAddress);
        connection = protocol.openConnection(urlPort,requestMethod);
    }

}
