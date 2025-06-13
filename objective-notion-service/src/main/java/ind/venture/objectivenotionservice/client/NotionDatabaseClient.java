package ind.venture.objectivenotionservice.client;

import ind.venture.objectivenotion.model.database.Database;
import ind.venture.objectivenotion.model.page.Page;
import ind.venture.objectivenotion.request.QueryDatabaseRequest;
import reactor.core.publisher.Mono;

import java.util.List;

public interface NotionDatabaseClient {
    Mono<Database> fetchDatabase(String apiKey, String databaseId);
    Mono<List<Page>> queryDatabase(String apiKey, String databaseId, QueryDatabaseRequest databaseRequest);


}