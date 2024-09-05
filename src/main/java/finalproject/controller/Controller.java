package finalproject.controller;

import finalproject.model.Model;
import finalproject.model.ModelInterface;
import finalproject.view.View;
import finalproject.view.ViewInterface;

import java.util.List;

/**
 * The entry point for the 2048 game, it contains the main method of the 2048 Game. This communicates user actions to the model then updates the view based on any
 * changes/information from the model.
 */
public class Controller implements ControllerInterface{

    private ModelInterface model;
    private ViewInterface view;
    private List<ATileMove> moves;
    private boolean alreadyWon = false;

    public static void main(String[] args) {
        new Controller();
    }

    public Controller(){
        model = new Model();                                    
        moves = model.startGame();  // .startGame(10,10) to paixnidi mas na einai 10x10
        view=new View( model.getWidth(), model.getHeight(),this);
        model.nameAdd(view.getPlayer());
        view.addTiles(moves);
        view.showScore(model.getScore(),model.getHighScore());
    }
    
    @Override
    public void restartGame(int newWidth, int newHeight) {
        alreadyWon = false;
        moves = model.restartGame(newWidth, newHeight);
        view.resetBoard(model.getWidth(), model.getHeight());
        view.addTiles(moves);
        view.showScore(model.getScore(), model.getHighScore());
    }
    @Override
    public void makeMove(Direction d) {
        moves = model.makeMove(d);
        if (moves.isEmpty()) {
            if (model.isGameOver())  view.showLoss();
        } else {
            view.moveTiles(moves);
            view.showScore(model.getScore(), model.getHighScore());
            if (model.isGameWon() && !alreadyWon) {
                alreadyWon = true;
                view.showWin();
            } else if (model.isGameOver()) 
                view.showLoss();
        }
    }
    @Override
    public void endGame() {
        model.endGame();
        model = null;
        moves = null;
        view = null;
    }
    @Override
    public ModelInterface getModel(){
        return model;
    }
    @Override
    public ViewInterface getView(){
        return view;
    }
}