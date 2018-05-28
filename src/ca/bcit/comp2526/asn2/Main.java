package ca.bcit.comp2526.asn2;

/**
 * 
 * Main Class. Launches Games.
 *
 * @author Viktor Alvar, Set B
 * @version 2018
 */
public class Main {
    
    /**
     * Drive's the program.
     * @param args unused
     */
    public static void main(String[] args) {
        Game g = new ChessGame();
        g.startGame();
    }

}
