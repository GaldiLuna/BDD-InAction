package steps.services;

public class Point {
    private final String name;

    private Point(String name) {
        this.name = name;
    }

    public static Point named(String name) {
        return new Point(name);
    }

    public net.thucydides.core.geometry.Point departingFrom(String emuPlains) {
        return null;
    }
}
