package ru.gob4j.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator<Integer> {

    private int positionX = 0;
    private int positionY = 0;
    private int[][] arrays;

    public MatrixIterator(int[][] arrays) {
        this.arrays = arrays;
    }


    @Override
    public boolean hasNext() {
        boolean result = false;
        if (positionX <= arrays.length && positionY < arrays.length) {
            result = true;
        }
        return result;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException("NoSuchElementException");
        }
        int result = this.arrays[positionY][positionX++];
        if (positionX == arrays[positionY].length) {
            positionY++;
            positionX = 0;
        }
        return result;
    }
}
