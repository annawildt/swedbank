package APIObjects;

import DataObjects.AuthenticationMethod;
import DataObjects.AuthenticationMethods;
import com.google.gson.Gson;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

public class APICommunicator {
    public static List<AuthenticationMethod> getAuthenticationMethods(String auth, String sessionId) {
        String url = "https://welcome.swedbank.se/TDE_DAP_Portal_REST_WEB/api/v5/identification/";
        HttpHeaders headers = createHeaders(auth, "@fdpc/portal-public/195.0.0");
        HttpEntity<String> request = new HttpEntity<>(null, headers);

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                Gson gson = new Gson();
                AuthenticationMethods authenticationMethod = gson.fromJson(
                        response.getBody(), AuthenticationMethods.class);
                return authenticationMethod.getAuthenticationMethods();
            } else {
                printError(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static HttpHeaders initiateBankIdVerification(long ssn, String auth) {
        Gson gson = new Gson();

        BankIDRequest request = new BankIDRequest(
                false, false, false, ssn);

        String url = "https://online.swedbank.se/TDE_DAP_Portal_REST_WEB/api/v5/identification/bankid/mobile";
        HttpHeaders headers = createHeaders(auth,"fdp-internet-bank/195.0.0");
        HttpEntity<String> httpRequest = new HttpEntity<>(gson.toJson(request), headers);

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, httpRequest, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                BankIdResponse bankIdResponse = gson.fromJson(
                        response.getBody(), BankIdResponse.class);
                System.out.println(bankIdResponse.toString());

                return response.getHeaders();
            } else {
                printError(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void checkStatusOnVerification(String auth, HttpHeaders responseHeaders) {
        String url = "https://online.swedbank.se/TDE_DAP_Portal_REST_WEB/api/v5/identification/bankid/mobile/verify";
        HttpHeaders headers = createHeaders(auth, "fdp-internet-bank/195.0.0");
        headers.set("Cookie", responseHeaders.get("Set-cookie").get(0));
        HttpEntity<String> httpRequest = new HttpEntity<>(null, headers);

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpRequest, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                Gson gson = new Gson();
                VerifyResponse verifyResponse = gson.fromJson(response.getBody(), VerifyResponse.class);
                System.out.println(verifyResponse.toString());
            } else {
                printError(response);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static HttpHeaders createHeaders(String auth, String client) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", auth);
        headers.set("X-Client", client);

        return headers;
    }

    private static void printError(ResponseEntity<String> response) {
        System.out.println("Invalid response: ");
        System.out.println("Status code: " + response.getStatusCode());
        System.out.println("Message: " + response.getBody());
    }
}
