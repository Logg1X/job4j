package ru.job4j.tracker.input;

import ru.job4j.tracker.exception.MenuOutException;

public class ValidateInput implements Input {

    private Input input;

    public ValidateInput(Input input) {
        this.input = input;
    }

    @Override
    public String ask(String question) {
        return this.input.ask(question);
    }

    @Override
    public int ask(String question, int[] range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = this.input.ask(question, range);
                invalid = false;
            } catch (MenuOutException moe) {
                System.out.println("Попробуйте снова.");
            } catch (NumberFormatException ex) {
                System.out.println("Некорректный ввод. Попробуйте снова.");
            }
        } while (invalid);
        return value;
    }

}
