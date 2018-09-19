package ru.job4j.threads;

import java.util.concurrent.locks.ReentrantLock;

public class Test {
    private int size;
    private ReentrantLock[][] board = new ReentrantLock[size][size];
}
