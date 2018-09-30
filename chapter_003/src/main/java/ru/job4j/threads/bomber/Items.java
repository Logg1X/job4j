package ru.job4j.threads.bomber;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Items implements Runnable {
    private final Names name;
    private ReentrantLock lock;
    private Bomber bomber;
    private Cell thisCell;

    public Items(Bomber bomber, Names name) {
        this.bomber = bomber;
        this.name = name;
    }

    @Override
    public void run() {
        boolean getLock;
        do {
            this.thisCell = bomber.randomCell();
            this.lock = bomber.getBoard()[thisCell.getY()][thisCell.getX()];
            getLock = lock.tryLock();
        } while (!getLock);
        System.out.println(
                String.format("%s появился в координатах %s:%s", this.name, this.thisCell.getY(), this.thisCell.getX())
        );
        while (!Thread.currentThread().isInterrupted()) {
            if (this.name.equals(Names.HERO)) {
                boolean kill = bomber.getBoard()[thisCell.getY()][thisCell.getX()].hasQueuedThreads();
                if (kill) {
                    System.out.println("Hero погиб.");
                    break;
                }
            }
            move(thisCell.getX(), thisCell.getY());
        }
    }

    public final boolean move(int x, int y) {
        boolean result = false;
        while (!result) {
            Way way = getRundomWay();
            while (!result) {
                switch (way) {
                    case LEFT:
                        result = wayLeft(x, y);
                        if (!result) {
                            break;
                        }
                        break;
                    case RIGHT:
                        result = wayRight(x, y);
                        if (!result) {
                            break;
                        }
                        break;
                    case UP:
                        result = wayUp(x, y);
                        if (!result) {
                            break;
                        }
                        break;
                    case DOWN:
                        result = wayDown(x, y);
                        if (!result) {
                            break;
                        }
                        break;
                    default:
                        break;
                }
                break;
            }
        }
        return result;
    }

    private boolean wayLeft(int x, int y) {
        boolean result = false;
        System.out.println(String.format("%s Ходит ВЛЕВО", this.name));
        if (x > 0) {
            System.out.println(String.format("%s пытается захватить %s : %s", this.name, y, x - 1));
            try {
                result = bomber.getBoard()[y][x - 1].tryLock(getTimeLock(), TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("В эту сторону ходить нелязя.");
        }
        if (result) {
            System.out.println(String.format("%s захватил %s : %s", this.name, y, x - 1));
            thisCell.setX(x - 1);
            bomber.getBoard()[y][x].unlock();
        }
        return result;
    }

    private boolean wayRight(int x, int y) {
        boolean result = false;
        System.out.println(String.format("%s Ходит Вправо", this.name));
        if (x < bomber.getBoard().length - 1) {
            System.out.println(String.format("%s пытается захватить %s : %s", this.name, y, x + 1));
            try {
                result = bomber.getBoard()[y][x + 1].tryLock(getTimeLock(), TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("В эту сторону ходить нелязя.");
        }
        if (result) {
            System.out.println(String.format("%s захватил %s : %s", this.name, y, x + 1));
            thisCell.setX(x + 1);
            bomber.getBoard()[y][x].unlock();
        }
        return result;
    }

    private boolean wayUp(int x, int y) {
        boolean result = false;
        System.out.println(String.format("%s Ходит ВВерх", this.name));
        if (y > 0) {
            System.out.println(String.format("%s пытается захватить %s : %s", this.name, y - 1, x));
            try {
                result = bomber.getBoard()[y - 1][x].tryLock(getTimeLock(), TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("В эту сторону ходить нелязя.");
        }
        if (result) {
            System.out.println(String.format("%s захватил %s : %s", this.name, y - 1, x));
            thisCell.setY(y - 1);
            bomber.getBoard()[y][x].unlock();
        }
        return result;
    }

    private boolean wayDown(int x, int y) {
        boolean result = false;
        System.out.println(String.format("%s Ходит Вниз", this.name));
        if (y < bomber.getBoard().length - 1) {
            System.out.println(String.format("%s пытается захватить %s : %s", this.name, y + 1, x));
            try {
                result = bomber.getBoard()[y + 1][x].tryLock(getTimeLock(), TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("В эту сторону ходить нелязя.");
        }
        if (result) {
            System.out.println(String.format("%s захватил %s : %s", this.name, y + 1, x));
            bomber.getBoard()[y][x].unlock();
            thisCell.setY(y + 1);
        }
        return result;
    }

    private Way getRundomWay() {
        return Way.values()[new Random().nextInt(4)];
    }

    private long getTimeLock() {
        long timeLock;
        if (!this.name.equals(Names.HERO)) {
            timeLock = 5000;
        } else {
            timeLock = 500;
        }
        return timeLock;
    }
}
