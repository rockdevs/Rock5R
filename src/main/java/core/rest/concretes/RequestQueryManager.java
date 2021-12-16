package core.rest.concretes;

import core.rest.abstracts.RequestQuery;
import core.rest.helper.HttpProtocol;
import core.rest.helper.RequestMethod;
import exception.InvalidUrlAddressException;

import java.io.*;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Map;

/**
 * @author Vugar Mammadli
 */
public final class RequestQueryManager implements RequestQuery {

    private HttpProtocol protocol;

    private String requestAddress;

    private RequestMethod requestMethod;

    private Map<String,String> parameters;

    private URLConnection connection;

    private URL urlPort;

    private OutputStream outputStream;


    int status;

    private final QueryMemento memento = new QueryMemento();


    public RequestQueryManager(HttpProtocol protocol, String requestAddress,
                               RequestMethod requestMethod, Map<String, String> parameters) throws InvalidUrlAddressException {
        this.protocol = protocol;
        this.requestAddress = requestAddress.trim();
        this.requestMethod = requestMethod;
        this.parameters = parameters;

        refactorRequestAddress();
    }

    private void refactorRequestAddress() throws InvalidUrlAddressException {
        if(requestAddress.isEmpty())
            throw new InvalidUrlAddressException();
        if(requestAddress.startsWith("http://") || requestAddress.startsWith("https://"))
            requestAddress  = requestAddress.split("//")[1];
        if(!requestAddress.startsWith("www."))
            requestAddress = "www."+requestAddress;
    }

    @Override
    public void request() throws URISyntaxException, IOException, InterruptedException {
        urlPort = new URL(protocol.getValue()+requestAddress);
        connection  = protocol.openConnection(urlPort,requestMethod);
        connection.setUseCaches(false);
        connection.setDoOutput(true);
//        connection.setRequestProperty("Content-Type",
//                "application/x-www-form-urlencoded");
//        connection.setRequestProperty("Content-Language", "en-US");
        outputStream = new DataOutputStream(connection.getOutputStream());
        outputStream.write(this.getParamsAsString(parameters).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
        connection.connect();


        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        System.out.println(content);
        in.close();

    }

    public  String getParamsAsString(Map<String, String> params)
            throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            result.append("&");
        }

        String resultString = result.toString();
        return resultString.length() > 0
                ? resultString.substring(0, resultString.length() - 1)
                : resultString;
    }
}
