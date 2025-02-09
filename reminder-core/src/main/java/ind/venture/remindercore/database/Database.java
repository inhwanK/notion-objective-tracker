package ind.venture.remindercore.database;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Database {

    private String object;
    private String id;
    private String url;
    @JsonProperty("created_time")
    private String createdTime;
    @JsonProperty("is_inline")
    private boolean isInline;

    public Database() {
    }

    public Database(String object, String id, String url, String createdTime, boolean isInline) {
        this.object = object;
        this.id = id;
        this.url = url;
        this.createdTime = createdTime;
        this.isInline = isInline;
    }


    public String getObject() {
        return object;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public boolean isInline() {
        return isInline;
    }

    @Override
    public String toString() {
        return "Database{" + "object='" + object + '\'' + ", id='" + id + '\'' + ", url='" + url + '\'' + ", createdTime='" + createdTime + '\'' + ", isInline=" + isInline + '}';
    }
}
