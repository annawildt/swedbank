import Logic.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
public class SpringApplication {
    public static void main(String[] args) {
        Application application = new Application();
        boolean runApp;

        do {
           runApp = application.run();
        } while (runApp);
    }
}
