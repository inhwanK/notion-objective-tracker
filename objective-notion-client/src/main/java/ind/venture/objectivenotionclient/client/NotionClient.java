package ind.venture.objectivenotionclient.client;

import ind.venture.objectivenotion.model.database.Database;
import ind.venture.objectivenotion.model.page.Page;
import ind.venture.objectivenotion.request.QueryDatabaseRequest;
import reactor.core.publisher.Mono;

import java.util.List;

public interface NotionClient {
    Mono<Database> fetchDatabase(String apiKey, String databaseId);
    Mono<List<Page>> queryDatabase(String apiKey, String databaseId, QueryDatabaseRequest databaseRequest);
}