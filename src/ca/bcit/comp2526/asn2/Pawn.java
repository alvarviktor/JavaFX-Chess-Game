package ca.bcit.comp2526.asn2;

/**
 * 
 * Pawn.
 *
 * @author Viktor Alvar, Set B
 * @version 2018
 */
public class Pawn extends ChessPiece {

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
     * A Boolean determining if it's the Pawns first move.
     */
    private boolean firstMove = true;

    /**
     * The Pawn's Piece color, "black" or "white".
     */
    private String pieceColour;
    
    private int pieceLevel = 1;

    /**
     * 
     * Constructs an object of type Pawn.
     * 
     * @param colour - "black" or "white"
     * @param x - column number
     * @param y - row number
     */
    public Pawn(String colour, int x, int y) {

        locationX = x;
        locationY = y;
        relocate(x * Tile.TILE_SIZE, y * Tile.TILE_SIZE);
        pieceColour = colour;
        if (colour.toLowerCase().equals("black")) {
            setText("\u265F ");
        } else if (colour.toLowerCase().equals("white")) {
            setText("\u2659 ");
        }
        setStyle("-fx-font: 65 arial");
        setX(ALIGN_COL);
        setY(ALIGN_ROW);

        
    }

    /**
     * Sets the locationX for this Pawn.
     * 
     * @param locationX
     *            the locationX to set
     */
    public void setLocationX(int locationX) {
        this.locationX = locationX;
    }

    /**
     * Sets the locationY for this Pawn.
     * 
     * @param locationY
     *            the locationY to set
     */
    public void setLocationY(int locationY) {
        this.locationY = locationY;
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

    /**
     * Determine's if the movement of the pawn is valid.
     * @param x - Destination locationX
     * @param y - Destination locationY
     * @return a boolean value
     */
    public boolean isValid(int x, int y) {
        boolean valid = false;
        if (firstMove) {
            if (pieceColour.equals("black")) {
                if ((y == (getLocationY() + 1) || y == (getLocationY() + 2)) 
                        && x == getLocationX()) {
                    valid = true;
                }
            } else {
                if ((y == (getLocationY() - 1) || y == (getLocationY() - 2)) 
                        && x == getLocationX()) {
                    valid = true;
                }
            }
        } else {
            if (pieceColour.equals("black")) {
                if ((y == getLocationY() + 1) && (x == getLocationX()
                        || x == getLocationX() + 8)) {
                    valid = true;
                }
            } else {
                if ((y == getLocationY() - 1) && (x == getLocationX()
                        || x == getLocationX() + 8)) {
                    valid = true;
                }
            }
        }
        return valid;
    }

    /**
     * Determine's if the movement of the pawn is valid.
     * @param x - Destination locationX
     * @param y - Destination locationY
     * @return a boolean value
     */
    public boolean isValid3D(int tileLevel, int x, int y) {
        boolean valid = false;
        if (tileLevel == pieceLevel) {
            valid = isValid(x, y);
        }
        if (firstMove) {
            if (tileLevel == 2 && (pieceLevel == 1 || pieceLevel == 3)) {
                if (pieceColour.equals("black")) {
                    if ((y == getLocationY() + 1 || y == getLocationY() + 2) 
                            && (x == getLocationX() + 8 
                            || x == getLocationX() - 8)) {
                        valid = true;
                        pieceLevel = tileLevel;
                    }
                } else {
                    if ((y == getLocationY() - 1 || y == getLocationY() - 2)
                            && (x == getLocationX() + 8 
                            || x == getLocationX() - 8)) {
                        valid = true;
                        pieceLevel = tileLevel;
                    }
                }
            }
        } else {
            if (tileLevel == 1 && pieceLevel == 2) {
                if (pieceColour.equals("black")) {
                    if (y == getLocationY() + 1 && (x == getLocationX() - 8)) {
                        valid = true;
                        pieceLevel = tileLevel;
                    }
                } else {
                    if (y == getLocationY() - 1 && (x == getLocationX() - 8)) {
                        valid = true;
                        pieceLevel = tileLevel;
                    }
                }
            }
            if (tileLevel == 3 && pieceLevel == 2) {
                pieceLevel = tileLevel;
                if (pieceColour.equals("black")) {
                    if (y == getLocationY() + 1 && x == getLocationX() + 8) {
                        valid = true;
                    }
                } else {
                    if (y == getLocationY() - 1 && x == getLocationX() + 8) {
                        valid = true;
                    }
                }
            }
        }
        return valid;
    }
    
    /**
     * Sets a boolean value if it's the pawns first move.
     * @param b a boolean value
     */
    public void setFirstMove(boolean b) {
        firstMove = b;
    }

    /**
     * Get's the pieceColour String value.
     * @return a String representing the pieceColour, "black" or "white"
     */
    public String getPieceColour() {
        return pieceColour;
    }
    
}
