package core.rest.concretes;

import core.rest.abstracts.RequestQuery;
import core.rest.helper.HttpProtocol;
import core.rest.helper.RequestMethod;
import exception.InvalidUrlAddressException;

import java.io.IOException;
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

    private HttpRequest request;

    private HttpResponse<String> response;

    private HttpClient client;

    private HttpRequest.BodyPublisher bodyPublisher;


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
        request = this.start()
                .timeout(Duration.of(5, ChronoUnit.SECONDS))
                .GET()
                .build();
        response = HttpClient.newHttpClient()
                .send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

    }


    public HttpRequest.Builder start() throws URISyntaxException {
        return  HttpRequest.newBuilder()
                .uri(new URI(protocol.getValue()+requestAddress));
    }



}
