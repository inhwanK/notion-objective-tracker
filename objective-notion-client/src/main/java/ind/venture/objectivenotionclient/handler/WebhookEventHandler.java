package ind.venture.objectivenotionclient.handler;

import ind.venture.objectivenotion.model.webhooks.NotionWebhookEvent;

public interface WebhookEventHandler {

    void handle(String apiKey, NotionWebhookEvent notionWebhookEvent);
    String getType();
}
