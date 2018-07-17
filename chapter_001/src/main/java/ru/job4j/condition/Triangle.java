package ru.job4j.condition;

/**
 * @author Toporov Pavel
 */

public class Triangle {
    /**
     * Вершина трейгольника №1.
     */
    private Point point1;
    /**
     * Вершина трейгольника №2.
     */
    private Point point2;
    /**
     * Вершина трейгольника №3.
     */
    private Point point3;

    /**
     * Конструктор класса.
     *
     * @param a вершина №1.
     * @param b вершина №2.
     * @param c вершина №3.
     */

    public Triangle(final Point a, final Point b, final Point c) {
        this.point1 = a;
        this.point2 = b;
        this.point3 = c;
    }

    /**
     * Метод вычисления полупериметра по длинам сторон.
     * (ab + ac + bc) / 2
     *
     * @param ab расстояние между точками point1 point2
     * @param ac расстояние между точками point1 point3
     * @param bc расстояние между точками point2 point3
     * @return Перимент.
     */
    public final double period(final double ab,
                               final double ac,
                               final double bc) {
        return (ab + ac + bc) / 2;
    }

    /**
     * Метод должен вычислить площадь треугольника.
     *
     * @return Вернуть прощадь,
     * если треугольник существует или -1,
     * если треугольника нет.
     */
    public final double area() {
        double rsl = -1;
        double ab = this.point1.distanceTo(this.point2);
        double ac = this.point1.distanceTo(this.point3);
        double bc = this.point2.distanceTo(this.point3);
        double p = this.period(ab, ac, bc);
        if (this.exist(ab, ac, bc)) {
            rsl = Math.sqrt(p * (p - ab) * (p - ac) * (p - bc));
        }
        return rsl;
    }

    /**
     * Проверка сущесвует ли треугольник.
     *
     * @param ab сторона №1.
     * @param ac сторона №1.
     * @param bc сторона №1.
     * @return результат истина/лож
     */
    private boolean exist(final double ab,
                          final double ac,
                          final double bc) {
        return (ab + ac > bc || ab + bc > ac || bc + ac > ab);
    }
}
