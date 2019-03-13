package ood.gamexo;

import java.util.Random;
import java.util.Scanner;

public class Game {
    private final Board board;
    private Player hero;
    private Player comp;
    private boolean isGame;

    public Game(Scanner scanner) {
        this.board = new Board(scanner);
        this.createPlayers(scanner);
        this.isGame = false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = new Game(scanner);
        while (!game.isGame) {
            game.playOn(scanner);
        }
    }

    public void playOn(Scanner scanner) {
        isGame = true;
        boolean flag = firstStep();
        while (isGame) {
            flag = heroStep(scanner, flag);
            flag = compStep(flag);
        }
        isGame = willGame(scanner);
    }

    private boolean heroStep(Scanner scanner, boolean flag) {
        while (!flag) {
            flag = this.move(scanner, hero);
            if (!flag) {
                System.out.println("Ячейка занята!");
            } else {
                System.out.println(String.format("'%s' сделал свой ход", hero.getFigure()));
            }
            if (this.checkWinner(hero)) {
                flag = false;
                this.isGame = false;
                break;
            }
        }
        return flag;
    }

    private boolean compStep(boolean flag) {
        while (flag) {
            if (this.autoMove(comp)) {
                System.out.println(String.format("'%s' сделал свой ход", comp.getFigure()));
                flag = false;
            }
            if (this.checkWinner(comp)) {
                this.isGame = false;
                break;
            } else if (!isWinner() && !this.makeMove()) {
                System.out.println("НИЧЬЯ!");
                this.isGame = false;
                break;
            }
        }
        return flag;
    }

    private boolean willGame(Scanner scanner) {
        boolean result = false;
        System.out.println("Начать новую игру? Y/N");
        String newGame = scanner.nextLine();
        if ("Y".equalsIgnoreCase(newGame)) {
            board.createBoard();
        } else {
            System.out.println("Выход из игры!");
            result = true;
        }
        return result;
    }

    private boolean checkWinner(Player player) {
        boolean result = this.isWinner();
        if (result) {
            System.out.println(String.format("'%s' ПОБЕДИЛ!", player.getFigure()));
            this.board.showBoard();
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
        this.hero = new Player(this.board.getBoard(), figure.charAt(0));
        String figure2 = figure.equals("X") ? "O" : "X";
        this.comp = new Player(this.board.getBoard(), figure2.charAt(0));
    }

    private boolean move(Scanner scanner, Player player) {
        boolean result = false;
        try {
            if (makeMove()) {
                this.board.showBoard(hero, comp, "Выбирите номер ячейки для хода: ");
                String cell = scanner.nextLine();
                int y = Integer.parseInt(String.valueOf(cell.charAt(0)));
                int x = Integer.parseInt(String.valueOf(cell.charAt(1)));
                if (this.makeMove(player, x, y)) {
                    this.board.setBoardCell(player.move(x, y));
                    result = true;
                }
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Не корректный ввод.");
            result = this.move(scanner, player);
        }

        return result;
    }

    private boolean autoMove(Player player) {
        Random random = new Random();
        boolean flag = false;
        if (makeMove()) {
            while (!flag) {
                int x = random.nextInt(this.board.getSize());
                int y = random.nextInt(this.board.getSize());
                if (makeMove(player, x, y)) {
                    this.board.setBoardCell(player.move(x, y));
                    flag = true;
                }
            }
        }
        return flag;
    }

    private boolean firstStep() {
        Random random = new Random();
        boolean result = random.nextBoolean();
        if (!result) {
            System.out.println(String.format("Первым ходит: '%s'", hero.getFigure()));
        } else {
            System.out.println(String.format("Первым ходит: '%s'", comp.getFigure()));
        }
        return result;
    }

    private boolean isWinner() {
        boolean result = false;
        for (int i = 0; i < this.board.getSize(); i++) {
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
            result = this.checker(this.board.getSize() - 1, 0, 0, -1, 1);
        }
        return result;
    }

    private boolean checker(int x, int y, int countCombo, int shiftX, int shiftY) {
        boolean result = false;
        if (this.board.getBoard()[y][x] != Board.EMPTY) {
            if (countCombo == this.board.getSize() - 1) {
                result = true;
            } else {
                if (this.board.getBoard()[y][x] == this.board.getBoard()[y + shiftY][x + shiftX]) {
                    result = checker(x + shiftX, y + shiftY, countCombo + 1, shiftX, shiftY);
                }
            }
        }
        return result;
    }

    private boolean makeMove(Player player, int x, int y) {
        boolean result = false;
        if (this.board.getBoard()[y][x] == Board.EMPTY) {
            result = true;
        }
        if (this.board.getBoard()[y][x] == player.getFigure() && this.board.getBoard()[y][x] != Board.EMPTY) {
            result = false;
        }
        if (this.board.getBoard()[y][x] != player.getFigure() && this.board.getBoard()[y][x] != Board.EMPTY) {
            result = false;
        }
        return result;
    }

    private boolean makeMove() {
        boolean result = false;
        int counter = 0;
        for (int i = 0; i < this.board.getSize(); i++) {
            for (int j = 0; j < this.board.getSize(); j++) {
                if (this.board.getBoard()[i][j] == '-') {
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
}
