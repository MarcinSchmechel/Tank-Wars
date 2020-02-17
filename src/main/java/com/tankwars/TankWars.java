package com.tankwars;

import com.tankwars.gfx.BoardCreator;
import com.tankwars.integration.Integration;
import com.tankwars.logic.Game;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TankWars extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BoardCreator board = new BoardCreator();
        GridPane gridPane = board.BoardCreate(50, 20, 20);
        Scene scene = new Scene(gridPane,Color.LIGHTGRAY);
        Game game = new Game();
        Integration integration = new Integration(game, gridPane);
        integration.displayGame();
        
        primaryStage.setTitle("TankWars");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
