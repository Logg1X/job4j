package ru.job4j.strategy;

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
