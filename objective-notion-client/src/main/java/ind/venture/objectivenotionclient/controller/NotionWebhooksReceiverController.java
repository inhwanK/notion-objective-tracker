package ind.venture.objectivenotionclient.controller;

import ind.venture.objectivenotion.model.webhooks.PagePropertiesUpdatedHook;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/webhooks")
public class NotionWebhooksReceiverController {

    @PostMapping("/page/properties-update")
    public void propertiesUpdate(@RequestBody PagePropertiesUpdatedHook pagePropertiesUpdate) {
        log.info("pagePropertiesUpdate: {}", pagePropertiesUpdate);
        return;
    }

    @GetMapping("/healthy")
    public String healthy() {
        log.info("healthy check");
        return "ok";
    }
}
