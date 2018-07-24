package ru.job4j.lyambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;



public class Funk {

    private List<Double> diaposon(int start, int and, Function<Integer, Double> funk) {
        List<Double> doubles = new ArrayList<>();
        for (int i = start; i <= and; i++) {
            doubles.add(funk.apply(i));
        }
        return doubles;
    }

    public List<Double> lianerFunk(int start, int and, double a, double b) {
        return this.diaposon(start, and, x -> a * x + b);
    }

    public List<Double> quadricFunk(int start, int end, double a, double b, double c) {
        return this.diaposon(start, end, x -> a * x * x + b * x + c);
    }

    public List<Double> logoriphmFunk(int start, int and) {
        return this.diaposon(start, and, Math::log);
    }


}
