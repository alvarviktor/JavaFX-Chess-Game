package ca.bcit.comp2526.asn2;

/**
 * 
 * ChessGame.
 *
 * @author Viktor Alvar, Set B
 * @version 2018
 */
public class ChessGame extends Game {

    @Override
    public void startGame() {
        Board b = new Board();
        b.run();
    }

}
