package APIObjects;

import DataObjects.Links;

public class VerifyResponse {
    private String imageChallengeData;
    private boolean extendedUsage;
    private String status;
    private Links links;
    private String serverTime;
    private String formattedServerTime;

    public String getImageChallengeData() {
        return imageChallengeData;
    }

    public boolean isExtendedUsage() {
        return extendedUsage;
    }

    public String getStatus() {
        return status;
    }

    public Links getLinks() {
        return links;
    }

    public String getServerTime() {
        return serverTime;
    }

    public String getFormattedServerTime() {
        return formattedServerTime;
    }

    public String toString() {
        return "imageChallengeData: " + imageChallengeData + "\nextendedUsage: " + extendedUsage +
                "\nstatus: " + status + "\nlinks: " + links.toString() + "\nserverTime: " + serverTime +
                "\nformattedServerTime: " + formattedServerTime;
    }
}
