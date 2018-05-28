package ca.bcit.comp2526.asn2;

/**
 * 
 * Queen.
 *
 * @author Viktor Alvar, Set B
 * @version 2018
 */
public class Queen extends ChessPiece {

    /**
     * Aligns the pawn to the center of the Tile.
     */
    public static final int ALIGN_ROW = 60;

    /**
     * Aligns the pawn to the center of the Tile.
     */
    public static final int ALIGN_COL = 15;

    /**
     * Default Serilization ID.
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
     * A String representing the Queens pieceColour, "black" or "white".
     */
    private String pieceColour;
    
    private int pieceLevel = 1;

    /**
     * 
     * Constructs an object of type Queen.
     * 
     * @param colour - "black" or "white"
     * @param x - column number
     * @param y - row number
     */
    public Queen(String colour, int x, int y) {
        locationX = x;
        locationY = y;
        
        relocate(x * Tile.TILE_SIZE, y * Tile.TILE_SIZE);
        pieceColour = colour;
        
        if (colour.toLowerCase().equals("black")) {
            setText("\u265B  ");
        } else if (colour.toLowerCase().equals("white")) {
            setText("\u2655 ");
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
    }

    @Override
    public boolean isValid(int x, int y) {
        boolean valid = false;
        if ((Math.abs(x - locationX) == Math.abs(y - locationY)) 
                && isPathClearDiagonal(x, y)) {
            valid = true;
        }
        if ((x == locationX || y == locationY) && isPathClearStraight(x, y)) {
            valid = true;
        }
        return valid;
    }

    /**
     * Determines if the path is clear in the straight direction.
     * @param x - Destination locationX
     * @param y - Destination locationY
     * @return a boolean
     */
    private boolean isPathClearStraight(int x, int y) {
        Boolean clear = true;
        boolean dirDown = (x == locationX && y > locationY);
        boolean dirUp = (x == locationX && y < locationY);
        boolean dirLeft = (y == locationY && x < locationX);
        boolean dirRight = (y == locationY && x > locationX);
        if (dirDown || dirUp) {
            for (int i = 1; i <= Math.abs(y - locationY) - 1; ++i) {
                if (pieceLevel == 1) {
                    if (dirUp) {
                        if (Board.MY_TILES[locationX][locationY - i].isHasPiece()) {
                            clear = false;
                        }
                    }
                    if (dirDown) {
                        if (Board.MY_TILES[locationX][locationY + i].isHasPiece()) {
                            clear = false;
                        }
                    }
                } else {
                    if (dirUp) {
                        if (Board.MY_3D_TILES[locationX][locationY - i].isHasPiece()) {
                            clear = false;
                        }
                    }
                    if (dirDown) {
                        if (Board.MY_3D_TILES[locationX][locationY + i].isHasPiece()) {
                            clear = false;
                        }
                    }
                }
            }
        }
        if ((dirLeft || dirRight)) {
            for (int i = 1; i <= Math.abs(x - locationX) - 1; ++i) {
                if (pieceLevel == 1) {
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
                } else {
                    if (dirLeft) {
                        if (Board.MY_3D_TILES[locationX - i][locationY].isHasPiece()) {
                            clear = false;
                        }  
                    }
                    if (dirRight) {
                        if (Board.MY_3D_TILES[locationX + i][locationY].isHasPiece()) {
                            clear = false;
                        }
                    }
                }
            }
        }
        return clear;
    }
    
    /**
     * Determines if the path is clear in the diagonal direction.
     * @param x - Destination locationX
     * @param y - Destination locationY
     * @return a boolean
     */
    private boolean isPathClearDiagonal(int x, int y) {
        boolean clear = true;
        int dirX = x > locationX ? 1 : -1;
        int dirY = y > locationY ? 1 : -1;
        int tileX = 0;
        int tileY = 0;
        for (int i = 1; i <= Math.abs(x - locationX) - 1; ++i) {
            tileX = locationX + (i * dirX);
            tileY = locationY + (i * dirY);
            if (pieceLevel == 1) {
                if (Board.MY_TILES[tileX][tileY].isHasPiece()) {
                    clear = false;
                }
            } else {
                if (Board.MY_3D_TILES[tileX][tileY].isHasPiece()) {
                    clear = false;
                }
            }
        }
        return clear;
    }

    @Override
    public String getPieceColour() {
        return pieceColour;
    }
    
//    private boolean moveUp(int x, int y) {
//        if (x == getLocationX() + )
//    }
//    
//    private boolean moveDown() {
//        
//    }
    
    @Override
    public boolean isValid3D(int tileLevel, int x, int y) {
        boolean valid = false;
        int pieceX = getLocationX();
        int pieceY = getLocationY();
        if (tileLevel == pieceLevel) {
             return isValid(x, y);
        } else {
            if (tileLevel > pieceLevel) {
                if (pieceColour.equals("black")) {
                    if ((x == pieceX + 7 && y == pieceY) //left
                            || (x == pieceX + 7 && y == pieceY + 1) //diagonal left-up
                            || (x == pieceX + 8 && y == pieceY + 1) //forward
                            || (x == pieceX + 9 && y == pieceY + 1) //diagonal right-up
                            || (x == pieceX + 9 && y == pieceY)     // right
                            || (x == pieceX + 8 && y == pieceY - 1) //backward
                            || (x == pieceX + 7 && y == pieceY - 1) //diagonal left-down
                            || (x == pieceX + 9 && y == pieceY - 1)){ //diagonal right-down  
                        valid = true;
                        pieceLevel = tileLevel;
                    }
                } else {
                    if ((x == pieceX + 7 && y == pieceY) //left
                            || (x == pieceX + 7 && y == pieceY + 1) //diagonal left-up
                            || (x == pieceX + 8 && y == pieceY + 1) //forward
                            || (x == pieceX + 9 && y == pieceY + 1) //diagonal right-up
                            || (x == pieceX + 9 && y == pieceY)     // right
                            || (x == pieceX + 8 && y == pieceY - 1) //backward
                            || (x == pieceX + 7 && y == pieceY - 1) //diagonal left-down
                            || (x == pieceX + 9 && y == pieceY - 1)){ //diagonal right-down  
                        valid = true;
                        pieceLevel = tileLevel;
                    }
                }
            } else {
                if (pieceColour.equals("black")) {
                    if ((x == pieceX - 7 && y == pieceY) //left
                            || (x == pieceX - 7 && y == pieceY + 1) //diagonal left-up
                            || (x == pieceX - 8 && y == pieceY + 1) //forward
                            || (x == pieceX - 9 && y == pieceY + 1) //diagonal right-up
                            || (x == pieceX - 9 && y == pieceY)     // right
                            || (x == pieceX - 8 && y == pieceY - 1) //backward
                            || (x == pieceX - 7 && y == pieceY - 1) //diagonal left-down
                            || (x == pieceX - 9 && y == pieceY - 1)){ //diagonal right-down  
                        valid = true;
                        pieceLevel = tileLevel;
                    }
                } else {
                    if ((x == pieceX - 7 && y == pieceY) //left
                            || (x == pieceX - 7 && y == pieceY + 1) //diagonal left-up
                            || (x == pieceX - 8 && y == pieceY + 1) //forward
                            || (x == pieceX - 9 && y == pieceY + 1) //diagonal right-up
                            || (x == pieceX - 9 && y == pieceY)     // right
                            || (x == pieceX - 8 && y == pieceY - 1) //backward
                            || (x == pieceX - 7 && y == pieceY - 1) //diagonal left-down
                            || (x == pieceX - 9 && y == pieceY - 1)){ //diagonal right-down  
                        valid = true;
                        pieceLevel = tileLevel;
                    }
                }
            } 
        }
        return valid;
    }
}
