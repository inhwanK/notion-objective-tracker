package ind.venture.remindercore.domain.property;

public class RichText {
    private Text text;
    private String plainText;
    private String href;

    public RichText() {
    }

    public RichText(Text text, String plainText, String href) {
        this.text = text;
        this.plainText = plainText;
        this.href = href;
    }

    public Text getText() {
        return text;
    }

    public String getPlainText() {
        return plainText;
    }

    public String getHref() {
        return href;
    }

    @Override
    public String toString() {
        return "RichText{" +
                "text=" + text +
                ", plainText='" + plainText + '\'' +
                ", href='" + href + '\'' +
                '}';
    }
}
