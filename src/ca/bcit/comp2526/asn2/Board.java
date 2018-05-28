package ca.bcit.comp2526.asn2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.ListIterator;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * 
 * Board.
 *
 * @author Viktor Alvar, Set B
 * @version 2018
 */
public class Board extends Application implements Serializable {

    /**
     * Number of Rows.
     */
    public static final int ROWS = 8;

    /**
     * Number of Columns.
     */
    public static final int COLUMNS = 8;

    /**
     * Number of Columns in a 3D Board.
     */
    public static final int COLUMNS_3D = 24;
    
    /**
     * Number of total chess pieces.
     */
    public static final int NUM_PIECES = 32;

    /**
     * Third column of Board.
     */
    public static final int COL_THREE = 3;

    /**
     * Fourth column of Board.
     */
    public static final int COL_FOUR = 4;

    /**
     * Fifth column of Board.
     */
    public static final int COL_FIVE = 5;

    /**
     * Sixth column of Board.
     */
    public static final int COL_SIX = 6;

    /**
     * Seventh column of Board.
     */
    public static final int COL_SEVEN = 7;

    /**
     * Sixth row of Board.
     */
    public static final int ROW_SIX = 6;

    /**
     * Seventh row of Board.
     */
    public static final int ROW_SEVEN = 7;
    
    /**
     * Alignment value so each ChessPiece when it is moved.
     */
    public static final int PIECE_OFFSET = 15;
    
    /**
     * Alignment value the board to the center.
     */
    public static final int BOARD_OFFSET = 30;
    
    /**
     * Number of Black or White Pieces.
     */
    public static final int NUM_OF_PIECES = 16;
    
    /**
     * Alignment value for the menu bar.
     */
    public static final int MENU_OFFSET = -385;
    
    /**
     * Alignment value to center the group of Tiles.
     */
    public static final int GROUP_OFFSET = 30;
    
    /**
     * Default Serialization ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 2D Array of Tile objects.
     */
    public static final Tile[][] MY_TILES = new Tile[ROWS][COLUMNS];
    
    /**
     * 2D Array of Tile objects.
     */
    public static final Tile[][] MY_3D_TILES = new Tile[COLUMNS_3D][ROWS];

    /**
     * Group of Tile nodes to be added to the scene.
     */
    private Group tileGroup = new Group();

    /**
     * Grid Pane that holds group of tiles and pieces.
     */
    private GridPane chessPane;

    /**
     * ChessPiece that is currently selected to be moved.
     */
    private ChessPiece selectedPiece;

    /**
     * A Boolean determining if it's white players turn.
     * Player White goes first by default.
     */
    private boolean whiteTurn = true;

    /**
     * A boolean that will be set when the chess game is serialized.
     */
    private boolean savedTurn;

    /**
     * ArrayList of all chessPieces.
     */
    private ArrayList<ChessPiece> myPieces = new ArrayList<ChessPiece>();
    
    /**
     * FileChooser to open and save a chess game state.
     */
    private FileChooser fileChooser = new FileChooser();

    /**
     * File to be saved or opened.
     */
    private File myFile;
    
    /**
     * A Stage used to call in other methods.
     */
    private Stage myStage;
    
    /**
     * Initializes and 8x8 grid.
     */
    private void initializeGrid() {
        for (int y = 0; y < MY_TILES.length; y++) {
            for (int x = 0; x < MY_TILES.length; x++) {
                MY_TILES[x][y] = new Tile((x + y) % 2 == 0, x, y);
                MY_TILES[x][y].setOnMouseClicked(this::handleTile);
                tileGroup.getChildren().add(MY_TILES[x][y]);

                drawPieces(x, y, MY_TILES);
            }
        }
    }

