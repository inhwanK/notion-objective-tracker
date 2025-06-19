package ind.venture.objectivenotion.model.page.property;

import ind.venture.objectivenotion.model.page.type.Date;
import lombok.ToString;

@ToString(callSuper = true)
public class DateProperty extends PageProperty {
    private Date date;

    public DateProperty() {
        super();
    }

    public DateProperty(String id, Date date) {
        super(id, "date");
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

}
