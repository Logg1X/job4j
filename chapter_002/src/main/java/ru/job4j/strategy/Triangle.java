package ru.job4j.strategy;


public class Triangle implements Shape {
    @Override
    public String draw() {
        StringBuilder triangle = new StringBuilder();
        triangle.append("   #   ").append(System.lineSeparator());
        triangle.append("  ###  ").append(System.lineSeparator());
        triangle.append(" ##### ").append(System.lineSeparator());
        triangle.append("#######").append(System.lineSeparator());
        return triangle.toString();
    }


}