    /**
     * Initializes and 8x24 grid.
     */
    private void initialize3DGrid() {
        for (int y = 0; y < ROWS; y++) {
            for (int x = 0; x < COLUMNS_3D; x++) {
                int tileX = x * Tile.TILE_SIZE_3D;
                int tileY = y * Tile.TILE_SIZE_3D;
                MY_3D_TILES[x][y] = new Tile((x + y) % 2 == 0, x, y);
                MY_3D_TILES[x][y].setHeight(Tile.TILE_SIZE_3D);
                MY_3D_TILES[x][y].setWidth(Tile.TILE_SIZE_3D);
                MY_3D_TILES[x][y].relocate(tileX, tileY);
                MY_3D_TILES[x][y].setOnMouseClicked(this::handleTile3D);
                tileGroup.getChildren().add(MY_3D_TILES[x][y]);

                ChessPiece temp = draw3DPieces(x, y, MY_3D_TILES);
                temp.relocate(tileX, tileY);
                temp.setTranslateX(15);
                temp.setTranslateY(35);
                temp.setStyle("-fx-font: 45 arial");
                temp.toFront();
            }
        }
    }
    
    private ChessPiece draw3DPieces(int x, int y, Tile[][] myTiles) {
        ChessPiece temp = new Pawn("black", x, y);
        if (y == 0) {
            if (x == 0 || x == COL_SEVEN) {
                temp = new Rook("black", x, y);
                temp.setOnMouseClicked(this::handlePiece);
                tileGroup.getChildren().add(temp);
                myPieces.add(temp);
                myTiles[x][y].setHasPiece(true);
            }
            if (x == 1 || x == COL_SIX) {
                temp = new Knight("black", x, y);
                temp.setOnMouseClicked(this::handlePiece);
                tileGroup.getChildren().add(temp);
                myPieces.add(temp);
                myTiles[x][y].setHasPiece(true);
            }
            if (x == 2 || x == COL_FIVE) {
                temp = new Bishop("black", x, y);
                temp.setOnMouseClicked(this::handlePiece);
                tileGroup.getChildren().add(temp);
                myPieces.add(temp);
                myTiles[x][y].setHasPiece(true);
            }
            if (x == COL_THREE) {
                temp = new King("black", x, y);
                temp.setOnMouseClicked(this::handlePiece);
                tileGroup.getChildren().add(temp);
                myPieces.add(temp);
                myTiles[x][y].setHasPiece(true);
            }
            if (x == COL_FOUR) {
                temp = new Queen("black", x, y);
                temp.setOnMouseClicked(this::handlePiece);
                tileGroup.getChildren().add(temp);
                myPieces.add(temp);
                myTiles[x][y].setHasPiece(true);
            }
        }
        if (y == 1 && x < ROWS) {
            temp = new Pawn("black", x, y);
            temp.setOnMouseClicked(this::handlePiece);
            tileGroup.getChildren().add(temp);
            myPieces.add(temp);
            myTiles[x][y].setHasPiece(true);
        }
        if (y == ROW_SIX && x < ROWS) {
            temp = new Pawn("white", x, y);
            temp.setOnMouseClicked(this::handlePiece);
            tileGroup.getChildren().add(temp);
            myPieces.add(temp);
            myTiles[x][y].setHasPiece(true);
        }
        if (y == ROW_SEVEN) {
            if (x == 0 || x == COL_SEVEN) {
                temp = new Rook("white", x, y);
                temp.setOnMouseClicked(this::handlePiece);
                tileGroup.getChildren().add(temp);
                myPieces.add(temp);
                myTiles[x][y].setHasPiece(true);
            }
            if (x == 1 || x == COL_SIX) {
                temp = new Knight("white", x, y);
                temp.setOnMouseClicked(this::handlePiece);
                tileGroup.getChildren().add(temp);
                myPieces.add(temp);
                myTiles[x][y].setHasPiece(true);
            }
            if (x == 2 || x == COL_FIVE) {
                temp = new Bishop("white", x, y);
                temp.setOnMouseClicked(this::handlePiece);
                tileGroup.getChildren().add(temp);
                myPieces.add(temp);
                myTiles[x][y].setHasPiece(true);
            }
            if (x == COL_THREE) {
                temp = new King("white", x, y);
                temp.setOnMouseClicked(this::handlePiece);
                tileGroup.getChildren().add(temp);
                myPieces.add(temp);
                myTiles[x][y].setHasPiece(true);
            }
            if (x == COL_FOUR) {
                temp = new Queen("white", x, y);
                temp.setOnMouseClicked(this::handlePiece);
                tileGroup.getChildren().add(temp);
                myPieces.add(temp);
                myTiles[x][y].setHasPiece(true);
            }
        }
        return temp;
    }
    
