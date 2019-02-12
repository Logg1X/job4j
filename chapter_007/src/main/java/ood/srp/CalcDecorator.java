package ood.srp;

import ru.job4j.calculator.BaseCalculator;

public abstract class CalcDecorator implements BaseCalculator{
    private BaseCalculator baseCalculator;

    public CalcDecorator(BaseCalculator baseCalculator) {
        this.baseCalculator = baseCalculator;
    }

    @Override
    public void add(double first, double second) {
        baseCalculator.add(first,second);
    }

    @Override
    public void subtract(double first, double second) {
        baseCalculator.add(first,second);
    }

    @Override
    public void div(double first, double second) {
        baseCalculator.add(first,second);
    }

    @Override
    public void multiple(double first, double second) {
        baseCalculator.add(first,second);
    }

    @Override
    public double getResult() {
        return baseCalculator.getResult();
    }
}
