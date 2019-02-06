package ood.srp;

import java.util.Scanner;

public abstract class OperImentation implements Operation {
    private final int key;
    private final String name;

    protected OperImentation(int key, String name) {
        this.key = key;
        this.name = name;
    }

    @Override
    public Double[] getParammetrs(Scanner scanner, double previousResult) {
        Double[] result = new Double[2];
        result[0] = previousResult;
        if (result[0] == 0) {
            System.out.println("Введите число:");
            result[0] = scanner.nextDouble();
        }
        System.out.println("Введите число:");
        result[1] = scanner.nextDouble();
        return result;
    }

    @Override
    public int getKey() {
        return this.key;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