    /**
     * An event listener when a tile is clicked.
     * Handles Piece movement after a piece is selected.
     * @param event a MouseEvent
     */
    public void handleTile(MouseEvent event) {
        Tile t = (Tile) event.getSource();
        System.out.println(t.getLocationX() + " " + t.getLocationY());
        try {
            int pieceX = selectedPiece.getLocationX();
            int pieceY = selectedPiece.getLocationY();
            int tileX = (t.getLocationX() * Tile.TILE_SIZE) + PIECE_OFFSET;
            int tileY = (t.getLocationY() * Tile.TILE_SIZE) + PIECE_OFFSET;
            if (selectedPiece.isValid(t.getLocationX(), t.getLocationY())) {
                selectedPiece.relocate(tileX, tileY);
                selectedPiece.setFirstMove(false);
                MY_TILES[pieceX][pieceY].setHasPiece(false);
                selectedPiece.setLocationX(t.getLocationX());
                selectedPiece.setLocationY(t.getLocationY());
                t.setHasPiece(true);
                selectedPiece = null;
                if (whiteTurn) {
                    whiteTurn = false;
                } else {
                    whiteTurn = true;
                }
            } else {
                System.out.println("Invalid Move");
            }
        } catch (NullPointerException e) {
            System.out.println("No Piece Selected");
        }
    }

    
    /**
     * An event listener when a piece is clicked.
     * Handles if a piece can be selected or not.
     * @param event2 a MouseEvent
     */
    public void handlePiece(MouseEvent event2) {
        ChessPiece p2 = (ChessPiece) event2.getSource();
        System.out.println(p2.getLocationX() + " " + p2.getLocationY()
                );
        if (whiteTurn && p2.getPieceColour().equals("white")) {
            p2.toFront();
            setPiece(p2);
        } else if (!whiteTurn && p2.getPieceColour().equals("black")) {
            p2.toFront();
            setPiece(p2);
        } else {
            System.out.println("Not your Turn");
        }

    }

    /**
     * An event listener when a tile is clicked.
     * Handles Piece movement after a piece is selected.
     * @param event a MouseEvent
     */
    public void handleTile3D(MouseEvent event) {
        Tile t = (Tile) event.getSource();
        System.out.println(t.isHasPiece());
        System.out.println(t.getLocationX() + " " + t.getLocationY() + " Level: " + t.getTileLevel());
        try {
            int pieceX = selectedPiece.getLocationX();
            int pieceY = selectedPiece.getLocationY();
            int tileX = (t.getLocationX() * Tile.TILE_SIZE_3D);
            int tileY = (t.getLocationY() * Tile.TILE_SIZE_3D) - 20;
            if (selectedPiece.isValid3D(t.getTileLevel(), t.getLocationX(), t.getLocationY())) {
                selectedPiece.relocate(tileX, tileY);
                selectedPiece.setFirstMove(false);
                MY_3D_TILES[pieceX][pieceY].setHasPiece(false);
                selectedPiece.setLocationX(t.getLocationX());
                selectedPiece.setLocationY(t.getLocationY());
                t.setHasPiece(true);
                selectedPiece = null;
                if (whiteTurn) {
                    whiteTurn = false;
                } else {
                    whiteTurn = true;
                }
            } else {
                System.out.println("Invalid Move");
            }
        } catch (NullPointerException e) {
            System.out.println("No Piece Selected");
        }
    }
    
