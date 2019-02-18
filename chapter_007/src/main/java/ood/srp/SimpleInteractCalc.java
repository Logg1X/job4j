package ood.srp;

import ru.job4j.calculator.Calculator;

import java.util.Map;
import java.util.Scanner;

public class SimpleInteractCalc implements InterCalc {
    private Calculator calculator;
    private double previousResult;
    private Map<Integer, Operation> allOperations;

    public SimpleInteractCalc(Calculator calculator) {
        this.calculator = calculator;
        this.allOperations = createOperations();
    }

    @Override
    public void resetResult() {
        previousResult = 0;
    }

    @Override
    public void showMenu() {
        allOperations.values().forEach(operation -> System.out.println(String.format("%s - %s", operation.getKey(), operation.getName())));
    }

    private Map<Integer, Operation> createOperations() {
        return Map.of(
                1, new Add(1, "Add"),
                2, new Subtract(2, "Subtract"),
                3, new Div(3, "Division"),
                4, new Multiple(4, "Multiple"));
    }

    @Override
    public Operation getOperation(int key) {
        Operation result;
        if (!allOperations.keySet().contains(key)) {
            throw new RuntimeException("Operation not found, try again!");
        }
        result = allOperations.get(key);

        return result;
    }

    @Override
    public double getResult() {
        return this.previousResult;
    }

    @Override
    public void setResult(double previousResult) {
        this.previousResult = previousResult;
    }

    private class Add extends OperImentation {

        protected Add(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Scanner scanner) {
            Double[] parammetrs = this.getParammetrs(scanner, previousResult);
            calculator.add(parammetrs[0], parammetrs[1]);
            previousResult = calculator.getResult();
            System.out.println(String.format("%s%s%s = %s%s", parammetrs[0], this.getSymbol(), parammetrs[1], previousResult, System.lineSeparator()));
        }

        @Override
        public String getSymbol() {
            return " + ";
        }
    }

    private class Subtract extends OperImentation {

        protected Subtract(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Scanner scanner) {
            Double[] parammetrs = this.getParammetrs(scanner, previousResult);
            calculator.subtract(parammetrs[0], parammetrs[1]);
            previousResult = calculator.getResult();
            System.out.println(String.format("%s%s%s = %s%s", parammetrs[0], this.getSymbol(), parammetrs[1], previousResult, System.lineSeparator()));
        }

        @Override
        public String getSymbol() {
            return " - ";
        }
    }

    private class Div extends OperImentation {

        protected Div(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Scanner scanner) {
            Double[] parammetrs = this.getParammetrs(scanner, previousResult);
            calculator.div(parammetrs[0], parammetrs[1]);
            previousResult = calculator.getResult();
            System.out.println(String.format("%s%s%s = %s%s", parammetrs[0], this.getSymbol(), parammetrs[1], previousResult, System.lineSeparator()));
        }

        @Override
        public String getSymbol() {
            return " \\ ";
        }
    }

    private class Multiple extends OperImentation {

        protected Multiple(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Scanner scanner) {
            Double[] parammetrs = this.getParammetrs(scanner, previousResult);
            calculator.multiple(parammetrs[0], parammetrs[1]);
            previousResult = calculator.getResult();
            System.out.println(String.format("%s%s%s = %s%s", parammetrs[0], this.getSymbol(), parammetrs[1], previousResult, System.lineSeparator()));
        }

        @Override
        public String getSymbol() {
            return " * ";
        }
    }


}
