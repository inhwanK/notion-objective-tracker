package ind.venture.objectivenotionservice.client;

import ind.venture.objectivenotion.model.database.Database;
import ind.venture.objectivenotion.model.page.Page;
import ind.venture.objectivenotion.request.QueryDatabaseRequest;
import reactor.core.publisher.Mono;

import java.util.List;

public interface NotionDatabaseClient {
    Mono<Database> fetchDatabase(String databaseId);
    Mono<List<Page>> queryDatabase(String databaseId, QueryDatabaseRequest databaseRequest);


}