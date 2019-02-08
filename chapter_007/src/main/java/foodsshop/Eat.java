package foodsshop;

import java.time.LocalDate;

public class Eat extends Food {
    public Eat(String name, LocalDate expaireDate, LocalDate createDate, double price, int discount) {
        super(name, expaireDate, createDate, price, discount);
    }
}
