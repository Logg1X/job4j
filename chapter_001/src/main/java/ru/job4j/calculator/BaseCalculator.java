package ru.job4j.calculator;

public interface BaseCalculator {
    void add(final double first, final double second);

    void subtract(final double first, final double second);

    void div(final double first, final double second);


    void multiple(final double first, final double second);

    double getResult();
}
