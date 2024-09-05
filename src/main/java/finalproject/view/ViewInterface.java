package finalproject.view;

import finalproject.controller.ATileMove;

import java.util.List;
/**
 * An interface describing tasks that the View shall be responsible for.
 */
public interface ViewInterface {

    /**
     * Displays the JDialog and lets the player enter his name
     * @return the name of the name the player gave to the JDialog
     */
    public String addName();

    /**
        @return the name of the name the player ,which was saved when he entered it
     */
    public String getPlayer();
    /**
     * Recreates the board with the given dimensions and all the tiles removed.
     * @param width The width of the board to be made.
     * @param height The height of the board to be made.
     * @throws IllegalArgumentException If width < 4 or width > 20 or height < 4 or height > 20
     */
    public void resetBoard(int width, int height) throws IllegalArgumentException;

    /**
     * Moves the given tiles on the board.
     * @param moves A list of all tiles to be moved; includes their past and present positions and values.
     */
    public void moveTiles(List<ATileMove> moves);

    /**
     * Adds the given tiles to the board.
     * @param newTiles A list of all tiles to be added to the board; includes their positions and values.
     */
    public void addTiles(List<ATileMove> newTiles);

    /**
     * Displays to the user that this game has been won.
     */
    public void showWin();

    /**
     * Displays to the user that the game has been lost.
     */
    public void showLoss();

    /**
     * Displays to the user the score of this game and the highScore of this session.
     * @param score The score of this game.
     * @param highScore The highScore of this session
     */
    public void showScore(int score, int highScore);
}
