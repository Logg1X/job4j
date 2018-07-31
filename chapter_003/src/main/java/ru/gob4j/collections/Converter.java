package ru.gob4j.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {

   public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {

            Iterator<Integer> inner = it.next();

            @Override
            public boolean hasNext() {
                if (!inner.hasNext() && it.hasNext()) {
                    inner = it.next();
                }
                return inner.hasNext();
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("NoSuchElementException");
                }
                return inner.next();
            }
        };
    }
}
