package ind.venture.objectivenotionservice.controller;


import ind.venture.objectivenotion.model.page.Page;
import ind.venture.objectivenotionservice.service.NotionDatabaseService;
import ind.venture.objectivenotionservice.service.NotionReminderService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReminderController {


    private final NotionReminderService notionReminderService;

    public ReminderController(NotionReminderService notionReminderService) {
        this.notionReminderService = notionReminderService;
    }

    @GetMapping("/{databaseId}/reminder-pages")
    public Mono<List<Page>> getAllReminderPage(
            @RequestHeader("Authorization") String apiKey,
            @PathVariable("databaseId") String databaseId
    ) {
        return notionReminderService.findAllReminderPage(apiKey, databaseId);
    }

    @GetMapping("/{databaseId}/today")
    public Mono<List<Page>> getTodayReminderPage(
            @RequestHeader("Authorization") String apiKey,
            @PathVariable("databaseId") String databaseId
    ) {
        return notionReminderService.findTodayReminderPage(apiKey, databaseId);
    }

    @GetMapping("/{databaseId}/weekly")
    public Mono<List<Page>> getWeeklyReminderPage(
            @RequestHeader("Authorization") String apiKey,
            @PathVariable("databaseId") String databaseId
    ) {
        return notionReminderService.findWeeklyReminderPage(apiKey, databaseId);
    }

}