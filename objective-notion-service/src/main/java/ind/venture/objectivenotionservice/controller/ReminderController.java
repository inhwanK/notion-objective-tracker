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
            @PathVariable("databaseId") String databaseId
    ) {
        return notionReminderService.findAllReminderPage(databaseId);
    }

    @GetMapping("/{databaseId}/today")
    public Mono<List<Page>> getTodayReminderPage(
            @PathVariable("databaseId") String databaseId
    ) {
        return notionReminderService.findTodayReminderPage(databaseId);
    }

    @GetMapping("/{databaseId}/weekly")
    public Mono<List<Page>> getWeeklyReminderPage(
            @PathVariable("databaseId") String databaseId
    ) {
        return notionReminderService.findWeeklyReminderPage(databaseId);
    }

}