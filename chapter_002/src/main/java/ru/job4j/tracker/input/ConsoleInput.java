package ru.job4j.tracker.input;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.exception.MenuOutException;

import java.util.Scanner;

/**
 * @author Toporov Pavel (mailto:per4mancerror@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class ConsoleInput implements Input {
    Scanner scanner = new Scanner(System.in);

    @Override
    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

    @Override
    public int ask(String question, int[] range) {
        int key = Integer.valueOf(this.ask(question));
        boolean exist = false;
        for (int i : range) {
            if (i == key - 1) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            throw new MenuOutException("Выход за пределы меню. ");
        }
        return key;
    }
}
