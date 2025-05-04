package ind.venture.remindercore.config;

import ind.venture.remindercore.domain.Database;
import ind.venture.remindercore.domain.Page;
import ind.venture.remindercore.request.DatabaseRequest;
import reactor.core.publisher.Mono;

import java.util.List;

public interface NotionClient {
    Mono<Database> fetchDatabase(String apiKey, String databaseId);
    Mono<List<Page>> queryDatabase(String apiKey, String databaseId, DatabaseRequest databaseRequest);
}