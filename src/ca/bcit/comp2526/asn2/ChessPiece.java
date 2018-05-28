package ca.bcit.comp2526.asn2;

import java.io.Serializable;

import javafx.scene.text.Text;

/**
 * 
 * Abstract ChessPiece that is contains the base methods for all chess pieces.
 *
 * @author Viktor Alvar, Set B
 * @version 2018
 */
public abstract class ChessPiece extends Text implements Serializable {

    /**
     * Default Serialization ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Returns the locationX value of the ChessPiece.
     * 
     * @return locationX
     */
    public abstract int getLocationX();

    /**
     * Set's the locationX value of the ChessPiece.
     * @param x - an int that represents locationX to be set
     */
    public abstract void setLocationX(int x);

    /**
     * Returns the locationY value of the ChessPiece.
     * 
     * @return locationY
     */
    public abstract int getLocationY();

    /**
     * Set's the locationY value of the ChessPiece.
     * @param y - an int that represents locationY to be set
     */
    public abstract void setLocationY(int y);

    /**
     * Return's a boolean value if the destination x and y are valid moves.
     * @param x - A Tile's locationX
     * @param y - A Tile's locationY
     * @return a true or false if the destination is valid
     */
    public abstract boolean isValid(int x, int y);
    
    /**
     * Return's a boolean value if the destination x and y are valid moves.
     * @param x - A Tile's locationX
     * @param y - A Tile's locationY
     * @return a true or false if the destination is valid
     */
    public abstract boolean isValid3D(int tileLevel, int x, int y);
    
    /**
     * Set's a boolean value if the ChessPiece has it's first move.
     * @param b - a boolean value
     */
    public abstract void setFirstMove(boolean b);

    /**
     * Get's the colour of the ChessPiece.
     * @return a String either "black" or "white"
     */
    public abstract String getPieceColour();
    
}
