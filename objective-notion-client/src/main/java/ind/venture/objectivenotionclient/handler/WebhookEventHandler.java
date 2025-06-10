package ind.venture.objectivenotionclient.handler;

import ind.venture.objectivenotion.model.webhooks.NotionWebhookEventDto;

public interface WebhookEventHandler {

    void handle(String apiKey, NotionWebhookEventDto notionWebhookEventDto);
}
