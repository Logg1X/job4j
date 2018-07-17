package ru.job4j.calculator;

/**
 * Класс Calculator.
 * Simple calculator for java task.
 *
 * @author Toporov pavel.
 * @version 2.0.
 * @since 5.06.2018.
 */
public class Calculator {

    /**
     * Результирующая переменная.
     */
    private double result;

    /**
     * Метод add - для операции сложение.
     *
     * @param first  - первый аргумент.
     * @param second - второй аргумент.
     */

    public final void add(final double first, final double second) {
        this.result = first + second;
    }

    /**
     * Метод add - для операции вычитание.
     *
     * @param first  - первый аргумент.
     * @param second - второй аргумент.
     */

    public final void subtract(final double first, final double second) {
        this.result = first - second;
    }

    /**
     * Метод add - для операции деление.
     *
     * @param first  - первый аргумент.
     * @param second - второй аргумент.
     */

    public final void div(final double first, final double second) {
        this.result = first / second;
    }

    /**
     * Метод add - для операции умножение.
     *
     * @param first  - первый аргумент.
     * @param second - второй аргумент.
     */

    public final void multiple(final double first, final double second) {
        this.result = first * second;
    }

    /**
     * @return result Возвращеет результат операции.
     */

    public final double getResult() {
        return this.result;
    }
}
