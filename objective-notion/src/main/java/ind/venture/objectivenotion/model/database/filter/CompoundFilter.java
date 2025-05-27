package ind.venture.objectivenotion.model.database.filter;

import java.util.List;

public class CompoundFilter implements QueryTopLevelFilter, CompoundFilterElement {
    private List<PropertyFilter> or;
    private List<PropertyFilter> and;

    private CompoundFilter(List<PropertyFilter> or, List<PropertyFilter> and) {
        this.or = or;
        this.and = and;
    }

    public static CompoundFilter or(List<PropertyFilter> or) {
        return new CompoundFilter(or, null);
    }

    public static CompoundFilter and(List<PropertyFilter> and) {
        return new CompoundFilter(null, and);
    }

    public List<PropertyFilter> getOr() {
        return or;
    }

    public List<PropertyFilter> getAnd() {
        return and;
    }
}
