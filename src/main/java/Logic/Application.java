package Logic;

import APIObjects.APICommunicator;
import DataObjects.AuthenticationMethod;
import Utils.Utils;
import org.springframework.http.HttpHeaders;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Application {
    public boolean run() {
        Utils.printMenu();
        return menuOptions();
    }

    private static boolean menuOptions() {
        boolean continueMenu = true;
        int option = Utils.userInputInteger();
        switch (option) {
            case 0:
                System.out.println("Exiting program...");
                continueMenu = false;
                break;
            case 1:
                getValidAuthenticationMethods();
                break;
            case 2:
                initiateBankIdAuth();
                break;
            default:
                System.out.println("Please enter a valid menu option.");
                menuOptions();
        }
        return continueMenu;
    }

    private static void getValidAuthenticationMethods() {
        System.out.println("Please enter Authorization for logged in session: ");
        String auth = Utils.userInputString();

        System.out.println("Please enter ID for logged in session: ");
        String sessionId = Utils.userInputString();

        List<AuthenticationMethod> authenticationMethods = APICommunicator.getAuthenticationMethods(auth, sessionId);
        if (authenticationMethods != null) {
            authenticationMethods.forEach(authenticationMethod -> System.out.println(authenticationMethod.toString()));
        } else {
            System.out.println("Returning to menu...");
        }
    }

    private static void initiateBankIdAuth() {
        System.out.println("Please enter social security number (12 characters) to initiate BankId on:");
        long ssn = Utils.getSocialSecurityInput();

        System.out.println("Please enter Authorization: ");
        String auth = Utils.userInputString();

        try {
            HttpHeaders headers = APICommunicator.initiateBankIdVerification(ssn, auth);

            if (headers != null) {
                System.out.println("Headers successfully retrieved, try to get status...");
                checkStatus10Seconds(auth, headers);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void checkStatus10Seconds(String auth, HttpHeaders headers) {
        try {
            Runnable runnable = () -> APICommunicator.checkStatusOnVerification(auth, headers);
            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

            ScheduledFuture<?> runner = scheduler.scheduleAtFixedRate(runnable, 1, 1, TimeUnit.SECONDS);
            Runnable canceller = () -> runner.cancel(false);
            scheduler.schedule(canceller, 10, TimeUnit.SECONDS);

            // Wait for status to print before returning to menu (10.5s)
            Thread.sleep(10500);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
