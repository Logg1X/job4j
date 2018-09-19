package ru.job4j.threads.bomber;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

public class Bomber {
    private final int size;
    private final Set<Cell> cells;
    private final ArrayList<Monster> monsters;
    private final int monstAmount;
    private final ReentrantLock[][] board;


    public Bomber(int size, int monstAmount) {
        this.board = new ReentrantLock[size][size];
        this.monstAmount = monstAmount;
        this.cells = new HashSet<>();
        this.size = size;
        this.monsters = new ArrayList<>(monstAmount);
        this.init();
        this.monstersInit();
//        this.heroInit();
        System.out.println(String.format("Количество монстров на поле %s: ", this.monsters.size()));
//        this.monsters.forEach(monster -> {
//            new Thread(monster).start();
//            System.out.println(String.format("Монстер %s начал движение", monsters.indexOf(monster)));
//        });
        for (Monster monster : monsters) {
            new Thread(monster).start();
            System.out.println(String.format("Монстер %s начал движение", monsters.indexOf(monster)));
        }
    }

    public ReentrantLock[][] getBoard() {
        return board;
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
        Cell cell = this.getNonLockedCell();
        Thread thread = new Thread(new Hero(this.board, cell));
        System.out.println(
                String.format("hero появился в координатах %s:%s", cell.getY(), cell.getX())
        );
        thread.start();
    }

    private void monstersInit() {
        for (int i = 0; i < monstAmount; i++) {
            Cell monsterCell = this.getNonLockedCell();
            monsters.add(new Monster(this.board, monsterCell));
            System.out.println(
                    String.format("monster появился в %s:%s", monsterCell.getY(), monsterCell.getX())
            );
        }
    }

    private Cell getNonLockedCell() {
        Cell cell = this.randomCell();
        while (!cells.contains(cell)
                && this.board[cell.getY()][cell.getX()].isLocked()) {
            cell = this.randomCell();
        }
        return cell;
    }
    private Cell randomCell() {
        return new Cell(
                new Random().nextInt(this.size - 1),
                new Random().nextInt(this.size - 1)
        );
    }


    public static void main(String[] args) {
        Bomber bomber = new Bomber(10,1);
    }
}
