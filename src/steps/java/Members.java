package steps.java;

public class Members {
    public FrequentFlyerMember getMember() {
        return new FrequentFlyerMember();
    }

    public static FrequentFlyerMember findByName(String name) {
        FrequentFlyerMember m = new FrequentFlyerMember();
        m.setName(name);
        return m;
    }
}
