package ind.venture.objectivenotionclient.controller;

import ind.venture.objectivenotion.model.webhooks.PagePropertiesUpdatedHook;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhook")
public class NotionWebhooksController {

    @PostMapping("/page/properties-update")
    public void propertiesUpdate(@RequestBody PagePropertiesUpdatedHook pagePropertiesUpdate) {
        return;
    }
}
