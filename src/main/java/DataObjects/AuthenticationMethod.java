package DataObjects;


public class AuthenticationMethod {
    private Location location;
    private String code;
    private String message;

    public AuthenticationMethod(Location location, String code, String message) {
     this.location = location;
     this.code = code;
     this.message = message;
    }

    public Location getLocation() {
        return location;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String toString() {
        return "location: {\n" + location.toString() + "\n}\ncode: " + code + "\nmessage: " + message;
    }
}
