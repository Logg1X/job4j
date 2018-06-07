package ru.job4j.condition;

/**
 * @version $Id$
 * since 0.1
 * @autor Toporov Pavel (per4mancerror@gmail.com).
 */
public class Point {
    private int x;
    private int y;

    public Point(int x, int b) {
        this.x = x;
        this.y = b;
    }

    /**
     * Method dinstanceTo - calculate of distance between points.
     *
     * @param that - point at which the distance is calculated
     */
    public double distanceTo(Point that) {
        return Math.sqrt(Math.pow(this.x - that.x, 2) + Math.pow(this.y - that.y, 2));
    }

    public static void main(String[] args) {
        Point a = new Point(0, 1);
        Point b = new Point(2, 5);
        System.out.println("x1 = " + a.x + "\n"
                + "y1 = " + a.y + "\n"
                + "x2 = " + b.x + "\n"
                + "y2 = " + b.y);
        System.out.println("Расстояние между точками А и В : " + a.distanceTo(b));
    }

}
