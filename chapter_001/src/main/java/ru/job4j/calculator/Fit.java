package ru.job4j.calculator;

/**
 * Программа расчета идеального веса.
 */
public class Fit {

    /**
     * Идеальный вес для мужщины.
     *
     * @param height Рост.
     * @return идеальный вес.
     */
    public final double manWeight(final double height) {
        return (height - 100) * 1.15;
    }


    /**
     * Идеальный вес для женщины.
     *
     * @param height Рост.
     * @return идеальный вес.
     */
    public final double womanWeight(final double height) {
        return (height - 110) * 1.15;
    }
}
