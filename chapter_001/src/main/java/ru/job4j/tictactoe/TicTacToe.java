package ru.job4j.tictactoe;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * @author Toporov Pavel (mailto:per4mancerror@gmail.com).
 * @version $Id$
 * @since 1.0
 */
public class TicTacToe extends Application {
    /**
     * Заголовок окна.
     */
    private static final String JOB4J = "Крестики-нолики www.job4j.ru";
    /**
     * Количество ячеек.
     */
    private final int size = 3;
    /**
     * Поле размером size.
     */
    private final Figure3T[][] cells = new Figure3T[size][size];
    /**
     * Класс логики.
     */
    private final Logic3T logic = new Logic3T(cells);

    /**
     * Формирует ячейку.
     *
     * @param x    x
     * @param y    y
     * @param size size
     * @return Приямоугольник
     */
    private Figure3T buildRectangle(final int x, final int y, final int size) {
        Figure3T rect = new Figure3T();
        rect.setX(x * size);
        rect.setY(y * size);
        rect.setHeight(size);
        rect.setWidth(size);
        rect.setFill(Color.WHITE);
        rect.setStroke(Color.BLACK);
        return rect;
    }

    /**
     * Отрисовывает О.
     *
     * @param x    х
     * @param y    у
     * @param size size
     * @return O-Group
     */
    private Group buildMarkO(final double x, final double y, final int size) {
        Group group = new Group();
        int radius = size / 2;
        Circle circle = new Circle(x + radius, y + radius, radius - 10);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.WHITE);
        group.getChildren().add(circle);
        return group;
    }

    /**
     * Отрисовывает всплывающее окно.
     *
     * @param message message
     */
    private void showAlert(final String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(JOB4J);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Проверяет доступные ходы.
     *
     * @return true/false если ходов больше не осталось.
     */
    private boolean checkState() {
        boolean gap = this.logic.hasGap();
        if (!gap) {
            checkWinner();
            this.showAlert("Все поля запонены! Начните новую Игру!");
        }
        return gap;
    }

    /**
     * Проверяет побидетяля.
     */
    private void checkWinner() {
        if (this.logic.isWinnerX()) {
            this.showAlert("Победили Крестики! Начните новую Игру!");
        } else if (this.logic.isWinnerO()) {
            this.showAlert("Победили Нолики! Начните новую Игру!");
        }
    }

    /**
     * Отрисовывает Х.
     *
     * @param x    x
     * @param y    y
     * @param size size
     * @return X-Group
     */
    private Group buildMarkX(final double x, final double y, final int size) {
        Group group = new Group();
        group.getChildren().addAll(
                new Line(
                        x + 10, y + 10,
                        x + size - 10, y + size - 10
                ),
                new Line(
                        x + size - 10, y + 10,
                        x + 10, y + size - 10
                )
        );
        return group;
    }

    /**
     * Обработчик событий.
     *
     * @param panel panel
     * @return checkState
     */
    private EventHandler<MouseEvent> buildMouseEvent(final Group panel) {
        return event -> {
            Figure3T rect = (Figure3T) event.getTarget();
            if (this.checkState()) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    rect.take(true);
                    panel.getChildren().add(
                            this.buildMarkX(rect.getX(), rect.getY(), 50)
                    );
                } else {
                    rect.take(false);
                    panel.getChildren().add(
                            this.buildMarkO(rect.getX(), rect.getY(), 50)
                    );
                }
                this.checkState();
            }
        };
    }

    /**
     * Отрисовывает игровое окно.
     *
     * @return panel
     */
    private Group buildGrid() {
        Group panel = new Group();
        for (int y = 0; y != this.size; y++) {
            for (int x = 0; x != this.size; x++) {
                Figure3T rect = this.buildRectangle(x, y, 50);
                this.cells[y][x] = rect;
                panel.getChildren().add(rect);
                rect.setOnMouseClicked(this.buildMouseEvent(panel));
            }
        }
        return panel;
    }

    /**
     * Точка входа в программу.
     *
     * @param stage stage
     */
    @Override
    public final void start(final Stage stage) {
        BorderPane border = new BorderPane();
        HBox control = new HBox();
        control.setPrefHeight(40);
        control.setSpacing(10.0);
        control.setAlignment(Pos.BASELINE_CENTER);
        Button start = new Button("Начать");
        start.setOnMouseClicked(
                event -> border.setCenter(this.buildGrid())
        );
        control.getChildren().addAll(start);
        border.setBottom(control);
        border.setCenter(this.buildGrid());
        stage.setScene(new Scene(border, 300, 300));
        stage.setTitle(JOB4J);
        stage.setResizable(false);
        stage.show();
    }
}
