package ru.job4j.tracker;

public class ValidateInput extends ConsoleInput {


    @Override
    public int ask(String question, int[] range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = super.ask(question, range);
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
