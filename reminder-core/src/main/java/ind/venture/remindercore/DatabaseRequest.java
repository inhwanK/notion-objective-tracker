package ind.venture.remindercore;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class DatabaseRequest {

    private final WebClient webClient;

    public DatabaseRequest(WebClient webClient) {
        this.webClient = webClient;
    }

    public void retrieveDatabaseInfo() {

    }
}
