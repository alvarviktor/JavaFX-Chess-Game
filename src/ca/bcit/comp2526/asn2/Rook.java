package ca.bcit.comp2526.asn2;

/**
 * 
 * Rook.
 *
 * @author Viktor Alvar, Set B
 * @version 2018
 */
public class Rook extends ChessPiece {

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
     * Availability of the piece if it clicked. False by default.
     */
    private boolean available;

    /**
     * A String representing the Rooks pieceColour, "black" or "white".
     */
    private String pieceColour;

    /**
     * 
     * Constructs an object of type Rook.
     * 
     * @param colour - "black" or "white"
     * @param x - column number
     * @param y - row number
     */
    public Rook(String colour, int x, int y) {
        locationX = x;
        locationY = y;
        
        relocate(x * Tile.TILE_SIZE, y * Tile.TILE_SIZE);
        pieceColour = colour;
        
        if (colour.toLowerCase().equals("black")) {
            setText("\u265C ");
        } else if (colour.toLowerCase().equals("white")) {
            setText("\u2656 ");
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

    /**
     * Returns the available for this Pawn.
     * 
     * @return available
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Sets the valid for this Pawn.
     * 
     * @param available
     *            the available to set
     */
    public void setAvailable(boolean available) {
        this.available = available;
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
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isValid(int x, int y) {
        boolean valid = false;
        if ((x == locationX || y == locationY) && isPathClear(x, y)) {
            valid = true;
        }
        return valid;
    }

    /**
     * Determines if the path is clear of other pieces between the
     * source location and destination location.
     * @param x - Destination locationX
     * @param y - Destination locationY
     * @return a boolean if the path is clear
     */
    private Boolean isPathClear(int x, int y) {
        Boolean clear = true;
        boolean dirDown = (x == locationX && y > locationY);
        boolean dirUp = (x == locationX && y < locationY);
        boolean dirLeft = (y == locationY && x < locationX);
        boolean dirRight = (y == locationY && x > locationX);
        if (dirDown || dirUp) {
            for (int i = 1; i <= Math.abs(y - locationY) - 1; ++i) {
                if (dirDown) {
                    if (Board.MY_TILES[locationX][locationY + i].isHasPiece()) {
                        clear = false;
                    }
                }
                if (dirUp) {
                    if (Board.MY_TILES[locationX][locationY - i].isHasPiece()) {
                        clear = false;
                    }
                }
            }
        }
        if (dirLeft || dirRight) {
            for (int i = 1; i <= Math.abs(x - locationX) - 1; ++i) {
                if (dirLeft) {
                    if (Board.MY_TILES[locationX - i][locationY].isHasPiece()) {
                        clear = false;
                    }
                }
                if (dirRight) {
                    if (Board.MY_TILES[locationX + i][locationY].isHasPiece()) {
                        clear = false;
                    }
                }
            }
        }
        return clear;
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
