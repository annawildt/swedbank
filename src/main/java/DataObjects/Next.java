package DataObjects;

public class Next {
    private String method;
    private String uri;

    public String getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

    public String toString() {
        return "\n\t\tmethod: " + method + "\nuri: " + uri;
    }
}
