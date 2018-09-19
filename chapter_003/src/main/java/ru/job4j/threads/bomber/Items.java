package ru.job4j.threads.bomber;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Items {
    private ReentrantLock[][] board;
    private Cell thisCell;

    private final String className = this.getClass().getSimpleName();

    public Items(ReentrantLock[][] board, Cell thisCell, int timeOut) {
        this.board = board;
        this.thisCell = thisCell;
    }

    public Cell getThisCell() {
        return thisCell;
    }

    public ReentrantLock[][] getBoard() {
        return board;
    }

    public final boolean move() {
        boolean result = false;
        int currentX = this.thisCell.getX();
        int currentY = this.thisCell.getY();
        while (!result) {
            Way way = getRundomWay();
            while (!result) {
                if (this.className.equals("Hero")) {
                    boolean kill = this.board[currentY][currentX].hasQueuedThreads();
                    if (kill) {
                        System.out.println("Бомбер погиб.");
                        return result;
                    }
                }
                    switch (way) {
                        case LEFT:
                            result = wayLeft(currentX, currentY);
                            if (!result) {
                                break;
                            }
                            break;
                        case RIGHT:
                            result = wayRight(currentX, currentY);
                            if (!result) {
                                break;
                            }
                            break;
                        case UP:
                            result = wayUp(currentX, currentY);
                            if (!result) {
                                break;
                            }
                            break;
                        case DOWN:
                            result = wayDown(currentX, currentY);
                            if (!result) {
                                break;
                            }
                            break;
                    }
                }
                    break;
            }
        return result;
    }


    private boolean wayLeft(int x, int y) {
        boolean result = false;
        System.out.println(String.format("%s Ходит ВЛЕВО", this.className));
        if (x > 0) {
            System.out.println(String.format("%s пытается захватить %s : %s", this.className, y, x - 1));
            try {
                result = board[y][x - 1].tryLock(500, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("В эту сторону ходить нелязя.");
        }
        if (result) {
            System.out.println(String.format("%s захватил %s : %s", this.className, y, x - 1));
            thisCell.setX(x - 1);
            board[y][x].unlock();
        }
        return result;
    }

    private boolean wayRight(int x, int y) {
        boolean result = false;
        System.out.println(String.format("%s Ходит Вправо", this.className));
        if (x < board.length - 1) {
            System.out.println(String.format("%s пытается захватить %s : %s", this.className, y, x + 1));
            try {
                result = board[y][x + 1].tryLock(500, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("В эту сторону ходить нелязя.");
        }
        if (result) {
            System.out.println(String.format("%s захватил %s : %s", this.className, y, x + 1));
            thisCell.setX(x + 1);
            board[y][x].unlock();
        }
        return result;
    }

    private boolean wayUp(int x, int y) {
        boolean result = false;
        System.out.println(String.format("%s Ходит ВВерх", this.className));
        if (y > 0) {
            System.out.println(String.format("%s пытается захватить %s : %s", this.className, y - 1, x));
            try {
                result = board[y - 1][x].tryLock(500, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("В эту сторону ходить нелязя.");
        }
        if (result) {
            System.out.println(String.format("%s захватил %s : %s", this.className, y - 1, x));
            thisCell.setY(y - 1);
            board[y][x].unlock();
        }
        return result;
    }

    private boolean wayDown(int x, int y) {
        boolean result = false;
        System.out.println(String.format("%s Ходит Вниз", this.className));
        if (y < board.length - 1) {
            System.out.println(String.format("%s пытается захватить %s : %s", this.className, y + 1, x));
            try {
                result = board[y + 1][x].tryLock(500, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("В эту сторону ходить нелязя.");
        }
        if (result) {
            System.out.println(String.format("%s захватил %s : %s", this.className, y + 1, x));
            board[y][x].unlock();
            thisCell.setY(y + 1);
        }
        return result;
    }

    private Way getRundomWay() {
        return Way.values()[new Random().nextInt(4)];
    }

}

