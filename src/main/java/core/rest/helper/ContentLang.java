package core.rest.helper;

public enum ContentLang {
    EN("en-US")
    ;

    private final String lang;
    ContentLang(String s) {
        lang = s;
    }

    public String get() {
        return lang;
    }
}