    /**
     * Set's a chessPiece to be the current selected piece.
     * @param p a ChessPiece that is clicked
     */
    public void setPiece(ChessPiece p) {
        selectedPiece = p;
    }

    /**
     * Draw's the chess pieces.
     * 
     * @param x - integer representing column number
     * @param y - integer representing row number
     * @param myTiles - A 2D array of Tiles Objects
     * @return - a ChessPiece object
     */
    private ChessPiece drawPieces(int x, int y, Tile[][] myTiles) {
        ChessPiece temp = new Pawn("black", x, y);
        if (y == 0) {
            if (x == 0 || x == COL_SEVEN) {
                temp = new Rook("black", x, y);
                temp.setOnMouseClicked(this::handlePiece);
                tileGroup.getChildren().add(temp);
                myPieces.add(temp);
                myTiles[x][y].setHasPiece(true);
            }
            if (x == 1 || x == COL_SIX) {
                temp = new Knight("black", x, y);
                temp.setOnMouseClicked(this::handlePiece);
                tileGroup.getChildren().add(temp);
                myPieces.add(temp);
                myTiles[x][y].setHasPiece(true);
            }
            if (x == 2 || x == COL_FIVE) {
                temp = new Bishop("black", x, y);
                temp.setOnMouseClicked(this::handlePiece);
                tileGroup.getChildren().add(temp);
                myPieces.add(temp);
                myTiles[x][y].setHasPiece(true);
            }
            if (x == COL_THREE) {
                temp = new King("black", x, y);
                temp.setOnMouseClicked(this::handlePiece);
                tileGroup.getChildren().add(temp);
                myPieces.add(temp);
                myTiles[x][y].setHasPiece(true);
            }
            if (x == COL_FOUR) {
                temp = new Queen("black", x, y);
                temp.setOnMouseClicked(this::handlePiece);
                tileGroup.getChildren().add(temp);
                myPieces.add(temp);
                myTiles[x][y].setHasPiece(true);
            }
        }
        if (y == 1) {
            temp = new Pawn("black", x, y);
            temp.setOnMouseClicked(this::handlePiece);
            tileGroup.getChildren().add(temp);
            myPieces.add(temp);
            myTiles[x][y].setHasPiece(true);
        }
        if (y == ROW_SIX) {
            temp = new Pawn("white", x, y);
            temp.setOnMouseClicked(this::handlePiece);
            tileGroup.getChildren().add(temp);
            myPieces.add(temp);
            myTiles[x][y].setHasPiece(true);
        }
        if (y == ROW_SEVEN) {
            if (x == 0 || x == COL_SEVEN) {
                temp = new Rook("white", x, y);
                temp.setOnMouseClicked(this::handlePiece);
                tileGroup.getChildren().add(temp);
                myPieces.add(temp);
                myTiles[x][y].setHasPiece(true);
            }
            if (x == 1 || x == COL_SIX) {
                temp = new Knight("white", x, y);
                temp.setOnMouseClicked(this::handlePiece);
                tileGroup.getChildren().add(temp);
                myPieces.add(temp);
                myTiles[x][y].setHasPiece(true);
            }
            if (x == 2 || x == COL_FIVE) {
                temp = new Bishop("white", x, y);
                temp.setOnMouseClicked(this::handlePiece);
                tileGroup.getChildren().add(temp);
                myPieces.add(temp);
                myTiles[x][y].setHasPiece(true);
            }
            if (x == COL_THREE) {
                temp = new King("white", x, y);
                temp.setOnMouseClicked(this::handlePiece);
                tileGroup.getChildren().add(temp);
                myPieces.add(temp);
                myTiles[x][y].setHasPiece(true);
            }
            if (x == COL_FOUR) {
                temp = new Queen("white", x, y);
                temp.setOnMouseClicked(this::handlePiece);
                tileGroup.getChildren().add(temp);
                myPieces.add(temp);
                myTiles[x][y].setHasPiece(true);
            }
        }
        return temp;
    }

