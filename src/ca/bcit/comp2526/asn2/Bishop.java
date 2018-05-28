package ca.bcit.comp2526.asn2;

/**
 * 
 * Bishop Class containing methods that determine the valid positions the Bishop
 * object can be moved to. Other methods include getting and setting the X and Y
 * location of the Bishop.
 *
 * @author Viktor Alvar, Set B
 * @version 2018
 */
public class Bishop extends ChessPiece {

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
     * A String that stores the pieceColour.
     */
    private String pieceColour;

    /**
     * 
     * Constructs an object of type Bishop.
     * 
     * @param colour - "black" or "white"
     * @param x - column number
     * @param y - row number
     */
    public Bishop(String colour, int x, int y) {
        locationX = x;
        locationY = y;

        relocate(x * Tile.TILE_SIZE, y * Tile.TILE_SIZE);

        pieceColour = colour;
        if (colour.toLowerCase().equals("black")) {
            setText("\u265D ");
        } else if (colour.toLowerCase().equals("white")) {
            setText("\u2657 ");
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
    public boolean isValid(int x, int y) {
        boolean valid = false;
        if (Math.abs(x - locationX) == Math.abs(y - locationY) 
                && isPathClear(x, y)) {
            valid = true;
        }
        return valid;
    }

    /**
     * Determine's if the the path between the source and destination.
     * does not have any Chess Pieces
     * @param x - Destination's locationX
     * @param y - Destination's locationY
     * @return a boolean value if the path is clear
     */
    private boolean isPathClear(int x, int y) {
        boolean clear = true;
        int dirX = x > locationX ? 1 : -1;
        int dirY = y > locationY ? 1 : -1;
        int tileX = 0;
        int tileY = 0;
        for (int i = 1; i <= Math.abs(x - locationX) - 1; ++i) {
            tileX = locationX + (i * dirX);
            tileY = locationY + (i * dirY);
            if (Board.MY_TILES[tileX][tileY].isHasPiece()) {
                clear = false;
            }
        }
        return clear;
    }

    @Override
    public void setFirstMove(boolean b) {
        
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
