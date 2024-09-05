package finalproject.model;

import finalproject.controller.ATileMove;
import finalproject.controller.Direction;

import java.util.List;
/**
 * Manipulates the state of the board according to the rules of the game as well as keeping track of the score, high score, and checks for victory conditions.
 */
public class Model implements ModelInterface{
    private Board board;
    private Player player;
    private Database database;
    
    @Override
    public List<ATileMove> startGame() {
        if (board == null) {
            board = new Board();
            return Rules.startGame(board, Rules.DFLT_WIDTH, Rules.DFLT_HEIGHT);
        } else {
            return board.getAllTiles();
        }
        }
    @Override
    public List<ATileMove> startGame(int width,int height){
        board = new Board();
        List<ATileMove> moves = null;
        try{
            moves = Rules.startGame(board,width,height);
        }catch (IllegalArgumentException e){ // if an exception is thrown due to bad width/height given, make the default board
            moves = Rules.startGame(board, Rules.DFLT_WIDTH, Rules.DFLT_HEIGHT);
        }
        return moves;

    }
    @Override
    public List<ATileMove> restartGame(int width, int height) {
        List<ATileMove> moves = null;
        try {
            moves = Rules.restartGame(board, width, height);
        } catch (IllegalArgumentException e) { // if an exception is thrown due to bad width/height given, make the default board
            moves = Rules.restartGame(board, Rules.DFLT_WIDTH, Rules.DFLT_HEIGHT);
        }
        return moves;
    }

    @Override
    public void endGame() {
        database=Database.getInstance();
        this.player.setHighScore(board.getHighScore());
        database.saveNewData(this.player);
	board = null;
    }
    @Override
    public List<ATileMove> makeMove(Direction d) {
        return Rules.makeMove(board, d);
    }
    @Override
    public void nameAdd(String name){
        Player pl;

        database = Database.getInstance();
        pl=database.retrievePlayer(name);
        System.out.println(pl);
        board.setHighScore(pl.getHighScore());
        this.player=pl;
    }
    @Override
    public int getScore(){
        return board.getScore();
    }
    @Override
    public int getHighScore() {
        return board.getHighScore();
    }
    @Override
    public boolean isGameWon() {
        return board.isGameWon();
    }
    @Override
    public boolean isGameOver() {
        return board.isGameOver();
    }
    @Override
    public int getWidth() {
        return board.getWidth();
    }
    @Override
    public int getHeight() {
        return board.getHeight();
    }
    
}
