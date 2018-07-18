package ru.job4j.chess.firuges.black;

import ru.job4j.chess.exeption.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 * @author Toporov Pavel (mailto:per4mancerror@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class PawnBlack implements Figure {
    private final Cell position;

    public PawnBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {

        if (source.y-dest.y > Math.abs(1) || source.x != dest.x || source.y < dest.y || source.y != dest.y + 1 && source.x != dest.x) {
            throw new ImpossibleMoveException("Фигура не может так пойти!");
        }
        return trace(source, dest);
    }

    @Override
    public Figure copy(Cell dest) {
        return new PawnBlack(dest);
    }
}
