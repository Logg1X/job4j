package ru.job4j;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.chess.Logic;
import ru.job4j.chess.exeption.FigureNotFoundException;
import ru.job4j.chess.exeption.ImpossibleMoveException;
import ru.job4j.chess.exeption.OccupiedWayException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LogicTest {
    private Logic logic;

    @Before
    public void start() {
        this.logic = new Logic();

        logic.add(new PawnBlack(Cell.A7));
        logic.add(new PawnBlack(Cell.B7));
        logic.add(new PawnBlack(Cell.C7));
        logic.add(new PawnBlack(Cell.D7));
        logic.add(new PawnBlack(Cell.E7));
        logic.add(new PawnBlack(Cell.F7));
        logic.add(new PawnBlack(Cell.G7));
        logic.add(new PawnBlack(Cell.H7));
        logic.add(new RookBlack(Cell.A8));
        logic.add(new KnightBlack(Cell.B8));
        logic.add(new BishopBlack(Cell.C8));
        logic.add(new QeenBlack(Cell.D8));
        logic.add(new KingBlack(Cell.E8));
        logic.add(new BishopBlack(Cell.F8));
        logic.add(new KnightBlack(Cell.G8));
        logic.add(new RookBlack(Cell.H8));
    }

    @Test
    public void whenStepPawnD7D6ThenMoveTrue() {
        boolean move = logic.move(Cell.D7, Cell.D6);
        assertThat(true, is(move));
    }

    @Test
    public void whenStepPawnD7D5ThenImpossibleMoveException() throws ImpossibleMoveException {
        try {
            boolean move = logic.move(Cell.D7, Cell.E3);
        } catch (ImpossibleMoveException ex) {
            assertThat(ex.getMessage(), is("Фигура не может так пойти!"));
        }
    }

    @Test
    public void whenStepBishopC8E6ThenOccupiedWayException() throws OccupiedWayException {
        try {
            boolean move = logic.move(Cell.C8, Cell.E6);
        } catch (OccupiedWayException ex) {
            assertThat(ex.getMessage(), is("Путь занят!"));
        }
    }

    @Test
    public void whenFigureNotFoundException() throws FigureNotFoundException {
        try {
            boolean move = logic.move(Cell.C6, Cell.E6);
        } catch (FigureNotFoundException ex) {
            assertThat(ex.getMessage(), is("Фигура не найдена"));
        }
    }

    @Test
    public void whenClean() throws FigureNotFoundException {
        try {
            boolean move = logic.move(Cell.C6, Cell.E6);
        } catch (FigureNotFoundException ex) {
            assertThat(ex.getMessage(), is("Фигура не найдена"));
        }
    }
}