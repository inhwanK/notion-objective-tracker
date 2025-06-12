package ind.venture.objectivenotionclient.handler;

import ind.venture.objectivenotion.model.webhooks.NotionWebhookEvent;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class WebhookEventDispatcher {

    private final Map<String, WebhookEventHandler> handlerMap;

    public WebhookEventDispatcher(List<WebhookEventHandler> handlers) {
        handlerMap = new HashMap<>();
        for (WebhookEventHandler handler : handlers) {
            handlerMap.put(handler.getType(), handler);
        }
    }

    public void dispatch(String apiKey, NotionWebhookEvent dto) {
        WebhookEventHandler handler = handlerMap.get(dto.getType());
        if (handler != null) {
            handler.handle(apiKey, dto);
        } else {
            System.out.println("지원하지 않는 이벤트 타입: " + dto.getType());
        }
    }

}
