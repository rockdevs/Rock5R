package core.rest.helper;

public enum ContentType {
    JSON("application/json")
    ,
    XML("application/xml")
    ,
    WWW("application/x-www-form-urlencoded")
    ;
    private final String value;

    ContentType(String s) {
        value = s;
    }

    public String get() {
        return value;
    }
}
