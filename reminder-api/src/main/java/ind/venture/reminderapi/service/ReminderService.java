package ind.venture.reminderapi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ReminderService {

    private final String apiKey;
    private final String databaseId;

    public ReminderService(
            @Value("${notion.api.key}") String apiKey,
            @Value("${notion.database.url}") String databaseId
    ) {
        this.apiKey = apiKey;
        this.databaseId = databaseId;
    }


}
