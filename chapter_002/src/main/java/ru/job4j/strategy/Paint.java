package ru.job4j.strategy;

public class Paint {
    Shape shape;

    public void draw(Shape shape) {
        System.out.println(shape.draw());
    }

    public static void main(String[] args) {
        Paint paint = new Paint();
        paint.draw(new Square());

    }
}
