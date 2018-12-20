package ru.job4j.chess;

import ru.job4j.chess.exeption.FigureNotFoundException;
import ru.job4j.chess.exeption.ImpossibleMoveException;
import ru.job4j.chess.exeption.OccupiedWayException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 * //TODO add comments.
 *
 * @author Toporov Pavel (mailto:per4mancerror@gmail.com).
 * @version $Id$.
 * @since 1.0.
 */
public class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    /**
     * Метод расставляет фигуры.
     *
     * @param figure фигура.
     */
    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    /**
     * Метод выполяет перемещение фигуры.
     *
     * @param source начальная ячейка.
     * @param dest   конечная ячейка.
     * @return true, если ячейка достигла конечной ячейки.
     * @throws ImpossibleMoveException перемещение невозможно.
     * @throws OccupiedWayException    путь занят.
     * @throws FigureNotFoundException фигура не найдена.
     */
    public boolean move(Cell source, Cell dest)
            throws FigureNotFoundException, ImpossibleMoveException, OccupiedWayException {
        boolean rst = false;
        int index = this.findBy(source);
        if (index == -1) {
            throw new FigureNotFoundException("Фигура не найдена");
        }
        Cell[] steps = this.figures[index].way(source, dest);
        if (!this.isLegalWay(steps)) {
            throw new OccupiedWayException("Путь занят!");
        }
        if (steps.length > 0 && steps[steps.length - 1].equals(dest)) {
            rst = true;
            this.figures[index] = this.figures[index].copy(dest);
        }
        return rst;
    }

    /**
     * Метод очищает старое значение ячейки
     */
    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    /**
     * Метод проверяет не заняты ли ячейки по пути от начальной до конечной ячейки фигурами.
     *
     * @param steps массив шагов.
     * @return true, если путь свободен.
     */

    private boolean isLegalWay(Cell[] steps) {
        boolean isLegal = true;
        for (Cell cell : steps) {
            if (this.findBy(cell) != -1) {
                isLegal = false;
                break;
            }
        }
        return isLegal;
    }

    /**
     * Метод проверяет наличие фигуры на ячейке.
     *
     * @param cell ячейка для проверки.
     * @return -1 если ячейка свободна.
     */

    private int findBy(Cell cell) {
        int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                rst = index;
                break;
            }
        }
        return rst;
    }
}
