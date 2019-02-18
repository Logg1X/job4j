package ood.srp;

public interface InterCalc {
    void resetResult();

    void showMenu();

    Operation getOperation(int key);

    double getResult();

    void setResult(double result);
}
