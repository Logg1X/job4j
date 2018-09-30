package ru.job4j.threads.bomber;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

public class Bomber {
    private final int size;
    private final Set<Cell> cells;
    private final Set<Cell> itemCells = new HashSet<>();
    private final ArrayList<Thread> monsters;
    private final int monstAmount;
    private final int blockAmount;
    private final ReentrantLock[][] board;


    public Bomber(int size, int monstAmount, int blockAmount) {
        this.board = new ReentrantLock[size][size];
        this.blockAmount = blockAmount;
        System.out.println(String.format("Количество монстров на поле %s: ", monstAmount));
        System.out.println(String.format("Количество блоков на поле %s: ", blockAmount));
        this.monstAmount = monstAmount;
        this.cells = new HashSet<>();
        this.size = size;
        this.monsters = new ArrayList<>(monstAmount);
        this.init();
        this.initBlocks();
        this.monstersInit();
        this.heroInit();
    }

    public static void main(String[] args) {
        Bomber bomber = new Bomber(10, 10, 33);
    }

    public ReentrantLock[][] getBoard() {
        return board;
    }

    public Cell randomCell() {
        Cell cell;
        while (true) {
            cell = new Cell(
                    new Random().nextInt(this.size),
                    new Random().nextInt(this.size)
            );
            if (!itemCells.contains(cell)) {
                itemCells.add(cell);
                break;
            }
        }
        return cell;
    }

    private void init() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new ReentrantLock();
                cells.add(new Cell(i, j));
            }
        }
    }

    private void heroInit() {
        Thread thread = new Thread(new Items(this, Names.HERO));
        thread.start();
    }

    private void monstersInit() {
        if (monstAmount > board.length) {
            System.out.println("Слишком много монстров для такого маленького поля!");
            System.exit(1);
            return;
        }
        for (int i = 0; i < monstAmount; i++) {
            monsters.add(new Thread(new Items(this, Names.MONSTER)));
        }
        for (Thread monster : monsters) {
            monster.setDaemon(true);
            monster.start();
        }
    }

    private void initBlocks() {
        if (blockAmount > (board.length * board.length) / 3) {
            System.out.println("Слишком много блоков для такого маленького поля!");
            System.exit(1);
            return;
        }
        Cell cell = this.randomCell();
        for (int i = 0; i < blockAmount;) {
            boolean isLocked = board[cell.getY()][cell.getX()].tryLock();
            if (isLocked) {
                i++;
            }
        }
    }
}
