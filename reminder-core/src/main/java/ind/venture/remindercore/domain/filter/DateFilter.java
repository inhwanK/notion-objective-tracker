package ind.venture.remindercore.domain.filter;

public class DateFilter {
    private boolean isEmpty;
    private boolean isNotEmpty;
    private String equals;
    private String after;
    private String before;
    private String onOrAfter;
    private String onOrBefore;


    private DateFilter(Builder builder) {
        this.equals = builder.equals;
        this.after = builder.after;
        this.before = builder.before;
        this.onOrAfter = builder.onOrAfter;
        this.onOrBefore = builder.onOrBefore;
        this.isEmpty = builder.isEmpty;
        this.isNotEmpty = builder.isNotEmpty;
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
        private boolean isEmpty = false;
        private boolean isNotEmpty = false;

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


        public Builder isEmpty(boolean isEmpty) {
            this.isEmpty = isEmpty;
            this.isNotEmpty = !isEmpty;
            return this;
        }

        public Builder isNotEmpty(boolean isNotEmpty) {
            this.isNotEmpty = isNotEmpty;
            this.isEmpty = !isNotEmpty;
            return this;
        }

        public DateFilter build() {
            return new DateFilter(this);
        }
    }
}