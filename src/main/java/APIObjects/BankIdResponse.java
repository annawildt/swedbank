package APIObjects;

import DataObjects.ImageChallenge;
import DataObjects.Links;

public class BankIdResponse {
    private String status;
    private ImageChallenge imageChallenge;
    private String imageChallengeData;
    private Links links;

    public String getStatus() {
        return status;
    }

    public ImageChallenge getImageChallenge() {
        return imageChallenge;
    }

    public String getImageChallengeData() {
        return imageChallengeData;
    }

    public Links getLinks() {
        return links;
    }

    public String toString() {
        return "status: " + status + "\nimageChallenge: {\n" + imageChallenge.toString() +
                "\n}\nimageChallengedData: " + imageChallengeData + "\nlinks: {" +
                links.toString() + "\n}";
    }
}