    /**
     * Add's the group of tiles and pieces to the root node GridPane.
     * 
     * @return GridPane object
     */
    private Parent rootNode() {
        initializeGrid();
        chessPane = new GridPane();
        chessPane.setPrefSize(ROWS * Tile.TILE_SIZE,
                COLUMNS * Tile.TILE_SIZE + BOARD_OFFSET);
        chessPane.getChildren().add(tileGroup);

        return chessPane;
    }

    /**
     * Open's a open file dialog to open a Chess Game state.
     * @return an EventHandler
     */
    private EventHandler<ActionEvent> handleOpen() {
        return new EventHandler<ActionEvent>() {

            @SuppressWarnings("unchecked")
            public void handle(ActionEvent event) {
                fileChooser.setTitle("Open Save File");
                fileChooser.getExtensionFilters().add(
                        new ExtensionFilter("Serializable Files", "*.ser"));
                try {
                    myFile = fileChooser.showOpenDialog(null);
                    if (myFile == null) {
                        return;
                    }
                    for (ChessPiece chessPiece : myPieces) {
                        tileGroup.getChildren().remove(chessPiece);
                    }
                    myPieces.clear();
                    FileInputStream fileIn = new FileInputStream(myFile);
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                    myPieces = (ArrayList<ChessPiece>) in.readObject();
                    in.close();
                    fileIn.close();
                } catch (IOException i) {
                    i.printStackTrace();
                    return;
                } catch (ClassNotFoundException c) {
                    System.out.println("Employee class not found");
                    c.printStackTrace();
                    return;
                }
                relocatePieces(myPieces);
                whiteTurn = savedTurn;
                resetBoard();
            }
        };
    }

    /**
     * Reset's the board's hasPiece boolean when a save file is opened.
     */
    private void resetBoard() {
        for (int y = 0; y < MY_TILES.length; y++) {
            for (int x = 0; x < MY_TILES.length; x++) {
                MY_TILES[x][y].setHasPiece(false);
            }
        }
        for (ChessPiece myPiece : myPieces) {
            int pieceX = myPiece.getLocationX();
            int pieceY = myPiece.getLocationY();
            MY_TILES[pieceX][pieceY].setHasPiece(true);
        }
    }
    
    /**
     * Relocates all Chess Pieces after a save file is opened.
     * @param loadedPieces2 an ArrayList of ChessPieces
     */
    private void relocatePieces(ArrayList<ChessPiece> loadedPieces2) {
        int i = 1;
        ListIterator<ChessPiece> iter = loadedPieces2.listIterator();
        while (iter.hasNext()) {
            ChessPiece temp = null;
            ChessPiece newPiece = iter.next();
            String pieceName = newPiece.getClass().getSimpleName();
            int pieceX = newPiece.getLocationX();
            int pieceY = newPiece.getLocationY();
            if (i++ <= NUM_OF_PIECES) {
                if (pieceName.equals("Rook")) {
                    temp = new Rook("black", pieceX, pieceY);
                    tileGroup.getChildren().add(temp);
                } else if (pieceName.equals("Knight")) {
                    temp = new Knight("black", pieceX, pieceY);
                    tileGroup.getChildren().add(temp);
                } else if (pieceName.equals("Bishop")) {
                    temp = new Bishop("black", pieceX, pieceY);
                    tileGroup.getChildren().add(temp);
                } else if (pieceName.equals("King")) {
                    temp = new King("black", pieceX, pieceY);
                    tileGroup.getChildren().add(temp);
                } else if (pieceName.equals("Queen")) {
                    temp = new Queen("black", pieceX, pieceY);
                    tileGroup.getChildren().add(temp);
                } else if (pieceName.equals("Pawn")) {
                    temp = new Pawn("black", pieceX, pieceY);
                    tileGroup.getChildren().add(temp);
                }
            } else {
                if (pieceName.equals("Rook")) {
                    temp = new Rook("white", pieceX, pieceY);
                    tileGroup.getChildren().add(temp);
                } else if (pieceName.equals("Knight")) {
                    temp = new Knight("white", pieceX, pieceY);
                    tileGroup.getChildren().add(temp);
                } else if (pieceName.equals("Bishop")) {
                    temp = new Bishop("white", pieceX, pieceY);
                    tileGroup.getChildren().add(temp);
                } else if (pieceName.equals("King")) {
                    temp = new King("white", pieceX, pieceY);
                    tileGroup.getChildren().add(temp);
                } else if (pieceName.equals("Queen")) {
                    temp = new Queen("white", pieceX, pieceY);
                    tileGroup.getChildren().add(temp);
                } else if (pieceName.equals("Pawn")) {
                    temp = new Pawn("white", pieceX, pieceY);
                    tileGroup.getChildren().add(temp);
                }
            }
            temp.setOnMouseClicked(this::handlePiece);
            iter.remove();
            iter.add(temp);
        }
    }

