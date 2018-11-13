/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeandleadder;

import javafx.scene.control.Label;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.shape.Circle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;


/**
 *
 * @author jelit
 */
public class DiceRoleSnake extends Application {
    public int rand;
    public Label randResult;
    
    public int cirPos[][] = new int[10][10];
    public int leadderPosition[][] = new int[6][3];
            
    public static final int Tile_Size = 60;
    public static final int width = 10;
    public static final int height = 10;
    
    public Circle player1;
    public Circle player2;
    
    public int playerPosition1 = 1;
    public int playerPosition2 = 1;
    
    public boolean player1Turn = false;
    public boolean player2Turn = false;
    
    public static int player1XPos = 30;
    public static int player1YPos = 590;
    
    public static int player2XPos = 30;
    public static int player2YPos = 590;
    
    public boolean gameStart = false;
    public Button gameButton;
    
    public int posCir1 =1 ;
    public int posCir2 =1;
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
                cirPos[i][j] = j * Tile_Size - 40;
            }
        }
        
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(cirPos[i][j] + " ");
                System.out.println();
            }
        }
        
        player1 = new Circle(20);
        player1.setId("player1");
        player1.setFill(Color.AQUA);
        player1.getStyleClass().add("snakeandleader/style.class");
        player1.setTranslateX(player1XPos);
        player1.setTranslateY(player1YPos);
        
        player2 = new Circle(20);
        player2.setId("player2");
        player2.setFill(Color.RED);
        player2.getStyleClass().add("snakeandleader/style.css");
        player2.setTranslateX(player2XPos);
        player2.setTranslateY(player2YPos);
        
        Button button1 = new Button("Player1");
        button1.setTranslateX(450);
        button1.setTranslateY(650);
        button1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if(gameStart){
                    if(player1Turn){
                        randResult.setText(String.valueOf(getDiceValue()));
                        move1Player();
                        translatePlayer(player1XPos, player1YPos, player1);
                        player1Turn = false;
                        player2Turn = true;
                        
                        if (player1XPos == 200 && player1YPos ==760){
                            translatePlayer(player1XPos = 80, player1YPos = 520, player1);
                        }
                    }
                }
            }
        });
        
        Button button2 = new Button("Player2");
        button2.setTranslateX(80);
        button2.setTranslateY(650);
        button2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if(gameStart){
                    if(player2Turn){
                     randResult.setText(String.valueOf(getDiceValue()));
                     move2Player();
                     translatePlayer(player2XPos, player2YPos, player2);
                        player2Turn = false;
                        player1Turn = true;
                    
                         if (player2XPos == 200 && player2YPos ==760){
                            translatePlayer(player2XPos = 80, player2YPos = 520, player2);
                        }
                    }
                }
            }
        });
        
        gameButton = new Button("Start Game");
        gameButton.setTranslateX(250);
        gameButton.setTranslateY(650);
       gameButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                gameStart = true;
                player1Turn = true;
                if (player2Turn == false) {
                    player1Turn = true;
                }else{
                    player2Turn = true;
                }
                gameButton.setText("Game Stated");
                player1XPos = 30;
                player1YPos = 590;
                
                player2XPos = 30;
                player2YPos = 590;
                
                player1.setTranslateX(player1XPos);
                player1.setTranslateY(player1YPos);
                player2.setTranslateX(player2XPos);
                player2.setTranslateY(player2YPos);
                gameStart = true;
                
                }
        });
        
        randResult = new Label("0");
        randResult.setTranslateX(400);
        randResult.setTranslateY(650);
        
        Image image = new Image("snakeandleadder/snakebg.jpg"); 
        
        ImageView bgImage = new ImageView();
        bgImage.setImage(image);
        bgImage.setFitHeight(620);
        bgImage.setFitWidth(600);
        
       
        tileGroup.getChildren().addAll(bgImage, player1, player2,button1, button2, gameButton, randResult);
        return root;    
    }
    private int getDiceValue(){
        rand = (int)(Math.random()*6+1);
        return rand;
    }
    private void move1Player(){
        for (int i = 0; i < rand; i++) {
            if (posCir1 % 2 == 1) {
                player1XPos+=60;
            }
            if (posCir1 % 2 == 0){
                player1XPos-=60;
            }
            if (player1XPos > 600){
                player1YPos -= 62;
                player1XPos -= 60;
                 posCir1 ++;
            }
            if (player1XPos < 30 ){
                player1YPos -= 62;
                player1XPos += 60;
                posCir1 ++;
            }
            if (player1XPos < 30 || player1YPos < 30 ){
                player1YPos = 40;
                player1XPos = 40;
                randResult.setText("Player 1 won");
                gameButton.setText("Start again");
            }
        }
    }
      private void move2Player(){
        for (int i = 0; i < rand; i++) {
            if (posCir2 % 2 == 1) {
                player2XPos+=60;
            }
            if (posCir2 % 2 == 0){
                player2XPos-=60;
            }
            if (player2XPos > 600){
                player2YPos -= 62;
                player2XPos -= 60;
                 posCir2 ++;
            }
            if (player2XPos < 30 ){
                player2YPos -= 62;
                player2XPos += 60;
                posCir2 ++;
            }
            if (player2XPos < 30 || player2YPos < 30 ){
                player2YPos = 30;
                player2XPos = 30;
                randResult.setText("Player 2 won");
                gameButton.setText("Start again");
            }
        }
    }
    private void translatePlayer(int x, int y, Circle b){
        TranslateTransition animate = new TranslateTransition(Duration.millis(1000),b);
        animate.setToX(x);
        animate.setToY(y);
        animate.setAutoReverse(false);
        animate.play();
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createConetent());
        primaryStage.setTitle("Snake and Leadder");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        AnimationTimer timer = new AnimationTimer() {

            @Override
            public void handle(long now) {
             
            }
        };
                
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
