package ind.venture.remindercore.domain.property;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageProperty {
    private String id;
    private String type;
    private List<RichText> title;
    private Date date;
    private String url;
    private String phoneNumber;
    private String email;
    @JsonProperty("created_time")
    private String createdTime;
    @JsonProperty("last_edited_time")
    private String lastEditedTime;

    public PageProperty() {
    }

    public PageProperty(
            String id,
            String type,
            List<RichText> title,
            Date date,
            String url,
            String phoneNumber,
            String email,
            String createdTime,
            String lastEditedTime
    ) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.date = date;
        this.url = url;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.createdTime = createdTime;
        this.lastEditedTime = lastEditedTime;
    }


    public String getId() {
        return id;
    }

    public List<RichText> getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public String getLastEditedTime() {
        return lastEditedTime;
    }
}
