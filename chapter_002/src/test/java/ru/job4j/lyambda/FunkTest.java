package ru.job4j.lyambda;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;


public class FunkTest {
    private Funk funk = new Funk();


    @Test
    public void whenLineFunk() {
        assertThat(funk.lianerFunk(1, 10, 2.0, 1.0),
                contains(3.0, 5.0, 7.0, 9.0, 11.0, 13.0, 15.0, 17.0, 19.0, 21.0)
        );
    }

    @Test
    public void whenQuadricFunk() {
        assertThat(funk.quadricFunk(1, 5, 4.0, 3.0, 2.0),
                contains(9.0, 24.0, 47.0, 78.0, 117.0)
        );
    }

    @Test
    public void whenLogoriphmDunk() {
        assertThat(funk.logoriphmFunk(1, 2),
                contains(0.0, 0.6931471805599453)
        );

    }

}
