package ru.job4j.threads.nonblockingcashe;

public class OptimisticException extends RuntimeException {
    public OptimisticException(String message) {
        super(message);
    }
}
