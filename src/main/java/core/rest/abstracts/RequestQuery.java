package core.rest.abstracts;

import exception.InvalidUrlAddressException;

import java.io.IOException;
import java.net.URISyntaxException;

public interface RequestQuery {
    String request() throws InvalidUrlAddressException, IOException, URISyntaxException, InterruptedException;

}
