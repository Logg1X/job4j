package ru.job4j.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenItIterator implements Iterator<Integer> {

    private int[] numbers;
    private int position = 0;

    public EvenItIterator(final int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int co = this.position; co < this.numbers.length; co++) {
            if (this.numbers[co] % 2 == 0) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException("NoSuchElementException");
        }
        while (this.numbers[this.position] % 2 != 0) {
            this.position++;
        }
        return this.numbers[this.position++];
    }
}
