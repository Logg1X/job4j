package ood.srp;

import java.util.Scanner;

public interface Operation {
    double execute(Scanner scanner);

    Double[] getParammetrs(Scanner scanner, double previousResult);

    int getKey();

    String getName();

    String getSymbol();

}
