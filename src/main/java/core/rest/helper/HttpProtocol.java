package core.rest.helper;

public enum HttpProtocol {
    HTTP("http://"),HTTPS("https://");

    private final String value;
    HttpProtocol(String val) {
        value=val;
    }

    public String getValue() {
        return value;
    }
}
