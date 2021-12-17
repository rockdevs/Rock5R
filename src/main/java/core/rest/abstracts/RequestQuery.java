package core.rest.abstracts;

import core.response.RockResponse;
import exception.InvalidUrlAddressException;

import java.io.IOException;
import java.net.URISyntaxException;

public interface RequestQuery {
    RockResponse request() throws InvalidUrlAddressException, IOException, URISyntaxException, InterruptedException;

}
