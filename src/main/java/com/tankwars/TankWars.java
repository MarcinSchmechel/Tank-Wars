package com.tankwars;

import com.tankwars.gfx.BoardCreator;
import com.tankwars.logic.Game;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
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
        int STAGE_SIZE_HEIGHT = 616+99;
        int STAGE_SIZE_WIDTH = 586;

        BoardCreator board = new BoardCreator();
        GridPane gridPane = board.BoardCreate(29, 20, 20);
        GridPane statusBar = new GridPane();
        BorderPane root= new BorderPane();
        statusBar.setMaxHeight(100);
        statusBar.setMinHeight(100);
        statusBar.setStyle("-fx-background-color: #63c263");


        statusBar.setPadding(new Insets(10, 10, 10, 10));

        Button startButton = new Button("START");
        startButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        startButton.setStyle("-fx-font-size: 3em; ");

        Label label = new Label("Boot [ 0 : 0 ] User");
        label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        label.setStyle("-fx-font-size: 3em; ");

        statusBar.setHgap(50);
        statusBar.add(startButton,0,0);
        statusBar.add(label,1,0);
        statusBar.setAlignment(Pos.CENTER);

        root.setCenter(gridPane);
        root.setTop(statusBar);
        Scene scene = new Scene(root, Color.LIGHTGRAY);

        Game game = new Game(gridPane, label);
        game.displayGame();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1500),k -> game.doFireBoot()));
        timeline.setCycleCount(Animation.INDEFINITE);

        Timeline timeline2 = new Timeline(new KeyFrame(Duration.millis(200),k -> game.doMove()));
        timeline2.setCycleCount(Animation.INDEFINITE);

        root.addEventFilter(KeyEvent.KEY_PRESSED, k -> {
            if ( k.getCode() == KeyCode.SPACE){
                game.fireBullet();
            }
        });

        startButton.setOnAction(actionEvent ->  {
            timeline.play();
            timeline2.play();
        });

        scene.setOnKeyPressed(event -> {
            game.handleClick(event.getCode());
        });

        primaryStage.setTitle("TankWars");
        primaryStage.setScene(scene);

        primaryStage.setMinHeight(STAGE_SIZE_HEIGHT);
        primaryStage.setMinWidth(STAGE_SIZE_WIDTH);
        primaryStage.setMaxHeight(STAGE_SIZE_HEIGHT);
        primaryStage.setMaxWidth(STAGE_SIZE_WIDTH);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
