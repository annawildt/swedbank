package DataObjects;

public class Location {
    private String method;
    private String uri;

    public Location(String method, String uri) {
        this.method = method;
        this.uri = uri;
    }

    public String getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

    public String toString() {
        return "\tmethod: " + method + "\n\turi: " + uri;
    }
}
