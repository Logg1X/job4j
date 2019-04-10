package ru.toppavel.exception;

public class BrandNotFoundException extends RuntimeException {
    private int id;
    private String message;

    public BrandNotFoundException(int id) {
        this.id = id;
    }

    public BrandNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return String.format("Brand not found with id or name: %s", message == null ? id : message);
    }
}
