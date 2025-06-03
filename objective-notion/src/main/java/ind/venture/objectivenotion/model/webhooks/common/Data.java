package ind.venture.objectivenotion.model.webhooks.common;

import java.util.List;

public class Data {
    private Parent parent;
    // 훅의 종류에 따라 타입이 바뀜
    private List<String> updatedProperties;

    public Parent getParent() {
        return parent;
    }

    public List<String> getUpdatedProperties() {
        return updatedProperties;
    }

    @Override
    public String toString() {
        return "Data{" +
                "parent=" + parent +
                ", updatedProperties=" + updatedProperties +
                '}';
    }
}
