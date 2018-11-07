/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeandleadder;

import java.awt.Label;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    public static int player1YPos = 740;
    
    public static int player2XPos = 40;
    public static int player2YPos = 740;
    
    public boolean gameStart = false;
    
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
