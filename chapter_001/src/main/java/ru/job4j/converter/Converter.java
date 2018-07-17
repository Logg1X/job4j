package ru.job4j.converter;

/**
 * Корвертор валюты.
 */
public class Converter {
    /**
     * Курс Евро к Рублю.
     */
    private final int oneEuroInRubles = 73;
    /**
     * Курс Доллора к Рублю.
     */
    private final int oneDollarInRubles = 62;

    /**
     * Конвертируем рубли в евро.
     *
     * @param value рубли.
     * @return Евро.
     */
    public final int rubleToEuro(final int value) {
        return value / this.oneEuroInRubles;
    }

    /**
     * Конвертируем рубли в доллары.
     *
     * @param value рубли.
     * @return Доллары
     */
    public final int rubleToDollar(final int value) {
        return value / this.oneDollarInRubles;
    }

    /**
     * Конвертируем евро в рубли.
     *
     * @param value евро.
     * @return Рубли.
     */
    public final int euroToRubles(final int value) {

        return value * this.oneEuroInRubles;
    }

    /**
     * Конвертируем доллары в рубли.
     *
     * @param value доллоры.
     * @return Рубли
     */
    public final int dollarToRubles(final int value) {
        return value * this.oneDollarInRubles;
    }

}




