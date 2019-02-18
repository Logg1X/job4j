package ood.srp;

public abstract class CalcDecorator implements InterCalc {
    InterCalc interactCalculator;

    public CalcDecorator(InterCalc interactCalculator) {
        this.interactCalculator = interactCalculator;
    }

    @Override
    public void resetResult() {
        interactCalculator.resetResult();
    }

    @Override
    public void showMenu() {
        interactCalculator.showMenu();
    }

    @Override
    public Operation getOperation(int key) {
        return interactCalculator.getOperation(key);
    }

    @Override
    public double getResult() {
        return interactCalculator.getResult();
    }

    @Override
    public void setResult(double result) {
        interactCalculator.setResult(result);
    }
}
