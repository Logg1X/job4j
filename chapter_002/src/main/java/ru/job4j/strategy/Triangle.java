package ru.job4j.strategy;

/**
 * @author Toporov Pavel (mailto:per4mancerror@gmail.com)
 * @version $Id$
 * @since 1.0
 */
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
