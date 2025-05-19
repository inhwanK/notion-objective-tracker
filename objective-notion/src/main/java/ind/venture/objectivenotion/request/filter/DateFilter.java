package ind.venture.objectivenotion.request.filter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class DateFilter {
    @JsonProperty("is_empty")
    private boolean empty;
    @JsonProperty("is_not_empty")
    private boolean notEmpty;
    private String equals;
    private String after;
    private String before;
    @JsonProperty("on_or_after")
    private String onOrAfter;
    @JsonProperty("on_or_before")
    private String onOrBefore;

    public DateFilter(
            boolean empty,
            boolean notEmpty,
            String equals,
            String after,
            String before,
            String onOrAfter,
            String onOrBefore
    ) {
        this.empty = empty;
        this.notEmpty = notEmpty;
        this.equals = equals;
        this.after = after;
        this.before = before;
        this.onOrAfter = onOrAfter;
        this.onOrBefore = onOrBefore;
    }

    public boolean isEmpty() {
        return empty;
    }

    public String getEquals() {
        return equals;
    }

    public String getAfter() {
        return after;
    }

    public String getBefore() {
        return before;
    }

    public String getOnOrAfter() {
        return onOrAfter;
    }

    public String getOnOrBefore() {
        return onOrBefore;
    }

    private DateFilter(Builder builder) {
        this.equals = builder.equals;
        this.after = builder.after;
        this.before = builder.before;
        this.onOrAfter = builder.onOrAfter;
        this.onOrBefore = builder.onOrBefore;
        this.empty = builder.empty;
        this.notEmpty = builder.notEmpty;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String equals = null;
        private String after = null;
        private String before = null;
        private String onOrAfter = null;
        private String onOrBefore = null;
        private boolean empty = false;
        private boolean notEmpty = false;

        public Builder equals(String equals) {
            this.equals = equals;
            return this;
        }

        public Builder after(String after) {
            this.after = after;
            return this;
        }

        public Builder before(String before) {
            this.before = before;
            return this;
        }

        public Builder onOrAfter(String onOrAfter) {
            this.onOrAfter = onOrAfter;
            return this;
        }

        public Builder onOrBefore(String onOrBefore) {
            this.onOrBefore = onOrBefore;
            return this;
        }


        public Builder empty(boolean empty) {
            this.empty = empty;
            this.notEmpty = !empty;
            return this;
        }

        public Builder notEmpty(boolean notEmpty) {
            this.notEmpty = notEmpty;
            this.empty = !notEmpty;
            return this;
        }

        public DateFilter build() {
            return new DateFilter(this);
        }
    }
}