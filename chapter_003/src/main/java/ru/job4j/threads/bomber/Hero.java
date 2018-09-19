package ru.job4j.threads.bomber;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Hero extends Items implements Runnable {


    public Hero(ReentrantLock[][] board, Cell thisCell) {
        super(board, thisCell,500);

    }

    @Override
    public void run() {
        ReentrantLock[][] board = this.getBoard();
        Cell cell = this.getThisCell();
        while (!Thread.currentThread().isInterrupted()) {
            try {
                if (!board[cell.getY()][cell.getX()].isLocked()) {
                    boolean result = board[cell.getY()][cell.getX()].tryLock();
                }
                boolean isGoed = move();
                if (isGoed) {
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
