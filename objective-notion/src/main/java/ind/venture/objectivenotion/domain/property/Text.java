package ind.venture.objectivenotion.domain.property;

public class Text {
    private String content;
    private Link link;

    public Text() {
    }

    public Text(String content, Link link) {
        this.content = content;
        this.link = link;
    }

    public String getContent() {
        return content;
    }

    public Link getLink() {
        return link;
    }
}

