package com.tankwars;

import com.tankwars.gfx.BoardCreator;
import com.tankwars.integration.Integration;
import com.tankwars.logic.Game;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TankWars extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BoardCreator board = new BoardCreator();
        GridPane gridPane = board.BoardCreate(29, 20, 20);
        Scene scene = new Scene(gridPane, Color.LIGHTGRAY);
        Game game = new Game();
        Integration integration = new Integration(game, gridPane);
        integration.displayGame();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(250),k ->integration.doMove()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        scene.setOnKeyPressed(event -> {
            integration.handleClick(event.getCode());
        });

        primaryStage.setTitle("TankWars");
        primaryStage.setScene(scene);
        int STAGE_SIZE_HEIGHT = 616;
        int STAGE_SIZE_WIDTH = 586;
        primaryStage.setMinHeight(STAGE_SIZE_HEIGHT);
        primaryStage.setMinWidth(STAGE_SIZE_WIDTH);
        primaryStage.setMaxHeight(STAGE_SIZE_HEIGHT);
        primaryStage.setMaxWidth(STAGE_SIZE_WIDTH);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
