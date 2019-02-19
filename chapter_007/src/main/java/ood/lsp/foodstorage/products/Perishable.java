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
}
