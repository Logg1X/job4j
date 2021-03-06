package ood.srp;

import java.util.Scanner;

public interface Operation {
    void execute(Scanner scanner);

    Double[] getParammetrs(Scanner scanner, double previousResult);

    int getKey();

    String getName();

    String getSymbol();

}
