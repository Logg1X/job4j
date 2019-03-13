package ood.gamexo;

import java.util.Scanner;

public class Board {
    public final static char EMPTY = '-';
    private final int defaultSize;
    private final int size;
    private char[][] board;

    public Board(Scanner scanner) {
        defaultSize = 3;
        this.size = setBoardSize(scanner);
        this.createBoard();
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoardCell(char[][] board) {
        this.board = board;
    }

    public int getSize() {
        return size;
    }

    private int setBoardSize(Scanner scanner) {
        int size = defaultSize;
        try {
            System.out.println("Введите размер доски.");
            while ((size = Integer.parseInt(scanner.nextLine())) < defaultSize) {
                System.out.println("Минимальный размер доски 3, Введите 3 или больше.");
            }
            this.board = new char[size][size];
        } catch (NumberFormatException e) {
            System.out.println("Необходимо вводить цифру, а не символ!");
            this.board = new char[defaultSize][defaultSize];
            System.out.println("Создана стандартная доска для игры размером '3x3' ");
        }
        return size;
    }

    public void createBoard() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.board[i][j] = EMPTY;
            }
        }
    }

    public void showBoard() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                System.out.print(String.format(" %s ", this.board[i][j]));
            }
            System.out.println();
        }
    }

    public void showBoard(Player hero, Player comp, String line) {
        System.out.println(line);
        char figure = hero.getFigure();
        char figure2 = comp.getFigure();
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.board[i][j] != figure && this.board[i][j] != figure2) {
                    System.out.print(String.format(" %s%s ", i, j));
                } else {
                    System.out.print(String.format(" %s  ", this.board[i][j]));
                }
            }
            System.out.println();
        }
    }
}
