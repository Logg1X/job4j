package ru.job4j.profession;

public class Doctor extends Profession {

    public Diagnose heal(Pacient pacient) {
        return new Diagnose();
    }
}
