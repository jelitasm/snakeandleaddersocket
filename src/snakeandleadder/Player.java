/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeandleadder;

import javafx.scene.shape.Circle;

/**
 *
 * @author aldan
 */
public class Player {
    public Circle gaco;
    public int position;
    public boolean isTurn;
    public int xPos;
    public int yPos;
    public int posCir;

    public Circle getGaco() {
        return gaco;
    }

    public void setGaco(Circle gaco) {
        this.gaco = gaco;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isIsTurn() {
        return isTurn;
    }

    public void setIsTurn(boolean isTurn) {
        this.isTurn = isTurn;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getPosCir() {
        return posCir;
    }

    public void setPosCir(int posCir) {
        this.posCir = posCir;
    }
    
    
    public void movePlayer(int rand){
        for (int i = 0; i < rand; i++) {
            if (this.posCir % 2 == 1) {
                this.xPos+=60;
            }
            if (this.posCir % 2 == 0){
                this.xPos-=60;
            }
            if (this.xPos > 600){
                this.yPos -= 62;
                this.xPos -= 60;
                 this.posCir ++;
            }
            if (this.xPos < 30 ){
                this.yPos -= 62;
                this.xPos += 60;
                this.posCir ++;
            }
            if (this.xPos < 30 || this.yPos < 30 ){
                this.yPos = 40;
                this.xPos = 40;
//                randResult.setText("Player 1 won");
//                gameButton.setText("Start again");
            }
        }
    }
}
