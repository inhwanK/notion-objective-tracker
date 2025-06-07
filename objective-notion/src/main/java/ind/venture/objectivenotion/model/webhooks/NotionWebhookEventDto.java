package ind.venture.objectivenotion.model.webhooks;

import com.fasterxml.jackson.annotation.JsonProperty;
import ind.venture.objectivenotion.model.webhooks.common.AccessibleBy;
import ind.venture.objectivenotion.model.webhooks.common.Author;
import ind.venture.objectivenotion.model.webhooks.common.Data;
import ind.venture.objectivenotion.model.webhooks.common.Entity;

import java.time.Instant;
import java.util.List;

public class NotionWebhookEventDto {
    private String id;
    private Instant timestamp;
    @JsonProperty("workspace_id")
    private String workspaceId;
    @JsonProperty("workspace_name")
    private String workspaceName;
    @JsonProperty("subscription_id")
    private String subscriptionId;
    @JsonProperty("integration_id")
    private String integrationId;
    private String type;
    private List<Author> authors;
    @JsonProperty("accessible_by")
    private List<AccessibleBy> accessibleBy;
    @JsonProperty("attempt_number")
    private int attemptNumber;
    private Entity entity;
    private Data data;

    
    public String getId() {
        return id;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getWorkspaceId() {
        return workspaceId;
    }

    public String getWorkspaceName() {
        return workspaceName;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public String getIntegrationId() {
        return integrationId;
    }

    public String getType() {
        return type;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public List<AccessibleBy> getAccessibleBy() {
        return accessibleBy;
    }

    public int getAttemptNumber() {
        return attemptNumber;
    }

    public Entity getEntity() {
        return entity;
    }

    public Data getData() {
        return data;
    }

    @Override
    public String toString() {
        return "PagePropertiesUpdatedHook{" +
                "id='" + id + '\'' +
                ", timestamp=" + timestamp +
                ", workspaceId='" + workspaceId + '\'' +
                ", workspaceName='" + workspaceName + '\'' +
                ", subscriptionId='" + subscriptionId + '\'' +
                ", integrationId='" + integrationId + '\'' +
                ", type='" + type + '\'' +
                ", authors=" + authors +
                ", accessibleBy=" + accessibleBy +
                ", attemptNumber=" + attemptNumber +
                ", entity=" + entity +
                ", data=" + data +
                '}';
    }
}