/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeandleadder;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author jelit
 */
public class Tile extends Rectangle 
{
    public Tile(int x, int y)
    {
        setWidth(DiceRoleSnake.Tile_Size);
        setHeight(DiceRoleSnake.Tile_Size);
        
        setFill(Color.GREEN);
        setStroke(Color.BLACK);
        
    }
    
}
