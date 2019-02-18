package ood.srp;

import java.util.Map;
import java.util.Scanner;

public class EngineerInteractCalc extends CalcDecorator {
    private Map<Integer, Operation> allOperations;

    public EngineerInteractCalc(InterCalc interactCalculator) {
        super(interactCalculator);
        allOperations = this.createOperations();
    }


    private Map<Integer, Operation> createOperations() {
        return Map.of(
                5, new Cosinus(5, "Cossinus"),
                6, new Sinus(6, "Sinus"));
    }

    @Override
    public void showMenu() {
        super.showMenu();
        this.allOperations.values().forEach(operation -> System.out.println(String.format("%s - %s", operation.getKey(), operation.getName())));
    }

    @Override
    public Operation getOperation(int key) {
        Operation result = null;
        try {
            result = super.getOperation(key);
        } catch (RuntimeException e) {
            if (!allOperations.keySet().contains(key)) {
                throw new RuntimeException("Operation not found, try again!");
            }
            result = allOperations.get(key);
        }
        return result;
    }


    private class Cosinus extends OperImentation {

        protected Cosinus(int key, String name) {
            super(key, name);
        }

        @Override
        public Double[] getParammetrs(Scanner scanner, double previousResult) {
            Double[] result = new Double[2];
            result[0] = previousResult;
            if (result[0] == 0) {
                System.out.println("Введите число");
                result[0] = scanner.nextDouble();
            }
            return result;
        }

        @Override
        public void execute(Scanner scanner) {
            Double[] parammetrs = this.getParammetrs(scanner, getResult());
            setResult(Math.cos(parammetrs[0]));
            System.out.println(String.format("%s %s = %s", this.getSymbol(), parammetrs[0], getResult()));
            getResult();
        }

        @Override
        public String getSymbol() {
            return "COS";
        }
    }

    private class Sinus extends OperImentation {

        protected Sinus(int key, String name) {
            super(key, name);
        }

        @Override
        public Double[] getParammetrs(Scanner scanner, double previousResult) {
            Double[] result = new Double[2];
            result[0] = previousResult;
            if (result[0] == 0) {
                System.out.println("Введите число");
                result[0] = scanner.nextDouble();
            }
            return result;
        }

        @Override
        public void execute(Scanner scanner) {
            Double[] parammetrs = this.getParammetrs(scanner, getResult());
            setResult(Math.sin(parammetrs[0]));
            System.out.println(String.format("%s %s = %s", this.getSymbol(), parammetrs[0], getResult()));
            getResult();
        }

        @Override
        public String getSymbol() {
            return "SIN";
        }
    }


}
