package ru.job4j.converter;

/**
 * Корвертор валюты.
 */
public class Converter {
    private final int oneEuroInRubles = 73;
    private final int oneDollarInRubles = 62;

    /**
     * Конвертируем рубли в евро.
     *
     * @param value рубли.
     * @return Евро.
     */
    public int rubleToEuro(int value) {
        return value / this.oneEuroInRubles;
    }

    /**
     * Конвертируем рубли в доллары.
     *
     * @param value рубли.
     * @return Доллары
     */
    public int rubleToDollar(int value) {
        return value / this.oneDollarInRubles;
    }

    /**
     * Конвертируем евро в рубли.
     *
     * @param value евро.
     * @return Рубли.
     */
    public int euroToRubles(int value) {

        return value * this.oneEuroInRubles;
    }

    /**
     * Конвертируем доллары в рубли.
     *
     * @param value доллоры.
     * @return Рубли
     */
    public int dollarToRubles(int value) {
        return value * this.oneDollarInRubles;
    }

}




