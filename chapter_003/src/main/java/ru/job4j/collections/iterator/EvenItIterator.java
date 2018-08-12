package ru.job4j.collections.iterator;

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
        while (this.position < this.numbers.length) {
            if (this.numbers[position] % 2 == 0) {
                result = true;
                break;
            }
            position++;
        }
        return result;
    }

    @Override
    public Integer next() {
        int result;
        if (!hasNext()) {
            throw new NoSuchElementException("NoSuchElementException");
        } else {
            result = this.numbers[this.position++];
        }
        return result;
    }
}
