package ood.gamexo;

public class Player {
    private final char[][] board;
    private final char figure;

    public Player(char[][] board, char figure) {
        this.board = board;
        this.figure = figure;
    }

    public char getFigure() {
        return figure;
    }


    public char[][] move(int x, int y) {
        this.board[y][x] = (this.figure);
        return this.board;
    }
}
