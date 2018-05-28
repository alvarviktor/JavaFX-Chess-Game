package ca.bcit.comp2526.asn2;

import java.io.Serializable;

import javafx.scene.paint.Color;

import javafx.scene.shape.Rectangle;

/**
 * 
 * Tiles that makes up the Board.
 *
 * @author Viktor Alvar, Set B
 * @version 2018
 */
public class Tile extends Rectangle implements Serializable {

    /**
     * Size of the Tile.
     */
    public static final int TILE_SIZE = 100;
    
    /**
     * Size of the 3D Tile.
     */
    public static final int TILE_SIZE_3D = 75;

    /**
     * Default Serialization ID.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Column number for the Tile.
     */
    private int locationX;

    /**
     * Row number for the Tile.
     */
    private int locationY;

    /**
     * Color of the tile object.
     */
    private String tileColour;

    /**
     * A boolean representing if there is a piece on a tile.
     */
    private boolean hasPiece;
    
    private int tileLevel;
    
    /**
     * 
     * Constructs an object of type Tile.
     * 
     * @param colour - light or dark
     * @param x - column number
     * @param y - row number
     */
    public Tile(boolean colour, int x, int y) {

        setWidth(TILE_SIZE);
        setHeight(TILE_SIZE);

        locationX = x;
        locationY = y;

        relocate(x * TILE_SIZE, y * TILE_SIZE);
        if (x > 7 && x < 16) {
            tileColour = (colour ? "#eeeed2" : "#00a9af");
            tileLevel = 2;
        } else if (x > 15) {
            tileColour = (colour ? "#eeeed2" : "#e491a3");
            tileLevel = 3;
        } else {
            tileColour = (colour ? "#eeeed2" : "#769656");
            tileLevel = 1;
        }
        setFill(Color.valueOf(tileColour));

    }

    public int getTileLevel() {
        return tileLevel;
    }
    
    /**
     * Returns the hasPiece for this Tile.
     * 
     * @return hasPiece
     */
    public boolean isHasPiece() {
        return hasPiece;
    }

    /**
     * Sets the hasPiece for this Tile.
     * 
     * @param hasPiece
     *            the hasPiece to set
     */
    public void setHasPiece(boolean hasPiece) {
        this.hasPiece = hasPiece;
    }

    /**
     * Returns the locationX for this Tile.
     * 
     * @return locationX
     */
    public int getLocationX() {
        return locationX;
    }

    /**
     * Returns the locationY for this Tile.
     * 
     * @return locationY
     */
    public int getLocationY() {
        return locationY;
    }

}
