package ind.venture.remindercore.controller;


import ind.venture.remindercore.domain.Database;
import ind.venture.remindercore.domain.Page;
import ind.venture.remindercore.service.NotionDatabaseService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NotionReminderController {

    private final NotionDatabaseService notionDatabaseService;

    public NotionReminderController(NotionDatabaseService notionDatabaseService) {
        this.notionDatabaseService = notionDatabaseService;
    }

    @GetMapping("/{databaseId}/info")
    public Mono<Database> getDatabaseInfo(
            @RequestHeader("Authorization") String apiKey,
            @PathVariable("databaseId") String databaseId
    ) {
        return notionDatabaseService.getDatabaseInfo(apiKey, databaseId);
    }

    @GetMapping("/{databaseId}/check")
    public Mono<Boolean> checkReminderDatabase(
            @RequestHeader("Authorization") String apiKey,
            @PathVariable("databaseId") String databaseId
    ) {
        return notionDatabaseService.checkHasReminderProperty(apiKey, databaseId);
    }

    @GetMapping("/{databaseId}/reminder-pages")
    public Mono<List<Page>> getAllReminderPage(
            @RequestHeader("Authorization") String apiKey,
            @PathVariable("databaseId") String databaseId
    ) {
        return notionDatabaseService.findAllReminderPage(apiKey, databaseId);
    }

    @GetMapping("/{databaseId}/today")
    public Mono<List<Page>> getTodayReminderPage(
            @RequestHeader("Authorization") String apiKey,
            @PathVariable("databaseId") String databaseId
    ) {
        return notionDatabaseService.findTodayReminderPage(apiKey, databaseId);
    }
}