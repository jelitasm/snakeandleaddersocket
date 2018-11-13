/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeandleadder;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
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
import javax.swing.JOptionPane;


/**
 *
 * @author jelit
 */
public class DiceRoleSnake extends Application {
    public int rand;
    public Label randResult;
    public Label usernameLabel;
    
    public int cirPos[][] = new int[10][10];
   public int leadderPosition[][] = new int[6][3];
            
    public static final int Tile_Size = 60;
    public static final int width = 10;
    public static final int height = 10;
    
    public Circle player1;
    public Circle player2;
    
   
    public boolean gameStart = false;
    public Button gameButton;
    
    public int posCir1 =1 ;
    public int posCir2 =1;
    private Group tileGroup = new Group();
    
    public String username;
    public String host;
    int port;
    Socket socket;
    DataOutputStream dos;
    public boolean attachmentOpen = false;
    private boolean isConnected = false;
    
    private Parent createConetent()
    {
        initFrame("aldhan", "localhost", 8080);
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
        
        final Player player1 = new Player();
        player1.setPosition(1);
        player1.setIsTurn(false);
        player1.setxPos(30);
        player1.setyPos(590);
        player1.setPosCir(1);
        
        Circle gaco1 = new Circle(20);
        gaco1.setId("player1");
        gaco1.setFill(Color.AQUA);
        gaco1.getStyleClass().add("snakeandleader/style.class");
        gaco1.setTranslateX(player1.getxPos());
        gaco1.setTranslateY(player1.getyPos());
        player1.setGaco(gaco1);
        
        
        Button button1 = new Button("Player1");
        button1.setTranslateX(450);
        button1.setTranslateY(650);
        button1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if(gameStart){
                    if(player1.isTurn){
                        int rand = getDiceValue();
                        randResult.setText(String.valueOf(rand));
                        player1.movePlayer(rand);
                        translatePlayer(player1.getxPos(), player1.getyPos(), player1.getGaco());
                        player1.setIsTurn(false);
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
                player1.setIsTurn(true);
                gameButton.setText("Game Stated");
                
                player1.getGaco().setTranslateX(player1.getxPos());
                player1.getGaco().setTranslateY(player1.getyPos());
                gameStart = true;
                
                }
        });
        
        randResult = new Label("0");
        randResult.setTranslateX(400);
        randResult.setTranslateY(650);
        
        usernameLabel = new Label("1233213");
        usernameLabel.setTranslateX(100);
        usernameLabel.setTranslateY(650);
        
        
        Image image = new Image("snakeandleadder/snakebg.jpg"); 
        
        ImageView bgImage = new ImageView();
        bgImage.setImage(image);
        bgImage.setFitHeight(620);
        bgImage.setFitWidth(600);

        
        Button button2 = new Button("Player2");
        tileGroup.getChildren().addAll(bgImage, player1.gaco,button1, button2, gameButton, randResult,usernameLabel);
        return root;    
    }
    private int getDiceValue(){
        rand = (int)(Math.random()*6+1);
        return rand;
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

    public void initFrame(String username, String host, int port){
        this.username = username;
        this.host = host;
        this.port = port;
        usernameLabel.setText("Masuk sebagai : " + username);
        /** Connect **/
        connect();
    }
    
    public void connect(){
        try {
            socket = new Socket(host, port);
            dos = new DataOutputStream(socket.getOutputStream());
            /** Send our username **/
            dos.writeUTF("CMD_JOIN "+ username);
            
            /** Start Client Thread **/
            new Thread(new ClientThread(socket, this)).start();
//            jButton1.setEnabled(true);
            // were now connected
            isConnected = true;
            
        }
        catch(IOException e) {
            isConnected = false;
//            JOptionPane.showMessageDialog(this, "Gagal konek ke server, silahkan coba beberapa saat lagi.!","Koneksi putus",JOptionPane.ERROR_MESSAGE);
//            appendMessage("[IOException]: "+ e.getMessage(), "Error", java.awt.Color.RED, java.awt.Color.RED);
        }
    }
    
    public boolean isConnected(){
        return this.isConnected;
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
