package ood.lsp.foodstorage.products;

import java.util.Objects;

public class RecyclableFood extends ProdDecorator {
    private boolean canReproduct;

    public RecyclableFood(Food product) {
        super(product);
        this.canReproduct = true;
    }

    @Override
    public boolean isRecyclable() {
        return this.canReproduct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Food eat = (Food) o;
        return Double.compare(eat.getPrice(), getPrice()) == 0
                && getDiscount() == eat.getDiscount()
                && Objects.equals(getName(), eat.getName())
                && Objects.equals(getExpaireDate(), eat.getExpaireDate())
                && Objects.equals(getCreateDate(), eat.getCreateDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(),
                getExpaireDate(),
                getCreateDate(),
                getPrice(),
                getDiscount());
    }

    @Override
    public String toString() {

        return "Food{"
                + "name='" + getName() + '\''
                + ", expaireDate=" + getExpaireDate()
                + ", createDate=" + getCreateDate()
                + ", price=" + getPrice()
                + ", discount=" + getDiscount()
                + ", canReproduct=" + isRecyclable()
                + '}';
    }
}
