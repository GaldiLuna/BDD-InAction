package steps.services;

public enum Status {
    Bronze, Silver, Gold, Platinum;

    public static Object statusLevelFor(int statusPoints) {
        return statusPoints;
    }
}
