package finalproject.view;

import finalproject.controller.Controller;
import finalproject.controller.ATileMove;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 * The View Panel, which creates the UI of the Game 
 */
public class View extends JFrame implements ViewInterface{
  private static final int MINSIZE = 4, MAXSIZE = 20;
  private static final int containerSpacing = 10, taskBarSize = 75;
  private static final Color backGroundColor = new Color(250, 248, 239);
  private static final Dimension scrnSize = Toolkit.getDefaultToolkit().getScreenSize();
  private ViewListener listener;
  private BarMenu barMenu;
  private ScoreView scoreView;
  private BoardView boardView;
  private String name;

  public View(int numTilesX, int numTilesY,Controller controller) throws IllegalArgumentException, NullPointerException{
      super("2048");
      if (numTilesX < MINSIZE || numTilesY < MINSIZE || numTilesX > MAXSIZE || numTilesY > MAXSIZE) 
          throw new IllegalArgumentException();
      listener = new ViewListener(controller);
      setLayout(new BorderLayout());
      String playerName;
      playerName=addName();
      createAndShowGUI(numTilesX,numTilesY,playerName);
  }

  @Override
  public String addName(){
    NameDialog dlg = new NameDialog(this);
    String[] results = dlg.run();

    if(results[0] != null && !results[0].isEmpty()){
      name= results[0];
      return results[0];
    }
    return  name= "playerX";
  }
  @Override
  public String getPlayer(){
    return name;
  }

    /**
     * Creates a GUI for a game with a given number of horizontal and vertical tiles for a given controller.
     * @param numTilesX The number of horizontal tiles.
     * @param numTilesY The number of vertical tiles.
     * @param controller The controller instance controlling this game.
     * @param playerName The playerName taken from the JDialog.
     * @throws IllegalArgumentException If numTilesX < 4 or numTilesX > 20 or numTilesY < 4 or numTilesY > 20.
     * @throws NullPointerException If controller == null.
     */
  private synchronized void createAndShowGUI(int numTilesX,int numTilesY,String playerName){
      barMenu   = new BarMenu(listener);

      scoreView = new ScoreView(playerName,listener);
      boardView = new BoardView();
      // Now determine the maximum size that the actual game board can be based on screen size and set the sizes of the other components
      buildBoardAndSetSizes(numTilesX, numTilesY);
      setJMenuBar(barMenu);
      add(scoreView,BorderLayout.NORTH);
      add(boardView,BorderLayout.SOUTH);

      addKeyListener(listener);
      addMouseListener(listener);
      addWindowListener(listener);
      getContentPane().setBackground(backGroundColor);
      pack();
      setMinimumSize(new Dimension(getSize().width + 10, getSize().height + 10));
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setResizable(true);
      setLocationRelativeTo(null); // center on the screen
      setVisible(true);
  }
  
  private void buildBoardAndSetSizes(int numTilesX, int numTilesY) {
    // create a container to hold the title as well as the score and bestscore

    int maxBoardWidth = scrnSize.width - taskBarSize;
    int boardWidth = boardView.setHorizontalConstraints(numTilesX, maxBoardWidth);
    scoreView.setPreferredSize(new Dimension(boardWidth, scoreView.getPreferredSize().height));
    scoreView.setMaximumSize(scoreView.getPreferredSize());
  
    int maxBoardHeight = scrnSize.height - scoreView.getPreferredSize().height - 4 * containerSpacing - taskBarSize;
    boardView.setVerticalConstraints(numTilesY, maxBoardHeight);
    boardView.createGrid();
  }
  @Override
  public synchronized void resetBoard(int numTilesX, int numTilesY) throws IllegalArgumentException {
    if (numTilesX < MINSIZE || numTilesY < MINSIZE || numTilesX > MAXSIZE || numTilesY > MAXSIZE) throw new IllegalArgumentException();
    boardView.resetBoard();
    buildBoardAndSetSizes(numTilesX, numTilesY);
    setMinimumSize(new Dimension(0, 0));
    pack();
    setMinimumSize(new Dimension(getSize().width + 10, getSize().height + 10));
    setLocationRelativeTo(null);
  }
  @Override
  public synchronized void moveTiles(List<ATileMove> moves) {
    boardView.moveTiles(moves);
    revalidate();
  }
  @Override
  public synchronized void addTiles(List<ATileMove> newTiles) {
    boardView.addTiles(newTiles);
  }
  @Override
  public synchronized void showWin() {
    JOptionPane.showMessageDialog(null, "Congratulations, you have won!"); 
  }
  @Override
  public synchronized void showLoss() {
    String[] options = { "Start New Game", "Exit" };
    int i = JOptionPane.showOptionDialog(null, "You lose", "Game Over!", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    if (i == 0) { 
            barMenu.getRestart().doClick();
    }
    if (i == 1) { 
      dispose();
    }
  }
  @Override
  public synchronized void showScore(int score, int bestScore) {
    scoreView.setScore(score);
    scoreView.setBestScore(bestScore);
  }
  
}