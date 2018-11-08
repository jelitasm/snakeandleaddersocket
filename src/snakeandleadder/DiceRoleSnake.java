/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeandleadder;

import java.awt.Label;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


/**
 *
 * @author jelit
 */
public class DiceRoleSnake extends Application {
    public int rand;
    public Label randResult;
    
    public int cirPos[][] = new int[10][10];
    public int leadderPosition[][] = new int[6][3];
            
    public static final int Tile_Size = 80;
    public static final int width = 10;
    public static final int height = 10;
    
    public Circle player1;
    public Circle player2;
    
    public int playerPosition1 = 1;
    public int playerPosition2 = 1;
    
    public boolean player1Turn = true;
    public boolean player2Turn = true;
    
    public static int player1XPos = 40;
    public static int player1YPos = 760;
    
    public static int player2XPos = 40;
    public static int player2YPos = 760;
    
    public boolean gameStart = false;
    public Button gameButton;
    
    private Group tileGroup = new Group();
    
    private Parent createConetent()
    {
        Pane root = new Pane();
        root.setPrefSize(width*Tile_Size, (height * Tile_Size)+80);
        root.getChildren().addAll(tileGroup);
        
        for(int i = 0; i < height; i++)
        {
            for(int j = 0; j < width; j++)
            {
                Tile tile = new Tile(Tile_Size, Tile_Size);
                tile.setTranslateX(j * Tile_Size);
                tile.setTranslateY(i * Tile_Size);
                tileGroup.getChildren().add(tile);
            }
        }
        
        player1 = new Circle(40);
        player1.setId("player1");
        player1.getStyleClass().add("style.class");
        player1.setTranslateX(player1XPos);
        player1.setTranslateY(player1YPos);
        
        player2 = new Circle(40);
        player2.setId("player2");
        player2.getStyleClass().add("style.css");
        player2.setTranslateX(player2XPos);
        player2.setTranslateY(player2YPos);
        
        Button button = new Button("Player2");
        button.setTranslateX(700);
        button.setTranslateY(820);
        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if(gameStart){
                    if(player1Turn){
                        
                    }
                }
            }
        });
        
        Button button2 = new Button("Player1");
        button2.setTranslateX(80);
        button2.setTranslateY(820);
        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if(gameStart){
                    if(player2Turn){
                    
                    }
                }
            }
        });
        
        gameButton = new Button("Start Game");
        gameButton.setTranslateX(80);
        gameButton.setTranslateY(820);
       gameButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                gameButton.setText("Game Stated");
                player1XPos = 40;
                player1YPos = 760;
                
                player2XPos = 40;
                player2YPos = 760;
                
                player1.setTranslateX(player1XPos);
                player1.setTranslateY(player1YPos);
                player2.setTranslateX(player2XPos);
                player2.setTranslateY(player2YPos);
                
                }
        });
        
        randResult = new Label("0");
        randResult.setLocation(300,820);
        //randResult.setTranslateX(300);
        //randResult.setTranslateY(820);
        
        //Image img = new Image()
       
        tileGroup.getChildren().addAll(player1, player2, button, button2, gameButton);
        return root;    
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createConetent());
        primaryStage.setTitle("Snake and Leadder");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private static class label {

        public label() {
        }
    }
    
}
