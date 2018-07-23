package ru.job4j.bank.models;

public class Accounts {

    private double value;

    private String requisites;

    public Accounts() {
    }

    public Accounts(double value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getRequisites() {
        return requisites;
    }

    public void setRequisites(String requisites) {
        this.requisites = requisites;
    }

    @Override
    public String toString() {
        return System.lineSeparator()
                + "Номер счёта: " + requisites + System.lineSeparator()
                + "Остаток :" + value + "р." + System.lineSeparator();
    }
}
