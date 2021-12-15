package core.rest.abstracts;

import exception.InvalidUrlAddressException;

import java.io.IOException;

public interface RockRequest {
    void request() throws InvalidUrlAddressException, IOException;

}
