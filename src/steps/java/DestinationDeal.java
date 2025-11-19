package steps.java;

import java.util.Objects;

public class DestinationDeal {
    private final String city;
    private final int price;

    public DestinationDeal(String city, int price) {
        this.city = city;
        this.price = price;
    }

    public String getCity() {
        return city;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DestinationDeal)) return false;
        DestinationDeal that = (DestinationDeal) o;
        return price == that.price && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, price);
    }

    @Override
    public String toString() {
        return "DestinationDeal{city='" + city + "', price=" + price + "}";
    }
}
