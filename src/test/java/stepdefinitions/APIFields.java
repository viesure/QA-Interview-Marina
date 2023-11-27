package stepdefinitions;

public class APIFields {
    public static final String CONTENT_TYPE_HEADER = "Content-type";
    public static final String APPLICATION_JSON = "application/json";

    public enum Condition {
        CLEAR("clear"),
        WINDY("windy"),
        MIST("mist"),
        DRIZZLE("drizzle"),
        DUST("dust");

        private final String conditionValue;

        Condition(String conditionValue) {
            this.conditionValue = conditionValue;
        }

        public String getConditionValue() {
            return conditionValue;
        }
    }

    public enum Description {
        FREEZING("freezing"),
        COLD("cold"),
        MILD("mild"),
        WARM("warm"),
        HOT("hot");

        private final String descriptionValue;

        Description(String descriptionValue) {
            this.descriptionValue = descriptionValue;
        }

        public String getDescriptionValue() {
            return descriptionValue;
        }
    }
    
}
