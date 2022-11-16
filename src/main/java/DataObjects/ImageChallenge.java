package DataObjects;

public class ImageChallenge {
    String method;
    String uri;

    public String getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

    public String toString(){
        return "\n\tmethod: " + method + "\n\turi: " + uri;
    }
}
