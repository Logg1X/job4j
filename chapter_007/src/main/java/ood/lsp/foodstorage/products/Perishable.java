package ood.lsp.foodstorage.products;

public class Perishable extends ProdDecorator {
    private final boolean keepRefrigerated;

    public Perishable(Food product) {
        super(product);
        this.keepRefrigerated = true;
    }

    @Override
    public boolean isPerishable() {
        return this.keepRefrigerated;
    }

    @Override
    public String toString() {
        return "Food{"
                + "name='" + getName() + '\''
                + ", expaireDate=" + getExpaireDate()
                + ", createDate=" + getCreateDate()
                + ", price=" + getPrice()
                + ", discount=" + getDiscount()
                + ", keepRefrigerated=" + isPerishable()
                + '}';
    }
}
