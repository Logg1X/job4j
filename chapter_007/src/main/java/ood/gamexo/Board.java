package ood.gamexo;

import java.util.Random;
import java.util.Scanner;

public class Board {
    private final char empty = '-';
    private final int size;
    private Player player;
    private Player comp;
    private char[][] board;

    public Board(int size) {
        this.size = size;
        this.board = new char[size][size];
        this.createBoard();
    }

    public Board() {
        this(3);
    }

    public static Board createBoard(Scanner scanner) {
        int size = 0;
        Board result = null;
        try {
            System.out.println("Введите размер доски.");
            while ((size = Integer.parseInt(scanner.nextLine())) < 3) {
                System.out.println("Минимальный размер доски 3, Введите 3 или больше.");
            }
            result = new Board(size);
        } catch (NumberFormatException e) {
            System.out.println("Необходимо вводить цифру, а не символ!");
            result = new Board();
            System.out.println("Создана стандартная доска для игры размером '3x3' ");
        }
        result.createBoard();
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = Board.createBoard(scanner);
        board.createPlayers(scanner);
        Player player = board.getPlayer();
        Player comp = board.getComp();
        boolean game = false;
        while (!game) {
            game = true;
            boolean flag = board.firstStep();
            while (game) {
                while (!flag) {
                    flag = board.move(scanner, player);
                    if (!flag) {
                        System.out.println("Ячейка занята!");
                    } else {
                        System.out.println(String.format("'%s' сделал свой ход", player.getFigure()));
                    }
                    if (board.checkWinner(player)) {
                        flag = false;
                        game = false;
                        break;
                    }
                }
                while (flag) {
                    if (board.move(comp)) {
                        System.out.println(String.format("'%s' сделал свой ход", comp.getFigure()));
                        flag = false;
                    }
                    if (board.checkWinner(comp)) {
                        game = false;
                        break;
                    } else if (!board.isWinner() && !board.makeMove()) {
                        System.out.println("НИЧЬЯ!");
                        game = false;
                        break;
                    }
                }
            }
            System.out.println("Начать новую игру? Y/N");
            String newGame = scanner.nextLine();
            if ("Y".equalsIgnoreCase(newGame)) {
                game = false;
                board.createBoard();
            } else {
                System.out.println("Выход из игры!");
                game = true;
            }
        }
    }


    public Player getPlayer() {
        return player;
    }

    public Player getComp() {
        return comp;
    }

    private char[][] createBoard() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.board[i][j] = this.empty;
            }
        }
        return this.board;
    }

    private void showBoard() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                System.out.print(" " + this.board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private boolean checkWinner(Player player) {
        boolean result = this.isWinner();
        if (result) {
            System.out.println(String.format("'%s' ПОБЕДИЛ!", player.getFigure()));
            this.showBoard();
        }
        return result;
    }

    private void createPlayers(Scanner scanner) {
        System.out.println("Введите фигуру, которой планируете играть 'Х/О'");
        String figure = scanner.nextLine().toUpperCase();
        while (!"X".equals(figure) & !"O".equals(figure)) {
            System.out.println("Необходимо выбрать 'X' или 'O'");
            figure = scanner.nextLine();
        }
        this.player = new Player(this.board, figure.charAt(0));
        String figure2 = figure.equals("X") ? "O" : "X";
        this.comp = new Player(this.board, figure2.charAt(0));
    }

    private boolean makeMove(Player player, int x, int y) {
        boolean result = false;
        if (board[y][x] == this.empty) {
            result = true;
        }
        if (board[y][x] == player.getFigure() && board[y][x] != this.empty) {
            result = false;
        }
        if (board[y][x] != player.getFigure() && board[y][x] != this.empty) {
            result = false;
        }
        return result;
    }

    private boolean makeMove() {
        boolean result = false;
        int counter = 0;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.board[i][j] == '-') {
                    counter++;
                }
            }
            if (counter > 0) {
                result = true;
                break;
            }
        }
        return result;
    }

    private boolean move(Scanner scanner, Player player) {
        boolean result = false;
        try {

            if (makeMove()) {
                this.showBoard(player, "Выбирите номер ячейки для хода: ");
                String cell = scanner.nextLine();
                int y = Integer.parseInt(String.valueOf(cell.charAt(0)));
                int x = Integer.parseInt(String.valueOf(cell.charAt(1)));
                if (this.makeMove(player, x, y)) {
                    this.board = player.move(x, y);
                    result = true;
                }
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Не корректный ввод.");
            result = this.move(scanner, player);
        }

        return result;
    }

    private boolean move(Player player) {
        Random random = new Random();
        boolean flag = false;
        if (makeMove()) {
            while (!flag) {
                int x = random.nextInt(this.size);
                int y = random.nextInt(this.size);
                if (makeMove(player, x, y)) {
                    this.board = player.move(x, y);
                    flag = true;
                }
            }
        }
        return flag;
    }

    private boolean checker(int x, int y, int countCombo, int shiftX, int shiftY) {
        boolean result = false;
        if (this.board[y][x] != this.empty) {
            if (countCombo == this.size - 1) {
                result = true;
            } else {
                if (this.board[y][x] == this.board[y + shiftY][x + shiftX]) {
                    result = checker(x + shiftX, y + shiftY, countCombo + 1, shiftX, shiftY);
                }
            }
        }
        return result;
    }

    public boolean isWinner() {
        boolean result = false;
        for (int i = 0; i < this.size; i++) {
            if (this.checker(i, 0, 0, 0, 1)
                    || this.checker(0, i, 0, 1, 0)) {
                result = true;
                break;
            }
        }
        if (!result) {
            result = this.checker(0, 0, 0, 1, 1);
        }
        if (!result) {
            result = this.checker(this.size - 1, 0, 0, -1, 1);
        }
        return result;
    }

    public void showBoard(Player player, String line) {
        System.out.println(line);
        char figure = player.getFigure();
        char figure2 = comp.getFigure();
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.board[i][j] != figure + ' ' && this.board[i][j] != figure2 + ' ') {
                    System.out.print(" " + i + j + " ");
                } else {
                    System.out.print(" " + this.board[i][j] + "  ");
                }
            }
            System.out.println();
        }
    }

    private boolean firstStep() {
        Random random = new Random();
        boolean result = random.nextBoolean();
        if (!result) {
            System.out.println(String.format("Первым ходит: '%s'", player.getFigure()));
        } else {
            System.out.println(String.format("Первым ходит: '%s'", comp.getFigure()));
        }
        return result;
    }
}
