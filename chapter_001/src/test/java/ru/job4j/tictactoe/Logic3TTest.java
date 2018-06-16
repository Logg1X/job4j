package ru.job4j.tictactoe;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class Logic3TTest {

    @Test
    public void whenHasOWinnerToFirstDiagonal() {
        Figure3T[][] table = {
                {new Figure3T(false), new Figure3T(), new Figure3T(true)},
                {new Figure3T(true), new Figure3T(false), new Figure3T(false)},
                {new Figure3T(false), new Figure3T(), new Figure3T(false)},
        };
        Logic3T login = new Logic3T(table);
        assertThat(login.isWinnerO(), is(true));
    }

    @Test
    public void whenHasOWinnerToSecondDiagonal() {
        Figure3T[][] table = {
                {new Figure3T(), new Figure3T(), new Figure3T(false)},
                {new Figure3T(), new Figure3T(false), new Figure3T()},
                {new Figure3T(false), new Figure3T(), new Figure3T()},
        };
        Logic3T login = new Logic3T(table);
        assertThat(login.isWinnerO(), is(true));
    }

    @Test
    public void whenHasOGorisontalLineOne() {
        Figure3T[][] table = {
                {new Figure3T(false), new Figure3T(false), new Figure3T(false)},
                {new Figure3T(false), new Figure3T(), new Figure3T()},
                {new Figure3T(true), new Figure3T(), new Figure3T()},
        };
        Logic3T login = new Logic3T(table);
        assertThat(login.isWinnerO(), is(true));
    }

    @Test
    public void whenHasOGorisontalLineTwo() {
        Figure3T[][] table = {
                {new Figure3T(false), new Figure3T(true), new Figure3T(false)},
                {new Figure3T(false), new Figure3T(false), new Figure3T(false)},
                {new Figure3T(true), new Figure3T(), new Figure3T()},
        };
        Logic3T login = new Logic3T(table);
        assertThat(login.isWinnerO(), is(true));
    }

    @Test
    public void whenHasOGorisontalLineThree() {
        Figure3T[][] table = {
                {new Figure3T(true), new Figure3T(false), new Figure3T(true)},
                {new Figure3T(false), new Figure3T(true), new Figure3T()},
                {new Figure3T(false), new Figure3T(false), new Figure3T(false)},
        };
        Logic3T login = new Logic3T(table);
        assertThat(login.isWinnerO(), is(true));
    }

    @Test
    public void whenHasOWertikalLine() {
        Figure3T[][] table = {
                {new Figure3T(true), new Figure3T(false), new Figure3T(true)},
                {new Figure3T(false), new Figure3T(false), new Figure3T()},
                {new Figure3T(false), new Figure3T(false), new Figure3T(true)},
        };
        Logic3T login = new Logic3T(table);
        assertThat(login.isWinnerO(), is(true));
    }

    @Test
    public void whenHasXWinnerToFirstDiagonal() {
        Figure3T[][] table = {
                {new Figure3T(true), new Figure3T(), new Figure3T(false)},
                {new Figure3T(false), new Figure3T(true), new Figure3T(true)},
                {new Figure3T(true), new Figure3T(), new Figure3T(true)},
        };
        Logic3T login = new Logic3T(table);
        assertThat(login.isWinnerX(), is(true));
    }

    @Test
    public void whenHasXWinnerToSecondDiagonal() {
        Figure3T[][] table = {
                {new Figure3T(), new Figure3T(), new Figure3T(true)},
                {new Figure3T(), new Figure3T(true), new Figure3T()},
                {new Figure3T(true), new Figure3T(), new Figure3T()},
        };
        Logic3T login = new Logic3T(table);
        assertThat(login.isWinnerX(), is(true));
    }

    @Test
    public void whenHasXGorisontalLineOne() {
        Figure3T[][] table = {
                {new Figure3T(true), new Figure3T(true), new Figure3T(true)},
                {new Figure3T(true), new Figure3T(), new Figure3T()},
                {new Figure3T(false), new Figure3T(), new Figure3T()},
        };
        Logic3T login = new Logic3T(table);
        assertThat(login.isWinnerX(), is(true));
    }

    @Test
    public void whenHasXGorisontalLineTwo() {
        Figure3T[][] table = {
                {new Figure3T(true), new Figure3T(false), new Figure3T(true)},
                {new Figure3T(true), new Figure3T(true), new Figure3T(true)},
                {new Figure3T(false), new Figure3T(), new Figure3T()},
        };
        Logic3T login = new Logic3T(table);
        assertThat(login.isWinnerX(), is(true));
    }

    @Test
    public void whenHasXGorisontalLineThree() {
        Figure3T[][] table = {
                {new Figure3T(false), new Figure3T(true), new Figure3T(false)},
                {new Figure3T(true), new Figure3T(false), new Figure3T()},
                {new Figure3T(true), new Figure3T(true), new Figure3T(true)},
        };
        Logic3T login = new Logic3T(table);
        assertThat(login.isWinnerX(), is(true));
    }

    @Test
    public void whenHasXWertikalLine() {
        Figure3T[][] table = {
                {new Figure3T(false), new Figure3T(true), new Figure3T(false)},
                {new Figure3T(true), new Figure3T(true), new Figure3T()},
                {new Figure3T(true), new Figure3T(true), new Figure3T(false)},
        };
        Logic3T login = new Logic3T(table);
        assertThat(login.isWinnerX(), is(true));
    }


    @Test
    public void whenHasGas() {
        Figure3T[][] table = {
                {new Figure3T(false), new Figure3T(false), new Figure3T(true)},
                {new Figure3T(true), new Figure3T(), new Figure3T(false)},
                {new Figure3T(false), new Figure3T(false), new Figure3T(true)},
        };
        Logic3T login = new Logic3T(table);
        assertThat(login.hasGap(), is(true));
    }
}
