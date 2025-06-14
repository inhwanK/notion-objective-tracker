package ind.venture.objectivenotion.model.page.type;

public class Date {
    private String start;
    private String end;
    private String timeZone;

    public Date() {
    }

    public Date(String start, String end, String timeZone) {
        this.start = start;
        this.end = end;
        this.timeZone = timeZone;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    @Override
    public String toString() {
        return "Date{" +
                "start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", timeZone='" + timeZone + '\'' +
                '}';
    }
}
