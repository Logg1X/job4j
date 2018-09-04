package ru.job4j.threads.pingpong;


import javafx.scene.shape.Rectangle;

public class RectangleMove implements Runnable {
    private final Rectangle rect;
    private int drive;
    private int speed;

    public RectangleMove(Rectangle rect) {
        this.rect = rect;
        this.drive = 0;
        this.speed = 10;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            if (this.rect.getX() < 300 && drive != -1) {
                this.rect.setX(this.rect.getX() + speed);
                if (this.rect.getX() >= 300) {
                    drive = -1;
                }
            } else if (drive == -1) {
                this.rect.setX(this.rect.getX() - speed);
                if (this.rect.getX() <= 0) {
                    drive = 1;
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
