package ru.job4j.threads.bomber;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Monster extends Items implements Runnable {
    public Monster(ReentrantLock[][] board, Cell thisCell) {
        super(board, thisCell,500);
        board[thisCell.getY()][thisCell.getX()].lock();

    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            ReentrantLock[][] board = this.getBoard();
            Cell cell = this.getThisCell();
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    if (!board[cell.getY()][cell.getX()].isLocked()) {
                        boolean result = board[cell.getY()][cell.getX()].tryLock();
                    }
                    boolean isGoed = move();
                    if (isGoed) {
                        Thread.sleep(5000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}

