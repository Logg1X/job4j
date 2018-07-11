package ru.job4j.chess.exeption;

public class CellNotFoundExeption extends RuntimeException {
    public CellNotFoundExeption(String message) {
        super(message);
    }
}