    /**
     * Opens a File Save Dialog that saves a Serialization file of
     * the current Chess Game.
     * @return an EventHandler.
     */
    private EventHandler<ActionEvent> handleSave() {
        return new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                
                savedTurn = whiteTurn;
                fileChooser.getExtensionFilters().add(
                        new ExtensionFilter("Serializable Files", "*.ser"));
                myFile = fileChooser.showSaveDialog(null);
                if (myFile != null) {
                try {                    
                    FileOutputStream fileOut = new FileOutputStream(myFile);
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(myPieces);
                    out.close();
                    fileOut.close();
                } catch (IOException i) {
                    i.printStackTrace();
                }
                }
            }
        };
    }

    /**
     * Open's a new 3D Chess Game.
     * @return an EventHandler.
     */
    private EventHandler<ActionEvent> handle3DChess() {
        return new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                myStage.close();
                myStage.setTitle("3D-Chess");
                tileGroup.getChildren().clear();
                chessPane.getChildren().clear();
                myPieces.clear();
                initialize3DGrid();
                chessPane = new GridPane();
                chessPane.setPrefSize(COLUMNS_3D * Tile.TILE_SIZE_3D,
                        ROWS * Tile.TILE_SIZE_3D);
                whiteTurn = true;
                tileGroup.setTranslateY(-0.5);
                chessPane.getChildren().add(tileGroup);
                myStage.setScene(new Scene(chessPane));
                myStage.show();
            }
        };
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        myStage = primaryStage;
        Scene scene = new Scene(rootNode());

        MenuBar myMenu = new MenuBar();
        myMenu.setId("file");
        EventHandler<ActionEvent> actionOpen = handleOpen();
        EventHandler<ActionEvent> actionSave = handleSave();
        EventHandler<ActionEvent> action3DChess = handle3DChess();
        MenuItem menuOpen = new MenuItem("Open");
        menuOpen.setOnAction(actionOpen);
        MenuItem menuSave = new MenuItem("Save");
        menuSave.setOnAction(actionSave);
        MenuItem menu3DChess = new MenuItem("3D-Chess");
        menu3DChess.setOnAction(action3DChess);
        Menu fileMenu = new Menu("File");
        fileMenu.getItems().addAll(menuOpen, menuSave, menu3DChess);
        fileMenu.setId("#fileMenu");

        myMenu.getMenus().addAll(fileMenu);

        myMenu.setTranslateY(MENU_OFFSET);

        chessPane.getChildren().add(myMenu);
        tileGroup.setTranslateY(GROUP_OFFSET);

        primaryStage.setTitle("Chess");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Launches Board.
     */
    public void run() {
        launch();
    }
}
