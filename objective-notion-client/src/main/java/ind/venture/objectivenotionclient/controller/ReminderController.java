package ind.venture.objectivenotionclient.controller;


import ind.venture.objectivenotion.model.page.Page;
import ind.venture.objectivenotionclient.service.NotionService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReminderController {

    private final NotionService notionService;

    public ReminderController(NotionService notionService) {
        this.notionService = notionService;
    }

    @GetMapping("/{databaseId}/reminder-pages")
    public Mono<List<Page>> getAllReminderPage(
            @RequestHeader("Authorization") String apiKey,
            @PathVariable("databaseId") String databaseId
    ) {
        return notionService.findAllReminderPage(apiKey, databaseId);
    }

    @GetMapping("/{databaseId}/today")
    public Mono<List<Page>> getTodayReminderPage(
            @RequestHeader("Authorization") String apiKey,
            @PathVariable("databaseId") String databaseId
    ) {
        return notionService.findTodayReminderPage(apiKey, databaseId);
    }

    @GetMapping("/{databaseId}/weekly")
    public Mono<List<Page>> getWeeklyReminderPage(
            @RequestHeader("Authorization") String apiKey,
            @PathVariable("databaseId") String databaseId
    ) {
        return notionService.findWeeklyReminderPage(apiKey, databaseId);
    }

}