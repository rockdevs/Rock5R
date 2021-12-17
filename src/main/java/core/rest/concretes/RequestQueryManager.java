package core.rest.concretes;

import core.rest.abstracts.RequestQuery;
import core.rest.helper.ContentLang;
import core.rest.helper.ContentType;
import core.rest.helper.HttpProtocol;
import core.rest.helper.RequestMethod;
import exception.InvalidUrlAddressException;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
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

    private  BufferedReader bufferedReader;

    private ContentLang contentLang = ContentLang.EN;

    private ContentType contentType = ContentType.JSON;

    private boolean useCases = false;

    private boolean doOutput = true;

    private boolean doInput = true;

    private final StringBuffer responseContent = new StringBuffer();

    private final QueryMemento memento = new QueryMemento();


    public RequestQueryManager(String protocol, String requestAddress,
                               String requestMethod, Map<String, String> parameters) throws InvalidUrlAddressException {
        this.protocol = convertProtocol(protocol);
        this.requestAddress = requestAddress.trim();
        this.requestMethod = convertRequestMethod(requestMethod);
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

    @Override
    public String request() throws URISyntaxException, IOException, InterruptedException {
        urlPort = new URL(protocol.getValue()+requestAddress);
        connection  = protocol.openConnection(urlPort,requestMethod);

        connection.setUseCaches(useCases);
        connection.setDoOutput(doOutput);
        connection.setDoInput(doInput);
        connection.setRequestProperty("Content-Type", contentType.get());
        connection.setRequestProperty("Content-Language", contentLang.get());

        outputStream = new DataOutputStream(connection.getOutputStream());

        outputStream.write(this.getParamsAsString(parameters).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();


        bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        while ((inputLine = bufferedReader.readLine()) != null)
            responseContent.append(inputLine);

        bufferedReader.close();
        return responseContent.toString();
    }



    private HttpProtocol convertProtocol(String s) throws InvalidUrlAddressException {
        return switch (s){
            case "http://" -> HttpProtocol.HTTP;
            case "https://"->HttpProtocol.HTTPS;
            default -> throw new InvalidUrlAddressException();
        };
    }

    private RequestMethod convertRequestMethod(String s) throws InvalidUrlAddressException {
        return switch (s){
            case "GET" -> RequestMethod.GET;
            case "POST"->RequestMethod.POST;
            case "PUT"->RequestMethod.PUT;
            case "DELETE"->RequestMethod.DELETE;
            case "HEAD"->RequestMethod.HEAD;
            default -> throw new InvalidUrlAddressException();
        };
    }
}
