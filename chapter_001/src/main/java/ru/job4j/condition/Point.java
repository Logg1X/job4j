package ru.job4j.condition;

/**
 * @author Toporov Pavel (per4mancerror@gmail.com).
 * @version $Id$
 * since 0.1
 */
public class Point {
    /**
     * Координата на оси "х".
     */
    private final int osX;
    /**
     * Координата на оси "у".
     */
    private final int osY;

    /**
     * Конструктор класса.
     *
     * @param x координаты на оси х
     * @param y координата на оси у
     */
    public Point(final int x, final int y) {
        this.osX = x;
        this.osY = y;
    }

    /**
     * Точка входа в программу.
     * Тестирование программы.
     *
     * @param args параметры.
     */
    public static void main(final String[] args) {
        Point a = new Point(0, 1);
        Point b = new Point(2, 5);
        System.out.println("x1 = " + a.osX + "\n"
                + "y1 = " + a.osY + "\n"
                + "x2 = " + b.osX + "\n"
                + "y2 = " + b.osY);
        System.out.println("Расстояние между точками А и В : "
                + a.distanceTo(b)
        );
    }

    /**
     * Method dinstanceTo - calculate of distance between points.
     *
     * @param that - point at which the distance is calculated
     * @return Result.
     */
    public final double distanceTo(final Point that) {
        return Math.sqrt(Math.pow(this.osX - that.osX, 2)
                + Math.pow(this.osY - that.osY, 2)
        );
    }
}
