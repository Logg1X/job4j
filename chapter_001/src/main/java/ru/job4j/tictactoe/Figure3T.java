package ru.job4j.tictactoe;

import javafx.scene.shape.Rectangle;

/**
 * @author Toporov Pavel (mailto:per4mancerror@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Figure3T extends Rectangle {
    /**
     * markX - Крестик.
     */
    private boolean markX = false;
    /**
     * markO - нолик.
     */
    private boolean markO = false;

    /**
     * Конструктор класса по умолчанию.
     */
    public Figure3T() {
    }

    /**
     * Конструктор класса.
     *
     * @param markXX Х
     */
    public Figure3T(final boolean markXX) {
        this.markX = markXX;
        this.markO = !markXX;
    }

    /**
     * Получаем фигуру.
     * Х - если true.
     * О - если false.
     *
     * @param markXX Х.
     */
    public final void take(final boolean markXX) {
        this.markX = markXX;
        this.markO = !markXX;
    }

    /**
     * Геттер MarkX.
     *
     * @return MarkX.
     */
    public final boolean hasMarkX() {
        return this.markX;
    }

    /**
     * Геттер hasMarkO.
     *
     * @return hasMarkO.
     */
    public final boolean hasMarkO() {
        return this.markO;
    }
}
