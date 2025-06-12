package ind.venture.objectivenotionclient.controller;

import ind.venture.objectivenotion.model.database.Database;
import ind.venture.objectivenotionclient.service.NotionService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class NotionDatabaseController {

    private final NotionService notionService;

    public NotionDatabaseController(NotionService notionService) {
        this.notionService = notionService;
    }

    @GetMapping("/{databaseId}/info")
    public Mono<Database> getDatabaseInfo(
            @RequestHeader("Authorization") String apiKey,
            @PathVariable("databaseId") String databaseId
    ) {
        return notionService.getDatabaseInfo(apiKey, databaseId);
    }

    @GetMapping("/{databaseId}/check")
    public Mono<Boolean> checkReminderDatabase(
            @RequestHeader("Authorization") String apiKey,
            @PathVariable("databaseId") String databaseId
    ) {
        return notionService.checkIsReminderDatabase(apiKey, databaseId);
    }
}
