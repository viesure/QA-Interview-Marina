package stepdefinitions;

public class APIFields {
    public static final String CONTENT_TYPE_HEADER = "Content-type";
    public static final String APPLICATION_JSON = "application/json";
    public static final int HTTP_STATUS_OK = 200;

    public enum Condition {
        clear,
        windy,
        mist,
        drizzle,
        dust;
    }

    public enum Description {
        freezing,
        cold,
        mild,
        warm,
        hot;
    }
}
