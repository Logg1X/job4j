package ru.job4j.loop;

/**
 * @author Toporov Pavel (mailto:per4mancerror@gmail.com).
 * @version $Id$
 * @since 0.1
 */
public class Board {
    /**
     * Шахматная Доска.
     *
     * @param width  Ширина.
     * @param height Высота.
     * @return Доска
     */
    public final String paint(final int width, final int height) {
        StringBuilder screen = new StringBuilder();
        String ln = System.lineSeparator();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if ((j + i) % 2 == 0) {
                    screen.append("X");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(ln);
        }
        return screen.toString();
    }
}
