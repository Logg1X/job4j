package ru.job4j.chess.firuges;

public interface Figure {
    Cell position();

    Cell[] way(Cell source, Cell dest);

    Figure copy(Cell dest);

    default String icon() {
        return String.format(
                "%s.png", this.getClass().getSimpleName());
    }

    /**
     * Метод запоминает ячейки по которым шла фигура от начальной до конечной ячейки.
     *
     * @param source начальная ячейка.
     * @param dest   конечная ячейка.
     * @return массив ячеек пути, где последняя ячейка массива = конечная ячейка.
     */

    default Cell[] trace(Cell source, Cell dest) {
        int size = Math.abs(source.x - dest.x) > Math.abs(source.y - dest.y)
                ? Math.abs(source.x - dest.x) : Math.abs(source.y - dest.y);
        Cell[] steps = new Cell[size];
        int deltaX = Integer.compare(dest.x, source.x);
        int deltaY = Integer.compare(dest.y, source.y);
        int itX = source.x;
        int itY = source.y;
        for (int i = 0; itX != dest.x || itY != dest.y; i++) {
            itX += deltaX;
            itY += deltaY;
            steps[i] = Cell.getCellByValue(itX, itY);
        }
        return steps;
    }

}
