package ca.bcit.comp2526.asn2;

/**
 * 
 * King.
 *
 * @author Viktor Alvar, Set B
 * @version 2018
 */
public class King extends ChessPiece {

    /**
     * Aligns the pawn to the center of the Tile.
     */
    public static final int ALIGN_ROW = 60;

    /**
     * Aligns the pawn to the center of the Tile.
     */
    public static final int ALIGN_COL = 15;

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
     * A String representing the Kings pieceColour, "black" or "white".
     */
    private String pieceColour;

    /**
     * 
     * Constructs an object of type King.
     * 
     * @param colour - "black" or "white"
     * @param x - column number
     * @param y - row number
     */
    public King(String colour, int x, int y) {

        locationX = x;
        locationY = y;
        relocate(x * Tile.TILE_SIZE, y * Tile.TILE_SIZE);
        pieceColour = colour;
        if (colour.toLowerCase().equals("black")) {
            setText("\u265A ");
        } else if (colour.toLowerCase().equals("white")) {
            setText("\u2654 ");
        }
        setStyle("-fx-font: 65 arial");
        setX(ALIGN_COL);
        setY(ALIGN_ROW);

    }

    /**
     * Returns the locationX for this Pawn.
     * 
     * @return locationX
     */
    public int getLocationX() {
        return locationX;
    }

    /**
     * Returns the locationY for this Pawn.
     * 
     * @return locationY
     */
    public int getLocationY() {
        return locationY;
    }

    @Override
    public void setLocationX(int x) {
        locationX = x;
    }

    @Override
    public void setLocationY(int y) {
        locationY = y;
    }

    @Override
    public void setFirstMove(boolean b) {
        
    }

    @Override
    public boolean isValid(int x, int y) {
        boolean valid = false;
        if (Math.abs(x - locationX) < 2 && Math.abs(y - locationY) < 2) {
            valid = true;
        }
        return valid;
    }

    @Override
    public String getPieceColour() {
        return pieceColour;
    }

    @Override
    public boolean isValid3D(int tileLevel, int x, int y) {
        // TODO Auto-generated method stub
        return false;
    }

}
