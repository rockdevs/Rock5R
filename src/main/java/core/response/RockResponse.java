package core.response;

import java.util.Map;

public class RockResponse {
    private String responseMessage;

    private Map<String,?> headersFields;

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public Map<String,?> getHeadersFields() {
        return headersFields;
    }

    public void setHeadersFields(Map<String,?> headersFields) {
        this.headersFields = headersFields;
    }
}
