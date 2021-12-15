package core.rest.helper;

public enum RequestMethod{
    GET("GET"),POST("POST"),PUT("PUT"),DELETE("DELETE"),HEAD("HEAD");

    private final String value;
    RequestMethod(String val) {
        value=val;
    }

    public String getValue() {
        return value;
    }
}