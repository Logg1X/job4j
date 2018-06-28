package ru.job4j.strategy;

/**
 * @author Toporov Pavel (mailto:per4mancerror@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class Square implements Shape {
    @Override
    public String draw() {
        StringBuilder square = new StringBuilder();
        square.append("####").append(System.lineSeparator());
        square.append("#  #").append(System.lineSeparator());
        square.append("#  #").append(System.lineSeparator());
        square.append("#  #").append(System.lineSeparator());
        square.append("####").append(System.lineSeparator());
        return square.toString();
    }
}
