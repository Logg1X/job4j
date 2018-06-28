package ru.job4j.tracker;

/**
 * @author Toporov Pavel (mailto:per4mancerror@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class StubInput implements Input {

    private final String[] value;
    private int position;

    public StubInput(String[] value) {
        this.value = value;
    }

    @Override
    public String ask(String qustion) {
        return this.value[this.position++];
    }

}
