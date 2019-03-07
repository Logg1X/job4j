package ood.lsp.foodstorage.products;

public class Perishable extends ProdDecorator {
    private final boolean perishable;

    public Perishable(Food product) {
        super(product);
        this.perishable = true;
    }

    @Override
    public boolean isPerishable() {
        return this.perishable;
    }

    @Override
    public String toString() {
        return "Food{"
                + "name='" + getName() + '\''
                + ", expaireDate=" + getExpaireDate()
                + ", createDate=" + getCreateDate()
                + ", price=" + getPrice()
                + ", discount=" + getDiscount()
                + ", perishable=" + isPerishable()
                + '}';
    }
}
