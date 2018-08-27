package ru.job4j.threads.pingpong;


import javafx.scene.shape.Rectangle;

public class RectangleMove implements Runnable {
    private final Rectangle rect;

    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void run() {
        int drive = 0;
        while (true) {
            if (this.rect.getX() < 300 && drive != -1) {
                this.rect.setX(this.rect.getX() + 25);
                if (this.rect.getX() == 300) {
                    drive = -1;
                }
            } else if (drive == -1) {
                this.rect.setX(this.rect.getX() - 25);
                if (this.rect.getX() == 0) {
                    drive = 1;
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}